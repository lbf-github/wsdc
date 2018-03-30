<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/storeProtal/list.js"></script>

	<header class="panel-heading">
	<div class="doc-buttons">
		<c:forEach items="${res}" var="key">
			${key.description}
		</c:forEach>
	</div>
	</header>

<form class="form-inline" role="form" id="searchForm"
	  name="searchForm">
	<div class="form-group">
		<label class="control-label"> <span
				class="h4 font-thin v-middle">商家名称:</span></label> <input
			class="input-medium ui-autocomplete-input" id="storeName"
			name="protalStoreFormMap.storeName">
	</div>
	<a href="javascript:void(0)" class="btn btn-default" id="search">查询</a>
	<a href="javascript:void(0);" class="btn btn-default" id="reset">重置</a>
	<!-- <a href="javascript:void(0)" class="btn btn-warning" id="callback_test">表格回调函数</a> -->
</form>
	<div class="table-responsive">
		<div id="paging" class="pagclass"></div>
	</div>

