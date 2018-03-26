package com.titi.controller.client;

import com.titi.annotation.SystemLog;
import com.titi.controller.index.BaseController;
import com.titi.entity.*;
import com.titi.mapper.*;
import com.titi.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 前台用户接口 action
 * 
 * @author lixiaoyu
 */
@Controller
@RequestMapping("/client/")
public class UserAccountClientController extends BaseController {

	/** --本地异常日志记录对象 -- **/
	private static final Logger logger = LoggerFactory
			.getLogger(UserAccountClientController.class);

	@Inject
	private UserAccountMapper userAccountMapper;

	@Inject
	private SmsCodeMapper smsCodeMapper;

	@Inject
	private AttachmentMapper attachmentMapper;
	
	@Inject
	private CustInfoMapper custInfoMapper;

	@Inject
	private CustAddressMapper custAddressMapper;
	
	/**
	 * 用户登录
	 */
	@RequestMapping(value = "userLogin.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@SystemLog(module = "用户登录", methods = "userLogin.do")
	public @ResponseBody
	OutputResult userLogin(HttpServletRequest request) {
		RequestUtils.init(request);
		OutputResult output = new OutputResult();
		try {
			if (Common.isEmpty(request.getParameter("login_name"))
					|| Common.isEmpty(request.getParameter("login_pass"))) {
				output.setResult(Constants.RESULT_PARAMETERS_DEFECT);
				output.setMsg("用户名或者密码为空");
			} else {
				UserAccountFormMap userAccount = userAccountMapper.findbyFrist(
						"mobile", request.getParameter("login_name"),
						UserAccountFormMap.class);
				if(userAccount != null){
					//用户存在,验证登录密码
					if(request.getParameter("login_pass").equals(userAccount.get("password").toString())){
						//登录成功返回，用户信息
						CustInfoFormMap custInfo = custInfoMapper.findbyFrist("account_id", userAccount.getInt("id").toString(), CustInfoFormMap.class);
						output.setResultDao(custInfo);
						output.setResult(Constants.RESULT_SUCCESS);
						output.setMsg("登录成功");
					}else{
						output.setResult(Constants.RESULT_LOGIN_ERROR);
						output.setMsg("密码错误,请检查密码是否正确");
					}
				}else{
					output.setResult(Constants.RESULT_LOGIN_ERROR);
					output.setMsg("用户名不存在,请检查用户名");
				}
			}
		} catch (Exception e) {
			logger.error("程序异常,登录错误", e);
			output.setResult(Constants.RESULT_STATUS_EXCEPTION);
			output.setMsg("程序异常,登录错误");
			e.printStackTrace();
		}
		return output;
	}
	
	/**
	 * 用户注册获取验证码,获取验证码需要将发送成功的验证码写入表中用于判断是否验证码是否过期
	 * mobile 账号
	 * code_type 1 注册，2 找回密码。
	 */
	@RequestMapping(value = "getCode.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@SystemLog(module = "用户获取验证码", methods = "getCode.do")
	public @ResponseBody
	OutputResult userGetCode(HttpServletRequest request) {
		RequestUtils.init(request);
		OutputResult output = new OutputResult();
		if (Common.isEmpty(request.getParameter("login_name"))
				|| Common.isEmpty(request.getParameter("code_type"))) {
			output.setResult(Constants.RESULT_PARAMETERS_DEFECT);
			output.setMsg("手机号不能为空");
		} else {
			boolean is_exist = userAccountMapper.findByAttribute("mobile",
					request.getParameter("login_name"), UserAccountFormMap.class)
					.size() > 0;
			boolean send = true;
			if("1".equals(request.getParameter("code_type"))){
				if(is_exist){
					output.setResult(Constants.RESULT_IS_EXIST);
					output.setMsg("获取验证码失败,该用户已经存在");
					send = false;
				}
			}else if("2".equals(request.getParameter("code_type"))){
				if(!is_exist){
					output.setResult(Constants.RESULT_IS_EXIST);
					output.setMsg("获取验证码失败,该用户不存在");
					send = false;
				}
			}
			if(send){
				SmsCodeFormMap smsCodeFormMap = getFormMap(SmsCodeFormMap.class);
				smsCodeFormMap.put("where", " where code_type = " + request.getParameter("code_type") + " and send_num="
						+ request.getParameter("login_name")
						+ " order by create_date desc");
				List<SmsCodeFormMap> smsCodeList = smsCodeMapper
						.findByWhere(smsCodeFormMap);
				try {
					if(smsCodeList.size() > 0){
						//验证码表中存在数据(包含已发送和已过期数据)
						int distime = Send.getDistanceTime(smsCodeList.get(0)
								.get("create_date") + "", new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss").format(new Date()));
						//根据手机号查询验证码表中该手机号接收验证码的最新一笔数据,判断与当前时间的间隔,若大于设置的过期值,则发送验证码,否则不发送
						if(distime > Constants.DISTANCETIME){
							String codeNum = Send.getSendCode();
							String msgResult = HttpSendSMSManag.sendSMS(request.getParameter("login_name"), codeNum, Integer.parseInt(request.getParameter("code_type")));
							String result_code = HttpSendSMSManag.parse(msgResult);
							if("1".equals(result_code)){//发送成功
								SmsCodeFormMap smsCode = getFormMap(SmsCodeFormMap.class);
								smsCode.set("version", 0);
								smsCode.set("code_num", codeNum);
								smsCode.set("send_num", request.getParameter("login_name"));
								smsCode.set("create_date", new Timestamp(System.currentTimeMillis()));
								smsCode.set("is_active", 0);
								smsCode.set("code_type", request.getParameter("code_type"));
								smsCodeMapper.addEntity(smsCode);
								output.setResult(Constants.RESULT_SUCCESS);
								output.setMsg("验证码发送成功");
							}else{
								output.setResult(Constants.RESULT_STATUS_EXCEPTION);
								output.setMsg("验证码发送失败,请联系后台管理人员");
							}
						}else{
							output.setResult(Constants.RESULT_STATUS_EXCEPTION);
							output.setMsg("验证码已经发送,请不要重复提交");
						}
					}else{
						//验证码表中不存在该手机号对应数据
						String codeNum = Send.getSendCode();
						String msgResult = HttpSendSMSManag.sendSMS(request.getParameter("login_name"), codeNum, Integer.parseInt(request.getParameter("code_type")));
						String result_code = HttpSendSMSManag.parse(msgResult);
						if("1".equals(result_code)){//发送成功
							SmsCodeFormMap smsCode = getFormMap(SmsCodeFormMap.class);
							smsCode.set("version", 0);
							smsCode.set("code_num", codeNum);
							smsCode.set("send_num", request.getParameter("login_name"));
							smsCode.set("create_date", new Timestamp(System.currentTimeMillis()));
							smsCode.set("is_active", 0);
							smsCode.set("code_type", request.getParameter("code_type"));
							smsCodeMapper.addEntity(smsCode);
							output.setResult(Constants.RESULT_SUCCESS);
							output.setMsg("验证码发送成功");
						}else{
							output.setResult(Constants.RESULT_STATUS_EXCEPTION);
							output.setMsg("验证码发送失败,请联系后台管理人员");
						}
					}
				} catch (Exception e) {
					logger.error("程序异常,验证码发送失败", e);
					output.setResult(Constants.RESULT_STATUS_EXCEPTION);
					output.setMsg("程序异常,验证码发送失败");
					e.printStackTrace();
				}
			}
		}
		return output;
	}

	/**
	 * 用户注册
	 */
	@RequestMapping(value = "userReg.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@Transactional(readOnly = false)
	@SystemLog(module = "用户注册", methods = "userReg.do")
	public @ResponseBody
	OutputResult userReg(HttpServletRequest request) {
		RequestUtils.init(request);
		OutputResult output = new OutputResult();
		if (Common.isEmpty(request.getParameter("mobile"))
				|| Common.isEmpty(request.getParameter("code_num"))
				|| Common.isEmpty(request.getParameter("login_pass"))) {
			output.setResult(Constants.RESULT_PARAMETERS_DEFECT);
			output.setMsg("请填入完整参数");
		} else {
			if (userAccountMapper.findByAttribute("mobile",
					request.getParameter("mobile"), UserAccountFormMap.class)
					.size() > 0) {
				output.setResult(Constants.RESULT_IS_EXIST);
				output.setMsg("获取验证码失败,该用户已经存在");
			} else {
				SmsCodeFormMap smsCodeFormMap = getFormMap(SmsCodeFormMap.class);
				smsCodeFormMap.put("where", " where code_type = 1 and send_num="
						+ request.getParameter("mobile")
						+ " order by create_date desc");
				List<SmsCodeFormMap> smsCodeList = smsCodeMapper
						.findByWhere(smsCodeFormMap);
				if (smsCodeList.size() > 0) {
					try {
						int distime = Send.getDistanceTime(smsCodeList.get(0)
								.get("create_date") + "", new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss").format(new Date()));
						// 根据手机号查询验证码表中该手机号接收验证码的最新一笔数据,判断与当前时间的间隔,若小于设置的过期值,则验证码有效,否则验证码无效(过期)
						if (distime > Constants.DISTANCETIME) {
							output.setResult(Constants.RESULT_LOGIN_ERROR);
							output.setMsg("验证码过期,请重新发送");
						} else {// 验证码有效,完成注册,存表
							if (smsCodeList.get(0).get("code_num").equals(request.getParameter("code_num"))){
								UserAccountFormMap userAccountFormMap = getFormMap(UserAccountFormMap.class);
								userAccountFormMap.set("mobile",
										request.getParameter("mobile"));
								userAccountFormMap.set("account_name",
										request.getParameter("mobile"));
								userAccountFormMap.set("password",
										request.getParameter("login_pass"));
								userAccountFormMap.set("create_date", new Timestamp(System.currentTimeMillis()));
//							userAccountFormMap.set("alipay_yn", 1);
//							userAccountFormMap.set("is_activation", 0);
//							userAccountFormMap.set("version", 0);
								userAccountMapper.addEntity(userAccountFormMap);

								CustInfoFormMap custInfoFormMap = getFormMap(CustInfoFormMap.class);
								custInfoFormMap.set("account_id",userAccountFormMap.get("id") );
								custInfoFormMap.set("shareCode", ShareCodeUtil.toSerialCode((Long) userAccountFormMap.get("mobile")) );
								custInfoFormMap.set("latitude",request.getParameter("latitude") );
								custInfoFormMap.set("longitude",request.getParameter("longitude") );

								custInfoMapper.addEntity(custInfoFormMap);
								//注册成功向employee表写入数据 2017.03.31
//							LeaseEmpFormMap empMap = getFormMap(LeaseEmpFormMap.class);
//							empMap.set("tb_user_id", userAccountFormMap.get("id"));
//							empMap.set("contact_moblie", request.getParameter("mobile"));
//							empMap.set("create_date", new Timestamp(System.currentTimeMillis()));
//							empMap.set("lease_identity", 3);
//							empMap.set("enterprise_id", 1000);
//							empMapper.addEntity(empMap);

								//注册时候返回用户基本信息，第二次客户端启动时自动登陆更新客户端信息（主要返回id，用户名以及密码）
								output.setResultDao(userAccountFormMap);
								output.setResult(Constants.RESULT_SUCCESS);
								output.setMsg("注册成功");
							}else {
								output.setResult(Constants.RESULT_LOGIN_ERROR);
								output.setMsg("验证码或者电话号码不正确，请重新注册");
							}
						}
					} catch (Exception e) {
						logger.error("程序异常,注册失败", e);
						output.setResult(Constants.RESULT_STATUS_EXCEPTION);
						output.setMsg("程序异常,注册失败");
						e.printStackTrace();
					}
				} else {// 验证码表中没有对应该手机号数据
					output.setResult(Constants.RESULT_LOGIN_ERROR);
					output.setMsg("验证码或者电话号码不正确，请重新注册");
				}
			}
		}
		return output;
	}
	
	/**
	 * 找回密码
	 */
	@RequestMapping(value = "resetPwd.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@Transactional(readOnly = false)
	@SystemLog(module = "找回密码", methods = "resetPwd.do")
	public @ResponseBody
	OutputResult resetPwd(HttpServletRequest request) {
		RequestUtils.init(request);
		OutputResult output = new OutputResult();
		if (Common.isEmpty(request.getParameter("mobile"))
				|| Common.isEmpty(request.getParameter("code_num"))
				|| Common.isEmpty(request.getParameter("new_pwd"))) {
			output.setResult(Constants.RESULT_PARAMETERS_DEFECT);
			output.setMsg("请填入完整参数");
		} else {
			UserAccountFormMap userAccount = userAccountMapper.findbyFrist("login_name",request.getParameter("mobile"), UserAccountFormMap.class);
			if (userAccount != null) {
				SmsCodeFormMap smsCodeFormMap = getFormMap(SmsCodeFormMap.class);
				smsCodeFormMap.put("where", " where code_type = 2 and send_num="
						+ request.getParameter("mobile")
						+ " order by create_date desc");
				List<SmsCodeFormMap> smsCodeList = smsCodeMapper
						.findByWhere(smsCodeFormMap);
				if (smsCodeList.size() > 0) {
					try {
						int distime = Send.getDistanceTime(smsCodeList.get(0)
								.get("create_date") + "", new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss").format(new Date()));
						// 根据手机号查询验证码表中该手机号接收验证码的最新一笔数据,判断与当前时间的间隔,若小于设置的过期值,则验证码有效,否则验证码无效(过期)
						if (distime > Constants.DISTANCETIME) {
							output.setResult(Constants.RESULT_LOGIN_ERROR);
							output.setMsg("验证码过期,请重新发送");
						} else {//验证码有效,重置用户密码
							userAccount.set("login_pass", request.getParameter("new_pwd"));
							userAccountMapper.editEntity(userAccount);
							output.setResult(Constants.RESULT_SUCCESS);
							output.setMsg("密码重置成功");
						}
					} catch (Exception e) {
						logger.error("程序异常,重置密码失败", e);
						output.setResult(Constants.RESULT_STATUS_EXCEPTION);
						output.setMsg("程序异常,重置密码失败");
						e.printStackTrace();
					}
				} else {// 验证码表中没有对应该手机号数据
					output.setResult(Constants.RESULT_LOGIN_ERROR);
					output.setMsg("验证码或者电话号码不正确，");
				}
			} else {
				output.setResult(Constants.RESULT_IS_EXIST);
				output.setMsg("获取验证码失败,该用户不存在");
			}
		}
		return output;
	}

	/**
	 * 我的收货地址--查询
	 */
	@RequestMapping(value = "getAddress.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@Transactional(readOnly = false)
	@SystemLog(module = "查询收货地址", methods = "getAddress.do")
	public @ResponseBody
	OutputResult getAddress(HttpServletRequest request) {
		RequestUtils.init(request);
		OutputResult output = new OutputResult();
		if (Common.isEmpty(request.getParameter("id"))) {
			output.setResult(Constants.RESULT_PARAMETERS_DEFECT);
			output.setMsg("请填入完整参数");
		} else {
			UserAddressMap userAddressMap = getFormMap(UserAddressMap.class);
			userAddressMap.put("where", " where user_id="
					+ request.getParameter("id")
					+ " order by create_date desc");
			List<UserAddressMap> userAddressList = custAddressMapper.findByWhere(userAddressMap);
			if (userAddressList != null && !userAddressList.isEmpty()) {
				output.setResult(Constants.RESULT_SUCCESS);
				output.setResultList(userAddressList);
				output.setMsg("查询成功");
			} else {
				output.setResult(Constants.RESULT_ERROR);
				output.setMsg("收货地址为空");
			}
		}
		return output;
	}

	/**
	 * 我的收货地址--删除
	 */
	@RequestMapping(value = "delAddress.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@Transactional(readOnly = false)
	@SystemLog(module = "删除收货地址", methods = "delAddress.do")
	public @ResponseBody
	OutputResult delAddress(HttpServletRequest request) {
		RequestUtils.init(request);
		OutputResult output = new OutputResult();
		try{
			if (Common.isEmpty(request.getParameter("id"))) {
				output.setResult(Constants.RESULT_PARAMETERS_DEFECT);
				output.setMsg("请填入完整参数");
			} else {

				UserAddressMap userAddressList = custAddressMapper.findbyFrist("id",request.getParameter("id"),UserAddressMap.class);
				if (userAddressList != null) {
					custAddressMapper.deleteByAttribute("id", request.getParameter("id"), UserAddressMap.class);
					output.setResult(Constants.RESULT_SUCCESS);
					output.setMsg("删除成功");
				} else {
					output.setResult(Constants.RESULT_ERROR);
					output.setMsg("删除失败，地址不存在");
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			output.setResult(Constants.RESULT_STATUS_EXCEPTION);
			output.setMsg("删除失败");
		}

		return output;
	}

	/**
	 * 我的收货地址--增加
	 *
	 */
	@RequestMapping(value = "addAddress.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@Transactional(readOnly = false)
	@SystemLog(module = "增加收货地址", methods = "addAddress.do")
	public @ResponseBody
	OutputResult addAddress(HttpServletRequest request) {
		RequestUtils.init(request);
		OutputResult output = new OutputResult();
		try{
			if (Common.isEmpty(request.getParameter("userId"))
					|| Common.isEmpty(request.getParameter("name"))
					|| Common.isEmpty(request.getParameter("mobile"))
					|| Common.isEmpty(request.getParameter("province"))
					|| Common.isEmpty(request.getParameter("city"))
					|| Common.isEmpty(request.getParameter("area"))
					|| Common.isEmpty(request.getParameter("address") )){
				output.setResult(Constants.RESULT_PARAMETERS_DEFECT);
				output.setMsg("请填入完整参数");
			} else {
				UserAddressMap userAddress = getFormMap(UserAddressMap.class);
				userAddress.set("user_id", request.getParameter("userId"));
				userAddress.set("name", request.getParameter("name"));
				userAddress.set("mobile", request.getParameter("mobile"));
				userAddress.set("province", request.getParameter("province"));
				userAddress.set("city", request.getParameter("city"));
				userAddress.set("area", request.getParameter("area"));
				userAddress.set("address", request.getParameter("address"));
				userAddress.set("create_date", new Timestamp(System.currentTimeMillis()));
				if (Common.isEmpty(request.getParameter("id"))) {
					//没有id做添加操作
					custAddressMapper.addEntity(userAddress);
					output.setResult(Constants.RESULT_SUCCESS);
					output.setMsg("添加成功");
				}else {
					userAddress.set("id", request.getParameter("id"));
					custAddressMapper.editEntity(userAddress);
					output.setResult(Constants.RESULT_SUCCESS);
					output.setMsg("编辑成功");
				}

			}
		}catch (Exception e){
			e.printStackTrace();
			output.setResult(Constants.RESULT_STATUS_EXCEPTION);
			output.setMsg("添加失败");
		}

		return output;
	}





	/**
	 * 支付宝账户设置
	 */
	@RequestMapping(value = "setAlipay.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@Transactional(readOnly = false)
	@SystemLog(module = "支付宝账户设置", methods = "setAlipay.do")
	public @ResponseBody
	OutputResult setAlipay(HttpServletRequest request) {
		RequestUtils.init(request);
		OutputResult output = new OutputResult();
		if (Common.isEmpty(request.getParameter("user_account_id")) 
				|| Common.isEmpty(request.getParameter("alipay_num"))) {
			output.setResult(Constants.RESULT_PARAMETERS_DEFECT);
			output.setMsg("用户ID或支付宝账户不能为空");
		} else {
			UserAccountFormMap userAccount = userAccountMapper.findbyFrist(
					"id", request.getParameter("user_account_id"),
					UserAccountFormMap.class);
			if (userAccount != null) {// 用户存在
				userAccount.set("alipay_num",
						request.getParameter("alipay_num"));
				try {
					userAccountMapper.editEntity(userAccount);
					output.setResult(Constants.RESULT_SUCCESS);
					output.setMsg("支付宝账户设置成功");
				} catch (Exception e) {
					logger.error("程序异常,设置失败", e);
					output.setResult(Constants.RESULT_STATUS_EXCEPTION);
					output.setMsg("程序异常,设置失败");
					e.printStackTrace();
				}
			} else {
				output.setResult(Constants.RESULT_ERROR);
				output.setMsg("该用户不存在");
			}
		}
		return output;
	}

	/**
	 * 设置用户信息
	 */
	@RequestMapping(value = "setuserInformation.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@Transactional(readOnly = false)
	@SystemLog(module = "设置用户信息", methods = "setuserInformation.do")
	public @ResponseBody
	OutputResult setuserInformation(HttpServletRequest request) {
		RequestUtils.init(request);
		OutputResult output = new OutputResult();
		if (Common.isEmpty(request.getParameter("user_account_id"))
				|| Common.isEmpty(request.getParameter("sex"))
				|| Common.isEmpty(request.getParameter("head_img"))
				|| Common.isEmpty(request.getParameter("area"))
				|| Common.isEmpty(request.getParameter("signature"))
				|| Common.isEmpty(request.getParameter("latitude"))
				|| Common.isEmpty(request.getParameter("longitude"))
				|| Common.isEmpty(request.getParameter("birth"))
				|| Common.isEmpty(request.getParameter("userName"))
				|| Common.isEmpty(request.getParameter("nick_name"))) {
			output.setResult(Constants.RESULT_PARAMETERS_DEFECT);
			output.setMsg("请完善用户信息后提交");
		} else {
			UserAccountFormMap userAccount = userAccountMapper.findbyFrist(
					"id", request.getParameter("user_account_id"),
					UserAccountFormMap.class);
			if (userAccount != null) {// 用户存在
				userAccount.set("sex", request.getParameter("sex"));
				userAccount.set("head_img", request.getParameter("head_img"));
				userAccount.set("area", request.getParameter("area"));
				userAccount.set("signature", request.getParameter("signature"));
				userAccount.set("latitude", request.getParameter("latitude"));
				userAccount.set("longitude", request.getParameter("longitude"));
				userAccount.set("birth", request.getParameter("birth"));
				userAccount.set("userName", request.getParameter("userName"));
				userAccount.set("nick_name", request.getParameter("nick_name"));
				try {
					userAccountMapper.editEntity(userAccount);
					getAccountImgUrl(userAccount);
					output.setResultDao(userAccount);
					output.setResult(Constants.RESULT_SUCCESS);
					output.setMsg("用户信息设置成功");
				} catch (Exception e) {
					logger.error("程序异常,设置失败", e);
					output.setResult(Constants.RESULT_STATUS_EXCEPTION);
					output.setMsg("程序异常,设置失败");
					e.printStackTrace();
				}
			} else {
				output.setResult(Constants.RESULT_ERROR);
				output.setMsg("该用户不存在");
			}
		}
		return output;
	}

	/**
	 * 设置用户身份证信息
	 */
	@RequestMapping(value = "setIdCard.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@Transactional(readOnly = false)
	@SystemLog(module = "设置用户身份证信息", methods = "setIdCard.do")
	public @ResponseBody
	OutputResult setIdCard(HttpServletRequest request) {
		RequestUtils.init(request);
		OutputResult output = new OutputResult();
		if (Common.isEmpty(request.getParameter("user_account_id"))
				|| Common.isEmpty(request.getParameter("real_name"))
				|| Common.isEmpty(request.getParameter("id_card_num"))) {
			output.setResult(Constants.RESULT_PARAMETERS_DEFECT);
			output.setMsg("请完善用户信息后提交");
		} else {
			UserAccountFormMap userAccount = userAccountMapper.findbyFrist(
					"id", request.getParameter("user_account_id"),
					UserAccountFormMap.class);
			if (userAccount != null) {// 用户存在
				userAccount.set("real_name", request.getParameter("real_name"));
				userAccount.set("id_card_num",
						request.getParameter("id_card_num"));
				try {
					userAccountMapper.editEntity(userAccount);
					output.setResult(Constants.RESULT_SUCCESS);
					output.setMsg("用户身份信息设置成功");
				} catch (Exception e) {
					logger.error("程序异常,设置失败", e);
					output.setResult(Constants.RESULT_STATUS_EXCEPTION);
					output.setMsg("程序异常,设置失败");
					e.printStackTrace();
				}
			} else {
				output.setResult(Constants.RESULT_ERROR);
				output.setMsg("该用户不存在");
			}
		}
		return output;
	}

	/**
	 * 设置用户身份证正面或手持身份证正面或用户头像
	 */
	@RequestMapping(value = "setUserFrontUrl.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@Transactional(readOnly = false)
	@SystemLog(module = "设置用户身份证正面或手持身份证正面或用户头像", methods = "setUserFrontUrl.do")
	public @ResponseBody
	OutputResult setUserFrontUrl(HttpServletRequest request) {
		RequestUtils.init(request);
		OutputResult output = new OutputResult();
		if (Common.isEmpty(request.getParameter("user_account_id"))
				|| Common.isEmpty(request.getParameter("fronturl"))) {
			output.setResult(Constants.RESULT_PARAMETERS_DEFECT);
			output.setMsg("请完善用户信息后提交");
		} else {
			UserAccountFormMap userAccount = userAccountMapper.findbyFrist(
					"id", request.getParameter("user_account_id"),
					UserAccountFormMap.class);
			if (userAccount != null) {// 用户存在
				try {
					AttachmentFormMap attachmentFormMap = getFormMap(AttachmentFormMap.class);
					attachmentFormMap.set("version", 0);
					if(request.getParameter("url_type") != null && "2".equals(request.getParameter("url_type"))){
						attachmentFormMap.set("front_body_url", request.getParameter("fronturl"));
						attachmentMapper.addAttachmentEntity(attachmentFormMap);
						//更新用户信息
						userAccount.set("front_body_imgurl", attachmentFormMap.get("id"));
						userAccountMapper.editEntity(userAccount);
						getAccountImgUrl(userAccount);
						output.setResultDao(userAccount);
						output.setResult(Constants.RESULT_SUCCESS);
						output.setMsg("手持身份证正面信息设置成功");
					}else if(request.getParameter("url_type") != null && "3".equals(request.getParameter("url_type"))){
						attachmentFormMap.set("url", request.getParameter("fronturl"));
						attachmentMapper.addAttachmentEntity(attachmentFormMap);
						//更新用户信息
						userAccount.set("head_img_id", attachmentFormMap.get("id"));
						userAccountMapper.editEntity(userAccount);
						getAccountImgUrl(userAccount);
						output.setResultDao(userAccount);
						output.setResult(Constants.RESULT_SUCCESS);
						output.setMsg("用户头像设置成功");
					}else{
						attachmentFormMap.set("front_url", request.getParameter("fronturl"));
						attachmentMapper.addAttachmentEntity(attachmentFormMap);
						//更新用户信息
						userAccount.set("id_card_front_img_id", attachmentFormMap.get("id"));
						userAccountMapper.editEntity(userAccount);
						getAccountImgUrl(userAccount);
						output.setResultDao(userAccount);
						output.setResult(Constants.RESULT_SUCCESS);
						output.setMsg("身份证正面信息设置成功");
					}
				} catch (Exception e) {
					logger.error("程序异常,设置失败", e);
					output.setResult(Constants.RESULT_STATUS_EXCEPTION);
					output.setMsg("程序异常,设置失败");
					e.printStackTrace();
				}
			} else {
				output.setResult(Constants.RESULT_ERROR);
				output.setMsg("该用户不存在");
			}
		}
		return output;
	}
	
	/**
	 * 设置用户身份证反面或手持身份证反面
	 */
	@RequestMapping(value = "setUserBackUrl.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@Transactional(readOnly = false)
	@SystemLog(module = "设置用户身份证反面或手持身份证反面", methods = "setUserBackUrl.do")
	public @ResponseBody
	OutputResult setUserBackUrl(HttpServletRequest request) {
		RequestUtils.init(request);
		OutputResult output = new OutputResult();
		if (Common.isEmpty(request.getParameter("user_account_id"))
				|| Common.isEmpty(request.getParameter("backurl"))) {
			output.setResult(Constants.RESULT_PARAMETERS_DEFECT);
			output.setMsg("请完善用户信息后提交");
		} else {
			UserAccountFormMap userAccount = userAccountMapper.findbyFrist(
					"id", request.getParameter("user_account_id"),
					UserAccountFormMap.class);
			if (userAccount != null) {// 用户存在
				try {
					AttachmentFormMap attachmentFormMap = getFormMap(AttachmentFormMap.class);
					attachmentFormMap.set("version", 0);
					if(request.getParameter("url_type") != null && "2".equals(request.getParameter("url_type"))){
						attachmentFormMap.set("back_body_url", request.getParameter("backurl"));
						attachmentMapper.addAttachmentEntity(attachmentFormMap);
						//更新用户信息
						userAccount.set("back_body_imgurl", attachmentFormMap.get("id"));
						userAccountMapper.editEntity(userAccount);
						getAccountImgUrl(userAccount);
						output.setResultDao(userAccount);
						output.setResult(Constants.RESULT_SUCCESS);
						output.setMsg("手持身份证反面信息设置成功");
					}else{
						attachmentFormMap.set("back_url", request.getParameter("backurl"));
						attachmentMapper.addAttachmentEntity(attachmentFormMap);
						//更新用户信息
						userAccount.set("id_card_back_img_id", attachmentFormMap.get("id"));
						userAccountMapper.editEntity(userAccount);
						getAccountImgUrl(userAccount);
						output.setResultDao(userAccount);
						output.setResult(Constants.RESULT_SUCCESS);
						output.setMsg("身份证反面信息设置成功");
					}
				} catch (Exception e) {
					logger.error("程序异常,设置失败", e);
					output.setResult(Constants.RESULT_STATUS_EXCEPTION);
					output.setMsg("程序异常,设置失败");
					e.printStackTrace();
				}
			} else {
				output.setResult(Constants.RESULT_ERROR);
				output.setMsg("该用户不存在");
			}
		}
		return output;
	}
	
	/**
	 * 封装获取用户图片信息的公共方法
	 * @param userAccount 用户信息
	 */
	public void getAccountImgUrl(UserAccountFormMap userAccount){
		if(userAccount.get("head_img_id") != null)
			userAccount.set("head_img_id", attachmentMapper.selectAttaById(userAccount.get("head_img_id").toString()).get("url"));
		
		if(userAccount.get("id_card_front_img_id") != null)
			userAccount.set("id_card_front_img_id", attachmentMapper.selectAttaById(userAccount.get("id_card_front_img_id").toString()).get("front_url"));
		
		if(userAccount.get("id_card_back_img_id") != null)
			userAccount.set("id_card_back_img_id", attachmentMapper.selectAttaById(userAccount.get("id_card_back_img_id").toString()).get("back_url"));
		
		if(userAccount.get("front_body_imgurl") != null)
			userAccount.set("front_body_imgurl", attachmentMapper.selectAttaById(userAccount.get("front_body_imgurl").toString()).get("front_body_url"));
		
		if(userAccount.get("back_body_imgurl") != null)
			userAccount.set("back_body_imgurl", attachmentMapper.selectAttaById(userAccount.get("back_body_imgurl").toString()).get("back_body_url"));
	}

}