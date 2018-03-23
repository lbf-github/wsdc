package com.lbf.wsdc.common.util;


import java.util.List;

/**
 * 扩展字符窜工具
 * <p> 
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {
	/**
	 * 
	 * 取文本长度,中文算两个
	 * 
	 * @param text
	 * @return
	 * @author 11500
	 * @date 2010-9-3 上午10:46:58
	 */
	public static int lengthForChinese(String text) {
		int length = 0;
		for (int i = 0; i < text.length(); i++) {
			if (new String(text.charAt(i) + "").getBytes().length > 1) {
				length += 2;
			} else {
				length += 1;
			}
		}
		return length;
	}
	
	/**
	 * 
	 * <p>
	 * @param text
	 * @return
	 */
	public static String convertCtrlNAndBlankToHtml(String text){
		if(isNotBlank(text)){
			text = text.replace("\n", "<br/>");
		}
		return text;
	}
	
	/**
	 * 将List<String>转换为s1,s2,s3返回
	 * @return
	 */
	public static String convertFromList(List<Long> sList){
		String res = "";
		for (int i = 0; i < sList.size(); i++) {
			res += sList.get(i);
			if(i != sList.size()-1){
				res += ",";
			}
		}
		return res;
	}
}
