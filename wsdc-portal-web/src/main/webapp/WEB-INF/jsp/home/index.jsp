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
                <div class="map"><b>网站简介</b></div>
                <p><img src="images/jj.jpg" style="width:260px; float:left; padding:10px; border:solid 1px #DCDCDC" />
					${intr.content}
				</p>    
            </div>
            
            <div class="mainborder">
                <div class="map"><b>商家</b></div>
                <%-- <ul class="imglistul">
                	<c:forEach var="list"  items="${jin}">
                    <li><img src="${list.img}" />${list.title}</li>
                    </c:forEach>
                </ul>  --%>
                <ul class="imglistul">
                	<c:forEach var="list"  items="${storeList}">
                    <li><img src="<%=basePath %>${list.storelogo}" /><a href="<%=basePath %>storeMenu/page.do?storeid=${list.storeid}">${list.storename}</a>
                    <a>配送费:${list.transportprice}</a>
                    <a>配送时间:${list.transporttime}</a>
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
