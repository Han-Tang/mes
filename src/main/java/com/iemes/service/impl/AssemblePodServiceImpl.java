package com.iemes.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.iemes.entity.RoutingStepFormMap;
import com.iemes.entity.SfcAssemblyFormMap;
import com.iemes.entity.SfcNcFormMap;
import com.iemes.entity.SfcStepFormMap;
import com.iemes.entity.ShopOrderFormMap;
import com.iemes.entity.ShopOrderSfcFormMap;
import com.iemes.mapper.BaseExtMapper;
import com.iemes.service.AssemblePodService;
import com.iemes.util.DateUtils;
import com.iemes.util.FormMap;
import com.iemes.util.ListUtils;
import com.iemes.util.NumberGenerate;
import com.iemes.util.ResponseHelp;
import com.iemes.util.ShiroSecurityHelper;

@Service
public class AssemblePodServiceImpl implements AssemblePodService {
	
	@Inject
	protected BaseExtMapper baseMapper;
	
	protected Logger log = Logger.getLogger(this.getClass());

	/**
	 * step1、根据SFC、当前operation、当前工单、当前站点去ly_sfc_step表查询对应的SFC记录
	 * 		如果不存在记录，则提示“该SFC不在当前操作上排队！！！”。
	 * 		如果存在，则判断该记录的status是否为0（新建），
	 * 		如果不为0，提示“当前状态为***，不能进行开始操作！！！”。
	 * step2、如果为0，更新ly_sfc_step表中该条记录的状态为：1（生产中）；
	 * 		更新ly_shoporder_sfc表的status为：1（生产中）；
	 * 		更新ly_shoporder表中的status为：1（生产中）。
	 */
	@Transactional
	public String assemblingStartValadata(FormMap<String, String> formMap) throws Exception {
		log.info(">>> 进入《开始按钮》校验方法");
		
		String sfc = formMap.getStr("wc_sfc");
		String operation = formMap.getStr("operation");
		String shopOrder = formMap.getStr("workorderNo");
		
		//step1
		SfcStepFormMap sfcStepFormMap = new SfcStepFormMap();
		sfcStepFormMap.put("operation", operation);
		sfcStepFormMap.put("sfc", sfc);
		sfcStepFormMap.put("site", ShiroSecurityHelper.getSite());
		sfcStepFormMap.put("shoporder", shopOrder);
		List<SfcStepFormMap> sfcStepFormMapList = baseMapper.findByNames(sfcStepFormMap);
		
		if (!ListUtils.isNotNull(sfcStepFormMapList)) {
			throw new Exception("该SFC不在当前操作上排队！！！");
		}
		
		sfcStepFormMap = null;
		for (SfcStepFormMap sfcStep : sfcStepFormMapList) {
			if ("0".equals(sfcStep.getStr("status")) || "1".equals(sfcStep.getStr("status"))) {
				sfcStepFormMap = sfcStep;
			}
		}
		
		if (sfcStepFormMap==null) {
			throw new Exception("当前SFC状态不为排队中，不能进行开始操作！！！");
		}
		
		//step2
		sfcStepFormMap.put("status", "1");
		baseMapper.editEntity(sfcStepFormMap);
		
		ShopOrderSfcFormMap shopOrderSfcFormMap = new ShopOrderSfcFormMap();
		shopOrderSfcFormMap.put("site", ShiroSecurityHelper.getSite());
		shopOrderSfcFormMap.put("shoporderId", shopOrder);
		shopOrderSfcFormMap.put("sfc", sfc);
		List<ShopOrderSfcFormMap> shopOrderSfcFormMapList = baseMapper.findByNames(shopOrderSfcFormMap);
		if (!ListUtils.isNotNull(shopOrderSfcFormMapList)) {
			throw new Exception("数据异常，未查询到该sfc["+sfc+"]相关信息！！！");
		}
		shopOrderSfcFormMap = shopOrderSfcFormMapList.get(0);
		shopOrderSfcFormMap.put("status", 1);
		baseMapper.editEntity(shopOrderSfcFormMap);
		
		ShopOrderFormMap shopOrderFormMap = new ShopOrderFormMap();
		shopOrderFormMap.put("site", ShiroSecurityHelper.getSite());
		shopOrderSfcFormMap.put("shoporder_no", shopOrder);
		List<ShopOrderFormMap> shopOrderFormMapList = baseMapper.findByNames(shopOrderFormMap);
		if (!ListUtils.isNotNull(shopOrderFormMapList)) {
			throw new Exception("数据异常，未查询到该工单["+shopOrder+"]相关信息！！！");
		}
		shopOrderFormMap = shopOrderFormMapList.get(0);
		shopOrderFormMap.put("status", 2);
		baseMapper.editEntity(shopOrderFormMap);
		return ResponseHelp.responseText2();
	}

