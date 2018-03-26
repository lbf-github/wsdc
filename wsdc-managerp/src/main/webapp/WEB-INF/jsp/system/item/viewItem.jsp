<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/common/common.jspf" %>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/item/viewItem.js"></script>
<style type="text/css">
.class1 {display:inline;} 
.class2 {display:inline;} 
</style>
	<header class="panel-heading">
		<div class="doc-buttons">
			<c:forEach items="${res}" var="key">
				${key.description}
			</c:forEach>
		</div>
		<form class="form-inline" role="form" id="searchForm">
			<div class="m-select">
				<div class="class1" id="shows">
					<input value="${id}" type="hidden" id="itemId">
					天数：<select id="select" onchange="change()">
							<option value="">请选择天数</option>
							<option value="1">第1天</option>
							<option value="2">第2天</option>
							<option value="3">第3天</option>
							<option value="4">第4天</option>
							<option value="5">第5天</option>
							<option value="6">第6天</option>
							<option value="7">第7天</option>
						</select>
						<input id="deleteItem" type="button" class="btn btn-danger marR10" value="删除"/>
				</div>
			</div>
		</form>
	</header>
	
	<div class="table-responsive">
		<div id="paging" class="pagclass"></div>
	</div>

