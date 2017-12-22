package com.iemes.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

/**
 * 日期工具
 * @author huahao
 *
 */
public class DateUtils {

	static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	static SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyyMMdd");
	
	static SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy_MM_dd");
	
	static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	
	static SimpleDateFormat numberFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	
	/**
	 * 返回当前时间，格式为yyyyMMddHHmmss
	 * @return
	 */
	public static String getDateTimeNumber() {
		return numberFormat.format(new Date());
	}
	
	/**
	 * 字符串转日期时间
	 * @param date
	 * @return
	 */
	public static Date stringToDateTime(String date){
		if (!StringUtils.isEmpty(date)) {
			try {
				return format.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	
	/**
	 * 字符串转日期
	 * @param date
	 * @return
	 */
	public static Date stringToDate(String date){
		if (!StringUtils.isEmpty(date)) {
			try {
				return dateFormat.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	
	/**
	 * 字符串转时间
	 * @param date
	 * @return
	 */
	public static Date stringToTime(String date){
		if (!StringUtils.isEmpty(date)) {
			try {
				return timeFormat.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	
	/**
	 * 字符串转日期
	 * @param date
	 * @param format	自定义日期格式
	 * @return
	 */
	public static Date stringToDate(String date,String format){
		if (!StringUtils.isEmpty(date)) {
			try {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
				return simpleDateFormat.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	
	/**
	 * 获取当前日期时间
	 * @return
	 */
	public static Date getDateTime(){
		Date date = null;
		try {
			date = format.parse(format.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 获取当前日期
	 * @return yyyy-MM-dd
	 */
	public static Date getDate(){
		Date date = null;
		try {
			date = dateFormat.parse(dateFormat.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 获取当前时间
	 * @return HH:mm:ss
	 */
	public static Date getTime(){
		Date date = null;
		try {
			date = timeFormat.parse(timeFormat.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 获取字符串类型的日期时间
	 * @return
	 */
	public static String getStringDateTime() {
		return format.format(new Date());
	}
	
	/**
	 * 获取字符串类型的时间
	 * @return
	 */
	public static String getStringTime() {
		return timeFormat.format(new Date());
	}
	
	/**
	 * 获取字符串类型的日期
	 * @return
	 */
	public static String getStringDate() {
		return dateFormat.format(new Date());
	}
	
	/**
	 * 获取字符串类型的日期
	 * @return
	 */
	public static String getStringDate2() {
		return dateFormat2.format(new Date());
	}
}
