package com.iemes.controller.qc;

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
import com.iemes.entity.NcCodeGroupFormMap;
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
@RequestMapping("/nc_group/")
public class NcCodeGroupController extends BaseController {
	@Inject
	private GradeCalculateService gradeCalculateService;
	
	@RequestMapping("nc_group_list")
	public String listUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/qc/nc_group/nc_group_list";
	}
	
	@ResponseBody
	@RequestMapping("nc_group_findByPage")
	public PageView findByPage(String pageNow,
			String pageSize) throws Exception {
		NcCodeGroupFormMap ncCodeGroupFormMap = getFormMap(NcCodeGroupFormMap.class);
		ncCodeGroupFormMap=toFormMap(ncCodeGroupFormMap, pageNow, pageSize,ncCodeGroupFormMap.getStr("orderby"));
		Session session = SecurityUtils.getSubject().getSession();
		ncCodeGroupFormMap.put("site", session.getAttribute("site"));
		pageView.setRecords(baseMapper.findByPage(ncCodeGroupFormMap));
		return pageView;
	}
	
	@RequestMapping("nc_group_addUI")
	public String addUI(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/qc/nc_group/nc_group_add";
	}
	
	@ResponseBody
	@RequestMapping("nc_group_addEntity")
//	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="产品质量管理",methods="不合格组维护-新增不合格组")//凡需要处理业务逻辑的.都需要记录操作日志
	public String addEntity() throws Exception {
		NcCodeGroupFormMap ncCodeGroupFormMap = getFormMap(NcCodeGroupFormMap.class);
		
		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		Session session = SecurityUtils.getSubject().getSession();
		ncCodeGroupFormMap.put("site", session.getAttribute("site"));
		// 通过工具类获取当前登录的bean
		UserFormMap userFormMap = (UserFormMap)Common.findUserSession(request);
		ncCodeGroupFormMap.put("by_user", userFormMap.getStr("accountName"));
		
		baseMapper.addEntity(ncCodeGroupFormMap);
		
		JSONObject json = new JSONObject();
		String rs = null;
		try {
			rs = gradeCalculateService.GradeCalculate("不合格组维护", null);
			json.put("result", ConstantController.SUCCESS);
			json.put("score", rs);
		} catch (Exception e) {
			json.put("result", ConstantController.ERROR);
		}
		return json.toString();
	}
	
	@RequestMapping("nc_group_editUI")
	public String editUI(Model model) throws Exception {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			model.addAttribute("ncCodeGroup", baseMapper.findbyFrist("id", id, NcCodeGroupFormMap.class));
		}
		return Common.BACKGROUND_PATH + "/qc/nc_group/nc_group_edit";
	}
	
	@ResponseBody
	@RequestMapping("nc_group_editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="产品质量管理",methods="不合格组维护-修改不合格组")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity() throws Exception {
		NcCodeGroupFormMap ncCodeGroupFormMap = getFormMap(NcCodeGroupFormMap.class);
		baseMapper.editEntity(ncCodeGroupFormMap);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("nc_group_deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="产品质量管理",methods="不合格组维护-删除不合格组")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() throws Exception {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			baseMapper.deleteByAttribute("id", id, NcCodeGroupFormMap.class);
		}
		return "success";
	}

}