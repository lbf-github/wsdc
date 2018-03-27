package com.titi.controller.client;

import com.titi.annotation.SystemLog;
import com.titi.controller.alipay.util.AlipayNotify;
import com.titi.controller.index.BaseController;
import com.titi.entity.OrderFormMap;
import com.titi.mapper.OrderMapper;
import com.titi.util.Common;
import com.titi.util.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * 支付宝回调通知接口 action
 * 
 * @author 陆彬峰
 */
@Controller
@RequestMapping("/client/")
public class AlipayNotifyClientController extends BaseController {

	/** --本地异常日志记录对象 -- **/
	private static final Logger logger = LoggerFactory
			.getLogger(AlipayNotifyClientController.class);
	
	@Inject
	private OrderMapper orderMapper;

	/**
	 * 异步接收支付宝支付结果 支付宝服务器调用
	 */
	@RequestMapping(value = "receiveNotify.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST, RequestMethod.GET })
	@SystemLog(module = "异步接收支付宝支付结果", methods = "receiveNotify.do")
	public @ResponseBody
	String receiveNotify(HttpServletRequest request, HttpServletResponse response) {
		RequestUtils.init(request);
		Map<String, String> underScoreKeyMap = RequestUtils.getStringParams(request);
		// 首先验证调用是否来自支付宝
		boolean verifyResult = AlipayNotify.verify(underScoreKeyMap);
		String resultResponse = "success";
		PrintWriter printWriter = null;
		try {
			if(verifyResult){
				printWriter = response.getWriter();
				//支付成功,判断支付宝返回的对外订单号和数据库的订单号,更新订单信息
				OrderFormMap orderForm = getFormMap(OrderFormMap.class);
				if(Common.isNotEmpty(underScoreKeyMap.get("out_trade_no"))){
					orderForm.put("where", " where sn1="+ underScoreKeyMap.get("out_trade_no")
							+ " order by create_date desc");
					List<OrderFormMap> orderForms = orderMapper.findByWhere(orderForm);
					if(orderForms.size() > 0){
						OrderFormMap record = orderForms.get(0);
						record.set("status", 1);
						record.set("pay_no", underScoreKeyMap.get("trade_no"));
						orderMapper.editEntity(record);
						resultResponse = "success";
					}else{
						resultResponse = "fail";
					}
				}else{
					resultResponse = "fail";
				}
				if (printWriter != null) {
					printWriter.print(resultResponse);
				}
			}else{
				resultResponse = "fail";
			}
		} catch (Exception e) {
			logger.error("alipay notify error:", e);
			resultResponse = "fail";
		}finally {
			if (printWriter != null) {
				printWriter.close();
			}
		}
		return resultResponse;
	}

}