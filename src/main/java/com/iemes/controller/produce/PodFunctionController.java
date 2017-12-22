package com.iemes.controller.produce;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
import com.iemes.controller.index.BaseController;
import com.iemes.entity.PodFunctionFormMap;
import com.iemes.entity.UserFormMap;
import com.iemes.mapper.pod.PodFunctionMapper;
import com.iemes.plugin.PageView;
import com.iemes.util.Common;

/**
 * 
 * @author lanyuan 2014-11-19
 * @Email: mmm333zzz520@163.com
 * @version 3.0v
 */
@Controller
@RequestMapping("/produce/")
public class PodFunctionController extends BaseController {

	@Inject
	private PodFunctionMapper podFunctionMapper; 

	@RequestMapping("podfunction_list")
	public String listUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/produce/process/podfunction_list";
	}

	@ResponseBody
	@RequestMapping("pod_function_findPage")
	public PageView pod_function_findPage(String pageNow,
			String pageSize)throws Exception{
		PodFunctionFormMap podFunctionFormMap = getFormMap(PodFunctionFormMap.class);
		podFunctionFormMap=toFormMap(podFunctionFormMap, pageNow, pageSize,podFunctionFormMap.getStr("orderby"));
		Session session = SecurityUtils.getSubject().getSession();
		//podFunctionFormMap.set("site", session.getAttribute("site"));
		pageView.setRecords(baseMapper.findByPage(podFunctionFormMap));
		return pageView;
	}

	@RequestMapping("pod_function_addUI")
	public String podFunctionAddUI(Model model) throws Exception {
		PodFunctionFormMap podFunctionFormMap = new PodFunctionFormMap();
		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		// 通过工具类获取当前登录的bean
		UserFormMap userFormMap = (UserFormMap)Common.findUserSession(request);
		podFunctionFormMap.put("by_user", userFormMap.getStr("accountName"));
		//获取当前的时间（timestamp）
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		podFunctionFormMap.put("create_time", sdf.format(new Timestamp(System.currentTimeMillis())));
		request.setAttribute("podFunctionFormMap", podFunctionFormMap);
		return Common.BACKGROUND_PATH + "/system/produce/process/podfunction_add";
	}

	@ResponseBody
	@RequestMapping("pod_function_addEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="生产过程控制",methods="pod功能维护-新增pod功能")//凡需要处理业务逻辑的.都需要记录操作日志
	public String addEntity() throws Exception {
		PodFunctionFormMap podFunctionFormMap = getFormMap(PodFunctionFormMap.class);

	/*	// 获取request
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		// 通过工具类获取当前登录的bean
		UserFormMap userFormMap = (UserFormMap)Common.findUserSession(request);
		podFunctionFormMap.put("by_user", userFormMap.getStr("accountName"));
		//获取当前的时间（timestamp）
		podFunctionFormMap.put("create_time", new Timestamp(System.currentTimeMillis()));*/
		baseMapper.addEntity(podFunctionFormMap);
		return "success";
	}

	/**
	 * 验证账号是否存在 
	 * 
	 * @author lanyuan Email：mmm333zzz520@163.com date：2014-2-19
	 * @param name
	 * @return
	 */
	@RequestMapping("isExist")
	@ResponseBody
	public boolean isExist(String name) {
		PodFunctionFormMap podFunctionFormMap = podFunctionMapper.findbyFrist("pod_function_no", name, PodFunctionFormMap.class);
		if (podFunctionFormMap == null) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping("pod_function_editUI")
	public String podFunctionEditUI(Model model) throws Exception {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			model.addAttribute("pod_function", baseMapper.findbyFrist("id", id, PodFunctionFormMap.class));
		}
		return Common.BACKGROUND_PATH + "/system/produce/process/podfunction_edit";
	}

	@ResponseBody
	@RequestMapping("pod_function_editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="生产过程控制",methods="pod功能维护-修改pod功能")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity() throws Exception {
		PodFunctionFormMap podFunctionFormMap = getFormMap(PodFunctionFormMap.class);
		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		// 通过工具类获取当前登录的bean
		UserFormMap userFormMap = (UserFormMap)Common.findUserSession(request);
		podFunctionFormMap.put("by_user", userFormMap.getStr("accountName"));
		//获取当前的时间（timestamp）
		podFunctionFormMap.put("create_time", new Timestamp(System.currentTimeMillis()));
		baseMapper.editEntity(podFunctionFormMap);
		return "success";
	}

	@ResponseBody
	@RequestMapping("pod_function_deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="生产过程控制",methods="pod功能维护-删除pod功能")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() throws Exception {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			baseMapper.deleteByAttribute("id", id, PodFunctionFormMap.class);
		}
		return "success";
	}
}