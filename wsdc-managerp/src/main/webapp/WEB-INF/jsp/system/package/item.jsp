<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/common/common.jspf" %>	
	<input value="${id }" id="id" type="hidden">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/package/item.js"></script>

	<header class="panel-heading">
		<button type="button" id="add" class="btn btn-primary marR10">新增</button>
		<button type="button" id="delete" class="btn btn-danger marR10">删除</button>
	</header>
	
	<div class="table-responsive">
		<div id="paging" class="pagclass"></div>
	</div>

