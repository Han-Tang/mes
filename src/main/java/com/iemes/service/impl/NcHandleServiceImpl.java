package com.iemes.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iemes.entity.NcRepairFormMap;
import com.iemes.entity.SfcNcFormMap;
import com.iemes.entity.SfcStepFormMap;
import com.iemes.entity.ShopOrderFormMap;
import com.iemes.entity.ShopOrderSfcFormMap;
import com.iemes.mapper.BaseExtMapper;
import com.iemes.service.NcHandleService;
import com.iemes.util.DateUtils;
import com.iemes.util.ListUtils;
import com.iemes.util.ResponseHelp;
import com.iemes.util.ShiroSecurityHelper;

@Service
public class NcHandleServiceImpl implements NcHandleService {
	
	@Inject
	private BaseExtMapper baseMapper;

	/**
	 * 不合格处置——报废
	 */
	@Transactional
	public String NcHandleScrap(NcRepairFormMap ncRepairFormMap) throws Exception {
		String sfc = ncRepairFormMap.getStr("sfc");
		String operation = ncRepairFormMap.getStr("operation");
		String shoporder = ncRepairFormMap.getStr("shoporder");
		String site = ShiroSecurityHelper.getSite();
		String nc_code = ncRepairFormMap.getStr("nc_code");
		ncRepairFormMap.put("create_time", DateUtils.getStringDateTime());
		ncRepairFormMap.put("by_user", ShiroSecurityHelper.getCurrentUsername());
		
		
		SfcStepFormMap sfcStepFormMap = new SfcStepFormMap(); 
		sfcStepFormMap.put("sfc", sfc);
		sfcStepFormMap.put("operation", operation);
		sfcStepFormMap.put("site", site);
		sfcStepFormMap.put("shoporder", shoporder);
		sfcStepFormMap.put("status", 4);
		List<SfcStepFormMap> sfcStepFormMapList = baseMapper.findByNames(sfcStepFormMap);
		if (!ListUtils.isNotNull(sfcStepFormMapList)) {
			throw new Exception("未找到sfc["+sfc+"]的相关步骤信息");
		}
		sfcStepFormMap = sfcStepFormMapList.get(0);
		sfcStepFormMap.put("status", 3);
		baseMapper.editEntity(sfcStepFormMap);
		
		baseMapper.addEntity(ncRepairFormMap);
		
		ncRepairFormMap.clear();
		ncRepairFormMap.put("where", " where sfc='"+sfc+"'" + 
				" and operation='"+operation+"'" + 
				" and site='"+site+"'" +
				" and shoporder='"+shoporder+"'" +
				" order by id desc"
		);
		List<NcRepairFormMap> ncRepairFormMapList= baseMapper.findByWhere(ncRepairFormMap);
		ncRepairFormMap = ncRepairFormMapList.get(0);
		
		SfcNcFormMap sfcNcFormMap = new SfcNcFormMap();
//		sfcNcFormMap.put("where", " where sfc = '"+sfc + "'" +
//				" and shoporder = '"+shoporder + "'" +
//				" and site = '"+ShiroSecurityHelper.getSite() + "'" + 
//				" and nc_code = '"+nc_code + "'" + 
//				" and status = '"+0 + "'" 
//				);
		sfcNcFormMap.put("sfc", sfc);
		sfcNcFormMap.put("shoporder", shoporder);
		sfcNcFormMap.put("site", ShiroSecurityHelper.getSite());
		sfcNcFormMap.put("nc_code", nc_code);
		sfcNcFormMap.put("status", 0);
		
		
		List<SfcNcFormMap> sfcNcFormMaplist = baseMapper.findByNames(sfcNcFormMap);
		if (!ListUtils.isNotNull(sfcNcFormMaplist)) {
			throw new Exception("未找到sfc["+sfc+"]的不良信息");
		}
		sfcNcFormMap = sfcNcFormMaplist.get(0);
		sfcNcFormMap.put("status", 1);
		sfcNcFormMap.put("repair_type", 1);
		sfcNcFormMap.put("repair_id", ncRepairFormMap.get("id"));
		baseMapper.editEntity(sfcNcFormMap);
		
		ShopOrderSfcFormMap shopOrderSfcFormMap = new ShopOrderSfcFormMap();
		shopOrderSfcFormMap.put("where ", " where site = '"+ShiroSecurityHelper.getSite()+"'" + 
				" and shoporderId = '"+shoporder + "'");
		List<ShopOrderSfcFormMap> shopOrderSfcFormMapList = baseMapper.findByWhere(shopOrderSfcFormMap);
		if (!ListUtils.isNotNull(shopOrderSfcFormMapList)) {
			throw new Exception("未找到工单["+shoporder+"]的详细信息");
		}
		shopOrderSfcFormMap = null;
		boolean over = true;
		for (ShopOrderSfcFormMap formMap : shopOrderSfcFormMapList) {
			String formSfc = formMap.getStr("sfc");
			int status = formMap.getInt("status");
			if (sfc.equals(formSfc)) {
				shopOrderSfcFormMap = formMap;
			}
			if (status!=2 || status!=3) {
				over = false;
			}
		}
		if (shopOrderSfcFormMap==null) {
			throw new Exception("未找到工单["+shoporder+"]的详细信息");
		}
		shopOrderSfcFormMap.put("status", 3);
		baseMapper.editEntity(shopOrderSfcFormMap);
		
		if (over) {
			ShopOrderFormMap shopOrderFormMap = new ShopOrderFormMap();
			shopOrderFormMap.put("where ", " where site = '"+ShiroSecurityHelper.getSite()+"'" + 
					" and shoporder_no = '"+shoporder + "'");
			List<ShopOrderFormMap> shopOrderFormMapList = baseMapper.findByWhere(shopOrderFormMap);
			if (!ListUtils.isNotNull(shopOrderFormMapList)) {
				throw new Exception("未找到工单["+shoporder+"]的详细信息");
			}
			shopOrderFormMap = shopOrderFormMapList.get(0);
			shopOrderFormMap.put("status", 3);
			baseMapper.editEntity(shopOrderFormMap);
		}
		return ResponseHelp.responseText2();
	}

