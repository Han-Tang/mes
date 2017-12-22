package com.iemes.controller.test;

import java.sql.Timestamp;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.iemes.annotation.SystemLog;
import com.iemes.controller.index.BaseController;
import com.iemes.entity.JayceFormMap;
import com.iemes.entity.UserFormMap;
import com.iemes.mapper.jayce.JayceMapper;
import com.iemes.plugin.PageView;
import com.iemes.util.Common;

/**
 * 
 * @author lanyuan 2014-11-19
 * @Email: mmm333zzz520@163.com
 * @version 3.0v
 */
@Controller
@RequestMapping("/test/")
public class JayceController extends BaseController {
	@Inject
	private JayceMapper jayceMapper; 

	@RequestMapping("jayce_list")
	public String listUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/test/jayce/jayce_list";
	}

	@ResponseBody
	@RequestMapping("jayce_findByPage")
	public PageView findByPage(String pageNow,
			String pageSize) throws Exception {
		JayceFormMap jayceFormMap = getFormMap(JayceFormMap.class);
		jayceFormMap=toFormMap(jayceFormMap, pageNow, pageSize,jayceFormMap.getStr("orderby"));
		pageView.setRecords(jayceMapper.findByPage(jayceFormMap));
		return pageView;
	}

	@RequestMapping("jayce_addUI")
	public String addUI(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/test/jayce/jayce_add";
	}

	@ResponseBody
	@RequestMapping("jayce_addEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="测试数据维护",methods="Jayce维护-新增Jayce")//凡需要处理业务逻辑的.都需要记录操作日志
	public String addEntity() throws Exception {
		JayceFormMap jayceFormMap = getFormMap(JayceFormMap.class);

		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		// 通过工具类获取当前登录的bean
		UserFormMap userFormMap = (UserFormMap)Common.findUserSession(request);
		jayceFormMap.put("byUser", userFormMap.getStr("accountName"));
		//获取当前的时间（timestamp）
		jayceFormMap.put("createDate", new Timestamp(System.currentTimeMillis()));
		jayceMapper.addEntity(jayceFormMap);
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
		JayceFormMap jayceFormMap = jayceMapper.findbyFrist("name", name, JayceFormMap.class);
		if (jayceFormMap == null) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping("jayce_editUI")
	public String editUI(Model model) throws Exception {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			model.addAttribute("jayce", jayceMapper.findbyFrist("id", id, JayceFormMap.class));
		}
		return Common.BACKGROUND_PATH + "/test/jayce/jayce_edit";
	}

	@ResponseBody
	@RequestMapping("jayce_editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="测试数据维护",methods="Jayce维护-修改Jayce")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity() throws Exception {
		JayceFormMap jayceFormMap = getFormMap(JayceFormMap.class);
		//获取当前的时间（timestamp）
		jayceFormMap.put("createDate", new Timestamp(System.currentTimeMillis()));
		jayceMapper.editEntity(jayceFormMap);
		return "success";
	}

	@ResponseBody
	@RequestMapping("jayce_deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="测试数据维护",methods="Jayce维护-删除Jayce")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() throws Exception {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			jayceMapper.deleteByAttribute("id", id, JayceFormMap.class);
		}
		return "success";
	}
}