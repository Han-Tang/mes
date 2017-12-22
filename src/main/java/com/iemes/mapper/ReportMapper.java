package com.iemes.mapper;

import java.util.List;
import java.util.Map;

import com.iemes.mapper.base.BaseMapper;

public interface ReportMapper extends BaseMapper{

	/**
	 * 获取测试报表数据
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getTestReportData(Map<String, Object> map)throws Exception;
	public List<Map<String, Object>> getScheduleReportData(Map<String, Object> map)throws Exception;
	public List<Map<String, Object>> getShoporderCercle(Map<String, Object> map)throws Exception;
	//public List<Map<String, Object>> getInventoryData(Map<String, Object> map)throws Exception;
	public List<Map<String, Object>> getReverseTracingData(Map<String, Object> map)throws Exception;
	public List<Map<String, Object>> getUnqualifiedData(Map<String, Object> map)throws Exception;
	public List<Map<String, Object>> getWelcomeData(Map<String, Object> map)throws Exception;
	public List<Map<String, Object>> getPercentData(Map<String, Object> map)throws Exception;
	
	
	/**
	 * 获取SFC信息 资源
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getSfcInfoData(Map<String,Object> map)throws Exception;
	
	
	/**
	 * 获取产能报表的数据  资源
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getCapacityReportDataByResource(Map<String,Object> map)throws Exception;
	
	/**
	 * 获取产能报表的数据  车间
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getCapacityReportDataByWorkcenter(Map<String,Object> map)throws Exception;
	
	/**
	 * 获取产能报表的数据 产线
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getCapacityReportDataByWorkcenterLine(Map<String,Object> map)throws Exception;
	
	/**
	 * 正向追溯报表的数据
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getForwardTracingReportData(Map<String,String> map)throws Exception;
	
	/**
	 * 获取产品的sfc
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String ,Object>> getSFCData(Map<String,String> map)throws Exception;
	
	/**
	 *  获取所有的工单和不良
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getShopOrderAndNcData(Map<String, Object> map);
	
	/**
	 *  获取所有的工单和完成
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getShopOrderAndCompleteData(Map<String, Object> map);
	
	/**
	 * 获取生产记录信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getProductionRecordListData(Map<String,Object> map)throws Exception;
	
	/**
	 * 获取生产记录sfc信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getProductionRecord_sfcListData(Map<String,Object> map)throws Exception;
	
	/**
	 * 获取生产记录sfc_assembly信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getProductionRecord_sfc_assemblyListData(Map<String,Object> map)throws Exception;
	
	/**
	 * 获取生产记录sfc_assembly信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getProductionRecord_sfc_ncListData(Map<String,Object> map)throws Exception;
	
}
