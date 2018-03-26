package com.titi.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 用户 加密工具类
 */
public class PwdUtil {
	
	// 全局数组
	private final static String[] strDigits = { "0", "1", "2", "3", "4", "5","6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };


	/**
	 * 用户加密新的方式
	 * @param password
	 * @return
	 */
	public static String encryptPassword(String password){
		password = md5(password);
		password = password.substring(3);
		password = md5(password);
		password = password.substring(7, 27);
		password = md5(password);
		password = password.substring(2, 18);
		password = md5(password);
		password = _encrypt(password);
		return md5(password);
	}

	// 返回形式为数字跟字符串
	private static String byteToArrayString(byte bByte) {
		int iRet = bByte;
		if (iRet < 0) {
			iRet += 256;
		}
		int iD1 = iRet / 16;
		int iD2 = iRet % 16;
		return strDigits[iD1] + strDigits[iD2];
	}

	// 转换字节数组为16进制字串
	private static String byteToString(byte[] bByte) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < bByte.length; i++) {
			sBuffer.append(byteToArrayString(bByte[i]));
		}
		return sBuffer.toString();
	}

	public static String md5(String strObj) {
		String resultString = null;
		try {
			resultString = new String(strObj);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteToString(md.digest(strObj.getBytes()));
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return resultString;
	}

	private static String _encrypt(String strObj) {
		int j = strObj.length();
		String key = "ad222715";
		int k = key.length();

		StringBuffer encryptBuffer = new StringBuffer("");

		for (int i = 0; i < j; i += k) {

			String _tmpStr = strObj.substring(i, i + k);
			encryptBuffer.append(strXor(_tmpStr, key));
		}
		String encrypt = encryptBuffer.toString();
		encrypt = base64Encode(encrypt.getBytes());
		encrypt = encrypt.replace("=", "");
		return encrypt;

	}

	public static String strXor(String str1, String str2) {
		String strXor = "";
		byte b1[] = str1.getBytes();
		byte b2[] = str2.getBytes();
		int temp = 0;
		if (b1.length <= b2.length) {
			temp = b2.length;
		} else {
			temp = b1.length;
		}
		for (int i = 0; i < temp; i++) {
			int b = (int) b1[i] ^ (int) b2[i];
			strXor = strXor + ((char) b);
		}
		return strXor;
	}

	public static String base64Encode(byte[] bytes) {
		return new BASE64Encoder().encode(bytes);
	}

	public static byte[] base64Decode(String base64Code) throws Exception {
		return new BASE64Decoder().decodeBuffer(base64Code);
	}
	
	public static void main(String[] args) {
		System.out.println(encryptPassword("asdfasdf"));
	}
}
