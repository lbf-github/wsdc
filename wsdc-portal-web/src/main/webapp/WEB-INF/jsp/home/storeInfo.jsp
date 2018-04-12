<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <link rel="stylesheet" type="text/css" href="${ctx}/js/sweetalert/sweetalert.css">
      <%--<link href="${ctx}css/css.css" rel="stylesheet" type="text/css" />--%>
      <link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.3.3/themes/default/easyui.css">
      <link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.3.3/themes/icon.css">
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
                    <li><img src="<%=basePath %>${list.foodic}" />
                        <%--<a href="<%=basePath %>thing/show.do?id=${list.menuid}">加入购物车</a>--%>
                        <input type="hidden" id="menuId" value="${list.menuid}">
                        <input type="button" class="buy" value="加入购物车" onclick="addCart1(${list.menuid})"/>

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
    <script type="text/javascript" src="${ctx}/js/sweetalert/sweetalert.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.3.3/plugins/jquery.messager.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.3.3/jquery.cookie.js"></script>
    <script type="text/javascript">


            function addCart1(menuId) {
                var _ticket = $.cookie("login_token");
                var _cart = $.cookie("cart_token");
                $.messager.confirm("系统提示", "您确认要加入购物车吗？", function(r) {
                    if (r) {
                        $.post("${ctx}/cart/addCart.do", {
                            menuid:menuId,
                            tokenId:_ticket,
                            cartId:_cart
                        }, function(result) {
                            <%--$.messager.alert("系统提示", result.mgf, "info", function () {--%>
                            <%--location.href='${basePath}buy/getbuying.do';--%>
                            <%--});--%>

                            if(result.success){
                                swal({
                                        title: '提示',
                                        text: result.message,
                                        confirmButtonText: "确定"
                                    },
                                    function(){

                                    });
                            }else{
                                swal({
                                        title: '提示',
                                        text: result.message,
                                        confirmButtonText: "确定"
                                    },
                                    function(){

                                        location.href = "http://localhost:8089/wsdc-sso";

                                    });
                            }
                        }, "json");
                    }
                });

            }


      <%--$(function(){--%>

          <%--$(".buy").click(function(){--%>
              <%--var menuId=$("#menuId").val();--%>
              <%--var _ticket = $.cookie("login_token");--%>
              <%--var _cart = $.cookie("cart_token");--%>
              <%--$.messager.confirm("系统提示", "您确认要加入购物车吗？", function(r) {--%>
                  <%--if (r) {--%>
                      <%--$.post("${ctx}/cart/addCart.do", {--%>
                          <%--menuid:menuId,--%>
                          <%--tokenId:_ticket,--%>
                          <%--cartId:_cart--%>
                      <%--}, function(result) {--%>
                          <%--&lt;%&ndash;$.messager.alert("系统提示", result.mgf, "info", function () {&ndash;%&gt;--%>
                              <%--&lt;%&ndash;location.href='${basePath}buy/getbuying.do';&ndash;%&gt;--%>
                          <%--&lt;%&ndash;});&ndash;%&gt;--%>
                          <%--alert(result);--%>
                          <%--if(result.success){--%>
                              <%--swal({--%>
                                      <%--title: '提示',--%>
                                      <%--text: data.message,--%>
                                      <%--confirmButtonText: "确定"--%>
                                  <%--},--%>
                                  <%--function(){--%>

                                  <%--});--%>
                          <%--}else{--%>
                              <%--swal({--%>
                                      <%--title: '提示',--%>
                                      <%--text: data.message,--%>
                                      <%--confirmButtonText: "确定"--%>
                                  <%--},--%>
                                  <%--function(){--%>

                                          <%--location.href = "http://localhost:8089/wsdc-sso";--%>

                                  <%--});--%>
                          <%--}--%>
                      <%--}, "json");--%>
                  <%--}--%>
              <%--});--%>
          <%--});--%>
      <%--})--%>

  </script>
  </body>
</html>
