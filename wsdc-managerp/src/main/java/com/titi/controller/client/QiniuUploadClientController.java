package com.titi.controller.client;

import com.qiniu.util.Auth;
import com.titi.annotation.SystemLog;
import com.titi.controller.index.BaseController;
import com.titi.entity.OutputResult;
import com.titi.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 七牛云上传接口 action
 * 
 * @author lixiaoyu
 */
@Controller
@RequestMapping("/client/")
public class QiniuUploadClientController extends BaseController {
	
	/** --本地异常日志记录对象 -- **/
	private static final Logger logger = LoggerFactory
			.getLogger(QiniuUploadClientController.class);
	
	// 设置好账号的ACCESS_KEY和SECRET_KEY
	String ACCESS_KEY = Constants.ACCESS_KEY;
	String SECRET_KEY = Constants.SECRET_KEY;
	
	// 要上传的空间
	String bucketname = Constants.BUCKETNAME;
		
	// 密钥配置
	Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	
	/**
	 * 获取token
	 */
	@RequestMapping(value = "getUpToken.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@SystemLog(module = "获取token", methods = "getUpToken.do")
	public @ResponseBody
	OutputResult getUpToken() {
		OutputResult output = new OutputResult();
		try {
			String token = auth.uploadToken(bucketname);
			output.setResult(Constants.RESULT_SUCCESS);
			output.setMsg("构建token成功");
			output.setResultDao(token);
		} catch (Exception e) {
			logger.error("程序异常", e);
			output.setResult(Constants.RESULT_STATUS_EXCEPTION);
			output.setMsg("程序异常");
			e.printStackTrace();
		}
		return output;
	}

}