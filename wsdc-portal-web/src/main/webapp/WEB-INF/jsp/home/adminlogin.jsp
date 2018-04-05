<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	pageContext.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="<%=basePath %>js/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=basePath %>css/style.css" />

<script src="<%=basePath %>js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="<%=basePath %>js/layer/layer.js" type="text/javascript"></script>
<script src="<%=basePath %>js/layui/layui.js"  type="text/javascript"></script>
<link rel="stylesheet" href="<%=basePath %>js/layui/css/layui.css" />
<link class="uiTheme" rel="stylesheet" type="text/css" href="<%=basePath %>js/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>js/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="<%=basePath %>js/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery-easyui-1.3.3/jquery.cookie.js"></script>
<title>登录页面</title>
</head>
<body class="login_body">
	<form class="layui-form" id="sqlform" method="post">
		<div class="login_div">
			<h1>欢迎登录</h1>
			<ul class="list-unstyled">
				<li style="height: 40px;">
					用户名： <input type="text" name="login"  lay-verify="required" />
				</li>
				<li style="height: 40px;">
					密&nbsp;&nbsp;&nbsp;码： <input type="password" name="pwd"  lay-verify="required" />
				</li>
				<li style="height: 40px;">
					角&nbsp;&nbsp;&nbsp;色：
					<input name="author" value="${basePath}admin/login.do" title="管理员" checked="" type="radio">
      				<input name="author" value="${basePath}shop/login.do" title="商家" type="radio">
				</li>
				<li style="height: 40px;">
					<ul class="list-inline">
						<li><input lay-submit  lay-filter="demo1"  type="submit" value="登录" class="btn btn-primary" /></li>
						<li><input onclick="location.href='${basePath}index.do'"  type="button" value="返回首页" class="btn btn-primary" /></li>
					</ul>
				</li>
			</ul>
		</div>
	</form>
	<!-- js -->
	<script type="text/javascript">
		function goRegister(){
			window.location.href="index.jsp";
		}
	</script>
	<script type="text/javascript">
//Demo
layui.use(['form', 'layedit', 'laydate','layer'], function(){
     var form = layui.form
     ,layedit = layui.layedit
     ,laydate = layui.laydate
     ,layer=layui.layer;
   
   //监听提交
     form.on('submit(demo1)', function(data){
	       $("#sqlform").form("submit",{
				url : $("input[name='author']:checked").val(),
				onSubmit : function() { },
				success : function(result) {
					var result = eval('(' + result + ')');
					if (result.success) {
							if("${basePath}admin/login.do"==$("input[name='author']:checked").val())
								location.href='${basePath}shop/admin_list.do';
							else
								location.href='${basePath}thing/shop_list.do';
						} else {
							$.messager.alert("系统提示", result.mgf);
						}
				}
			});  
    		 return false;
    	}) 
});

 
</script>
	<!-- 导入js -->
	<script type="text/javascript" src="<%=basePath %>js/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>