<%@page import="com.lbf.wsdc.service.IntroduceService"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("basePath", basePath);
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
      <link rel="stylesheet" type="text/css" href="${ctx}/js/sweetalert/sweetalert.css">
      <%--<link href="${ctx}css/css.css" rel="stylesheet" type="text/css" />--%>
      <link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.3.3/themes/default/easyui.css">
      <link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.3.3/themes/icon.css">
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
                <div class="map"><b>店铺订单</b></div>

                <%--<div class="layui-input-block">--%>
                    <%--<input class="layui-btn layui-btn-normal" lay-submit  lay-filter="demo1"  type="submit" value="修改"></input>--%>
                <%--</div>--%>

               <div class="layui-form">
  <table class="layui-table">
    <thead>
      <tr>
          <th>订单号</th>
          <th>用户账号</th>
          <th>菜单</th>
          <th>配送地址</th>
          <th>价格</th>
          <th>订单状态</th>
          <th>评论</th>
          <th>操作</th>
      </tr> 
    </thead>
    <tbody>
    <c:forEach var="list"  items="${storeOrdersList}">
      <tr>
      	<td>${list.ordercode}</td>
        <td>
        ${list.uaccount}
        </td>
        <td>${list.menus}</td>
        <td>${list.address}</td>
          <td>${list.price}</td>
          <td>
              <c:if test="${list.state==0}">下单</c:if>
              <c:if test="${list.state==1}">完成</c:if>
          </td>
          <td>${list.comment}</td>
        <td>
            <c:if test="${list.state==0}">
                <input type="button" class="buy" value="确认订单" onclick="doOrder(${list.orderid})"/>
            </c:if>
        </td>
      </tr>
      </c:forEach>
    </tbody>

  </table>

            </div>
                <%--<div class="layui-input-block">--%>
                    <%--<input class="layui-btn layui-btn-normal" lay-submit  lay-filter="demo1"  type="button" value="添加地址" onclick="toInsert()"></input>--%>
                <%--</div>--%>
            </div>
            </td>
        </tr>

    </table>

    <%@ include file="foot.jsp" %>
    <script type="text/javascript" src="${ctx}/js/sweetalert/sweetalert.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.3.3/plugins/jquery.messager.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.3.3/jquery.cookie.js"></script>
    <script>



//跳转评论页面
    function doComment(orderId) {

        $.messager.confirm("系统提示", "您确认要评论吗？", function(r) {
            if (r) {
                location.href = "${ctx}/order/toComment.do?orderId="+orderId;
            }
        });

    }

    //确认订单
        function doOrder(orderId) {

            $.messager.confirm("系统提示", "您要确认订单吗？", function(r) {
                if (r) {
                    $.post("${ctx}/order/doOrderSure.do", {
                        orderId:orderId
                    }, function(result) {
                        if(result.success){
                            swal({
                                    title: '提示',
                                    text: result.message,
                                    confirmButtonText: "确定"
                                },
                                function(){

                                    location.reload();
                                });
                        }else{
                            swal({
                                    title: '提示',
                                    text: result.message,
                                    confirmButtonText: "确定"
                                },
                                function(){

                                });
                        }
                    }, "json");
                }
            });

        }


    </script>
  </body>
</html>
