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
      <link rel="stylesheet" type="text/css" href="${ctx}/js/sweetalert/sweetalert.css">
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
                <div class="map"><b>店铺管理</b></div>
                
                <form class="layui-form" id="edit" method="post">
                
  <%--<div class="layui-form-item">--%>
    <%--<label class="layui-form-label">商家名称</label>--%>
    <%--<div class="layui-input-block">--%>
      <%--${user.account }--%>
    <%--</div>--%>
  <%--</div>--%>
<div class="layui-form-item">
    <label class="layui-form-label">商家名称</label>
    <div class="layui-input-block">
      <input id="storename" name="storename" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input" type="text" value="${storeinfo.storename}">
        <%--<input id="userid" name="userid" type="hidden" value="${user.userid}">--%>
    </div>
  </div>
      <div class="layui-form-item">
          <label class="layui-form-label">商家类型</label>
          <div class="layui-input-block">
          <select id="typeId" name="typeId" style="width: 150px">
              <option value="0">请选择</option>
              <c:forEach var="list" items="${storeType}">
                  <option value="${list.stypeid}">${list.typename}</option>
              </c:forEach>
          </select>
          </div>
      </div>



      <div class="layui-form-item">
          <label class="layui-form-label">地址</label>
          <div class="layui-input-block">
              <input id="address" name="address" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input" type="text" value="${storeinfo.address}">
              <%--<input id="userid" name="userid" type="hidden" value="${user.userid}">--%>
          </div>
      </div>
      <div class="layui-form-item">
          <label class="layui-form-label">起送价</label>
          <div class="layui-input-block">
              <input id="startprice" name="startprice" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input" type="text" value="${storeinfo.startprice}">
              <%--<input id="userid" name="userid" type="hidden" value="${user.userid}">--%>
          </div>
      </div>
      <div class="layui-form-item">
          <label class="layui-form-label">配送费</label>
          <div class="layui-input-block">
              <input id="transportprice" name="transportprice" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input" type="text" value="${storeinfo.transportprice}">
              <%--<input id="userid" name="userid" type="hidden" value="${user.userid}">--%>
          </div>
      </div>
      <div class="layui-form-item">
          <label class="layui-form-label">配送时间</label>
          <div class="layui-input-block">
              <input id="transporttime" name="transporttime" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input" type="text" value="${storeinfo.transporttime}">
              <%--<input id="userid" name="userid" type="hidden" value="${user.userid}">--%>
          </div>
      </div>
      <div class="layui-form-item">
          <label class="layui-form-label">优惠公告</label>
          <div class="layui-input-block">
              <input id="cheapennotice" name="cheapennotice" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input" type="text" value="${storeinfo.cheapennotice}">
              <%--<input id="userid" name="userid" type="hidden" value="${user.userid}">--%>
          </div>
      </div>
      <div class="layui-form-item">
          <label class="layui-form-label">新用户公告</label>
          <div class="layui-input-block">
              <input id="newusernotice" name="newusernotice" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input" type="text" value="${storeinfo.newusernotice}">
              <%--<input id="userid" name="userid" type="hidden" value="${user.userid}">--%>
          </div>
      </div>



  <div class="layui-form-item">
    <label class="layui-form-label">&nbsp;</label>
    <div class="layui-input-block">
      <input class="layui-btn layui-btn-normal" lay-submit  lay-filter="demo1"  type="submit" value="提交"></input>
        <input class="layui-btn layui-btn-normal" lay-submit  lay-filter="demo1"  type="button" value="开店" onclick="openStore()"></input>
        <input class="layui-btn layui-btn-normal" lay-submit  lay-filter="demo1"  type="button" value="关店" onclick="closeStore()"></input>
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
				url : "${ctx}/storeInfo/doUpdateStore.do?tokenId="+_ticket,
				onSubmit : function() { },
				success : function(result) {
					var result = eval('(' + result + ')');
					$.messager.confirm("系统提示", result.message,function (r) {
                        if(r){

//                                location.href = "http://localhost:8081/wsdcp/index.do";
                        }
                    });
				}
			});  
    		 return false;
    	}) 
});


function openStore() {
    var _ticket = $.cookie("login_token");
    $.messager.confirm("系统提示", "您确认要开始营业吗？", function(r) {
        if (r) {
            $.post("${ctx}/storeInfo/openStore.do", {
                tokenId:_ticket
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

function closeStore() {
    var _ticket = $.cookie("login_token");
    $.messager.confirm("系统提示", "您确认要停止营业吗？", function(r) {
        if (r) {
            $.post("${ctx}/storeInfo/closeStore.do", {
                tokenId:_ticket
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
