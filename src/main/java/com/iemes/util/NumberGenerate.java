package com.iemes.util;

/**
 * 编号获取工具类
 * @author huahao
 *
 */
public class NumberGenerate {

	/**
	 * SFC生产方法
	 * @return
	 */
	public static String getSfcNumberGenerate() {
		StringBuffer rs = new StringBuffer("sfc");
		String date = DateUtils.getDateTimeNumber();
		String random = RandomUitls.getRandom(6);
		rs.append(date);
		rs.append(random);
		return rs.toString();
	}
	
	/**
	 * 返回物料批次号
	 * @return
	 */
	public static String getItemBatchByDay(){
		String date = DateUtils.getStringDate2();
		return "item_batch_"+date;
	}
}
