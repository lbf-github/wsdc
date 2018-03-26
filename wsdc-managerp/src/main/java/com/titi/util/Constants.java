package com.titi.util;

public final class Constants {
	
	/** --七牛云ACCESS_KEY -- **/
	public static final String ACCESS_KEY = "M0ZNB2qkmyhr8otq-VJMw4Cdl8lob7CLkohiiSTF";
	
	/** --七牛云SECRET_KEY -- **/
	public static final String SECRET_KEY = "mMGGNxe3I03M3cg-p7jEYygUDSS1-bj0Ij8FMsON";
	
	/** --七牛云上传空间 -- **/
	public static final String BUCKETNAME = "tt-qq-upload";
	
	/** --七牛云 文件访问域名 -- **/
	public static final String DOMAIN = "http://files.qq.tt/";
	
	/** --图片访问的URL -- **/
	public static String IMAGE_URL = "http://files.qq.tt/";
	
	/** --骑手二维码链接地址 -- **/
	public static String QRCODE_CONTENT = "http://market.qq.tt/member/qrcode/index/rider_id/";

	/** -- 数据为空 -- **/
	public static String RESULT_ERROR ="1" ;

	/** -- 状态异常 -- **/
	public static String RESULT_STATUS_EXCEPTION ="2" ;

	/** -- 操作成功 -- **/
	public static String RESULT_SUCCESS ="3" ;

	/** -- 参数缺失 -- **/
	public static String RESULT_PARAMETERS_DEFECT ="4" ;

	/** --用户名或者密码错误 -- **/
	public static String RESULT_LOGIN_ERROR ="5" ;

	/** -- 已存在 -- **/
	public static String RESULT_IS_EXIST ="6" ;

	/** -- 过期时间(秒) -- **/
	public static int DISTANCETIME = 130;

}
