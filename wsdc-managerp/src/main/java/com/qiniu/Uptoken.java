package com.qiniu;

import org.json.JSONException;

import com.titi.util.Constants;
import com.qiniu.Mac;
import com.qiniu.Config;
import com.qiniu.PutPolicy;

public class Uptoken {
	public static String makeUptoken(String basePath, String key) throws AuthException,
			JSONException {
		Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
		String bucketName = Constants.BUCKETNAME;
		PutPolicy putPolicy = new PutPolicy(bucketName);
		// 可以根据自己需要设置过期时间,sdk默认有设置，具体看源码
		// putPolicy.expires = getDeadLine();
		putPolicy.returnUrl = basePath+"qiniuCallback.jsp";
		putPolicy.returnBody = "{\"name\": $(fname),\"size\": \"$(fsize)\",\"w\": \"$(imageInfo.width)\",\"h\": \"$(imageInfo.height)\",\"key\":'"+ key + "'}";
		String uptoken = putPolicy.token(mac);
		return uptoken;
	}

}
