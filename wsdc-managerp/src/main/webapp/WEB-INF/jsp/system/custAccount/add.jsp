<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf"%>
<script type="text/javascript" src="${ctx}/js/system/custAccount/add.js">
	
</script>
<style type="text/css">
	.col-sm-3 {
		width: 15%;
		float: left;
		text-align: right;
	}

	.col-sm-9 {
		width: 85%;
		float: left;
		text-align: left;
	}

	label[class^="btn btn-default"] {
		margin-top: -4px;
	}
</style>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="form" name="form" class="form-horizontal" method="post"
		action="${ctx}/custAccount/addEntity.shtml">
		<section class="panel panel-default">
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">手机号码</label>
				<div class="col-sm-9">
					<input type="text" class="form-control isMobile" 
						placeholder="请输入手机号码" name="custAccountFormMap.mobile" id="mobile">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">密码</label>
				<div class="col-sm-9">
					<input type="password" class="form-control checkPwd" minlength="3" maxlength="20" required
						placeholder="请输入密码" name="custAccountFormMap.password" id="password">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">注册来源</label>
				<div class="col-sm-9">
					<div class="btn-group m-r">
						<button data-toggle="dropdown"
							class="btn btn-sm btn-default dropdown-toggle">
							<span class="dropdown-label">微信</span> <span class="caret"></span>
						</button>
						<ul class="dropdown-menu dropdown-select">
							<li class=""><a href="#"><input type="radio"
									name="custAccountFormMap.reg_from" value="1" checked="checked">其他</a></li>
							<li class="active"><a href="#"><input type="radio"
									name="custAccountFormMap.reg_from" value="0">微信</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<footer class="panel-footer text-right bg-light lter">
		<button type="submit" class="btn btn-success btn-s-xs">提交</button>
		</footer> </section>
	</form>
</body>
</html>