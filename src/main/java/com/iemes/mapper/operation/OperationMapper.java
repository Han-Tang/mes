package com.iemes.mapper.operation;

import java.util.List;
import java.util.Map;

import com.iemes.mapper.base.BaseMapper;

public interface OperationMapper extends BaseMapper {

	/**
	 * 获取所有未被使用的工序
	 * @return
	 */
	public List<Map<String, String>> getOperationWhereNoUse(String site);
}
