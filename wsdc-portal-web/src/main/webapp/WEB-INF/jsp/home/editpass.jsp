<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                <div class="map"><b>修改密码</b></div>
                
                <form id="reg" class="layui-form"  method="post" >
                
  <div class="layui-form-item">
    <label class="layui-form-label">用户名</label>
    <div class="layui-input-block">
      ${user.account }
    </div>
  </div>
                    <c:if test="${pwdNull==1}">
                        <div class="layui-form-item">
                            <label class="layui-form-label">原始密码</label>
                            <div class="layui-input-block">
                                <input name="old" id="old"  lay-verify="old" placeholder="请输入" autocomplete="off" class="layui-input" type="password">
                                <input id="userId" name="userId" type="hidden" value="${user.userid}">
                            </div>
                        </div>
                    </c:if>
  <%--<div class="layui-form-item">--%>
    <%--<label class="layui-form-label">原始密码</label>--%>
    <%--<div class="layui-input-block">--%>
      <%--<input name="old" id="old"  lay-verify="old" placeholder="请输入" autocomplete="off" class="layui-input" type="password">--%>
    <%--</div>--%>
  <%--</div>--%>
  <div class="layui-form-item">
    <label class="layui-form-label">新密码</label>
    <div class="layui-input-block">
      <input name="pwd" id="pwd" lay-verify="pwd"  placeholder="请输入" autocomplete="off" class="layui-input" type="password">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">确认新密码</label>
    <div class="layui-input-block">
      <input name="twopwd" id="twopwd" lay-verify="twopwd" placeholder="请输入" autocomplete="off" class="layui-input" type="password">
    </div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">&nbsp;</label>
    <div class="layui-input-block">
      <input class="layui-btn layui-btn-normal" id="sub" type="submit" lay-filter="demo1"  lay-submit value="提交"></input>
    </div>
  </div>
  </form>
                
            </div>
            </td>
        </tr>
    </table>
    <script src="${ctx}/js/jquery-validation/jquery.form.js" type="text/javascript"></script>
    <script src="${ctx}/js/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/js/sweetalert/sweetalert.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.3.3/plugins/jquery.messager.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.3.3/jquery.cookie.js"></script>

        	<script type="text/javascript">
                <%--action="${ctx}/protalUser/updatePwd.do"--%>



                //ajax提交表单





//Demo
layui.use(['form', 'layedit', 'laydate','layer'], function(){
     var form = layui.form
     ,layedit = layui.layedit
     ,laydate = layui.laydate
     ,layer=layui.layer;
     
   //自定义验证规则
     form.verify({
       <%--old:function(value){--%>
           <%----%>
    	   <%--if("${client.pwd}"!=value)--%>
    		   <%--return "原始密码输入有误";--%>
       <%--}--%>
       pwd: [/(.+){6,12}$/, '密码必须6到12位']
       ,twopwd: function(value){
         if($("#pwd").val()!=value)
           return "两次输入的密码不一至";
       }
     });
   
   //监听提交
     form.on('submit(demo1)', function(data){
         var userId = $("#userId").val();
	       $("#reg").form("submit",{
				url : "${ctx}/protalUser/updatePwd.do?userId="+userId,
				onSubmit : function() { },
				success : function(result) {
					var result = eval('(' + result + ')');
				    if (result.success) {
				    	$.messager.alert("系统提示", result.message, "info", function () {
				    		location.href='${ctx}/index.do';
				        }); 
						
					} else {
						$.messager.alert("系统提示", result.message);
						return;
					}  
				}
			});  
    		 return false;
    	}) 
});

 
</script>
    <%@ include file="foot.jsp" %> 
    
  </body>
</html>
