package com.iemes.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iemes.entity.RoutingStepFormMap;
import com.iemes.entity.SfcStepFormMap;
import com.iemes.entity.ShopOrderFormMap;
import com.iemes.entity.ShopOrderSfcFormMap;
import com.iemes.mapper.base.BaseMapper;
import com.iemes.service.ShopOrderLssuedService;
import com.iemes.util.DateUtils;
import com.iemes.util.NumberGenerate;
import com.iemes.util.ShiroSecurityHelper;

@Service
public class ShopOrderLssuedServiceImpl implements ShopOrderLssuedService {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Inject
	protected BaseMapper baseMapper;

	/**
	 * 工单下达
	 */
	@Transactional
	public void shopOrderLssued(ShopOrderFormMap shopOrderFormMap) throws Exception {
		log.info(">>> 进入工单下达方法");
		
		String process_route = shopOrderFormMap.getStr("process_route");
		String shoporder_no = shopOrderFormMap.getStr("workorderNo");
		String workline = shopOrderFormMap.getStr("workline");
		int shoporder_numbers = Integer.parseInt(shopOrderFormMap.getStr("shoporder_numbers"));
		
		
		//1、根据ly_shoporder表中的process_route字段关联ly_route_step表的process_route字段，
		//再根据ly_route_step表的process_route字段和order==0，找到该工单的工艺路线的首操作operation；
		RoutingStepFormMap routingStepFormMap = new RoutingStepFormMap();
		routingStepFormMap.put("process_route", process_route);
		routingStepFormMap.put("order", 0);
		List<RoutingStepFormMap> routingStepFormMapList = baseMapper.findByNames(routingStepFormMap);
		if (routingStepFormMapList==null || routingStepFormMapList.size()<=0) {
			throw new Exception("当前工单下的工艺路线为空！！！！");
		}
		routingStepFormMap = routingStepFormMapList.get(0);
		
		
		//2、在ly_shoporder_sfc表中插入N条数量，字段status为：0（新建）；
		//3、向ly_sfc_step表插入N条数据，操作为2.3步骤中的首操作operation，status（状态）为0（新建）；
		List<ShopOrderSfcFormMap> shopOrderSfcFormMapList = new ArrayList<ShopOrderSfcFormMap>();
		List<SfcStepFormMap> sfcStepFormMapList = new ArrayList<SfcStepFormMap>();
		for (int i=0;i<shoporder_numbers;i++) {
			String sfc = NumberGenerate.getSfcNumberGenerate();
			ShopOrderSfcFormMap shopOrderSfcFormMap = new ShopOrderSfcFormMap();
			shopOrderSfcFormMap.put("shoporderId", shoporder_no);
			shopOrderSfcFormMap.put("sfc", sfc);
			shopOrderSfcFormMap.put("status", 0);
			shopOrderSfcFormMap.put("create_time", DateUtils.getStringDateTime());
			shopOrderSfcFormMap.put("site", ShiroSecurityHelper.getSite());
			shopOrderSfcFormMapList.add(shopOrderSfcFormMap);
			
			
			SfcStepFormMap sfcStepFormMap = new SfcStepFormMap();
			sfcStepFormMap.put("sfc", sfc);
			sfcStepFormMap.put("workcenter", workline);
			sfcStepFormMap.put("process_route", process_route);
			sfcStepFormMap.put("operation", routingStepFormMap.getStr("operation"));
			sfcStepFormMap.put("status", "0");
			sfcStepFormMap.put("createTime", DateUtils.getStringDateTime());
			sfcStepFormMap.put("byUser", ShiroSecurityHelper.getCurrentUsername());
			sfcStepFormMap.put("shoporder", shoporder_no);
			sfcStepFormMap.put("site", ShiroSecurityHelper.getSite());
			sfcStepFormMapList.add(sfcStepFormMap);
		}
		baseMapper.batchSave(shopOrderSfcFormMapList);
		baseMapper.batchSave(sfcStepFormMapList);
		
		//4、将ly_shoporder表中的工单状态status修改为：1（已下达）。
		shopOrderFormMap.put("status", "1");
		baseMapper.editEntity(shopOrderFormMap);
	}

}
