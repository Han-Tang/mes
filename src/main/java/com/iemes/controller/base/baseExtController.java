package com.iemes.controller.base;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iemes.controller.index.BaseController;
import com.iemes.util.Common;

/**
 * 公共扩展控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/base/")
public class baseExtController extends BaseController {
	
	@RequestMapping("getOperation")
	public String getOperation(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/public/operation_list";
	}
	
	@RequestMapping("getOperation2")
	public String getOperation2(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/public/operation_list2";
	}
	
	@RequestMapping("getResource")
	public String getResource(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/public/resource_list";
	}
	
	@RequestMapping("getPodFunction")
	public String getfPodFunctionNo(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/public/podfunction_list";
	}
	
	@RequestMapping("getWorkOrder")
	public String getWorkOrder(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/public/workorder_list";
	}
	
	//获取未完成订单
	@RequestMapping("getUnFinishedWorkOrder")
	public String getUnFinishedWorkOrder(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/public/unfinish_workshop_list";
	}
	
	@RequestMapping("getfWorkCenter")
	public String getfWorkCenter(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/public/workcenter_list";
	}
	
	@RequestMapping("getfWorkCenterLine")
	public String getfWorkCenterLine(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/public/workcenterLine_list";
	}
	
	@RequestMapping("getfWorkCenterChild")
	public String getfWorkCenterChild(Model model) throws Exception {
		String workcenter = getPara("workcenter");
		model.addAttribute("workcenter", workcenter);
		return Common.BACKGROUND_PATH + "/public/workcenter_child_list";
	}
	
	@RequestMapping("getItemBom")
	public String getItemBom(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/public/bom_list";
	}

	@RequestMapping("getItem")
	public String getItem(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/public/item_list";
	}
	
	@RequestMapping("getItemChild")
	public String getItemChild(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/public/item_child_list";
	}
	
	@RequestMapping("getItemMain")
	public String getItemMain(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/public/item_main_list";
	}
	
	@RequestMapping("getRoute")
	public String getRoute(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/public/route_list";
	}
	
	@RequestMapping("getNcCodeGroup")
	public String getNcCodeGroup(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/public/nc_group_list";
	}
	
	@RequestMapping("getNcCode")
	public String getNcCode(Model model)throws Exception{
		return Common.BACKGROUND_PATH + "/public/nc_code_list";
	}
	
	@RequestMapping("getSite")
	public String getSite(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/public/site_list";
	}
	
	@RequestMapping("getSfc")
	public String getSfc(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/public/sfc_list";
	}
	
	@RequestMapping("getExamInfoList")
	public String getExamInfoList(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/public/exam_list2";
	}
	
	/**
	 * 获取已接收但未使用的SFC列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getSfcByAssemble")
	public String getSfcByAssemble(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		String itemNo = getPara("itemNo");
		model.addAttribute("itemNo", itemNo);
		return Common.BACKGROUND_PATH + "/public/assemble_sfclist";
	}
}
