package com.sun.demo.base.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @date 2018/03
 * @author szy
 * 
 */
public final class ToolsMD5 {

	/**
	 * 32位MD5加密算法
	 * 
	 * @param plainText
	 *            被加密的字符串
	 * @return 加密后
	 */
	public static String Md5_32(String plainText) {
		if (plainText != null) {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(plainText.getBytes());
				byte b[] = md.digest();
				int i;
				StringBuffer buf = new StringBuffer("");
				for (int offset = 0; offset < b.length; offset++) {
					i = b[offset];
					if (i < 0)
						i += 256;
					if (i < 16)
						buf.append("0");
					buf.append(Integer.toHexString(i));
				}
				return buf.toString();// 32位加密
			} catch (NoSuchAlgorithmException e) {
				return null;
			}
		} else {
			return null;
		}
	}
	
	/**
	 * 获取byte数组的信息摘要加密信息
	 * @param buffer 字节数组
	 * @return
	 */
	public final static String obtainMessageDigest(byte[] buffer) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(buffer);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	
	
}
