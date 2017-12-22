package com.iemes.service;

import com.iemes.entity.ShopOrderFormMap;

/**
 * 工单下达service
 * @author huahao
 *
 */
public interface ShopOrderLssuedService {

	/**
	 * 工单下达
	 * 1、根据ly_shoporder表中的process_route字段关联ly_route_step表的process_route字段，
	 * 	     再根据ly_route_step表的process_route字段和order==0，找到该工单的工艺路线的首操作operation；
	 * 2、在ly_shoporder_sfc表中插入N条数量，字段status为：0（新建）；
	 * 3、向ly_sfc_step表插入N条数据，操作为2.3步骤中的首操作operation，status（状态）为0（新建）；
	 * 4、将ly_shoporder表中的工单状态status修改为：1（已下达）。
	 * 5、N代表工单生产数量，SFC系统自动生成，需要有相应的功能维护
	 * @param shopOrderFormMap
	 */
	void shopOrderLssued(ShopOrderFormMap shopOrderFormMap)throws Exception;
}
