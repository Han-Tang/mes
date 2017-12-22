package com.iemes.controller.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iemes.controller.base.BaseResponse;
import com.iemes.controller.index.BaseController;
import com.iemes.mapper.KanbanMapper;
import com.iemes.mapper.ReportMapper;
import com.iemes.plugin.PageView;
import com.iemes.util.Common;
import com.iemes.util.FormMap;
import com.iemes.util.ShiroSecurityHelper;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/report/")
public class ReportController extends BaseController{

	@Inject
	private ReportMapper reportMapper;
	
	@Inject
	private KanbanMapper kanbanMapper;

	/**
	 * 测试报表跳转方法
	 * @return
	 */
	@RequestMapping("testReportUi")
	public String testReportUi(){
		return Common.BACKGROUND_PATH + "/test/echarts/echarts_test";
	}

	@RequestMapping("scheduler_reportUi")
	public String schedulerReportUi(){
		return Common.BACKGROUND_PATH + "/report/schedulerReport";
	}

	@RequestMapping("inventory_reportUi")
	public String inventory(){
		return Common.BACKGROUND_PATH + "/report/inventoryReport";
	}

	@RequestMapping("unqualified_reportUi")
	public String unqualified(Model model){
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/report/unqualifiedReport";
	}

	@RequestMapping("reverseTracing_ReportUi")
	public String listUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/report/reverseTracingReport";
	}

	@RequestMapping("get_sfcinfo")
	public String sfcUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/report/sfc_list";
	}
	
	@RequestMapping("productionRecord_reportUI")
	public String productionRecord_reportUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/report/productionRecord_report/productionRecord_bar";
	}
	
	@RequestMapping("sfc_assemblyInfo_listUI")
	public String sfc_assemblyInfo_listUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/report/sfc_assembly_info_trace/sfc_assemblyInfo_query";
	}
	
	@RequestMapping("get_sfc_assemblyInfo_list")
	public String get_sfc_assemblyInfo_list(Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("res", findByRes());
		String sfc = request.getParameter("sfc");
		String operation = request.getParameter("operation");
		JSONObject json = new JSONObject();
		json.put("sfc", sfc);
		json.put("operation", operation);
		String data = json.toString().replaceAll("\"", "'");
		model.addAttribute("query_form", data);
		return Common.BACKGROUND_PATH + "/report/sfc_assembly_info_trace/sfc_assemblyInfo_list";
	}
	
	
	@RequestMapping("get_productionRecord_list")
	public String get_productionRecord_list(Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("res", findByRes());
		String shoporder = request.getParameter("shoporder");
		String status = request.getParameter("status");
		String SFC = request.getParameter("SFC");
		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
		String batch = request.getParameter("batch");
		JSONObject json = new JSONObject();
		json.put("shoporder", shoporder);
		json.put("status", status);
		json.put("SFC", SFC);
		json.put("start_time", start_time);
		json.put("end_time", end_time);
		json.put("batch", batch);
		String data = json.toString().replaceAll("\"", "'");
		model.addAttribute("query_form", data);
		return Common.BACKGROUND_PATH + "/report/productionRecord_report/productionRecord_list";
	}
	
	@RequestMapping("get_productionRecord_sfc_list")
	public String get_productionRecord_sfc_list(Model model,HttpServletRequest request) throws Exception {
		model.addAttribute("res", findByRes());
		String sfc = request.getParameter("sfc");
		model.addAttribute("sfc", sfc);
		return Common.BACKGROUND_PATH + "/report/productionRecord_report/productionRecord_sfc_list";
	}
	
	@RequestMapping("get_productionRecord_sfc_assembly_list")
	public String get_productionRecord_sfc_Assembly_list(Model model,HttpServletRequest request) throws Exception {
		model.addAttribute("res", findByRes());
		String sfc = request.getParameter("sfc");
		String operation = request.getParameter("operation");
		model.addAttribute("sfc", sfc);
		model.addAttribute("operation", operation);
		return Common.BACKGROUND_PATH + "/report/productionRecord_report/productionRecord_sfc_assembly_list";
	}
	
	@RequestMapping("get_productionRecord_sfc_nc_list")
	public String get_productionRecord_sfc_nc_list(Model model,HttpServletRequest request) throws Exception {
		model.addAttribute("res", findByRes());
		String sfc = request.getParameter("sfc");
		String operation = request.getParameter("operation");
		model.addAttribute("sfc", sfc);
		model.addAttribute("operation", operation);
		return Common.BACKGROUND_PATH + "/report/productionRecord_report/productionRecord_sfc_nc_list";
	}
	/**
	 * 取报表数据方法（公共） 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getReportData")
	public BaseResponse getReportData() throws Exception{
		BaseResponse response = new BaseResponse();
		FormMap<String, ?> formMap = getFormMap(FormMap.class);
		Map<String, Object> map = new HashMap<String, Object>();
		if (formMap!=null && formMap.size()!=0) {
			Set<String> set = formMap.keySet();
			for (String key : set){
				map.put(key, formMap.get(key));
			}
		}
		Session session = SecurityUtils.getSubject().getSession();
		map.put("site", session.getAttribute("site"));
		List<Map<String, Object>> list = null;
		String reportMethod = formMap.getStr("reportMethod");
		if (reportMethod.equals("getTestReportData")) {
			list = reportMapper.getTestReportData(map);
		}
		else if(reportMethod.equals("getScheduleReportData"))
		{
			list = reportMapper.getScheduleReportData(map);
		}
		else if(reportMethod.equals("getShoporderCercle"))
		{
			list = reportMapper.getShoporderCercle(map);
		}
		else if(reportMethod.equals("getInventoryData"))
		{
			list = kanbanMapper.findKanBanData(map);
		}
		else if(reportMethod.equals("getReverseTracingData"))
		{
			list = reportMapper.getReverseTracingData(map);
		}

		else if(reportMethod.equals("getWelcomeData"))
		{
			list = reportMapper.getWelcomeData(map);
		}
		else if(reportMethod.equals("getPercentData"))
		{
			list=reportMapper.getPercentData(map);
		}
		else if(reportMethod.equals("getShopOrderAndNcData"))
		{
			list=reportMapper.getShopOrderAndNcData(map);
		}
		else if(reportMethod.equals("getShopOrderAndCompleteData"))
		{
			list=reportMapper.getShopOrderAndCompleteData(map);
		}

		response.setResult(true);
		response.setData(list);
		response.setMessage("执行成功");
		return response;
	}

	@ResponseBody
	@RequestMapping("getReverseTracingData")
	public PageView findByPage(String pageNow,
			String pageSize) throws Exception {
		pageView = new PageView();
		FormMap<String, ?> formMap = getFormMap(FormMap.class);
		Map<String, Object> map = new HashMap<String, Object>();
		if (formMap!=null && formMap.size()!=0) {
			Set<String> set = formMap.keySet();
			for (String key : set){
				map.put(key, formMap.get(key));
			}
		}
		Session session = SecurityUtils.getSubject().getSession();
		map.put("site", session.getAttribute("site"));
		pageView.setRecords(reportMapper.getReverseTracingData(map));
		return pageView;
	}

	@ResponseBody
	@RequestMapping("getUnqualifiedData")
	public PageView getReverseTracingData(String pageNow,
			String pageSize) throws Exception {
		pageView = new PageView();
		FormMap<String, ?> formMap = getFormMap(FormMap.class);
		Map<String, Object> map = new HashMap<String, Object>();
		if (formMap!=null && formMap.size()!=0) {
			Set<String> set = formMap.keySet();
			for (String key : set){
				map.put(key, formMap.get(key));
			}
		}
		Session session = SecurityUtils.getSubject().getSession();
		map.put("site", session.getAttribute("site"));
		pageView.setRecords(reportMapper.getUnqualifiedData(map));
		return pageView;
	}

	@ResponseBody
	@RequestMapping("getSfcInfoData")
	public PageView getSfcInfoData(String pageNow,
			String pageSize) throws Exception {
		pageView = new PageView();
		FormMap<String, ?> formMap = getFormMap(FormMap.class);
		Map<String, Object> map = new HashMap<String, Object>();
		if (formMap!=null && formMap.size()!=0) {
			Set<String> set = formMap.keySet();
			for (String key : set){
				map.put(key, formMap.get(key));
			}
		}
		Session session = SecurityUtils.getSubject().getSession();
		map.put("site", session.getAttribute("site"));
		pageView.setRecords(reportMapper.getSfcInfoData(map));
		return pageView;
	}
		
	//生产记录表  productionRecord_Report
	@ResponseBody
	@RequestMapping("getProductionRecordListData")
	public PageView getProductionRecordListData(String pageNow,
			String pageSize, HttpServletRequest request) throws Exception {
		String shoporder = request.getParameter("shoporder");
		String status = request.getParameter("status");
		String sfc = request.getParameter("SFC");
		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
		String batch = request.getParameter("batch");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shoporder", StringUtils.isEmpty(shoporder)?"":shoporder);
		map.put("status", StringUtils.isEmpty(status)?"":status);
		map.put("sfc", StringUtils.isEmpty(sfc)?"":sfc);
		map.put("start_time", StringUtils.isEmpty(start_time)?"":start_time);
		map.put("end_time", StringUtils.isEmpty(end_time)?"":end_time);
		map.put("batch", StringUtils.isEmpty(batch)?"":batch);
		map.put("site", ShiroSecurityHelper.getSite());
		
		int page=Integer.parseInt(pageNow==null?"1":pageNow);
		pageView = new PageView(page);

		List<Map<String, Object>> list = reportMapper.getProductionRecordListData(map);
		pageView.setRecords(list);	
		
		LuogicPaging(pageNow,pageSize,list);
		
		return pageView;
	}
	
	//生产记录表  productionRecord_Report_sfc
	@ResponseBody
	@RequestMapping("getProductionRecord_sfcListData")
	public PageView getProductionRecord_sfcListData(String pageNow, String pageSize, HttpServletRequest request) throws Exception {
		String sfc = request.getParameter("SFC");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sfc", sfc);
		map.put("site", ShiroSecurityHelper.getSite());
		
		int page = Integer.parseInt(pageNow == null ? "1" : pageNow);
		pageView = new PageView(page);
		List<Map<String, Object>> list = reportMapper.getProductionRecord_sfcListData(map);
		LuogicPaging(pageNow, pageSize, list);
		return pageView;
	}
	
	//生产记录表  productionRecord_Report_sfc_assembly
	@ResponseBody
	@RequestMapping("getProductionRecord_sfc_assemblyListData")
	public PageView getProductionRecord_sfc_assemblyListData(String pageNow, String pageSize, HttpServletRequest request) throws Exception {
		String sfc = request.getParameter("SFC");
		String operation = request.getParameter("operation");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sfc", sfc);
		map.put("operation", operation);
		map.put("site", ShiroSecurityHelper.getSite());
		
		int page = Integer.parseInt(pageNow == null ? "1" : pageNow);
		pageView = new PageView(page);

		List<Map<String, Object>> list = reportMapper.getProductionRecord_sfc_assemblyListData(map);
		LuogicPaging(pageNow, pageSize, list);
		return pageView;
	}
	
	// 生产记录表 productionRecord_Report_sfc_nc
	@ResponseBody
	@RequestMapping("getProductionRecord_sfc_ncListData")
	public PageView getProductionRecord_sfc_ncListData(String pageNow, String pageSize, HttpServletRequest request) throws Exception {
		String sfc = request.getParameter("SFC");
		String operation = request.getParameter("operation");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sfc", sfc);
		map.put("operation", operation);
		map.put("site", ShiroSecurityHelper.getSite());
		
		int page = Integer.parseInt(pageNow == null ? "1" : pageNow);
		pageView = new PageView(page);

		List<Map<String, Object>> list = reportMapper.getProductionRecord_sfc_ncListData(map);
		LuogicPaging(pageNow, pageSize, list);
		return pageView;
	}
	
	
	//SFC装配信息表  sfc_assembly_info_trace
	@ResponseBody
	@RequestMapping("sfc_assembly_info_listData")
	public PageView sfc_assembly_info_listData(String pageNow, String pageSize, HttpServletRequest request) throws Exception {
		String sfc = request.getParameter("sfc");
		String operation = request.getParameter("operation");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sfc", sfc);
		map.put("operation", operation);
		map.put("site", ShiroSecurityHelper.getSite());
		
		int page = Integer.parseInt(pageNow == null ? "1" : pageNow);
		pageView = new PageView(page);

		List<Map<String, Object>> list = reportMapper.getProductionRecord_sfc_assemblyListData(map);
		LuogicPaging(pageNow, pageSize, list);
		return pageView;
	}

}
