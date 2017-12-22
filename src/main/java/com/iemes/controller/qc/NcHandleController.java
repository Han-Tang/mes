package com.iemes.controller.qc;


import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.iemes.controller.index.BaseController;
import com.iemes.entity.NcRepairFormMap;
import com.iemes.entity.UserFormMap;
import com.iemes.mapper.BaseExtMapper;
import com.iemes.service.NcHandleService;
import com.iemes.util.Common;
import com.iemes.util.ListUtils;
import com.iemes.util.ResponseHelp;

/**
 * 
 * @author lanyuan 2014-11-19
 * @Email: mmm333zzz520@163.com
 * @version 3.0v
 */
@Controller
@RequestMapping("/qc/")
public class NcHandleController extends BaseController {

	@Inject
	private BaseExtMapper baseExtMapper;
	
	@Inject
	private NcHandleService ncHandleService;

	/**
	 * 跳转到不合格品处置页面
	 * @return
	 */
	@RequestMapping("nc_handle")
	public String forwardTracingReportUi(){
		return Common.BACKGROUND_PATH + "/qc/nc_handle";
	}

	/**
	 * 根据sfc查询不合格产品
	 * @param sfc
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getNcHandleSFCData")
	public String getNcHandleSFCData(String sfc,Model model) throws Exception{
		Session session = SecurityUtils.getSubject().getSession();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sfc", sfc);
		map.put("site", session.getAttribute("site").toString());
		try {
			List<Map<String, Object>> ncInfo = baseExtMapper.findNCBySFC(map);
			List<Map<String, Object>> operationList = baseExtMapper.findAllOperationBySfc(map);
			model.addAttribute("ncInfo", ncInfo);
			model.addAttribute("operationList", operationList);
			if (!ListUtils.isNotNull(ncInfo)) {
				model.addAttribute("exist", false);
			}else {
				model.addAttribute("exist", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Common.BACKGROUND_PATH + "/qc/nc_handle_info";
	}


	/**
	 * 处理不合格品 维修 or 报废
	 * @param radio
	 * @param remarks
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("addNcHandleMsg")
	public String addNcHandleMsg(String sfc,String radio,String remarks,String operation, Model model){
		Session session = SecurityUtils.getSubject().getSession();
		Map<String,Object> map = new HashMap<String,Object>();
		String site = session.getAttribute("site").toString();
		map.put("sfc", sfc);
		map.put("site", site);
		
		List<Map<String, Object>> data = baseExtMapper.findNCRepairBySFC(map);
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		UserFormMap userFormMap = (UserFormMap)Common.findUserSession(request);
		
		NcRepairFormMap ncRepairFormMap = new NcRepairFormMap();
		ncRepairFormMap.put("byUser", userFormMap.getStr("accountName"));
		ncRepairFormMap.put("createDate", new Timestamp(System.currentTimeMillis()));
		ncRepairFormMap.put("site", site);
		ncRepairFormMap.put("sfc", sfc);
		ncRepairFormMap.put("repair_desc", remarks);
		ncRepairFormMap.put("workcenter", data.get(0).get("workcenter"));
		ncRepairFormMap.put("workcenter_child", data.get(0).get("workcenter_child"));
		ncRepairFormMap.put("operation", data.get(0).get("operation"));
		ncRepairFormMap.put("operation2", operation);
		ncRepairFormMap.put("shoporder", data.get(0).get("shoporder"));
		ncRepairFormMap.put("nc_code", data.get(0).get("nc_code"));
		ncRepairFormMap.put("nc_code_group", data.get(0).get("nc_code_group"));
		try {
			//维修
			if(radio.equals("repair")){
				return ncHandleService.NcHandleRepair(ncRepairFormMap);
			}
			else{ //报废
				return ncHandleService.NcHandleScrap(ncRepairFormMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHelp.responseErrorText("操作异常："+e.getMessage());
		}
	}

}