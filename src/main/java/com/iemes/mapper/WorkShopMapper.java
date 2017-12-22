package com.iemes.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iemes.entity.WorkCenterFormMap;
import com.iemes.mapper.base.BaseMapper;

public interface WorkShopMapper extends BaseMapper{
	
	public List<WorkCenterFormMap> getParentWorkCenterMap(@Param("workcenterNo") String workcenterNo);
	
	public List<WorkCenterFormMap> getChildrenWorkCenterMap(@Param("workcenterNo") String workcenterNo);
	
}
