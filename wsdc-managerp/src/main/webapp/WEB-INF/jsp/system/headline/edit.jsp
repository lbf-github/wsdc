<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/common.jspf"%>
<script type="text/javascript" src="${ctx}/js/system/headline/edit.js"></script>
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
</style>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	
	<form id="form" name="form" class="form-horizontal" method="post" action="${ctx}/headline/editEntity.shtml">
		<input type="hidden" class="form-control" value="${headline.id}" name="headlineFormMap.id" id="id">
		<section class="panel panel-default">
		<div class="panel-body">
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">内容</label>
				<div class="col-sm-9">
					<textarea rows="4" cols="68" class="form-control" minlength="1" maxlength="100" required
						placeholder="请输入头条内容" name="appUpdateFormMap.title" id="title">${headline.title}</textarea>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">选择地区</label>
				<div class="col-sm-9" id="area">
					<select id="province_id" required onchange="getCities();" name="headlineFormMap.province_id" class="form-control m-b"
						tabindex="-1">
					</select>
					<select id="city_id" required name="adFormMap.city_id" class="form-control m-b city_id" tabindex="-1"></select>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label" >链接地址</label>
				<div class="col-sm-9">
					<input class="form-control" placeholder="请输入链接地址" name="headlineFormMap.url" value="${headline.url }" type="url" />
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
					<input class="form-control" type="text" name="headlineFormMap.seq" placeholder="请输入数字" value="${headline.seq }" digits="true" min="1" max="99999999" />
				</div>
			</div>
		</div>
		<footer class="panel-footer text-right bg-light lter">
			<button type="submit" class="btn btn-success btn-s-xs">提交</button>
		</footer> 
		</section>
		<script type="text/javascript">
			$(function(){
				$('#detail').val('${headline.detail}');
				ue.ready(function() {
					ue.setContent('${headline.detail}');
				});
			});
			getProvince("${headline.province_id}");
			getCity("${headline.province_id}","${headline.city_id}");
		</script>
	</form>
</body>
</html>