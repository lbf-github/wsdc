<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/common.jspf"%>
<script type="text/javascript" src="${ctx}/js/system/custAccount/edit.js"></script>

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
		action="${ctx}/custAccount/editEntity.shtml">
		<input type="hidden" class="form-control"
			value="${custAccount.id}" name="custAccountFormMap.id" id="id">
		<section class="panel panel-default">
		
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">手机号码</label>
				<div class="col-sm-9">
					<input type="text" class="form-control isMobile"
						placeholder="请输入手机号码" value="${custAccount.mobile}"
						name="custAccountFormMap.mobile" id="mobile">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">密码</label>
				<div class="col-sm-9">
					<input type="password" class="form-control" minlength="3" maxlength="20" required
						placeholder="请输入密码" value="${custAccount.password}"
						name="custAccountFormMap.password" id="password" />
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">注册来源</label>
				<div class="col-sm-9">
					<div class="btn-group m-r">
						<button data-toggle="dropdown"
							class="btn btn-sm btn-default dropdown-toggle">
							<span class="dropdown-label">
							<c:if test="${custAccount.reg_from eq 1}">其他</c:if>
							<c:if test="${custAccount.reg_from eq 0}">微信</c:if></span> <span class="caret"></span>
						</button>
						<ul class="dropdown-menu dropdown-select">
							<li class=""><a href="#"><input type="radio"
									name="custAccountFormMap.reg_from" value="1"
									<c:if test="${custAccount.reg_from eq 1}"> checked="checked"</c:if>>其他</a></li>
							<li class="active"><a href="#"><input type="radio"
									name="custAccountFormMap.reg_from" value="0"
									<c:if test="${custAccount.reg_from eq 0}"> checked="checked"</c:if>>微信</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<footer class="panel-footer text-right bg-light lter">
		<button type="submit" class="btn btn-success btn-s-xs">保存</button>
		</footer> </section>
	</form>
</body>
</html>