	/**
	 * 不合格品处置——维修
	 * 
	 */
	@Transactional
	public String NcHandleRepair(NcRepairFormMap ncRepairFormMap) throws Exception {
		String sfc = ncRepairFormMap.getStr("sfc");
		String operation = ncRepairFormMap.getStr("operation");
		String operation2 = ncRepairFormMap.getStr("operation2");
		String shoporder = ncRepairFormMap.getStr("shoporder");
		String site = ShiroSecurityHelper.getSite();
		String nc_code = ncRepairFormMap.getStr("nc_code");
		ncRepairFormMap.put("create_time", DateUtils.getStringDateTime());
		ncRepairFormMap.put("by_user", ShiroSecurityHelper.getCurrentUsername());
		
		
		SfcStepFormMap sfcStepFormMap = new SfcStepFormMap();
		sfcStepFormMap.put("sfc", sfc);
		sfcStepFormMap.put("operation", operation);
		sfcStepFormMap.put("site", site);
		sfcStepFormMap.put("shoporder", shoporder);
		sfcStepFormMap.put("status", 4);
		List<SfcStepFormMap> sfcStepFormMapList = baseMapper.findByNames(sfcStepFormMap);
		if (!ListUtils.isNotNull(sfcStepFormMapList)) {
			throw new Exception("未找到sfc["+sfc+"]的相关步骤信息");
		}
		sfcStepFormMap = sfcStepFormMapList.get(0);
		sfcStepFormMap.put("status", 2);
		sfcStepFormMap.put("finishTime", DateUtils.getStringDateTime());
		baseMapper.editEntity(sfcStepFormMap);
		
		baseMapper.addEntity(ncRepairFormMap);
		
		ncRepairFormMap.clear();
		ncRepairFormMap.put("where", " where sfc='"+sfc+"'" + 
				" and operation='"+operation+"'" + 
				" and site='"+site+"'" +
				" and shoporder='"+shoporder+"'"
		);
		List<NcRepairFormMap> ncRepairFormMapList= baseMapper.findByWhere(ncRepairFormMap);
		ncRepairFormMap = ncRepairFormMapList.get(0);
		
		SfcNcFormMap sfcNcFormMap = new SfcNcFormMap();
		sfcNcFormMap.put("sfc", sfc);
		sfcNcFormMap.put("shoporder", shoporder);
		sfcNcFormMap.put(site, ShiroSecurityHelper.getSite());
		sfcNcFormMap.put("nc_code", nc_code);
		sfcNcFormMap.put("status", 0);
		
		List<SfcNcFormMap> sfcNcFormMaplist = baseMapper.findByNames(sfcNcFormMap);
		if (!ListUtils.isNotNull(sfcNcFormMaplist)) {
			throw new Exception("未找到sfc["+sfc+"]的不良信息");
		}
		sfcNcFormMap = sfcNcFormMaplist.get(0);
		sfcNcFormMap.put("status", 1);
		sfcNcFormMap.put("repair_type", 1);
		sfcNcFormMap.put("repair_id", ncRepairFormMap.get("id"));
		baseMapper.editEntity(sfcNcFormMap);
		
		ShopOrderSfcFormMap shopOrderSfcFormMap = new ShopOrderSfcFormMap();
		shopOrderSfcFormMap.put("site", ShiroSecurityHelper.getSite());
		shopOrderSfcFormMap.put("shoporderId", shoporder);
		shopOrderSfcFormMap.put("sfc", sfc);
		shopOrderSfcFormMap.put("status", 4);
		List<ShopOrderSfcFormMap> shopOrderSfcFormMapList = baseMapper.findByNames(shopOrderSfcFormMap);
		if (!ListUtils.isNotNull(shopOrderSfcFormMapList)) {
			throw new Exception("未找到工单["+shoporder+"]的详细信息");
		}
		shopOrderSfcFormMap = shopOrderSfcFormMapList.get(0);
		shopOrderSfcFormMap.put("status", 1);
		baseMapper.editEntity(shopOrderSfcFormMap);
		
		ShopOrderFormMap shopOrderFormMap = new ShopOrderFormMap();
		shopOrderFormMap.put("site", ShiroSecurityHelper.getSite());
		shopOrderFormMap.put("shoporder_no", shoporder);
		List<ShopOrderFormMap> shopOrderFormMapList = baseMapper.findByNames(shopOrderFormMap);
		if (!ListUtils.isNotNull(shopOrderFormMapList)) {
			throw new Exception("未找到工单["+shoporder+"]的详细信息");
		}
		shopOrderFormMap = shopOrderFormMapList.get(0);
		String process_route = shopOrderFormMap.getStr("process_route");
		String workcenter = shopOrderFormMap.getStr("workcenter");
		
		sfcStepFormMap.clear();
		sfcStepFormMap.put("sfc", sfc);
		sfcStepFormMap.put("process_route", process_route);
		sfcStepFormMap.put("status", 0);
		sfcStepFormMap.put("createTime", DateUtils.getStringDateTime());
		sfcStepFormMap.put("byUser", ShiroSecurityHelper.getCurrentUsername());
		sfcStepFormMap.put("workcenter", workcenter);
		sfcStepFormMap.put("operation", operation2);
		sfcStepFormMap.put("site", site);
		sfcStepFormMap.put("shoporder", shoporder);
		baseMapper.addEntity(sfcStepFormMap);
		return ResponseHelp.responseText2();
	}
}
