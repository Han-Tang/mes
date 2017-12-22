package com.iemes.mapper;

import java.util.List;
import java.util.Map;

import com.iemes.entity.PodButtonFormMap;
import com.iemes.mapper.base.BaseMapper;

public interface BaseExtMapper extends BaseMapper{
	
	/**
	 * 动态Map查询
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getDataByMap(Map<String, String> map)throws Exception;
	
	/**
	 * 查询物料表
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getItemsByShopOrder(Map<String, String> pars)throws Exception;
	
	/**
	 * 查询podbutton表
	 * @param map
	 * @return
	 */
	public  List<PodButtonFormMap> selectOperationPod(PodButtonFormMap map);
	
	/**
	 * 根据工单、站点查找首操作
	 * @param map
	 */
	public List<Map<String, Object>> getFirstOperationByShopOrderAndSite(Map<String, Object> map);
	
	/**
	 * 根据sfc查询不合格产品信息
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> findNCBySFC(Map<String, Object> map);
	
	/**
	 * 根据sfc查询  车间 产线 操作 nc_code,nc_code_group 
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> findNCRepairBySFC(Map<String, Object> map);
	
	/**
	 * 查询学生考试成绩
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> findScore(Map<String, Object> map);
	
	/**
	 * 获取已接收但未使用的SFC列表
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getAssembleSfcList(Map<String, Object> map);
	
	/**
	 *   根据名称在默认站点 查询出pod按钮对应的id
	 * @param podBtnName
	 * @return
	 */
	public String findPodButtonIdByName(Map<String, Object> map);
	
	/**
	 * 根据sfc查询对应的所有工艺步骤
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> findAllOperationBySfc(Map<String, Object> map);
}
