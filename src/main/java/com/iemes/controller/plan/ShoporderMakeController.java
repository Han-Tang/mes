package com.iemes.controller.plan;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iemes.annotation.SystemLog;
import com.iemes.controller.base.BaseResponse;
import com.iemes.controller.base.ConstantController;
import com.iemes.controller.index.BaseController;
import com.iemes.entity.ShopOrderFormMap;
import com.iemes.score.GradeCalculateService;
import com.iemes.service.ShopOrderLssuedService;
import com.iemes.util.Common;
import com.iemes.util.ResponseHelp;
import com.iemes.util.ShiroSecurityHelper;

/**
 * 
 * 计划订单下达
 */
@Controller
@RequestMapping("/plan/")
public class ShoporderMakeController extends BaseController {

	@Inject
	private GradeCalculateService gradeCalculateService;

	@Inject
	private ShopOrderLssuedService shopOrderLssuedService;

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// 订单下达菜单跳转
	@RequestMapping("shoporder_Issued")
	public String shoporder_IssuedUI(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/product_plan/shoporder_Issued/shoporder_Issued";
	}

	/**
	 * 根据工单号获取工单信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getShoporderInfoByNo")
	public BaseResponse getShoporderInfoByNo(Model model) throws Exception {
		BaseResponse response = new BaseResponse();
		String shopOrderNo = getPara("shoporder_no");
		ShopOrderFormMap shopOrderFormMap = baseMapper.findbyFrist("shoporder_no", shopOrderNo, ShopOrderFormMap.class);
		Date startTime = shopOrderFormMap.getDate("shoporder_start_date");
		Date endTime = shopOrderFormMap.getDate("shoporder_end_date");
		shopOrderFormMap.put("shoporder_start_date", simpleDateFormat.format(startTime));
		shopOrderFormMap.put("shoporder_end_date", simpleDateFormat.format(endTime));
		response.setData(shopOrderFormMap);
		response.setResult(true);
		return response;
	}

	/**
	 * 工单下达
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("shoporderIssued")
	@SystemLog(module = "生产计划管理", methods = "订单下达-订单下达功能") // 凡需要处理业务逻辑的.都需要记录操作日志
	public String shoporderIssued(Model model) {
		ShopOrderFormMap shopOrderFormMap = getFormMap(ShopOrderFormMap.class);

		try {
			shopOrderLssuedService.shopOrderLssued(shopOrderFormMap);
			String rs = gradeCalculateService.GradeCalculate("计划工单下达", null);
			ResponseHelp.setScore(rs);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHelp.responseErrorText(ConstantController.ERROR);
		}
		return ResponseHelp.responseText();
	}

	/**
	 * 工单是否下达
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping("isLssued")
	@ResponseBody
	public boolean isLssued(String name) {
		String shoporderNo = getPara("shoporderNo");
		
		ShopOrderFormMap shopOrderFormMap = new ShopOrderFormMap();
		shopOrderFormMap.put("shoporder_no", shoporderNo);
		shopOrderFormMap.put("site", ShiroSecurityHelper.getSite());
		shopOrderFormMap.put("status", 1);
		List<ShopOrderFormMap> list = baseMapper.findByNames(shopOrderFormMap);
		if (list == null || list.size() <= 0) {
			return false;
		}
		return true;
	}
}