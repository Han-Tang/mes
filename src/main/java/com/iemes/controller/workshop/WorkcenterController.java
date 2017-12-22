package com.iemes.controller.workshop;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.iemes.annotation.SystemLog;
import com.iemes.controller.base.ConstantController;
import com.iemes.controller.index.BaseController;
import com.iemes.entity.OperationResourceFormMap;
import com.iemes.entity.UserFormMap;
import com.iemes.entity.WorkCenterFormMap;
import com.iemes.entity.WorkCenterRelationFormMap;
import com.iemes.mapper.WorkShopMapper;
import com.iemes.plugin.PageView;
import com.iemes.score.GradeCalculateService;
import com.iemes.util.Common;

import net.sf.json.JSONObject;

/**
 * 
 * @author lanyuan 2014-11-19
 * @Email: mmm333zzz520@163.com
 * @version 3.0v
 */
@Controller
@RequestMapping("/workshop/")
public class WorkcenterController extends BaseController {

	@Inject
	private GradeCalculateService gradeCalculateService;

	@Inject
	private WorkShopMapper workShopMapper;

	@RequestMapping("workcenter_list")
	public String listUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/workshopdata/workcenter/workcenter_list";
	}

	@ResponseBody
	@RequestMapping("workcenter_findByPage")
	public PageView findByPage(String pageNow, String pageSize) throws Exception 
	{
		Session session = SecurityUtils.getSubject().getSession();
		WorkCenterFormMap workCenterFormMap = getFormMap(WorkCenterFormMap.class);
		workCenterFormMap.put("site", session.getAttribute("site"));
		workCenterFormMap=toFormMap(workCenterFormMap, pageNow, pageSize,workCenterFormMap.getStr("orderby"));
		List<WorkCenterFormMap> workCenterFormMaps = workShopMapper.findByPage(workCenterFormMap);
		if(CollectionUtils.isNotEmpty(workCenterFormMaps))
		{
			//加载工作中心的父工作中心
			for(WorkCenterFormMap w : workCenterFormMaps)
			{
				List<WorkCenterFormMap> fWorkcenter = workShopMapper.getParentWorkCenterMap(w.getStr("workcenter_no"));
				if(CollectionUtils.isNotEmpty(fWorkcenter))
				{
					w.put("workcenter_parent", fWorkcenter.get(0).getStr("workcenter_no"));
				}
			}
		}
		pageView.setRecords(workCenterFormMaps);
		return pageView;
	}

	/**
	 * 查询出工作中心是车间
	 * @param pageNow
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("workcenter_workShop_findByPage")
	public PageView workcenter_workShop_findByPage(String pageNow, String pageSize) throws Exception 
	{
		Session session = SecurityUtils.getSubject().getSession();
		WorkCenterFormMap workCenterFormMap = new WorkCenterFormMap();
		workCenterFormMap.put("site", session.getAttribute("site"));
		workCenterFormMap.put("workcenter_class", "workshop");
		List<WorkCenterFormMap> list = baseMapper.findByNames(workCenterFormMap);
		pageView = getPageView(pageNow, pageSize, getPara("orderby"));
		pageView.setRecords(list);
		pageView.setQueryResult(list.size(), list);
		return pageView;
	}
	
	/**
	 * 查询所有产线
	 * @param pageNow
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("workcenter_line_findByPage")
	public PageView workcenter_line_findByPage(String pageNow, String pageSize) throws Exception 
	{
		Session session = SecurityUtils.getSubject().getSession();
		WorkCenterFormMap workCenterFormMap = new WorkCenterFormMap();
		workCenterFormMap.put("site", session.getAttribute("site"));
		workCenterFormMap.put("workcenter_class", "production_line");
		List<WorkCenterFormMap> list = baseMapper.findByNames(workCenterFormMap);
		pageView = getPageView(pageNow, pageSize, getPara("orderby"));
		pageView.setRecords(list);
		pageView.setQueryResult(list.size(), list);
		return pageView;
	}



	/**
	 *  根据传过来的workcenter_no 查对应的workcenter_child 
	 * @param pageNow
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("workcenter_child_findByPage")
	public PageView findChildByPage(String pageNow, String pageSize) throws Exception 
	{
		//取得前端的值
		//		FormMap<String, String> formMap = getFormMap(FormMap.class);
		String workcenter = getPara("workcenter");
		Session session = SecurityUtils.getSubject().getSession();
		//1、现根据关系表查出产线的workcenter_no
		//2、再用下面的方法查出list
		WorkCenterRelationFormMap workCenterRelationFormMap = new WorkCenterRelationFormMap();
		workCenterRelationFormMap.put("workcenter", workcenter);
		workCenterRelationFormMap.put("site", session.getAttribute("site"));
		List<WorkCenterRelationFormMap> list = baseMapper.findByNames(workCenterRelationFormMap);

		if (list!=null) {
			StringBuffer workcenters = new StringBuffer();
			for(int i=0;i<list.size();i++){
				String workcenter_child = list.get(i).getStr("workcenter_child");
				workcenters.append("'"+workcenter_child+"'");
				if (i!=list.size()-1) {
					workcenters.append(",");
				}
			}

			WorkCenterFormMap workCenterFormMap = new WorkCenterFormMap();
//			workCenterFormMap.put("workcenter_no", workcenters.toString());
//			workCenterFormMap.put("site", session.getAttribute("site"));
//			
			workCenterFormMap.put("where", "where workcenter_no in ("+workcenters.toString()+") "
					+ " and site = '"+session.getAttribute("site")+"'");
			List<WorkCenterFormMap> rs = baseMapper.findByWhere(workCenterFormMap);
			pageView = getPageView(pageNow, pageSize, getPara("orderby"));
			pageView.setRecords(rs);
			pageView.setQueryResult(list.size(), rs);
		}
		return pageView;
	}

	@RequestMapping("workcenter_add")
	public String addUI(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/workshopdata/workcenter/workcenter_add";
	}

	@ResponseBody
	@RequestMapping("workcenter_addEntity")
	@SystemLog(module="车间数据维护",methods="工作中心维护-新增工作中心")//凡需要处理业务逻辑的.都需要记录操作日志
	public String addEntity() throws Exception {
		JSONObject json = new JSONObject();
		WorkCenterFormMap workCenterFormMap = getFormMap(WorkCenterFormMap.class);
		Session session = SecurityUtils.getSubject().getSession();
		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		// 通过工具类获取当前登录的bean
		UserFormMap userFormMap = (UserFormMap)Common.findUserSession(request);
		workCenterFormMap.put("byUser", userFormMap.getStr("accountName"));
		workShopMapper.addEntity(workCenterFormMap);

		//保存工作中心关联表
		String parentWorkcenterNo = (String) request.getParameter("workcenterFormMap.workcenter_parent");
		if(StringUtils.isNotEmpty(parentWorkcenterNo))
		{
			if(workShopMapper.findByAttribute("workcenter_no", parentWorkcenterNo, WorkCenterFormMap.class) != null)
			{
				WorkCenterRelationFormMap relationMap = new WorkCenterRelationFormMap();
				relationMap.put("workcenter", parentWorkcenterNo);
				relationMap.put("workcenter_child", workCenterFormMap.getStr("workcenter_no"));
				relationMap.put("site", session.getAttribute("site"));
				workShopMapper.addEntity(relationMap);
			}
		}
		String rs = gradeCalculateService.GradeCalculate("工作中心维护", null);
		json.put("result", ConstantController.SUCCESS);
		json.put("score", rs);
		return json.toString();
	}

	@RequestMapping("workcenter_edit")
	public String editUI(Model model) throws Exception 
	{
		String id = getPara("id");
		if(Common.isNotEmpty(id))
		{
			WorkCenterFormMap workCenterFormMap = workShopMapper.findbyFrist("id", id, WorkCenterFormMap.class);
			List<WorkCenterFormMap> fWorkcenter = workShopMapper.getParentWorkCenterMap(workCenterFormMap.getStr("workcenter_no"));
			if(CollectionUtils.isNotEmpty(fWorkcenter))
			{
				workCenterFormMap.put("workcenter_parent", fWorkcenter.get(0).getStr("workcenter_no"));
			}
			model.addAttribute("workcenter", workCenterFormMap);
		}

		return Common.BACKGROUND_PATH + "/workshopdata/workcenter/workcenter_edit";
	}

	@ResponseBody
	@RequestMapping("workcenter_editEntity")
	@SystemLog(module="车间数据维护",methods="工作中心维护-修改工作中心")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity() throws Exception {
		Session session = SecurityUtils.getSubject().getSession();
		String site = session.getAttribute("site").toString();
		
		WorkCenterFormMap workCenterFormMap = getFormMap(WorkCenterFormMap.class);
		workShopMapper.editEntity(workCenterFormMap);
		if (!StringUtils.isEmpty(workCenterFormMap.getStr("workcenter_parent"))) {
			WorkCenterRelationFormMap workcenterRelation = new WorkCenterRelationFormMap();
			workcenterRelation.put("workcenter_child", workCenterFormMap.getStr("workcenter_no"));
			workcenterRelation.put("workcenter", workCenterFormMap.getStr("workcenter_parent"));
			workcenterRelation.put("site", site);
			List<WorkCenterRelationFormMap> list = baseMapper.findByNames(workcenterRelation);
			if (list==null || list.size()<=0) {
				baseMapper.addEntity(workcenterRelation);
			}else {
				workcenterRelation = list.get(0);
				baseMapper.editEntity(workcenterRelation);
			}
		}
		return "success";
	}

	@ResponseBody
	@RequestMapping("workcenter_deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="车间数据维护",methods="工作中心维护-删除工作中心")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() throws Exception {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			workShopMapper.deleteByAttribute("id", id, WorkCenterFormMap.class);
		}
		return "success";
	}
	
	/**
	 * 验证工作中心是否存在
	 * 
	 * @author lanyuan Email：mmm333zzz520@163.com date：2014-2-19
	 * @param name
	 * @return
	 */
	@RequestMapping("workcenter_isExist")
	@ResponseBody
	public boolean workcenter_isExist(String name) {
		Session session = SecurityUtils.getSubject().getSession();
		String site = session.getAttribute("site").toString();
		
		WorkCenterFormMap workCenterFormMap = new WorkCenterFormMap();
		workCenterFormMap.put("workcenter_no", name);
		workCenterFormMap.put("site", site);
		List<WorkCenterFormMap> list = baseMapper.findByNames(workCenterFormMap);
		if (list == null || list.size()<=0) {
			return true;
		} else {
			return false;
		}
	}

}