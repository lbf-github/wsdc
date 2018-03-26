<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/itemProduct/list.js"></script>

	<header class="panel-heading">
		<form class="form-inline" role="form" id="searchForm">
			<div class="doc-buttons">
				<c:forEach items="${res}" var="key">
					${key.description}
				</c:forEach>
			</div>
			<div class="form-group">
				<label>商品名称:</label>
				<input class="input-medium ui-autocomplete-input" id="item_nm" name="itemProductFormMap.item_nm" />
			</div>
			
			<a href="javascript:void(0)" class="btn btn-default" id="search">查询</a>
			<a href="javascript:void(0);" class="btn btn-default" id="reset">重置</a>
		</form>
	</header>
	
	<div class="table-responsive">
		<div id="paging" class="pagclass"></div>
	</div>

