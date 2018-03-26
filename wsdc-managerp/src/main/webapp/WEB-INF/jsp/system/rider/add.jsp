<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf" %>
<script type="text/javascript" src="${ctx}/js/system/rider/add.js"></script>
<script type="text/javascript" src="${ctx}/js/My97DatePickerBeta/My97DatePicker/WdatePicker.js"></script>
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
		action="${ctx}/rider/addEntity.shtml">
		<section class="panel panel-default">
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">选择所属站点</label>
				<div class="col-sm-9">
					<select id="station_id" name="riderFormMap.station_id" class="form-control m-b"
						tabindex="-1" required>
					</select>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">姓名</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" minlength="2" maxlength="20" required
						placeholder="请输入姓名" name="riderFormMap.name">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">性别</label>
				<div class="col-sm-9">
					<div class="btn-group m-r">
						<button data-toggle="dropdown"
							class="btn btn-sm btn-default dropdown-toggle">
							<span class="dropdown-label">男</span> <span class="caret"></span>
						</button>
						<ul class="dropdown-menu dropdown-select">
							<li class=""><a href="#"><input type="radio"
									name="riderFormMap.sex" value="1" checked="checked">男</a></li>
							<li class="active"><a href="#"><input type="radio"
									name="riderFormMap.sex" value="2">女</a></li>
							<li class=""><a href="#"><input type="radio"
									name="riderFormMap.sex" value="0">未知</a></li>
						</ul>
					</div>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">年龄</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" digits="true" min="1" max="999" 
						placeholder="请输入数字" name="riderFormMap.age" id="age">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">派送地区</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" minlength="2" maxlength="100" required
						placeholder="请输入派送地区" name="riderFormMap.area">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">手机号码</label>
				<div class="col-sm-9">
					<input type="text" class="form-control isMobile" 
						placeholder="请输入手机号码" name="riderFormMap.mobile" id="mobile">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">身份证号</label>
				<div class="col-sm-9">
					<input type="text" class="form-control isIDCard" 
						placeholder="请输入身份证号码" name="riderFormMap.id_num" id="id_num">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">入职时间</label>
				<div class="col-sm-9">
					<input class="Wdate" type="text" onClick="WdatePicker()" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" 
						placeholder="请选择入职日期" name="riderFormMap.entry_date" id="entry_date" />
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