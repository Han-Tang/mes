package com.iemes.controller.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.iemes.annotation.SystemLog;
import com.iemes.controller.base.BaseResponse;
import com.iemes.controller.base.ConstantController;
import com.iemes.controller.index.BaseController;
import com.iemes.entity.ItemFormMap;
import com.iemes.entity.UserFormMap;
import com.iemes.entity.WorkshopInventoryFormMap;
import com.iemes.plugin.PageView;
import com.iemes.score.GradeCalculateService;
import com.iemes.util.Common;
import com.iemes.util.NumberGenerate;

import net.sf.json.JSONObject;

/**
 * 
 * @author lanyuan 2014-11-19
 * @Email: mmm333zzz520@163.com
 * @version 3.0v
 */
@Controller
@RequestMapping("/workshop_inventory/")
public class WorkshopInventoryController extends BaseController {
	
	@Inject
	private GradeCalculateService gradeCalculateService;
	
	@RequestMapping("workshop_inventory_list")
	public String listUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/workshop_inventory/list";
	}
	
	@ResponseBody
	@RequestMapping("workshop_inventory_findByPage")
	public PageView findByPage(String pageNow,
			String pageSize) throws Exception {
		WorkshopInventoryFormMap workshopInventoryFormMap = getFormMap(WorkshopInventoryFormMap.class);
		workshopInventoryFormMap.put("$orderby", " order by id desc");
		workshopInventoryFormMap=toFormMap(workshopInventoryFormMap, pageNow, pageSize,workshopInventoryFormMap.getStr("orderby"));
		Session session = SecurityUtils.getSubject().getSession();
		workshopInventoryFormMap.put("site", session.getAttribute("site"));
		pageView.setRecords(baseMapper.findByPage(workshopInventoryFormMap));
		pageView.setOrderby(" order by id desc");
		return pageView;
	}
	
	@RequestMapping("workshop_inventory_addUI")
	public String addUI(Model model) throws Exception {
		String batch = NumberGenerate.getItemBatchByDay();
		model.addAttribute("batch", batch);
		return Common.BACKGROUND_PATH + "/workshop_inventory/add";
	}
	
	@ResponseBody
	@RequestMapping("workshop_inventory_addEntity")
	@SystemLog(module="车间库存管理",methods="车间库存接收")//凡需要处理业务逻辑的.都需要记录操作日志
	public String addEntity() throws Exception {
		List<WorkshopInventoryFormMap> list = new ArrayList<WorkshopInventoryFormMap>(); 
		WorkshopInventoryFormMap workshopInventoryFormMap = getFormMap(WorkshopInventoryFormMap.class);
		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		// 通过工具类获取当前登录的bean
		UserFormMap userFormMap = (UserFormMap)Common.findUserSession(request);
		workshopInventoryFormMap.put("by_user", userFormMap.getStr("accountName"));
		workshopInventoryFormMap.put("receive", 1);
		
		String itemSfcs =  workshopInventoryFormMap.getStr("item_sfcs");	//物料SFC
		String item_nos =  workshopInventoryFormMap.getStr("item_nos");		//物料号
		Set<String> set = workshopInventoryFormMap.keySet();
		
		if (!StringUtils.isEmpty(itemSfcs) && !StringUtils.isEmpty(item_nos) ) {
			String[] itemSfcArr = itemSfcs.split(",");
			String[] itemNos = item_nos.split(",");
			
			for (int i=0;i<itemSfcArr.length;i++) {
				WorkshopInventoryFormMap workshopInventoryForm = new WorkshopInventoryFormMap();
				for (String key : set) {
					workshopInventoryForm.put(key, workshopInventoryFormMap.get(key));
				}
				workshopInventoryForm.put("item_sfc", itemSfcArr[i]);
				workshopInventoryForm.put("item", itemNos[i]);
				list.add(workshopInventoryForm);
			}
			baseMapper.batchSave(list);
		}else {
			return "error";
		}
		JSONObject json = new JSONObject();
		String rs = null;
		try {
			rs = gradeCalculateService.GradeCalculate("物料接收", null);
			json.put("result", ConstantController.SUCCESS);
			json.put("score", rs);
		} catch (Exception e) {
			json.put("result", ConstantController.ERROR);
			json.put("message", e.getMessage());
		}
		return json.toString();
	}
	
	/**
	 * 根据物料号获取物料信息，在物料接收时，用户选择一个物料，扫描物料的SFC时，将物料的信息带入到下面的列表，
	 * 最后提交整个列表保存物料接收结果
	 * @param pageNow
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("workshop_inventory_getItemInfo")
	public PageView getItemInfo(String pageNow,
			String pageSize) throws Exception {
		
		String sfc = getPara("sfc");
		String item = getPara("item");
		String site = getPara("site");
		
		ItemFormMap itemFormMap = new ItemFormMap();
		itemFormMap.put("item_no", item);
		itemFormMap.put("site", site);
		itemFormMap.put("sfc", sfc);
		
		itemFormMap = toFormMap(itemFormMap, pageNow, pageSize, itemFormMap.getStr("orderby"));
		List<ItemFormMap> list = baseMapper.findByPage(itemFormMap);
		System.out.println("-------------"+list.size());
		for (ItemFormMap itemForm : list) {
			itemForm.put("sfc", sfc);
		}
		pageView.setRecords(list);
		return pageView;
	}

	@ResponseBody
	@RequestMapping("workshop_inventory_getItemInfo2")
	public BaseResponse getItemInfo2(String pageNow,
			String pageSize) throws Exception {
		BaseResponse response = new BaseResponse();
		
		String item = getPara("item");
		String site = getPara("site");
		
		ItemFormMap itemFormMap = new ItemFormMap();
		itemFormMap.put("item_no", item);
		itemFormMap.put("site", site);
		
		List<ItemFormMap> list = baseMapper.findByNames(itemFormMap);
		ItemFormMap itemForm = list.get(0);
		
		response.setData(itemForm);
		response.setResult(true);
		return response;
	}
}