	/**
	 * 
	 * step1、根据SFC、当前operation、站点、工单去ly_sfc_step表查询对应的SFC记录。
	 * 		如果不存在记录，则提示“该SFC不在当前操作上生产！！！”。
	 *		如果存在，则判断该记录的status是否为1（生产中），
	 *			如果不为1，提示“当前状态不为生产中，不能进行完成操作！！！”。
	 * step2、	如果为1，更新ly_sfc_step表中该条记录的状态为：2（已完成）,
	 * 				根据前一步的记录中的process_route关联ly_route_step表中process_route，找到对应的步骤记录；
	 * 				再根据operation查找next_operation是否存在；
	 *			如果不存在，则表示该工艺路线已经完成，则更新ly_shoporder_sfc表的status为：2（已完成）。
	 * 				然后根据ly_sfc_step表中的shoporder字段关联ly_shoporder_sfc表的shoporderId字段，查找ly_shoporder_sfc表中对应该工单的status（状态）是否都为2（已完成）或者3（报废），
	 * 				如果是，修改ly_shoporder表的status为3（已完成）；如果否则不作操作。
	 * step3、	如果存在，则根据得到的next_operation向ly_sfc_step表插入一条记录，该记录的operation为next_operation的值，status（状态）为0（创建）；
	 */
	@Transactional
	public String process_finish(FormMap<String, Object> formMap) throws Exception {
		log.info(">>> 进入《完成按钮》处理方法中");
		
		String sfc = formMap.getStr("wc_sfc");
		String shoporder = formMap.getStr("workorderNo");
		String operation = formMap.getStr("operation");
		
		//step1
		SfcStepFormMap sfcStepFormMap = new SfcStepFormMap();
		sfcStepFormMap.put("operation", operation);
		sfcStepFormMap.put("sfc", sfc);
		sfcStepFormMap.put("site", ShiroSecurityHelper.getSite());
		sfcStepFormMap.put("shoporder", shoporder);
		sfcStepFormMap.put("status", 1);
		List<SfcStepFormMap> sfcStepFormMapList = baseMapper.findByNames(sfcStepFormMap);
		
		ShopOrderFormMap shopOrderFormMap = new ShopOrderFormMap();
		shopOrderFormMap.put("where", " where shoporder_no='"+shoporder+"' and site = '"+ShiroSecurityHelper.getSite()+"'");
		List<ShopOrderFormMap> shopOrderFormMapList = baseMapper.findByWhere(shopOrderFormMap);
		if (!ListUtils.isNotNull(shopOrderFormMapList)) {
			throw new Exception("未找到当前工单["+shoporder+"]的详细信息！！！");
		}
		shopOrderFormMap = shopOrderFormMapList.get(0);
		String workline = shopOrderFormMap.getStr("workline");
		
		if (!ListUtils.isNotNull(sfcStepFormMapList)) {
			throw new Exception("该SFC不在当前操作上生产！！！");
		}
		
		sfcStepFormMap = sfcStepFormMapList.get(0);
		if (!"1".equals(sfcStepFormMap.getStr("status"))) {
			throw new Exception("当前SFC状态不为生产中，不能进行完成操作！！！");
		}
		
		//step2
		sfcStepFormMap.put("status", "2");
		sfcStepFormMap.put("finishTime", DateUtils.getStringDateTime());
		baseMapper.editEntity(sfcStepFormMap);
		
		String process_route = shopOrderFormMap.getStr("process_route");
		RoutingStepFormMap routingStepFormMap = new RoutingStepFormMap();
		routingStepFormMap.put("process_route", process_route);
		routingStepFormMap.put("site", ShiroSecurityHelper.getSite());
		List<RoutingStepFormMap> routingStepFormMapList = baseMapper.findByNames(routingStepFormMap);
		if (!ListUtils.isNotNull(routingStepFormMapList)) {
			throw new Exception("未找到当前操作["+operation+"]的工艺步骤信息！！！");
		}
		
		String next_operation = null;
		String next_operation2 = null;
		for (RoutingStepFormMap formap :routingStepFormMapList) {
			if (operation.equals(formap.getStr("operation"))) {
				next_operation = formap.getStr("next_operation");
			}
			if (operation.equals(formap.getStr("next_operation"))) {
				next_operation2 = formap.getStr("next_operation");
			}
		}
		if (StringUtils.isEmpty(next_operation) && StringUtils.isEmpty(next_operation2)) {
			throw new Exception("未找到当前操作["+operation+"]的工艺步骤信息！！！");
		}
		if (StringUtils.isEmpty(next_operation)) {
			//最后一个步骤了
			
			ShopOrderSfcFormMap shopOrderSfcFormMap = new ShopOrderSfcFormMap(); 
			shopOrderSfcFormMap.put("shoporderId", shoporder);
			shopOrderSfcFormMap.put("site", ShiroSecurityHelper.getSite());
			List<ShopOrderSfcFormMap> shopOrderSfcFormMapList = baseMapper.findByWhere(shopOrderSfcFormMap);
			if (!ListUtils.isNotNull(shopOrderSfcFormMapList)) {
				throw new Exception("未找到当前SFC["+sfc+"]的详细信息！！！");
			}
			
			shopOrderSfcFormMap = null;
			boolean isOver = true;
			for (ShopOrderSfcFormMap formap : shopOrderSfcFormMapList) {
				int status = formap.getInt("status");
				if (status!=2 && status!=3) {
					isOver = false;
				}
				if (sfc.equals(formap.getStr("sfc"))) {
					shopOrderSfcFormMap = formap;
				}
			}
			if (shopOrderSfcFormMap==null) {
				throw new Exception("未找到当前SFC["+sfc+"]的详细信息！！！");
			}
			shopOrderSfcFormMap.put("status", 2);
			shopOrderSfcFormMap.put("finish_time", DateUtils.getStringDateTime());
			baseMapper.editEntity(shopOrderSfcFormMap);
			
			if (isOver) {
				shopOrderFormMap.put("status", 3);
				baseMapper.editEntity(shopOrderFormMap);
			}
			
		}else {
			//step3   不是最后一个步骤
			sfcStepFormMap.clear();
			sfcStepFormMap.put("sfc", sfc);
			sfcStepFormMap.put("process_route", process_route);
			sfcStepFormMap.put("operation", next_operation);
			sfcStepFormMap.put("status", "0");
			sfcStepFormMap.put("createTime", DateUtils.getStringDateTime());
			sfcStepFormMap.put("byUser", ShiroSecurityHelper.getCurrentUsername());
			sfcStepFormMap.put("shoporder", shoporder);
			sfcStepFormMap.put("site", ShiroSecurityHelper.getSite());
			sfcStepFormMap.put("workcenter", workline);
			baseMapper.addEntity(sfcStepFormMap);
		}
		return ResponseHelp.responseText2();
	}

