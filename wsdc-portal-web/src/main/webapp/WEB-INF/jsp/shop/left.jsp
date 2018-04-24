<%@ page language="java" import="java.util.*,com.lbf.wsdc.portal.model.*" pageEncoding="utf-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
	String path_left = request.getContextPath();
	String basePath_left = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path_left + "/";
	pageContext.setAttribute("basePath_left", basePath_left);
	request.setAttribute("thingtype2", request.getSession().getAttribute("thingtype2"));
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="leftborder">
     <div class="lefttop"><b>我的店铺</b></div>
     <%--<span>--%>
     	<%--<%if(session.getAttribute("client")==null){ %>--%>
     	<%--<form class="layui-form" id="sqlform" method="post">--%>
         <%--<div>用户：<input id="Text1" name="login" lay-verify="required" type="text" style="width:120px" /></div>--%>
         <%--<div>密码：<input id="Text2" name="pwd" lay-verify="required" type="password" style="width:120px" /></div>--%>
         <%--<div><input id="Button1" type="submit" value="登陆" lay-submit  lay-filter="loginbtn"  type="submit" /><input id="Button2" type="button" value="注册" onclick="location.href='<%=basePath_left%>client/reg.do'" /></div>--%>
         <%--</form>--%>
         <%--<%}else{--%>
	   		<%--Client c=(Client)session.getAttribute("client");--%>
		 <%--%>--%>
		 <%--<div>用户：<%=c.getLogin() %></div>--%>
		 <%--<div>电话：<%=c.getTel() %></div>--%>
		 <%--<div>邮箱：<%=c.getMail() %></div>--%>
		 <%--<div><a href="<%=basePath_left %>client/editform.do">修改资料</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="<%=basePath_left %>client/pwd.do">修改密码</a></div>--%>
		 <%----%>
		 <%--<div><a href="<%=basePath_left %>buy/my_list.do">我的订单</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="<%=basePath_left %>pinglun/my_list.do">我的点评</a></div>--%>
		 <%--<div><a href="<%=basePath_left %>buy/getbuying.do">购物车</a>&nbsp;&nbsp;&nbsp;&nbsp;<a id="exit" href="<%=basePath_left %>client/exit.do">安全退出</a></div>--%>
		 <%--<%} %>--%>
     <%--</span>--%>
	<%--<div id="singleloginIndex">--%>
		<%--<a href="http://localhost:8089/wsdc-sso">登录</a>--%>
		<%--<span>|</span>--%>
		<%--<a href="http://localhost:8089/wsdc-sso">注册</a>--%>
	<%--</div>--%>
	<div><a href="javascript:void(0)" onclick="toManager();">店铺管理</a></div>
	<div><a href="javascript:void(0)" onclick="toStoreOrder();">查看订单</a></div>
	<div><a href="javascript:void(0)" onclick="toMyMenu();">菜单详情</a></div>
	<div><a href="javascript:void(0)" onclick="back1();" >返回首页</a></div>
	<div><a id="exit" href="javascript:void(0)">安全退出</a></div>
	<%--<span>　　</span>--%>
	<%--<div id="singlelogin-t"><a href="javascript:void(0)" onclick="toBecomeStore();">我要开店</a></div>--%>

 </div>
  <%--<div class="leftborder">--%>
                    <%--<div class="lefttop"><b>菜谱分类</b></div>--%>
                    <%--<ul class="typeul">--%>
                    	<%--<c:forEach var="list"  items="${left_thingtype2}">--%>
                        <%--<li><a href="<%=basePath_left %>thing/web_list.do?thingtype2Id=${list.id}">${list.type }</a></li>--%>
                        <%--</c:forEach>--%>
                    <%--</ul>--%>
                <%--</div>--%>
	<script type="text/javascript" src="${ctx}/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/loginIndex.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.cookie.js"></script>
                <script type="text/javascript">
//Demo
layui.use(['form', 'layedit', 'laydate','layer'], function(){
     var form = layui.form
     ,layedit = layui.layedit
     ,laydate = layui.laydate
     ,layer=layui.layer;
   
   //监听提交
     form.on('submit(loginbtn)', function(data){
	       $("#sqlform").form("submit",{
				url : "${basePath_left}client/login.do",
				onSubmit : function() { },
				success : function(result) {
					var result = eval('(' + result + ')');
					if (result.success) {
							$.messager.alert("系统提示", result.mgf, "info", function () {
								location.href='${basePath}index.do';
					        }); 
						} else {
							$.messager.alert("系统提示", result.mgf);
						}
				}
			});  
    		 return false;
    	}) 
});

$("#exit").click(function(){
    $.messager.confirm("系统提示", "确认要退出吗？", function(r) {
		if (r) {
			 $.post("${ctx}/protalUser/exit.do", function(result) {
				if (result.success) {
						 $.messager.alert("系统提示", result.message, "info", function () {
							location.href='http://localhost:8081/wsdcp';
				        });
				} else {
					$.messager.alert("系统提示", result.message);
				}
			}, "json");
		}
	});
    return false; // 阻止表单自动提交事件
});

function toBecomeStore() {
    var _ticket = $.cookie("login_token");
    if(_ticket != null && _ticket != ""){
        location.href = "${ctx}/storeInfo/toRegisterStore.do?tokenId="+_ticket;
	}else{
        $.messager.alert("系统提示","请登录","info",function () {
            location.href = "http://localhost:8089/wsdc-sso";
        })
	}
}
function back1() {
    location.href="http://localhost:8081/wsdcp/index";

}

function toStoreOrder() {
    var _ticket = $.cookie("login_token");
    location.href="http://localhost:8081/wsdcp/order/toStoreOrder.do?tokenId="+_ticket;
}

function toManager() {
    var _ticket = $.cookie("login_token");
    location.href = "${ctx}/storeInfo/toSkipManager.do?tokenId="+_ticket;
}
function toMyMenu() {
    var _ticket = $.cookie("login_token");
    location.href = "${ctx}/storeMenu/toMyMenu.do?tokenId="+_ticket;
}
 
</script>
  