<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/seoCases/list.js"></script>

	<header class="panel-heading">
		<div class="doc-buttons">
			<c:forEach items="${res}" var="key">
				<span>${key.description}</span>
			</c:forEach>
		</div>
		
		<form class="form-inline" role="form" id="searchForm">
			<div class="form-group">
				<label>标题:</label>
				<input id="title" class="input-medium ui-autocomplete-input" name="seoCasesFormMap.title" />
			</div>
			<a href="javascript:void(0)" class="btn btn-default" id="search">查询</a>
			<a href="javascript:void(0);" class="btn btn-default" id="reset">重置</a>
		</form>
	</header>
	<div class="table-responsive">
		<div id="paging" class="pagclass"></div>
	</div>

