<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf" %>
<script type="text/javascript" src="${ctx}/js/system/headline/add.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/js/ueditor/themes/default/css/ueditor.css"/>
<script type="text/javascript" src="${ctx}/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/js/ueditor/ueditor.all.js"></script>  
<style type="text/css">
	.col-sm-3 {
		width: 15%;
		float: left;
	}

	.col-sm-9 {
		width: 85%;
		float: left;
	}

	label[class^="btn btn-default"]{
		margin-top: -4px;
	}

</style>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="form" name="form" class="form-horizontal" method="post" action="${ctx}/headline/addEntity.shtml">
		<section class="panel panel-default">
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">内容</label>
				<div class="col-sm-9">
					<textarea rows="4" cols="68" class="form-control" minlength="1" maxlength="100" required
						name="headlineFormMap.title" id="title" placeholder="请输入头条内容"></textarea>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">选择地区</label>
				<div class="col-sm-9" id="area">
					<select id="province_id" onchange="getCities();" name="headlineFormMap.province_id" class="form-control m-b"
						tabindex="-1" required>
					</select>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label" >链接地址</label>
				<div class="col-sm-9">
					<input name="headlineFormMap.url" class="form-control" type="url" placeholder="请输入链接地址"/>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">详情</label>
					<textarea name="headlineFormMap.detail" id="detail" style="height:300px;width:80%;float:left"></textarea>
	   				<script type="text/javascript">
						var ue = UE.getEditor('detail');
					</script>
				</div> 
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">排序</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" name="headlineFormMap.seq" digits="true" min="1" max="99999999" placeholder="请输入数字(数字越大越靠前)"/>
				</div>
			</div>
		</div>
		<footer class="panel-footer text-right bg-light lter">
			<button type="submit" class="btn btn-success btn-s-xs">提交</button>
		</footer> 
		</section>
	</form>
	<script type="text/javascript">
	</script>
</body>
</html>