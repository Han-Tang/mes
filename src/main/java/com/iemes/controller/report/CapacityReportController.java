package com.iemes.controller.report;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iemes.controller.base.BaseResponse;
import com.iemes.controller.index.BaseController;
import com.iemes.entity.BaseExtFormMap;
import com.iemes.mapper.ReportMapper;
import com.iemes.plugin.PageView;
import com.iemes.util.Common;
import com.iemes.util.FormMap;

@Controller
@RequestMapping("/capacity_report/")
public class CapacityReportController extends BaseController{
	
	@Inject
	private ReportMapper reportMapper;
	
	/**
	 * 产能报表跳转方法
	 * @return
	 */
	@RequestMapping("capacityReportUi")
	public String capacityReportUi(){
		return Common.BACKGROUND_PATH + "/report/capacity_report/capacity_bar";
	}
	
	/**
	 * 取报表数据方法（产能报表）
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
		
		//判断条件是车间or产线or资源
		if (!Common.isEmpty(formMap.getStr("resource"))){
			//资源
			list = reportMapper.getCapacityReportDataByResource(map);	
		}else if (!Common.isEmpty(formMap.getStr("workcenter2"))) {
			//产线
			list = reportMapper.getCapacityReportDataByWorkcenterLine(map);
		}else {
			//车间
			list = reportMapper.getCapacityReportDataByWorkcenter(map);
		}	
		
		response.setResult(true);
		response.setData(list);
		response.setMessage("执行成功");
		return response;
	}

	@RequestMapping("forwardTracingReportUI")
	public String forwardTracingReportUi(){
		return Common.BACKGROUND_PATH + "/report/forward_tracing_report/forward_tracing_bar";
	}

	@ResponseBody
	@RequestMapping("get_sfc_findByPage")
	public PageView get_sfc_findByPage(String pageNow,String pageSize) throws Exception{
		
		//查询产品sfc
		Map<String, String> map = new HashMap<String, String>();
		Session session = SecurityUtils.getSubject().getSession();
		map.put("site", session.getAttribute("site").toString());
	
		List<Map<String, Object>> data = null;
		try {
			data = reportMapper.getSFCData(map);
		} catch (Exception e) {
			System.out.println(e);
		}
		pageView = getPageView(pageNow, pageSize, getPara("orderby"));
		pageView.setQueryResult(data.size(), data);
		return pageView;
	}
	
	/**
	 * 取报表数据方法（正向追溯报表）Forward tracing
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("forward_tracing_findByPage")
	public PageView forward_tracing_findByPage(String pageNow,String pageSize) throws Exception{
		
		String cp_sfc = getPara("SFC");
		
		//根据站点 和 成品物料 去查询 正向追溯报表数据
		Map<String, String> map = new HashMap<String, String>();
		Session session = SecurityUtils.getSubject().getSession();
		map.put("site", session.getAttribute("site").toString());
		map.put("sfc", cp_sfc);
		
		List<Map<String, Object>> data = null;
		try {
			data = reportMapper.getForwardTracingReportData(map);
		} catch (Exception e) {
			System.out.println(e);
		}
		pageView = getPageView(pageNow, pageSize, getPara("orderby"));
		pageView.setQueryResult(data.size(), data);
		return pageView;
	}

	/**
	 * 正向追溯报表list页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("forward_tracing_list")
	public String forward_tracing_list(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/report/forward_tracing_report/forward_tracing_list";
	}
}
