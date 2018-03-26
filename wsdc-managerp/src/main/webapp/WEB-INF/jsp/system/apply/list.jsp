<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/apply/list.js"></script>

	<header class="panel-heading">
	<div class="doc-buttons">
		<c:forEach items="${res}" var="key">
			${key.description}
		</c:forEach>
	</div>
		<!-- <div class="line line-dashed line-lg pull-in"></div>
		<form class="form-inline" role="form" id="searchForm">
			<div class="form-group">
				<label>商品包名称:</label>
				<input class="input-medium ui-autocomplete-input" name="packageFormMap.title" />
			</div>
			<a href="javascript:void(0)" class="btn btn-default" id="search">查询</a>
		</form> -->
	</header>
	
	<div class="table-responsive">
		<div id="paging" class="pagclass"></div>
	</div>

