package com.iemes.mapper;

import java.util.List;

import com.iemes.entity.RoleFormMap;
import com.iemes.mapper.base.BaseMapper;

public interface RoleMapper extends BaseMapper{
	public List<RoleFormMap> seletUserRole(RoleFormMap map);
	
	public void deleteById(RoleFormMap map);
}
