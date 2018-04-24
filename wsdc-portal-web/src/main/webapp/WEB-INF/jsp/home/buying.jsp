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
                <div class="map"><b>购物车</b></div>
                <%--<div class="layui-form-item">--%>
                    <%--<label class="layui-form-label">收货地址：</label>--%>
                    <%--<div class="layui-input-block">--%>
                <%--<select id="addressAddress" style="width: 500px">--%>
                    <%--<c:forEach var="list" items="${uaddresses}">--%>
                        <%--<option value="${list.addressid}">联系人：${list.ucontact} 　　　地址：${list.uaddress}</option>--%>
                    <%--</c:forEach>--%>
                <%--</select>--%>
                    <%--</div>--%>
                <%--</div>--%>
               <div class="layui-form">
                   <div class="layui-form-item">
                       <label class="layui-form-label">收货地址：</label>
                       <div class="layui-input-block">
                           <select id="addressAddress" style="width: 500px">
                               <c:forEach var="list" items="${uaddresses}">
                                   <option value="${list.addressid}">联系人：${list.ucontact} 　　　地址：${list.uaddress}</option>
                               </c:forEach>
                           </select>
                       </div>
                   </div>
  <table class="layui-table">
    <thead>
      <tr>
      	<th>图片</th>
        <th>名称</th>
        <th>数量</th>
        <th>单价</th>
        <th>操作</th>
      </tr> 
    </thead>
    <tbody>
    <c:forEach var="list"  items="${menuListCart}">
      <tr>
      	<td><img src="${basePath }${list.foodic}" style="width:100px; border:#CCCCCC; padding:3px" /></td>
        <td>${list.foodname}</td>
        <td>${list.num}</td>
        <td>${list.foodprices}元</td>
        <td><input type="button" class="buy" value="删除" onclick="deleteMenu(${list.menuid})"/></td>
      </tr>
      </c:forEach>
    </tbody>
  </table>


  <div style="width:100%; text-align:right; font-size:16px"><button id="sure" class="layui-btn layui-btn-normal" type="button" onclick="toOrder()">共计人民币<b style="color:red">${sum }</b>元,确认订单</button></div>
            </div>
            </div>
            </td>
        </tr>
    </table>
    <%@ include file="foot.jsp" %>
    <script src="${ctx}/js/jquery-validation/jquery.form.js" type="text/javascript"></script>
    <script src="${ctx}/js/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/js/sweetalert/sweetalert.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.3.3/plugins/jquery.messager.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.3.3/jquery.cookie.js"></script>
    <script>

        function deleteMenu(menuId) {
            var _cart = $.cookie("cart_token");
            $.messager.confirm("系统提示", "您确认要取消吗？", function(r) {
                if (r) {
                    $.post("${ctx}/cart/delMenuById.do", {
                        menuid:menuId,
                        cartId:_cart
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


    <%--$(function(){ --%>
    	<%--$(".del").click(function(){--%>
    		<%--var id = $(this).attr("id");--%>
            <%--var _cart = $.cookie("cart_token");--%>
		    <%--$.messager.confirm("系统提示", "您确认要取消吗？", function(r) {--%>
				<%--if (r) {--%>
					<%--$.post("${ctx}/cart/delMenuById.do", {--%>
                        <%--menuid:id,--%>
                        <%--cartId:_cart--%>
					<%--}, function(result) {--%>
						<%--if (result.success) {--%>
								 <%--$.messager.alert("系统提示", result.message, "info", function () {--%>
									<%--window.location.reload();--%>
						        <%--}); --%>
						<%--} else {--%>
							<%--$.messager.alert("系统提示", result.message);--%>
						<%--}--%>
					<%--}, "json");--%>
				<%--}--%>
			<%--});--%>
    	<%--});--%>
    	<%----%>
    	<%--$("#sure").click(function(){--%>
		    <%--$.messager.confirm("系统提示", "您确认要生成订单吗？", function(r) {--%>
				<%--if (r) {--%>
					<%--$.post("${basePath}buy/add.do", function(result) {--%>
						<%--if (result.success) {--%>
								 <%--$.messager.alert("系统提示", result.mgf, "info", function () {--%>
									<%--localhost.href="${basePath}buy/my_buy.do";--%>
						        <%--}); --%>
						<%--} else {--%>
							<%--$.messager.alert("系统提示", result.mgf);--%>
						<%--}--%>
					<%--}, "json");--%>
				<%--}--%>
			<%--});--%>
    	<%--});--%>
    <%--})--%>


        function toOrder() {
            var _cart = $.cookie("cart_token");
            var _ticket = $.cookie("login_token");
            var addressid = $("#addressAddress").val();
            $.messager.confirm("系统提示", "您确认要生成订单吗？", function(r) {
                if (r) {
                    $.post("${ctx}/order/makeOrder.do", {
                        addressid:addressid,
                        cartId:_cart,
                        tokenId:_ticket
                    }, function(result) {
                        if(result.success){
                            swal({
                                    title: '提示',
                                    text: result.message,
                                    confirmButtonText: "确定"
                                },
                                function(){
                                    //成功则跳转到订单表
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
