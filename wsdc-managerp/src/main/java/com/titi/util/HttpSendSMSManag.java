package com.titi.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import com.qiniu.Http;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * @className HttpSendSMSManag.java
 * @desc 短信验证管理类 本类用于向手机号码发送短信，且返回响应码
 * @author tangliang
 * @date 2016年3月23日 下午4:50:26
 * @version 0.0.1-SNAPSHOT
 */
public class HttpSendSMSManag {

	private static Logger logger = LoggerFactory.getLogger(HttpSendSMSManag.class);

	protected static String CHARSET = "UTF-8";
	protected static String SERVERIP = "http://120.55.242.177/OpenPlatform/OpenApi?";
	protected static String SERVERPORT = "80";
	protected static String ACCOUT = "vipayy8";
	protected static String PSWD = "langzuyule.168";
	protected static String MES_TEMPLATE = "您的注册验证码是：";
	protected static String MES_TEMPLATE1 = "您当前正在修改密码操作您的验证码是：";
	protected static String MES_TEMPLATE2 = "您当前正在找回密码操作您的验证码是：";
	protected static String MES_TEMPLATE4 = "您当前正在修改支付密码操作，您的验证码是：";
	protected static String MES_TEMPLATE5 = "您当前正在更换手机号操作，您的验证码是：";
	protected static String MES_TEMPLATE6 = "您好,你的账号审核已通过";
	protected static String MES_SIGNATURE = "";

	protected static String AC="1001@800119310002";
	protected static String AUTHKEY = "1E380A8EF1F226BE70466983BB967C05";
	protected static String CGID = "378";


	public static void main(String[] args){


		String s = sendSMS("13419529441", "2134", 1);

//		String s = "<xml name=\"sendOnce\" result=\"1\"><Item cid=\"800119310002\" sid=\"1001\" msgid=\"1147318191979651000\" total=\"1\" price=\"0.1\" remain=\"20044.300\" /></xml>";
		System.out.println(s);
		System.out.println(parse(s)+"---11111111111");
	}
	/**
	 * @desc 发送短信静态方法.l/okiko,l.
	 * @param phone
	 *            传入手机号码，多个手机号码用英文的逗号隔开，组成字符串传入进来
	 * @param content
	 *            已经生成验证码的内容 一般是随机4位数字字符串
	 * @return 20160323170509,0 这个格式响应码 逗号前表示时间、0表示成功、其他数字表示失败
	 *         1000323170509151700
	 * @author tangliang
	 */
	public static String sendSMS(String phone, String content,int stats) {
		/**
		 * http://222.73.117.158/msg/HttpBatchSendSM?account=111111&pswd=123456
		 * &mobile=18900000000,13800138000&msg=test&needstatus=true&product=
		 * 99999
		 */
		HttpClient client = Http.getClient();
		String mobile = phone;
		String msg="";
		if(stats==1){
			msg = MES_TEMPLATE+content;
		}else if(stats==2){
			msg = MES_TEMPLATE2+content;
		}else if(stats==3){
			msg = MES_TEMPLATE+content;
		}else if(stats==4){
			msg = MES_TEMPLATE4+content;
		}else if(stats==5||stats==6){
			msg = MES_TEMPLATE5+content;
		}else if(stats==7){
			msg = MES_TEMPLATE6;
		}else{
			msg = MES_SIGNATURE+content;
		}


		List<NameValuePair> params = new ArrayList<NameValuePair>();
		/*params.add(new BasicNameValuePair("action","sendOnce"));
		params.add(new BasicNameValuePair("account", ACCOUT));
		params.add(new BasicNameValuePair("pswd", PSWD));
		params.add(new BasicNameValuePair("mobile", mobile));*/
		params.add(new BasicNameValuePair("action","sendOnce"));
		params.add(new BasicNameValuePair("ac", AC));
		params.add(new BasicNameValuePair("authkey", AUTHKEY));
		params.add(new BasicNameValuePair("cgid", CGID));
		params.add(new BasicNameValuePair("m", mobile));
		params.add(new BasicNameValuePair("c", msg));
		/*try {
			*//*params.add(new BasicNameValuePair("msg", URLEncoder.encode(msg, CHARSET)));
			params.add(new BasicNameValuePair("needstatus", "true"));*//*
			params.add(new BasicNameValuePair("c", URLEncoder.encode(msg, CHARSET)));
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}*/

		String param = URLEncodedUtils.format(params, CHARSET);
		String url = SERVERIP + param;

		logger.info(url);
		HttpPost post = new HttpPost(url);
		HttpResponse response;
		try {
			response = client.execute(post);
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, CHARSET);
			logger.info("result:" + result);
			return result;
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
			return "";
		} catch (IOException e1) {
			e1.printStackTrace();
			return "";
		}
	}

	/**
	 * 解析短信发送返回的XML
	 * @param protocolXML
	 * @return
	 */
	public static String parse(String protocolXML) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(
					protocolXML)));

			Element root = doc.getDocumentElement();
			return root.getAttribute("result");
//			NodeList books = root.getChildNodes();

//			if (books != null) {
//				for (int i = 0; i < books.getLength(); i++) {
//					Node book = books.item(i);
//					if (book.getNodeName().equals("State")) {
//						redultcode = book.getFirstChild().getNodeValue();
//					}
//					System.out.println(book.getNodeName());
//				}
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
