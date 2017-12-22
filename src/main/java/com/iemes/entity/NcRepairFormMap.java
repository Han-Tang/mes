package com.iemes.entity;

import com.iemes.annotation.TableSeg;
import com.iemes.util.FormMap;

/**
 * 维修记录表
 * 扩展FormMap
 * @author Administrator
 *
 */
@TableSeg(tableName = "ly_nc_repair", id="id")
public class NcRepairFormMap extends FormMap<String,Object>{

	private static final long serialVersionUID = 1L;

}
