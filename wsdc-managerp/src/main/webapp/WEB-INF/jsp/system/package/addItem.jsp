<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/common/common.jspf" %>	
	<input value="${id }" id="id" type="hidden">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/package/addItem.js"></script>
	<header class="panel-heading">
		<button type="button" id="add" class="btn btn-primary marR10">添加</button>
		<div class="m-select">
			<div class="class1" id="shows">
				标题：<input id="name" style="border: 1px solid" />
				<input id="append" type="button" class="j-select-sure" value="确定"/>
			</div></br>
		</div>
		
	</header>
	
	<div class="table-responsive">
		<div id="paging" class="pagclass"></div>
	</div>
