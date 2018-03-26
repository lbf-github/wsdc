<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf" %>
<script type="text/javascript" src="${ctx}/js/system/package/list.js"></script>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="editNameForm" name="form" class="form-horizontal" method="post" action="${ctx}/package/updateTitle.shtml">
		<section class="panel panel-default">
		<div class="panel-body">
			<div class="form-group">
				<input type="hidden" name="wrapFormMap.id" value="${wrap.id }">
				<input type="hidden" name="wrapFormMap.package_id" value="${wrap.package_id }">
				<input type="hidden" name="wrapFormMap.item_id" value="${wrap.item_id }">
				<textarea rows="5" cols="70%" placeholder="请输入标题信息..." name="wrapFormMap.title">${wrap.title }</textarea>
			</div>
		</div>
		<footer class="panel-footer text-right bg-light lter">
			<button type="submit" class="btn btn-success btn-s-xs">修改</button>
		</footer> 
		</section>
	</form>
</body>
</html>