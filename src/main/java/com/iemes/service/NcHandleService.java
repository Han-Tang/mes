package com.iemes.service;

import com.iemes.entity.NcRepairFormMap;

public interface NcHandleService {

	/**
	 * 不合格处置——报废
	 * @return
	 */
	String NcHandleScrap(NcRepairFormMap ncRepairFormMap)throws Exception;
	
	/**
	 * 不合格处置——维修
	 * @param ncRepairFormMap
	 * @return
	 * @throws Exception
	 */
	String NcHandleRepair(NcRepairFormMap ncRepairFormMap)throws Exception;
}
