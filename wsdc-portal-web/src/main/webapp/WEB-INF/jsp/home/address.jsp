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
                <div class="map"><b>我的地址</b></div>

                <%--<div class="layui-input-block">--%>
                    <%--<input class="layui-btn layui-btn-normal" lay-submit  lay-filter="demo1"  type="submit" value="修改"></input>--%>
                <%--</div>--%>

               <div class="layui-form">
  <table class="layui-table">
    <thead>
      <tr>
      	<th>联系人</th>
        <th>性别</th>
        <th>联系电话</th>
        <th>收货地址</th>
        <th>操作</th>
      </tr> 
    </thead>
    <tbody>
    <c:forEach var="list"  items="${addressList}">
      <tr>
      	<td>${list.ucontact}</td>
        <td>
        <c:if test="${list.usex==0}">男</c:if>
            <c:if test="${list.usex==1}">女</c:if>
        </td>
        <td>${list.utel}</td>
        <td>${list.uaddress}</td>
        <td><input type="button" class="buy" value="编辑" onclick="updateAddress(${list.addressid})"/>　<input type="button" class="buy" value="删除" onclick="deleteAddress(${list.addressid})"/></td>
      </tr>
      </c:forEach>
    </tbody>

  </table>

            </div>
                <div class="layui-input-block">
                    <input class="layui-btn layui-btn-normal" lay-submit  lay-filter="demo1"  type="button" value="添加地址" onclick="toInsert()"></input>
                </div>
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

    function deleteAddress(addressId) {

        $.messager.confirm("系统提示", "您确认要删除收货地址吗？", function(r) {
            if (r) {
                $.post("${ctx}/address/deleteAddress.do", {
                    addressid:addressId
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

//跳转编辑页面
    function updateAddress(addressId) {

        $.messager.confirm("系统提示", "您确认要修改收货地址吗？", function(r) {
            if (r) {
                location.href = "${ctx}/address/toUpdate.do?addressId="+addressId;
            }
        });

    }
    //跳转编辑页面 功能为添加
    function toInsert() {

        $.messager.confirm("系统提示", "您确认要添加收货地址吗？", function(r) {
            if (r) {
                location.href = "${ctx}/address/toInsert.do";
            }
        });

    }

    </script>
  </body>
</html>
