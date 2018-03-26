package com.titi.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class RequestUtils {
	public static final Map<String, String> getStringParams(
			HttpServletRequest request) {

		Map<String, String[]> rawParam = request.getParameterMap();

		Map<String, String> underScoreKeyMap = new HashMap<String, String>();

		for (String key : rawParam.keySet()) {
			if (rawParam.get(key) != null) {

				String[] value = rawParam.get(key);
				if (value != null && value.length == 1) {
					underScoreKeyMap.put(key, value[0]);
				}
			}
		}
		return underScoreKeyMap;
	}

	public static final Map<String, String> convertKeyToCamelCase(
			Map<String, String> map) {

		Map<String, String> camelCaseKeyMap = new HashMap<String, String>();
		for (String key : map.keySet()) {
			String camelKey = StringUtils.camelName(key);
			camelCaseKeyMap.put(camelKey, map.get(key));
		}
		return camelCaseKeyMap;
	}
	
	/** -- 输出request携带的参数信息 -- **/
	public static void init(HttpServletRequest request) {
		System.out.println("-->action time :"+ new java.sql.Timestamp(System.currentTimeMillis()));
		System.out.println("-->action url: "+ request.getRequestURL());
		System.out.println("-->action Parameter: "+ RequestUtils.getStringParams(request) );
	}
}