	@Transactional
	public String fit_out(SfcAssemblyFormMap sfcAssemblyFormMap) throws Exception{
		String item_sfc = sfcAssemblyFormMap.getStr("item_sfc");
		String item_no = sfcAssemblyFormMap.getStr("item");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("site", ShiroSecurityHelper.getSite());
		map.put("itemNo", item_no);
		List<Map<String, Object>> itemList = baseMapper.getAssembleSfcList(map);
		
		boolean result = false;
		for (Map<String, Object> item : itemList) {
			if (item_sfc.equals(item.get("item_sfc"))) {
				result = true;
			}
		}
		if (!result) {
			throw new Exception("物料SFC："+item_sfc+"不属于"+item_no+"物料或已使用！！！");
		}
		
		sfcAssemblyFormMap.put("by_user", ShiroSecurityHelper.getCurrentUsername());
		sfcAssemblyFormMap.put("batch", NumberGenerate.getItemBatchByDay());
		baseMapper.addEntity(sfcAssemblyFormMap);
		return ResponseHelp.responseText2();
	}

	/**
	 * step1、根据不良代码和SFC信息、status为0的值关联ly_sfc_nc表中的nc_code和sfc字段，判断是否存在。
	 * 			如果存在，则提示：“该不良信息已经被记录，无需重复记录！！！”
	 * step2、	如果不存在，则不良代码和SFC信息插入到ly_sfc_nc表中，status为0（打开），并提示记录成功。
	 * 			同时根据扫描的sfc和当前operation关联ly_sfc_step表的sfc和operation字段，得到记录后修改该记录的status为4（记录不良）。
	 * 			根据扫描的sfc关联ly_shoporder_sfc表的sfc字段，得到该记录后修改status为4（记录不良）。
	 * @param formMap
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public String recode_nccode(FormMap formMap)throws Exception {
		String sfc = formMap.getStr("wc_sfc");
		String operation = formMap.getStr("operation");
		String nc_code = formMap.getStr("nc_code_no");
		String shoporder = formMap.getStr("workorderNo");
		String resource = formMap.getStr("resource");
		String nc_code_group = formMap.getStr("nc_code_group_no");
		
		
		SfcNcFormMap sfcNcFormMap = new SfcNcFormMap();
		sfcNcFormMap.put("nc_code", nc_code);
		sfcNcFormMap.put("sfc", sfc);
		sfcNcFormMap.put("status", 0);
		List<SfcNcFormMap> sfcNcFormMapList = baseMapper.findByNames(sfcNcFormMap);
		if (ListUtils.isNotNull(sfcNcFormMapList)) {
			throw new Exception("该不良信息已经被记录，无需重复记录！！！");
		}
		
		
		sfcNcFormMap.clear();
		sfcNcFormMap.set("sfc", sfc);
		sfcNcFormMap.set("site", ShiroSecurityHelper.getSite());
		sfcNcFormMap.set("operation", operation);
		sfcNcFormMap.set("nc_code", nc_code);
		sfcNcFormMap.set("by_user", ShiroSecurityHelper.getCurrentUsername());
		sfcNcFormMap.set("create_time", DateUtils.getStringDateTime());
		sfcNcFormMap.set("shoporder", shoporder);
		sfcNcFormMap.set("resource", resource);
		sfcNcFormMap.set("status", 0);
		sfcNcFormMap.set("nc_code_group", nc_code_group);
		//保存记录不良信息
		baseMapper.addEntity(sfcNcFormMap);
		
		
		SfcStepFormMap sfcStepFormMap = new SfcStepFormMap();
		sfcStepFormMap.put("sfc", sfc);
		sfcStepFormMap.put("operation", operation);
		sfcStepFormMap.put("shoporder", shoporder);
		sfcStepFormMap.put("site", ShiroSecurityHelper.getSite());
		sfcStepFormMap.put("status", 1);
		List<SfcStepFormMap> sfcStepFormMapList = baseMapper.findByNames(sfcStepFormMap);
		if (!ListUtils.isNotNull(sfcStepFormMapList)) {
			throw new Exception("未找到"+sfc+"在操作"+operation+"上相关的步骤信息");
		}
		SfcStepFormMap SfcStepForm = sfcStepFormMapList.get(0);
		SfcStepForm.put("status", "4");
		baseMapper.editEntity(SfcStepForm);
		
		ShopOrderSfcFormMap shopOrderSfcFormMap = new ShopOrderSfcFormMap();
		shopOrderSfcFormMap.put("sfc", sfc);
		shopOrderSfcFormMap.put("site", ShiroSecurityHelper.getSite());
		shopOrderSfcFormMap.put("shoporderId", shoporder);
		
		List<ShopOrderSfcFormMap> shopOrderSfcFormMapList = baseMapper.findByNames(shopOrderSfcFormMap);
		if (!ListUtils.isNotNull(shopOrderSfcFormMapList)) {
			throw new Exception("未找到相关的sfc["+sfc+"]的详细信息");
		}
		
		shopOrderSfcFormMap = shopOrderSfcFormMapList.get(0);
		shopOrderSfcFormMap.put("status", 4);
		baseMapper.editEntity(shopOrderSfcFormMap);
		return ResponseHelp.responseText2();
	}

}
