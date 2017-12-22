package com.iemes.controller.workshop;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

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
import com.iemes.entity.OperationFormMap;
import com.iemes.entity.OperationPodFormMap;
import com.iemes.entity.UserFormMap;
import com.iemes.mapper.operation.OperationMapper;
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
public class OperationController extends BaseController {
	
	@Inject
	private GradeCalculateService gradeCalculateService;
	
	@Inject
	private OperationMapper operationMapper;

	@RequestMapping("operation_list")
	public String listUI(Model model) {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/workshopdata/operation/operation_list";
	}

	@ResponseBody
	@RequestMapping("operation_findByPage")
	public PageView findByPage(String pageNow,
			String pageSize) {
		Session session = SecurityUtils.getSubject().getSession();
		OperationFormMap operationFormMap = getFormMap(OperationFormMap.class);
		operationFormMap.put("site", session.getAttribute("site"));
		operationFormMap=toFormMap(operationFormMap, pageNow, pageSize,operationFormMap.getStr("orderby"));
		pageView.setRecords(baseMapper.findByPage(operationFormMap));
		return pageView;
	}

	@RequestMapping("operation_addUI")
	public String addUI(Model model) {
		return Common.BACKGROUND_PATH + "/workshopdata/operation/operation_add";
	}

	@ResponseBody
	@RequestMapping("operation_addEntity")
	@SystemLog(module="车间数据维护",methods="操作维护-新增操作")//凡需要处理业务逻辑的.都需要记录操作日志
	public String addEntity(String txtGroupsSelect) {
		JSONObject json = new JSONObject();
		try {
			OperationFormMap operationFormMap = getFormMap(OperationFormMap.class);
			operationFormMap.put("txtGroupsSelect", txtGroupsSelect);
			// 获取request
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
			// 通过工具类获取当前登录的bean
			UserFormMap userFormMap = (UserFormMap)Common.findUserSession(request);
			operationFormMap.put("byUser", userFormMap.getStr("accountName"));
			baseMapper.addEntity(operationFormMap);

			if(!Common.isEmpty(txtGroupsSelect)){
				String[] txt = txtGroupsSelect.split(",");
				OperationPodFormMap  operationPodFormMap = getFormMap(OperationPodFormMap.class);
				for (String podButtonNO : txt) {
					operationPodFormMap.put("operation_no", operationFormMap.get("id"));
					operationPodFormMap.put("pod_button_no", podButtonNO);
					operationMapper.addEntity(operationPodFormMap);
				}
			}
			String rs = gradeCalculateService.GradeCalculate("操作维护", null);
			json.put("result", ConstantController.SUCCESS);
			json.put("score", rs);
		} catch (Exception e) {
			return ConstantController.ERROR;
		}
		return json.toString();
	}

	@RequestMapping("operation_editUI")
	public String editUI(Model model) {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			model.addAttribute("operation", baseMapper.findbyFrist("id", id, OperationFormMap.class));
		}
		return Common.BACKGROUND_PATH + "/workshopdata/operation/operation_edit";
	}

	@ResponseBody
	@RequestMapping("operation_editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="车间数据维护",methods="操作维护-修改操作")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity(String txtGroupsSelect) {
		try {
			OperationFormMap operationFormMap = getFormMap(OperationFormMap.class);
			operationFormMap.put("txtGroupsSelect", txtGroupsSelect);
			baseMapper.editEntity(operationFormMap);	
			operationMapper.deleteByAttribute("operation_no", operationFormMap.get("id")+"", OperationPodFormMap.class);
			if(!Common.isEmpty(txtGroupsSelect)){
				String[] txt = txtGroupsSelect.split(",");
				OperationPodFormMap  operationPodFormMap = getFormMap(OperationPodFormMap.class);
				for (String podButtonNO : txt) {
					operationPodFormMap.put("operation_no", operationFormMap.get("id"));
					operationPodFormMap.put("pod_button_no", podButtonNO);
					operationMapper.addEntity(operationPodFormMap);
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
			return ConstantController.ERROR;
		}
		return ConstantController.SUCCESS;
	}

	@ResponseBody
	@RequestMapping("operation_deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="车间数据维护",methods="操作维护-删除操作")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() {
		try {String[] ids = getParaValues("ids");
		for (String id : ids) {
			operationMapper.deleteByAttribute("operation_no", id, OperationPodFormMap.class);
			baseMapper.deleteByAttribute("id", id, OperationFormMap.class);
		}
		} catch (Exception e) {
			e.printStackTrace();
			return ConstantController.ERROR;
		}
		return ConstantController.SUCCESS;
	}

	
	/**
	 * 验证操作是否存在
	 * 
	 * @author lanyuan Email：mmm333zzz520@163.com date：2014-2-19
	 * @param name
	 * @return
	 */
	@RequestMapping("operation_isExist")
	@ResponseBody
	public boolean operationIsExist(String name) {
		Session session = SecurityUtils.getSubject().getSession();
		String site = session.getAttribute("site").toString();
		
		OperationFormMap operationFormMap = new OperationFormMap();
		operationFormMap.put("operation_no", name);
		operationFormMap.put("site", site);
		List<OperationFormMap> list = baseMapper.findByNames(operationFormMap);
		if (list == null || list.size()<=0) {
			return true;
		} else {
			return false;
		}
	}
}