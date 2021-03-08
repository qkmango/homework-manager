package cn.qkmango.hm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version 1.0
 * <p>DateTimeUtil</p>
 * <p>日期时间工具类</p>
 * @className User
 * @author: Mango
 * @date: 2021-02-21 14:05
 */

public class DateTimeUtil {

	/**
	 * 获取系统日期时间字符串，19位长度
	 * @return 返回系统当前日期时间的字符串
	 */
	public static String getSysDateTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String dateStr = sdf.format(date);
		return dateStr;
	}

	/**
	 * 获取系统日期字符串，10位长度
	 * @return 返回系统当前日期的字符串
	 */
	public static String getSysDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String dateStr = sdf.format(date);
		return dateStr;
	}
	
}
