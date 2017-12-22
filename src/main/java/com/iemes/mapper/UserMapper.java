package com.iemes.mapper;

import java.util.List;

import com.iemes.entity.UserFormMap;
import com.iemes.mapper.base.BaseMapper;


public interface UserMapper extends BaseMapper{

	public List<UserFormMap> findUserPage(UserFormMap userFormMap);
	
}
