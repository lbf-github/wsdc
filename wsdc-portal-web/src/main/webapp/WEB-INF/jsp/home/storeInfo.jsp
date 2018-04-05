<%--<%@page import="com.java.service.IntroduceService"%>--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <link href="css/css.css" rel="stylesheet" type="text/css" />
    <title>welcome-wsdc</title>
  </head>
  
  <body>
    <%@ include file="top.jsp" %>
    <table class="big" cellpadding="0" cellspacing="0" style="margin-top:10px">
        <tr>
            <td style="width:200px">
                <%@ include file="left.jsp" %> 
            </td>
            
           <td style="width:20px"></td>
            <td style="width:800px">
            <div class="mainborder">
                <div class="map"><b>商家简介</b></div>
                <p><img src="${storeList.storelogo}" style="width:260px; float:left; padding:10px; border:solid 1px #DCDCDC" />
					商家名称：${storeList.storename}<br />
                    商家地址：${storeList.address}<br />
                    配送时间：${storeList.transporttime}<br />
				</p>    
            </div>
            
            <div class="mainborder">
                <div class="map"><b>菜单</b></div>
                <%-- <ul class="imglistul">
                	<c:forEach var="list"  items="${jin}">
                    <li><img src="${list.img}" />${list.title}</li>
                    </c:forEach>
                </ul>  --%>
                <ul class="imglistul">
                	<c:forEach var="list"  items="${menuList}">
                    <li><img src="<%=basePath %>${list.foodic}" /><a href="<%=basePath %>thing/show.do?id=${list.menuid}">加入购物车</a>
                    <a>${list.foodname}</a>
                    <a>价格:${list.foodprices}</a>
                    </li>

                    </c:forEach>
                </ul> 
            </div>
            </td>
        </tr>
    </table>
    
    
    <%@ include file="foot.jsp" %> 
  </body>
</html>
