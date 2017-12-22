package com.iemes.controller.plan;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

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
import com.iemes.entity.ShopOrderFormMap;
import com.iemes.entity.UserFormMap;
import com.iemes.plugin.PageView;
import com.iemes.score.GradeCalculateService;
import com.iemes.util.Common;

/**
 * 
 * @author lanyuan 2014-11-19
 * @Email: mmm333zzz520@163.com
 * @version 3.0v
 */
@Controller
@RequestMapping("/product_plan/")
public class ShoporderMaintenanceController extends BaseController {
	
	@Inject
	private GradeCalculateService gradeCalculateService;
	
	@RequestMapping("shoporder_maintenance_list")
	public String listUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/product_plan/shoporder_maintenance/shoporder_maintenance_list";
	}
	
	@ResponseBody
	@RequestMapping("shoporder_maintenance_findByPage")
	public PageView findByPage(String pageNow,
			String pageSize) throws Exception {
		Session session = SecurityUtils.getSubject().getSession();
		ShopOrderFormMap shopOrderFormMap = getFormMap(ShopOrderFormMap.class);
		shopOrderFormMap.put("site", session.getAttribute("site"));
		shopOrderFormMap=toFormMap(shopOrderFormMap, pageNow, pageSize,shopOrderFormMap.getStr("orderby"));
		pageView.setRecords(baseMapper.findByPage(shopOrderFormMap));
		return pageView;
	}
	
	@RequestMapping("shoporder_maintenance_addUI")
	public String addUI(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/product_plan/shoporder_maintenance/shoporder_maintenance_add";
	}
	
	@ResponseBody
	@RequestMapping("shoporder_maintenance_addEntity")
//	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="生产计划管理",methods="计划订单维护-新增订单")//凡需要处理业务逻辑的.都需要记录操作日志
	public String addEntity() throws Exception {
		Session session = SecurityUtils.getSubject().getSession();
		ShopOrderFormMap shopOrderFormMap = getFormMap(ShopOrderFormMap.class);
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		UserFormMap userFormMap = (UserFormMap)Common.findUserSession(request);
		shopOrderFormMap.put("by_user", userFormMap.getStr("accountName"));
		shopOrderFormMap.put("site", session.getAttribute("site"));
		shopOrderFormMap.put("status", 0);
		baseMapper.addEntity(shopOrderFormMap);
		JSONObject json = new JSONObject();
		String rs = null;
		try {
			rs = gradeCalculateService.GradeCalculate("计划工单维护", null);
			json.put("result", ConstantController.SUCCESS);
			json.put("score", rs);
		} catch (Exception e) {
			json.put("result", ConstantController.ERROR);
		}
		return json.toString();
	}
	
	@RequestMapping("shoporder_maintenance_editUI")
	public String editUI(Model model) throws Exception {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			model.addAttribute("shopOrder", baseMapper.findbyFrist("id", id, ShopOrderFormMap.class));
		}
		return Common.BACKGROUND_PATH + "/product_plan/shoporder_maintenance/shoporder_maintenance_edit";
	}
	
	@ResponseBody
	@RequestMapping("shoporder_maintenance_editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="生产计划管理",methods="计划订单维护-编辑订单")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity() throws Exception {
		ShopOrderFormMap shopOrderFormMap = getFormMap(ShopOrderFormMap.class);
		baseMapper.editEntity(shopOrderFormMap);
		return "success";
	}
	
	/**
	 * 获取未完成订单分页列表
	 * @param pageNow
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("unfinished_workcenter_findByPage")
	public PageView findUnFinishedWorkcenterByPage(String pageNow, String pageSize) throws Exception {
		
		//查询未完成订单
		ShopOrderFormMap shopOrderFormMap = new ShopOrderFormMap();
		Session session = SecurityUtils.getSubject().getSession();
		shopOrderFormMap.put("where", " where site = '"+session.getAttribute("site")+"' and status in (0,1)");
		List<ShopOrderFormMap> list = baseMapper.findByWhere(shopOrderFormMap);
		pageView = getPageView(pageNow, pageSize, getPara("orderby"));
		if (list!=null) {
			pageView.setRecords(list);
		}
		return pageView;
	}
	
	/**
	 * 工单是否存在
	 * @param name
	 * @return
	 */
	@RequestMapping("isExist")
	@ResponseBody
	public boolean isExist(String name) {
		Session session = SecurityUtils.getSubject().getSession();
		String site = session.getAttribute("site").toString();
		ShopOrderFormMap shopOrderFormMap = new ShopOrderFormMap();
		shopOrderFormMap.put("shoporder_no", name);
		shopOrderFormMap.put("site", site);
		List<ShopOrderFormMap> list = baseMapper.findByNames(shopOrderFormMap);
		if (list == null || list.size()<=0) {
			return true;
		} else {
			return false;
		}
	}

}