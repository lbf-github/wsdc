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
                <div class="map"><b>菜单修改</b></div>
                
                <form class="layui-form" id="edit" method="post">
                
  <div class="layui-form-item">
      <input type="hidden" id="foodic" name="foodic" value="${myMenu.foodic}" />
      <button type="button" class="layui-btn" id="test1">上传图片</button>
      <div class="layui-upload-list">
          <img class="layui-upload-img" id="demo1" style="width:100px">
          <p id="demoText"></p>
      </div>
  </div>
<div class="layui-form-item">
    <label class="layui-form-label">名称</label>
    <div class="layui-input-block">
      <input id="foodname" name="foodname" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input" type="text" value="${myMenu.foodname}">
        <input id="menuid" name="menuid" type="hidden" value="${myMenu.menuid}">
    </div>
  </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">单价</label>
                        <div class="layui-input-block">
                            <input id="foodprices" name="foodprices" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input" type="text" value="${myMenu.foodprices}">
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
				url : "${ctx}/storeMenu/addOrUpdate.do?tokenId="+_ticket,
				onSubmit : function() { },
				success : function(result) {
					var result = eval('(' + result + ')');
					$.messager.confirm("系统提示", result.message,function (r) {
					    if(r){
                            location.href = "${ctx}/storeMenu/toMyMenu.do?tokenId="+_ticket;
                        }

                    });
				}
			});  
    		 return false;
    	}) 
});

 
</script>
    <script>
        layui.use('upload', function(){
            var $ = layui.jquery
                , upload = layui.upload;

            //普通图片上传
            var uploadInst = upload.render({
                elem: '#test1'
                ,accept:'images'
                ,auto:true
                ,size:102400//上传附件大小
                ,url: '../upload.do'
                ,before: function(obj){
                    //预读本地文件示例，不支持ie8
                    obj.preview(function(index, file, result){
                        $('#demo1').attr('src', result); //图片链接（base64）
                    });
                }
                ,done: function(res){
                    //如果上传失败
                    if(res.code > 0){
                        return layer.msg('上传失败');
                    }
                    $("#foodic").val(res.data.src);
                    return layer.msg('上传成功');
                }
                ,error: function(){
                    //演示失败状态，并实现重传
                    var demoText = $('#demoText');
                    demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
                    demoText.find('.demo-reload').on('click', function(){
                        uploadInst.upload();
                    });
                }
            });

        });
    </script>
  </body>
</html>
