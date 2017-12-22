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
import com.iemes.entity.SukeyFormMap;
import com.iemes.entity.UserFormMap;
import com.iemes.mapper.sukey.SukeyMapper;
import com.iemes.plugin.PageView;
import com.iemes.util.Common;


@Controller
@RequestMapping("/test2/")
public class SukeyController extends BaseController {
	@Inject
	private SukeyMapper sukeyMapper; 

	@RequestMapping("sukey_list")
	public String listUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/test/sukey/sukey_list";
	}

	@ResponseBody
	@RequestMapping("sukey_findByPage")
	public PageView findByPage(String pageNow,
			String pageSize) throws Exception {
		SukeyFormMap sukeyFormMap = getFormMap(SukeyFormMap.class);
		sukeyFormMap=toFormMap(sukeyFormMap, pageNow, pageSize,sukeyFormMap.getStr("orderby"));
		pageView.setRecords(sukeyMapper.findByPage(sukeyFormMap));
		return pageView;
	}

	@RequestMapping("sukey_addUI")
	public String addUI(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/test/sukey/sukey_add";
	}

	@ResponseBody
	@RequestMapping("sukey_addEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="测试数据维护",methods="sukey维护-新增sukey")//凡需要处理业务逻辑的.都需要记录操作日志
	public String addEntity() throws Exception {
		SukeyFormMap sukeyFormMap = getFormMap(SukeyFormMap.class);

		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		// 通过工具类获取当前登录的bean
		UserFormMap userFormMap = (UserFormMap)Common.findUserSession(request);
		sukeyFormMap.put("byUser", userFormMap.getStr("accountName"));
		//获取当前的时间（timestamp）
		sukeyFormMap.put("createDate", new Timestamp(System.currentTimeMillis()));
		sukeyMapper.addEntity(sukeyFormMap);
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
		SukeyFormMap sukeyFormMap = sukeyMapper.findbyFrist("name", name, SukeyFormMap.class);
		if (sukeyFormMap == null) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping("sukey_editUI")
	public String editUI(Model model) throws Exception {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			model.addAttribute("sukey", sukeyMapper.findbyFrist("id", id, SukeyFormMap.class));
		}
		return Common.BACKGROUND_PATH + "/test/sukey/sukey_edit";
	}

	@ResponseBody
	@RequestMapping("sukey_editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="测试数据维护",methods="sukey维护-修改sukey")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity() throws Exception {
		SukeyFormMap sukeyFormMap = getFormMap(SukeyFormMap.class);
		//获取当前的时间（timestamp）
		sukeyFormMap.put("createDate", new Timestamp(System.currentTimeMillis()));
		sukeyMapper.editEntity(sukeyFormMap);
		return "success";
	}

	@ResponseBody
	@RequestMapping("sukey_deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="测试数据维护",methods="sukey维护-删除sukey")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() throws Exception {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			sukeyMapper.deleteByAttribute("id", id, SukeyFormMap.class);
		}
		return "success";
	}
}