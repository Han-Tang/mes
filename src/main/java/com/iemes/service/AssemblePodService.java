package com.iemes.service;

import com.iemes.entity.SfcAssemblyFormMap;
import com.iemes.util.FormMap;

public interface AssemblePodService {

	/**
	 * 开始按钮校验
	 * @param formMap
	 * @return
	 */
	String assemblingStartValadata(FormMap<String, String> formMap)throws Exception;
	
	/**
	 * 完成
	 * @param formMap
	 * @return
	 */
	String process_finish(FormMap<String, Object> formMap)throws Exception;
	
	/**
	 * 装配
	 * @param sfcAssemblyFormMap
	 * @return
	 */
	String fit_out(SfcAssemblyFormMap sfcAssemblyFormMap)throws Exception;
	
	/**
	 * 记录不良
	 * @param sfcAssemblyFormMap
	 * @return
	 */
	String recode_nccode(FormMap formMap)throws Exception;
}
