package cn.qkmango.hm.utils;

import java.util.UUID;


/**
 * UUID工具类
 */
public class UUIDUtil {

	/**
	 * 获取UUID（去除无用的连接符 - ）
	 * @return 返回UUID字符串（去除无用的连接符 - ）
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-","");
	}
	
}
