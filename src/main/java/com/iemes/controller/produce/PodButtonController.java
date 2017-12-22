package com.iemes.controller.produce;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

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
import com.iemes.controller.index.BaseController;
import com.iemes.entity.PodButtonFormMap;
import com.iemes.entity.UserFormMap;
import com.iemes.mapper.BaseExtMapper;
import com.iemes.mapper.pod.PodButtonMapper;
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
public class PodButtonController extends BaseController {
	@Inject
	private PodButtonMapper podButtonMapper; 
	@Inject
	private BaseExtMapper baseExtMapper;

	@RequestMapping("pod_button_list")
	public String listUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/produce/process/podbutton_list";
	}

	@ResponseBody
	@RequestMapping("pod_button_findPage")
	public PageView pod_button_findPage(String pageNow,
			String pageSize)throws Exception{
		PodButtonFormMap podButtonFormMap = getFormMap(PodButtonFormMap.class);
		podButtonFormMap=toFormMap(podButtonFormMap, pageNow, pageSize,podButtonFormMap.getStr("orderby"));
		Session session = SecurityUtils.getSubject().getSession();
		podButtonFormMap.set("site", session.getAttribute("site"));
		pageView.setRecords(baseMapper.findByPage(podButtonFormMap));
		return pageView;
	}

	@RequestMapping("pod_button_addUI")
	public String podFunctionAddUI(Model model) throws Exception {
		PodButtonFormMap podButtonFormMap = new PodButtonFormMap();
		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		// 通过工具类获取当前登录的bean
		UserFormMap userFormMap = (UserFormMap)Common.findUserSession(request);
		podButtonFormMap.put("by_user", userFormMap.getStr("accountName"));
		//获取当前的时间（timestamp）
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		podButtonFormMap.put("create_time", sdf.format(new Timestamp(System.currentTimeMillis())));
		request.setAttribute("podButtonFormMap", podButtonFormMap);
		return Common.BACKGROUND_PATH + "/system/produce/process/podbutton_add";
	}

	@ResponseBody
	@RequestMapping("pod_button_addEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="生产过程控制",methods="pod按钮维护-新增pod按钮")//凡需要处理业务逻辑的.都需要记录操作日志
	public String addEntity() throws Exception {
		PodButtonFormMap podButtonFormMap = getFormMap(PodButtonFormMap.class);

		/*		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		// 通过工具类获取当前登录的bean
		UserFormMap userFormMap = (UserFormMap)Common.findUserSession(request);
		podButtonFormMap.put("by_user", userFormMap.getStr("accountName"));
		podButtonFormMap.put("site", "*");*/
		baseMapper.addEntity(podButtonFormMap);
		return "success";
	}

	/**
	 * 验证账号是否存在 
	 * 
	 * @author lanyuan Email：mmm333zzz520@163.com date：2014-2-19
	 * @param name
	 * @return
	 */
	@RequestMapping("isExistButton")
	@ResponseBody
	public boolean isExist(String name) {
		Session session = SecurityUtils.getSubject().getSession();
		String site = session.getAttribute("site").toString();
		PodButtonFormMap podButtonForm = new PodButtonFormMap();
		podButtonForm.put("site", site);
		podButtonForm.put("pod_button_no", name);
		List<PodButtonFormMap> list = podButtonMapper.findByNames(podButtonForm);
		if (list == null || list.size()<=0) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping("pod_button_editUI")
	public String podFunctionEditUI(Model model) throws Exception {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			model.addAttribute("pod_button", baseMapper.findbyFrist("id", id, PodButtonFormMap.class));
		}
		return Common.BACKGROUND_PATH + "/system/produce/process/podbutton_edit";
	}

	@ResponseBody
	@RequestMapping("pod_button_editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="生产过程控制",methods="pod按钮维护-修改pod按钮")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity() throws Exception {
		PodButtonFormMap podButtonFormMap = getFormMap(PodButtonFormMap.class);
		baseMapper.editEntity(podButtonFormMap);
		return "success";
	}

	@ResponseBody
	@RequestMapping("pod_button_deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="生产过程控制",methods="pod按钮维护-删除pod按钮")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() throws Exception {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			baseMapper.deleteByAttribute("id", id, PodButtonFormMap.class);
		}
		return "success";
	}

	@RequestMapping("selButton")
	public String selectButton(Model model) throws Exception {
		PodButtonFormMap podButtonFormMap = getFormMap(PodButtonFormMap.class);
		Session session = SecurityUtils.getSubject().getSession();
		podButtonFormMap.put("where", "where site = '*'");
		Object operationNo = podButtonFormMap.get("operation_no");
		if(null!=operationNo){
			List<PodButtonFormMap> list = baseExtMapper.selectOperationPod(podButtonFormMap);
			String ugid = "";
			for (PodButtonFormMap ml : list) {
				ugid += ml.get("id")+",";
			}
			ugid = Common.trimComma(ugid);
			model.addAttribute("txtPodSelect", ugid);
			model.addAttribute("operationPod", list);
			if(StringUtils.isNotBlank(ugid)){
				podButtonFormMap.put("where", " where id not in ("+ugid+") and site = '*'");
			}
		}
		//查询相应站点对应的podButtonList
		List<PodButtonFormMap> pods = podButtonMapper.findByWhere(podButtonFormMap);
				//baseMapper.findByAttribute("site", (String)session.getAttribute("site"), PodButtonFormMap.class);
		model.addAttribute("pod", pods);
		return Common.BACKGROUND_PATH + "/workshopdata/operation/pod_button_select";
	}
}