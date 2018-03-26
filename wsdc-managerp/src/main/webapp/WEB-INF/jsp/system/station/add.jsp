<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf" %>
<script type="text/javascript" src="${ctx}/js/system/station/add.js"></script>
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
	<form id="form" name="form" class="form-horizontal" method="post"
		action="${ctx}/station/addEntity.shtml">
		<section class="panel panel-default">
		<div class="panel-body">
			
			<div class="form-group">
				<label class="col-sm-3 control-label">选择分类</label>
				<div class="col-sm-9">
					<select id="classify_id" name="stationFormMap.classify_id" class="form-control m-b" required
						tabindex="-1">
					</select>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">链接</label>
				<div class="col-sm-9">
					<input type="url" class="form-control" minlength="2" maxlength="100" required
						placeholder="请输入站点链接" name="stationFormMap.url">
				</div>
			</div>
		
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">名称</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" minlength="2" maxlength="50" required
						placeholder="请输入站点名称" name="stationFormMap.name">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">区域</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" minlength="2" maxlength="100" 
						placeholder="请输入区域" name="stationFormMap.area">
				</div>
			</div>
		</div>
		
		<footer class="panel-footer text-right bg-light lter">
			<button type="submit" class="btn btn-success btn-s-xs">提交</button>
		</footer> 
		</section>
	</form>
</body>
</html>