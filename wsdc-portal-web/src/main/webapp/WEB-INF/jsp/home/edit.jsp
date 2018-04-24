<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	pageContext.setAttribute("basePath", basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
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
                <div class="map"><b>修改资料</b></div>
                
                <form class="layui-form" id="edit" method="post">
                
  <div class="layui-form-item">
    <label class="layui-form-label">账户</label>
    <div class="layui-input-block">
      ${user.account }
    </div>
  </div>
<div class="layui-form-item">
    <label class="layui-form-label">姓名</label>
    <div class="layui-input-block">
      <input id="username" name="username" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input" type="text" value="${user.username}">
        <input id="userid" name="userid" type="hidden" value="${user.userid}">
    </div>
  </div> 
  <div class="layui-form-item">
    <label class="layui-form-label">性别</label>
    <div class="layui-input-block">
        <c:if test="${user.sex==0}">
            <input name="sex" value="0" title="男" checked="checked" type="radio">
            <input name="sex" value="1" title="女"  type="radio">
        </c:if>
        <c:if test="${user.sex==1}">
            <input name="sex" value="0" title="男" type="radio">
            <input name="sex" value="1" title="女" checked="checked" type="radio">
        </c:if>

    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">联系电话</label>
    <div class="layui-input-block">
      <input name="tel"  lay-verify="required|phone" placeholder="请输入" autocomplete="off" class="layui-input" type="text" value="${user.tel }">
    </div>
  </div>
  <%--<div class="layui-form-item">--%>
    <%--<label class="layui-form-label">送餐地址</label>--%>
    <%--<div class="layui-input-block">--%>
      <%--<input name="address" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input" type="text" value="${client.address }">--%>
    <%--</div>--%>
  <%--</div>--%>
  <div class="layui-form-item">
    <label class="layui-form-label">邮箱</label>
    <div class="layui-input-block">
      <input name="email" lay-verify="email" placeholder="请输入邮箱" autocomplete="off" class="layui-input" type="text" value="${user.email }">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">&nbsp;</label>
    <div class="layui-input-block">
      <input class="layui-btn layui-btn-normal" lay-submit  lay-filter="demo1"  type="submit" value="修改"></input>
    </div>
  </div>
  </form>
                
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
    <script type="text/javascript">
//Demo
layui.use(['form', 'layedit', 'laydate','layer'], function(){
     var form = layui.form
     ,layedit = layui.layedit
     ,laydate = layui.laydate
     ,layer=layui.layer;
   
   //监听提交
     form.on('submit(demo1)', function(data){
         var _ticket = $.cookie("login_token");
	       $("#edit").form("submit",{
				url : "${ctx}/protalUser/editUser.do?tokenId="+_ticket,
				onSubmit : function() { },
				success : function(result) {
					var result = eval('(' + result + ')');
					$.messager.alert("系统提示", result.message);
				}
			});  
    		 return false;
    	}) 
});

 
</script>
  </body>
</html>
