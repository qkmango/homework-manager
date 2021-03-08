package cn.qkmango.hm.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * MD5工具类
 * 此类可以生成MD5的字符串
 */
public class MD5Util {


	/**
	 * 传入字符串，获取MD5的字符串
	 * @param password 传入要进行MD5加密的字符串
	 * @return 返回 加密后的MD5字符串
	 */
	public static String getMD5(String password) {
		try {
			// 得到一个信息摘要器
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] result = digest.digest(password.getBytes());
			StringBuffer buffer = new StringBuffer();
			// 把每一个byte 做一个与运算 0xff;
			for (byte b : result) {
				// 与运算
				int number = b & 0xff;// 加盐
				String str = Integer.toHexString(number);
				if (str.length() == 1) {
					buffer.append("0");
				}
				buffer.append(str);
			}

			// 标准的md5加密后的结果
			return buffer.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}
	
}
