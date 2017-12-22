package com.iemes.controller.qc;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
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
import com.iemes.entity.NcCodeFormMap;
import com.iemes.entity.NcCodeGroupRelFormMap;
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
@RequestMapping("/qc/")
public class NcCodeController extends BaseController {
	@Inject
	private GradeCalculateService gradeCalculateService;
	
	@RequestMapping("nc_list")
	public String listUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/qc/nc/nc_list";
	}
	
	@ResponseBody
	@RequestMapping("nc_findByPage")
	public PageView findByPage(String pageNow,
			String pageSize) throws Exception {
		NcCodeFormMap ncCodeFormMap = getFormMap(NcCodeFormMap.class);
		ncCodeFormMap=toFormMap(ncCodeFormMap, pageNow, pageSize,ncCodeFormMap.getStr("orderby"));
		Session session = SecurityUtils.getSubject().getSession();
		ncCodeFormMap.put("site", session.getAttribute("site"));
		List<NcCodeFormMap> ncCodeFormMaps = baseMapper.findByPage(ncCodeFormMap);
		//加载每个不合格代码所属的不合格组代码
		if(CollectionUtils.isNotEmpty(ncCodeFormMaps))
		{
			for(NcCodeFormMap nc : ncCodeFormMaps)
			{
				String ncCode = nc.getStr("nc_code");
				List<NcCodeGroupRelFormMap> ncCodeGroupRelFormMaps = baseMapper.findByAttribute("nc_code", ncCode, NcCodeGroupRelFormMap.class);
				if(CollectionUtils.isNotEmpty(ncCodeGroupRelFormMaps))
				{
					NcCodeGroupRelFormMap ncCodeGroupRelFormMap = ncCodeGroupRelFormMaps.get(0);
					nc.put("nc_code_group_no", ncCodeGroupRelFormMap.getStr("nc_code_group"));
				}
			}
		}
		pageView.setRecords(ncCodeFormMaps);
		return pageView;
	}
	
	@RequestMapping("nc_addUI")
	public String addUI(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/qc/nc/nc_add";
	}
	
	@ResponseBody
	@RequestMapping("nc_addEntity")
//	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="产品质量管理",methods="不合格维护-新增不合格")//凡需要处理业务逻辑的.都需要记录操作日志
	public String addEntity() throws Exception {
		NcCodeFormMap ncCodeFormMap = getFormMap(NcCodeFormMap.class);
		
		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		// 通过工具类获取当前登录的bean
		UserFormMap userFormMap = (UserFormMap)Common.findUserSession(request);
		Session session = SecurityUtils.getSubject().getSession();
		ncCodeFormMap.put("site", session.getAttribute("site"));
		ncCodeFormMap.put("by_user", userFormMap.getStr("accountName"));
		baseMapper.addEntity(ncCodeFormMap);
		
		//保存不合格代码与不合代码组关联关系
		String nc_code_group_no = request.getParameter("ncCodeGroupFormMap.nc_code_group_no");
		NcCodeGroupRelFormMap ncCodeGroupRelFormMap = new NcCodeGroupRelFormMap();
		ncCodeGroupRelFormMap.put("site", session.getAttribute("site"));
		ncCodeGroupRelFormMap.put("nc_code", ncCodeFormMap.getStr("nc_code"));
		ncCodeGroupRelFormMap.put("nc_code_group", nc_code_group_no);
		baseMapper.addEntity(ncCodeGroupRelFormMap);
		
		JSONObject json = new JSONObject();
		String rs = null;
		try {
			rs = gradeCalculateService.GradeCalculate("不合格代码维护", null);
			json.put("result", ConstantController.SUCCESS);
			json.put("score", rs);
		} catch (Exception e) {
			json.put("result", ConstantController.ERROR);
		}
		return json.toString();
	}
	
	@RequestMapping("nc_editUI")
	public String editUI(Model model) throws Exception {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			NcCodeFormMap ncCodeFormMap = baseMapper.findbyFrist("id", id, NcCodeFormMap.class);
			//加载所属不合格组
			String ncCode = ncCodeFormMap.getStr("nc_code");
			List<NcCodeGroupRelFormMap> ncCodeGroupRelFormMaps = baseMapper.findByAttribute("nc_code", ncCode, NcCodeGroupRelFormMap.class);
			if(CollectionUtils.isNotEmpty(ncCodeGroupRelFormMaps))
			{
				NcCodeGroupRelFormMap ncCodeGroupRelFormMap = ncCodeGroupRelFormMaps.get(0);
				ncCodeFormMap.put("nc_code_group_no", ncCodeGroupRelFormMap.getStr("nc_code_group"));
			}
			model.addAttribute("ncCode", ncCodeFormMap);
		}
		return Common.BACKGROUND_PATH + "/qc/nc/nc_edit";
	}
	
	@ResponseBody
	@RequestMapping("nc_editEntity")
	@SystemLog(module="产品质量管理",methods="不合格维护-修改不合格")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity() throws Exception {
		Session session = SecurityUtils.getSubject().getSession();
		String site = session.getAttribute("site").toString();
		
		NcCodeFormMap ncCodeFormMap = getFormMap(NcCodeFormMap.class);
		
		//保存不合格代码与不合代码组关联关系
		String groupNo = ncCodeFormMap.getStr("nc_code_group_no");
		String ncCode = ncCodeFormMap.getStr("nc_code");
		
		
		NcCodeGroupRelFormMap ncCodeGroupRelFormMap = new NcCodeGroupRelFormMap();
		ncCodeGroupRelFormMap.put("nc_code", ncCode);
		ncCodeGroupRelFormMap.put("site", site);
		List<NcCodeGroupRelFormMap> list = baseMapper.findByNames(ncCodeGroupRelFormMap);
		if (list!=null || list.size()>0) {
			ncCodeGroupRelFormMap = list.get(0);
			ncCodeGroupRelFormMap.put("nc_code_group", groupNo);
			baseMapper.editEntity(ncCodeGroupRelFormMap);
		}
		
		baseMapper.editEntity(ncCodeFormMap);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("nc_deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="产品质量管理",methods="不合格维护-删除不合格")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() throws Exception {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			baseMapper.deleteByAttribute("id", id, NcCodeFormMap.class);
		}
		return "success";
	}

}