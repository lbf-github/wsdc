package com.titi.util;

import java.io.IOException;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.support.PropertiesLoaderUtils;

public class WebUtil {

	public static String YMDHMS = "yyyy-MM-dd HH:mm:ss";
	public static String YMD = "yyyy-MM-dd";
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	private final static String regEx = "[\u4e00-\u9fa5]";

	public static String trim(Object obj) {

		if (String.class.isInstance(obj)) {
			String str = String.valueOf(obj);

			if (str != null) {
				return str.trim();
			} else {
				return "";
			}
		} else {
			Date date = (Date) obj;

			if (date == null) {
				return "";
			} else {
				return formatDateByPattern(date, YMDHMS);
			}
		}

	}

	/** --检查字符串是否为空-- **/
	public static boolean isEmpty(String str) {
		// return trim(str).isEmpty();
		return str == null ? true : str.trim().length() == 0;
	}

	/** --检查集合是否为空-- **/
	public static <E> boolean isEmpty(List<E> list) {
		if (list == null) {
			return true;
		} else if (list.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static String formatDateByPattern(String date, String pattern) {
		try {
			if (isEmpty(date)) {
				return "";
			}

			SimpleDateFormat sdf = new SimpleDateFormat(pattern);

			return sdf.format(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "";
	}

	public static String formatDateByPattern(Date date, String pattern) {
		if (date == null) {
			return "";
		} else {
			return new SimpleDateFormat(pattern).format(date);
		}
	}

	/**
	 * 计算输入的日期与当前时间已失去的时长
	 *
	 * @param date
	 *            输入的一个已过去的时间
	 * @return 描述已失去的时长的字符串
	 */
	public static String pastTime(Date date) {

		long day = 0;
		long hour = 0;
		long min = 0;

		String pastTime = "";

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(YMDHMS);

			Date d1 = sdf.parse(sdf.format(new Date()));
			Date d2 = sdf.parse(sdf.format(date));

			Calendar cal = Calendar.getInstance();
			cal.setTime(d1);
			long time1 = cal.getTimeInMillis();// System.out.println("time1=" +
												// time1);
			cal.setTime(d2);
			long time2 = cal.getTimeInMillis();// System.out.println("time2=" +
												// time2);

			long diff = 0;

			/*
			 *
			 * if(time1<time2) { diff = time2 - time1; } else { diff = time1 -
			 * time2; }
			 */

			diff = time1 - time2;

			// System.out.println("diff=" + diff);
			day = (diff) / (1000 * 3600 * 24);
			// System.out.println("day=" + day);
			if (day == 0) {
				hour = (diff / (60 * 60 * 1000) - day * 24);
				// System.out.println("hour=" + hour);
				if (hour == 0) {
					min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
					// System.out.println("min=" + min);

					if (min <= 0) {
						pastTime = "刚刚";
					} else {
						pastTime = min + "分钟前";
					}

				} else if (hour < 24) {
					pastTime = hour + "小时前";
				}
			}
			if (day == 1) {
				pastTime = "昨天";
			} else if (day > 1) {
				pastTime = day - 1 + "天前";
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pastTime;
	}

	/**
	 * 计算两个日期之间相差的天数
	 *
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 */
	public static int daysBetween(Date smdate, Date bdate) {

		long between_days = 0;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Date d1 = sdf.parse(sdf.format(smdate));
			Date d2 = sdf.parse(sdf.format(bdate));

			Calendar cal = Calendar.getInstance();
			cal.setTime(d1);
			long time1 = cal.getTimeInMillis();
			cal.setTime(d2);
			long time2 = cal.getTimeInMillis();

			between_days = (time2 - time1) / (1000 * 3600 * 24);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 字符串的日期格式的计算
	 */
	public static int daysBetween(String smdate, String bdate) {
		long between_days = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(smdate));
			long time1 = cal.getTimeInMillis();
			cal.setTime(sdf.parse(bdate));
			long time2 = cal.getTimeInMillis();
			between_days = (time2 - time1) / (1000 * 3600 * 24);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Integer.parseInt(String.valueOf(between_days));
	}

	public static String doubleTrans(double d) {
		if (Math.round(d) - d == 0) {
			return String.valueOf((long) d);
		}
		return String.valueOf(d);
	}

	public static String getIpAddr(HttpServletRequest request) {

		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/** --将字符串转为int-- * */
	public static int toInt(String str) {
		int flag = 0;
		try {
			flag = Integer.valueOf(str);
		} catch (NumberFormatException ex) {

		}
		return flag;
	}

	/** --将字符串转为long-- * */
	public static long toLong(String str) {
		long flag = 0;
		try {
			flag = Long.valueOf(str);
		} catch (NumberFormatException ex) {

		}
		return flag;
	}

	/** --读取属性文件--* */
	public static String getPropertyParams(String key, String fileName) {
		Properties keyWordProperties = null;
		try {
			keyWordProperties = PropertiesLoaderUtils
					.loadAllProperties(fileName);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return keyWordProperties.getProperty(key);
	}

	/**
	 * 用MD5加密
	 *
	 * @param String
	 *            orignString 原字符串
	 * @return String resultString 加密后的字符串
	 */
	public static String MD5Encode(String originString) {
		String resultString = null;
		try {
			resultString = new String(originString);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes()));
		} catch (Exception ex) {

		}
		return resultString;
	}

	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/** --判断字符串是否有中文-- **/
	public static boolean isContainsChinese(String str) {

		Pattern pat = Pattern.compile(regEx);
		Matcher matcher = pat.matcher(str);
		boolean flg = false;
		if (matcher.find()) {
			flg = true;
		}
		return flg;
	}

	/** --主键生成策略,通过UUID,随机数也可以通过这么生成-- **/
	public static String getPrimaryKey() {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
		UUID uuid = UUID.randomUUID();
		return dateformat.format(new Date())
				+ uuid.toString().replaceAll("-", "").substring(0, 18);
	}

	/** --获得客户端浏览器-- **/
	public static String getNavigatorInfo(HttpServletRequest request) {
		try {
			String info = request.getHeader("user-agent");
			if (info.indexOf("MSIE") != -1) {
				return info.substring(info.indexOf("MSIE"),
						info.indexOf("MSIE") + 8);
			} else if (info.indexOf("Firefox") != -1) {
				return info.substring(info.indexOf("Firefox"), info.length());
			} else if (info.indexOf("Chrome") != -1) {
				return info.substring(info.indexOf("Chrome"), info.length());
			} else if (info.indexOf("Opera") != -1) {
				return info.substring(info.indexOf("Opera"),
						info.indexOf("Opera") + 11);
			} else if (info.indexOf("Lunascape") != -1) {
				return info.substring(info.indexOf("Lunascape"), info.length());
			} else if (info.indexOf("AppleWebKit") != -1) {
				return info.substring(info.indexOf("AppleWebKit"),
						info.length());
			} else {
				return "其他";
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取客户端操作系统信息，目前只匹配Win 7、WinXP、Win2003、Win2000、MAC、WinNT、Linux、Mac68k、Win9x
	 *
	 * @param userAgent
	 *            request.getHeader("user-agent")的返回值
	 * @return
	 */
	public static String getClientOS(String userAgent) {
		String cos = "unknow os";

		Pattern p = Pattern.compile(".*(Windows NT 6\\.1).*");
		Matcher m = p.matcher(userAgent);
		if (m.find()) {
			cos = "Win 7";
			return cos;
		}

		p = Pattern.compile(".*(Windows NT 5\\.1|Windows XP).*");
		m = p.matcher(userAgent);
		if (m.find()) {
			cos = "WinXP";
			return cos;
		}

		p = Pattern.compile(".*(Windows NT 5\\.2).*");
		m = p.matcher(userAgent);
		if (m.find()) {
			cos = "Win2003";
			return cos;
		}

		p = Pattern.compile(".*(Win2000|Windows 2000|Windows NT 5\\.0).*");
		m = p.matcher(userAgent);
		if (m.find()) {
			cos = "Win2000";
			return cos;
		}

		p = Pattern.compile(".*(Mac|apple|MacOS8).*");
		m = p.matcher(userAgent);
		if (m.find()) {
			cos = "MAC";
			return cos;
		}

		p = Pattern.compile(".*(WinNT|Windows NT).*");
		m = p.matcher(userAgent);
		if (m.find()) {
			cos = "WinNT";
			return cos;
		}

		p = Pattern.compile(".*Linux.*");
		m = p.matcher(userAgent);
		if (m.find()) {
			cos = "Linux";
			return cos;
		}

		p = Pattern.compile(".*(68k|68000).*");
		m = p.matcher(userAgent);
		if (m.find()) {
			cos = "Mac68k";
			return cos;
		}

		p = Pattern
				.compile(".*(9x 4.90|Win9(5|8)|Windows 9(5|8)|95/NT|Win32|32bit).*");
		m = p.matcher(userAgent);
		if (m.find()) {
			cos = "Win9x";
			return cos;
		}

		return cos;
	}
	
	/** --生成32位数字-- **/
	public static String getRandNum() {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
		int max=9999;
        int min=1000;
        Random random = new Random();
        String num = random.nextInt(max)%(max-min+1) + min+"";//生成4位的数字
		String result = dateformat.format(new Date())+"0"+System.nanoTime()+num;//32位的数字
		return result.substring(0,32);
	}
	
	/** --传入Date 输出固定格式的 日期 如:2016年9月2日周(五) -- **/
	public static String getWeekOfDate(Date dt) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日");  
        String[] weekDays = {"周(日)", "周(一)", "周(二)", "周(三)", "周(四)", "周(五)", "周(六)"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return sdf.format(dt)+weekDays[w];
    }

	public static void main(String[] args) throws ParseException {
		System.out.println(WebUtil.getWeekOfDate(new Date(System.currentTimeMillis())));
	}

}
