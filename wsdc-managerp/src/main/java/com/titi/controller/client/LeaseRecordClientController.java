package com.titi.controller.client;

import com.titi.annotation.SystemLog;
import com.titi.controller.index.BaseController;
import com.titi.entity.LeaseRecordFormMap;
import com.titi.entity.OutputResult;
import com.titi.entity.UserAccountFormMap;
import com.titi.mapper.LeaseRecordMapper;
import com.titi.mapper.UserAccountMapper;
import com.titi.util.Common;
import com.titi.util.Constants;
import com.titi.util.RequestUtils;
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

/**
 * 租赁记录信息接口 action
 * 
 * @author lixiaoyu
 */
@Controller
@RequestMapping("/client/")
public class LeaseRecordClientController extends BaseController {

	/** --本地异常日志记录对象 -- **/
	private static final Logger logger = LoggerFactory
			.getLogger(LeaseRecordClientController.class);

	@Inject
	private LeaseRecordMapper leaseRecordMapper;
	
	@Inject
	private UserAccountMapper userAccountMapper;

	/**
	 * 新增租车订单
	 */
	@RequestMapping(value = "addLeaseRecord.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@Transactional(readOnly = false)
	@SystemLog(module = "新增租车订单", methods = "addLeaseRecord.do")
	public @ResponseBody
	OutputResult addLeaseRecord(HttpServletRequest request) {
		RequestUtils.init(request);
		OutputResult output = new OutputResult();
		try {
			if (Common.isEmpty(request.getParameter("user_account_id"))) {
				output.setResult(Constants.RESULT_PARAMETERS_DEFECT);
				output.setMsg("用户ID不能为空");
			}else{
				UserAccountFormMap userAccount = userAccountMapper.findbyFrist(
						"id", request.getParameter("user_account_id"),
						UserAccountFormMap.class);
				if (userAccount != null && userAccount.get("alipay_yn") != null) {// 用户存在
					if("1".equals(userAccount.get("alipay_yn").toString())){//验证用户是否通过身份审核
						output.setResult(Constants.RESULT_STATUS_EXCEPTION);
						output.setMsg("用户未通过审核,不能提交订单");
					}else{
						LeaseRecordFormMap leaseRecord = getFormMap(LeaseRecordFormMap.class);
						leaseRecord.set("version", 0);
						leaseRecord.set("user_account_id", request.getParameter("user_account_id"));
						if(Common.isNotEmpty(request.getParameter("electrombile_id"))){
							leaseRecord.set("electrombile_id", request.getParameter("electrombile_id"));
						}else{
							leaseRecord.set("electrombile_id", "0");
						}
						
						if(Common.isNotEmpty(request.getParameter("pay_type"))){
							leaseRecord.set("pay_type", request.getParameter("pay_type"));
						}
						
						if(Common.isNotEmpty(request.getParameter("total_price"))){
							leaseRecord.set("total_price", Double.parseDouble(request.getParameter("total_price")));
						}
						
						leaseRecord.set("pay_time", new Timestamp(System.currentTimeMillis()));
						
						if(Common.isEmpty(request.getParameter("pick_time"))){
							leaseRecord.set("pick_time", new Timestamp(System.currentTimeMillis()));
						}else{
							leaseRecord.set("pick_time", new Timestamp(Long.parseLong(request.getParameter("pick_time"))));
						}
						
						if(Common.isEmpty(request.getParameter("back_time"))){
							leaseRecord.set("back_time", new Timestamp(System.currentTimeMillis()));
						}else{
							leaseRecord.set("back_time", new Timestamp(Long.parseLong(request.getParameter("back_time"))));
						}
						
						leaseRecord.set("create_name", userAccount.get("real_name"));
						leaseRecord.set("create_date", new Timestamp(System.currentTimeMillis()));
						leaseRecord.set("update_name", userAccount.get("real_name"));
						leaseRecord.set("update_date", new Timestamp(System.currentTimeMillis()));
						
						if(Common.isNotEmpty(request.getParameter("subject"))){
							leaseRecord.set("record_name", request.getParameter("subject"));
						}
						
						if(Common.isNotEmpty(request.getParameter("body"))){
							leaseRecord.set("record_detail", request.getParameter("body"));
						}
						
						if(Common.isEmpty(request.getParameter("record_num"))){
							leaseRecord.set("record_num", 1);
						}else{
							leaseRecord.set("record_num", Integer.parseInt(request.getParameter("record_num")));
						}
						
						if(Common.isNotEmpty(request.getParameter("out_trade_no"))){
							leaseRecord.set("record_out_trade_no", request.getParameter("out_trade_no"));
						}
						
						if(Common.isEmpty(request.getParameter("deposit_price"))){
							leaseRecord.set("deposit_price", 0.00);
						}else{
							leaseRecord.set("deposit_price", Double.parseDouble(request.getParameter("deposit_price")));
						}
						
						if(Common.isEmpty(request.getParameter("rent_price"))){
							leaseRecord.set("rent_price", 0.00);
						}else{
							leaseRecord.set("rent_price", Double.parseDouble(request.getParameter("rent_price")));
						}
						
						leaseRecord.set("record_pay_status", 1);
						leaseRecordMapper.addEntity(leaseRecord);
						output.setResult(Constants.RESULT_SUCCESS);
						output.setMsg("订单提交成功");
					}
				}else{
					output.setResult(Constants.RESULT_LOGIN_ERROR);
					output.setMsg("用户名或密码错误,找不到对应用户");
				}
			}
		} catch (Exception e) {
			logger.error("程序异常,提交订单失败", e);
			output.setResult(Constants.RESULT_STATUS_EXCEPTION);
			output.setMsg("程序异常,提交订单失败");
			e.printStackTrace();
		}
		return output;
	}

}