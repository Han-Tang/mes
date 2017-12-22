package com.iemes.mapper;

import java.util.List;
import java.util.Map;

import com.iemes.mapper.base.BaseMapper;

public interface KanbanMapper extends BaseMapper{
	
	/**
	 * 查询物料消耗信息
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> findKanBanData(Map<String, Object> map);
}
