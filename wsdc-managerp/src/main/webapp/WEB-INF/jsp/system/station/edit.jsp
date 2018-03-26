<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/common.jspf"%>
<script type="text/javascript" src="${ctx}/js/system/station/edit.js"></script>

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
	
	<form id="form" name="form" class="form-horizontal" method="post"
		action="${ctx}/station/editEntity.shtml">
		<input type="hidden" class="form-control checkCust" value="${station.id}"
			name="stationFormMap.id" id="id">
		
		<section class="panel panel-default">
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">所属分类</label>
				<div class="col-sm-9">
					<select id="classify_id" name="stationFormMap.classify_id" required
						class="form-control m-b">
					</select>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">链接</label>
				<div class="col-sm-9">
					<input type="url" class="form-control"
						placeholder="请输入链接" name="stationFormMap.url" id="url" value="${station.url}"/>
				</div>
			</div>
		
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="panel-body">
				<div class="form-group">
					<label class="col-sm-3 control-label">站点名称</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" minlength="2" maxlength="50" required
							placeholder="请输入站点名称" name="stationFormMap.name" id="name" value="${station.name}"/>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">站点区域</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" minlength="2" maxlength="100"
							placeholder="请输入站点区域" name="stationFormMap.area" id="area" value="${station.area}"/>
					</div>
				</div>
			</div>
			<footer class="panel-footer text-right bg-light lter">
			<button type="submit" class="btn btn-success btn-s-xs">提交</button>
		</footer> 
	</section>
	</form>
	<script type="text/javascript">
		onloadurl();
		getStationClassify("${station.classify_id}");
	</script>
</body>
</html>