<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.qiniu.*,org.json.JSONObject,com.titi.util.WebUtil,java.text.SimpleDateFormat,java.util.Date"%>
<%
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	response.setContentType("application/json");
	String path = request.getContextPath();
	String dir = new SimpleDateFormat("yyyy/MM/dd/").format(new Date());
	String key = dir+WebUtil.getPrimaryKey();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	JSONObject json = new JSONObject();
	json.append("token", Uptoken.makeUptoken(basePath,key));
	json.append("key", key);
	response.getWriter().print(json.toString());
%>
