package com.iemes.score;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.iemes.controller.base.ConstantController;
import com.iemes.entity.ExamInfoFormMap;
import com.iemes.entity.GradeCalculateFormMap;
import com.iemes.entity.PointGradeFormMap;
import com.iemes.entity.UserFormMap;
import com.iemes.mapper.BaseExtMapper;
import com.iemes.util.Common;

/**
 * 评分系统计算方法
 * @author Administrator
 *
 */
@Component
public class GradeCalculateService {
	
	@Inject
	private BaseExtMapper baseExtMapper;

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 评分算法，如果成功，返回分值，如果失败，返回null或者""
	 * @param func
	 * @param func2
	 * @return
	 * @throws Exception
	 */
	public String GradeCalculate(String func, String func2) throws Exception{
		String rs = "";
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Session session = SecurityUtils.getSubject().getSession();
		String site = session.getAttribute("site").toString();
		UserFormMap userFormMap = (UserFormMap)Common.findUserSession(request);
		
		if (StringUtils.isEmpty(func2)) {
			func2 = "";
		}
		
		//1、查询考试信息
		ExamInfoFormMap examInfoFormMap = new ExamInfoFormMap();
		examInfoFormMap.put("site", site);
		examInfoFormMap.put("enabled", "1");
		List<ExamInfoFormMap> examInfoFormList = baseExtMapper.findByNames(examInfoFormMap);
		if (examInfoFormList==null || examInfoFormList.size()<=0) {
			return rs;
		}
		ExamInfoFormMap ExamInfoFormData = examInfoFormList.get(0);
		
		//2、获取考试评分信息
		PointGradeFormMap pointGradeFormMap = new PointGradeFormMap();
		pointGradeFormMap.put("function", func);
		pointGradeFormMap.put("exam_no", ExamInfoFormData.getStr("exam_no"));
		pointGradeFormMap.put("site", site);
		List<PointGradeFormMap> gradeList = baseExtMapper.findByNames(pointGradeFormMap);
		if (gradeList==null || gradeList.size()<=0) {
			return rs;
		}
		PointGradeFormMap rsdata = gradeList.get(0);
		
		
		
		//2、判断当前时间是否为考试时间
		Date startTIme = ExamInfoFormData.getDate("exam_start_time");
		Date endTime = ExamInfoFormData.getDate("exam_end_time");
		if (new Date().getTime()>=startTIme.getTime() &&
				new Date().getTime()<endTime.getTime()) {
			//3、根据func、func2、站点、考试编号判断数据是否存在
			GradeCalculateFormMap gradeCalculateFormMap = new GradeCalculateFormMap();
			gradeCalculateFormMap.put("function", func);
			gradeCalculateFormMap.put("func2", func2);
			gradeCalculateFormMap.put("site", site);
			gradeCalculateFormMap.put("exam_no", ExamInfoFormData.getStr("exam_no"));
			List<GradeCalculateFormMap> gradeCalculateFormList = baseExtMapper.findByNames(gradeCalculateFormMap);
			if (gradeCalculateFormList==null || gradeCalculateFormList.size()<=0) {
				//插入数据
				if (!StringUtils.isEmpty(func2)) {
					if (ConstantController.开始.equals(func2)) {
						rs = "15";
					}else if (ConstantController.完成.equals(func2)) {
						rs = "15";
					}else if (ConstantController.装配.equals(func2)) {
						rs = "15";
					}else if (ConstantController.记录不良.equals(func2)) {
						rs = "15";
					}
				}else {
					rs = rsdata.getInt("point")+"";
				}
				
				GradeCalculateFormMap gradeCalculateForm = new GradeCalculateFormMap();
				gradeCalculateForm.put("function", func);
				gradeCalculateForm.put("func2", func2);
				gradeCalculateForm.put("descirption", rsdata.getStr("descirption"));
				//GradeCalculateForm.put("enable", func);
				gradeCalculateForm.put("point", rsdata.getInt("point"));
				gradeCalculateForm.put("exam_start_time", format.format(ExamInfoFormData.getDate("exam_start_time")));
				gradeCalculateForm.put("exam_end_time", format.format(ExamInfoFormData.getDate("exam_end_time")));
				gradeCalculateForm.put("exam_no", ExamInfoFormData.getStr("exam_no"));
				gradeCalculateForm.put("exam_name", ExamInfoFormData.getStr("exam_name"));
				gradeCalculateForm.put("by_user", userFormMap.getStr("accountName"));
				gradeCalculateForm.put("create_time", format.format(new Date()));
				gradeCalculateForm.put("site", site);
				baseExtMapper.addEntity(gradeCalculateForm);
			}
		}
		return rs;
	}
}
