package com.iemes.controller.teaching;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iemes.annotation.SystemLog;
import com.iemes.controller.base.BaseResponse;
import com.iemes.controller.base.ConstantController;
import com.iemes.controller.index.BaseController;
import com.iemes.entity.ExamInfoFormMap;
import com.iemes.entity.PointGradeFormMap;
import com.iemes.mapper.teaching.ExamInfoMapper;
import com.iemes.mapper.teaching.PointGradeMapper;
import com.iemes.plugin.PageView;
import com.iemes.util.Common;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping("/point_grade/")
public class pointGradeController extends BaseController {
	
	@Inject
	private PointGradeMapper pointGradeMapper;
	
	@Inject
	private ExamInfoMapper examInfoMapper;

	@RequestMapping("point_grade_list")
	public String listUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/teaching_score/pointGrade_list";
	}
	
	@RequestMapping("grade_list")
	public String listgradeUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/teaching_score/student_grade_list";
	}
	
	@ResponseBody
	@RequestMapping("putScore")
	public String putScore(){
		String exam_no = getPara("exam_no");
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute("score", exam_no);
		return ConstantController.SUCCESS;
	}


	@ResponseBody
	@RequestMapping("point_grade_findByPage")
	public PageView findByPage(String pageNow,
			String pageSize) throws Exception {
		PointGradeFormMap pointGradeFormMap = getFormMap(PointGradeFormMap.class);
		Session session = SecurityUtils.getSubject().getSession();
		String exam_no = session.getAttribute("score")+"";
		
		if(exam_no == null || "".equals(exam_no) || "null".equalsIgnoreCase(exam_no))
		{
			pointGradeFormMap.put("site", "*");
			pointGradeFormMap.put("exam_no", "");
		}
		else
		{
			pointGradeFormMap.put("site", session.getAttribute("site"));
			pointGradeFormMap.put("exam_no", exam_no);
			session.removeAttribute("score");
		}
		
		pointGradeFormMap=toFormMap(pointGradeFormMap, pageNow, pageSize,pointGradeFormMap.getStr("orderby"));
		pageView.setRecords(pointGradeMapper.findByPage(pointGradeFormMap));
		return pageView;
	}	
	

	@RequestMapping("point_grade_editUI")
	public String editUI(Model model) throws Exception {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			model.addAttribute("sukey", pointGradeMapper.findbyFrist("id", id, PointGradeFormMap.class));
		}
		return Common.BACKGROUND_PATH + "/teaching_test/pointGrade_list";
	}
	

	@ResponseBody
	@RequestMapping("point_grade_update_all")
	@SystemLog(module="数据维护",methods="修改参数")//凡需要处理业务逻辑的.都需要记录操作日志
	public BaseResponse pointGradeUpdate() throws Exception {
		BaseResponse response = new BaseResponse();
		Session session = SecurityUtils.getSubject().getSession();
		String site = session.getAttribute("site").toString();
		
		//评分信息
		String data = getPara("score_data");
		String exam_data = getPara("exam_data");
		JSONArray jsonArr = JSONArray.fromObject(data);
		JSONObject examData = JSONObject.fromObject(exam_data);
		
		//考試信息
		String exam_no = examData.getString("exam_no");
		String exam_name = examData.getString("exam_name");
		String exam_start_time = examData.getString("exam_start_time");
		String exam_end_time = examData.getString("exam_end_time");
		String enabled = getPara("enabled");
		if ("true".equals(enabled)){
			enabled = "1";
		}else {
			enabled= "0";
		}
		
		//1、查询该评分信息是否存在
		ExamInfoFormMap examInfoFormMap = new ExamInfoFormMap();
		examInfoFormMap.put("exam_no", exam_no);
		examInfoFormMap.put("site", site);
		List<ExamInfoFormMap> ExamInfoFormList = baseMapper.findByNames(examInfoFormMap);
		response.setMessage("考试信息保存成功");
		
		if (ExamInfoFormList!=null && ExamInfoFormList.size()>0) {
			//1、删除考试信息
			examInfoFormMap.clear();
			examInfoFormMap = ExamInfoFormList.get(0);
			baseMapper.deleteByAttribute("id", examInfoFormMap.getInt("id")+"", ExamInfoFormMap.class);
			
			//2、根据考试编号查询评分信息
			PointGradeFormMap pointGrade = new PointGradeFormMap();
			pointGrade.put("exam_no", exam_no);
			pointGrade.put("site", site);
			List<PointGradeFormMap> PointGradeFormList = baseMapper.findByNames(pointGrade);
			
			//3、删除评分信息
			String ids = "";
			for (int i=0;i<PointGradeFormList.size();i++) {
				PointGradeFormMap PointGradeForm =  PointGradeFormList.get(i);
				ids = ids + PointGradeForm.get("id");
				if (i!=jsonArr.size()-1) {
					ids = ids + ",";
				}
			}
			PointGradeFormMap pointGradeForm = new PointGradeFormMap();
			pointGradeForm.put("id", ids);
			baseMapper.deleteByNames(pointGradeForm);
			
//			
//			
//			
//			examInfoFormMap.clear();
//			examInfoFormMap.put("exam_no", exam_no);
//			examInfoFormMap.put("exam_name", exam_name);
//			examInfoFormMap.put("exam_start_time", exam_start_time);
//			examInfoFormMap.put("exam_end_time", exam_end_time);
//			examInfoFormMap.put("enabled", enabled);
//			examInfoFormMap.put("site", site);
//			baseMapper.deleteByNames(examInfoFormMap);
			response.setMessage("考试信息修改成功");
		}
		
		//添加评分项信息
		List<PointGradeFormMap> list = new ArrayList<PointGradeFormMap>();
		for (int i=0;i<jsonArr.size();i++) {
			JSONObject obj =  jsonArr.getJSONObject(i);
			Set<String> set = obj.keySet();
			PointGradeFormMap pointGradeFormMap = new PointGradeFormMap();
			for (String key : set) {
				if (!"id".equalsIgnoreCase(key)) {
					pointGradeFormMap.put(key, obj.get(key));
				}
			}
			if (pointGradeFormMap.getBoolean("enable")) {
				pointGradeFormMap.set("enable", 1);
			} else {
				pointGradeFormMap.set("enable", 0);
			}
			pointGradeFormMap.set("site",session.getAttribute("site"));
			pointGradeFormMap.set("exam_no",exam_no);
			list.add(pointGradeFormMap);
		}
		baseMapper.batchSave(list);
		
		//添加考试信息
		examInfoFormMap.clear();
		examInfoFormMap.put("exam_no", exam_no);
		examInfoFormMap.put("exam_name", exam_name);
		examInfoFormMap.put("exam_start_time", exam_start_time);
		examInfoFormMap.put("exam_end_time", exam_end_time);
		examInfoFormMap.put("enabled", enabled);
		examInfoFormMap.put("site", site);
		baseMapper.addEntity(examInfoFormMap);
		
		response.setResult(true);
		return response;
	}
	
	@ResponseBody
	@RequestMapping("exam_save")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="数据维护",methods="修改数据")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editExamEntity() throws Exception {
		ExamInfoFormMap examInfoFormMap = getFormMap(ExamInfoFormMap.class);
		ExamInfoFormMap examInfoFormMap2 = new ExamInfoFormMap();
		examInfoFormMap2.put("1", "1");
		if(examInfoFormMap.getStr("enabled").equals("on"))
		{
			examInfoFormMap.put("enabled", 1);
		}
		else
		{
			examInfoFormMap.put("enabled", 0);
		}
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute("exam_no", examInfoFormMap.get("exam_no"));
		examInfoFormMap.put("site",session.getAttribute("site"));
		examInfoMapper.addEntity(examInfoFormMap);
		return "success";
	}
	
	@RequestMapping("point_exam_list")
	public String examInfoListUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/teaching_score/exam_list";
	}
	
	@ResponseBody
	@RequestMapping("exam_findBySite")
	public PageView findExamPage(String pageNow,
			String pageSize) throws Exception {
		ExamInfoFormMap examInfoFormMap = getFormMap(ExamInfoFormMap.class);
		Session session = SecurityUtils.getSubject().getSession();
		examInfoFormMap.put("site",session.getAttribute("site"));
		examInfoFormMap=toFormMap(examInfoFormMap, pageNow, pageSize,examInfoFormMap.getStr("orderby"));
		pageView.setRecords(pointGradeMapper.findByPage(examInfoFormMap));
		return pageView;
	}	
	
/*	@ResponseBody
	@RequestMapping("exam_findBySite")
	public PageView findBySite(String pageNow,
			String pageSize) throws Exception {
		PointGradeFormMap pointGradeFormMap = getFormMap(PointGradeFormMap.class);
		Session session = SecurityUtils.getSubject().getSession();
		pointGradeFormMap.put("site",session.getAttribute("site"));
		pointGradeFormMap=toFormMap(pointGradeFormMap, pageNow, pageSize,pointGradeFormMap.getStr("orderby"));
		pageView.setRecords(pointGradeMapper.findByPage(pointGradeFormMap));
		return pageView;
	}	*/
}