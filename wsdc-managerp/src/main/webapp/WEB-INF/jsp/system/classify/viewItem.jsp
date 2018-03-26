<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/common/common.jspf" %>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/classify/viewItem.js"></script>
<style type="text/css">
.class1 {display:inline;} 
.class2 {display:inline;} 
</style>
	<header class="panel-heading">
		<form class="form-inline" role="form" id="searchForm">
			<div class="m-select">
				<div class="class1" id="shows">
					<input value="${id}" type="hidden" id="itemId">
					<input id="deleteItemClassify" type="button" class="btn btn-danger marR10" value="删除"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label"> <span
					class="h4 font-thin v-middle">产品名称:</span></label> <input
					class="input-medium ui-autocomplete-input" id="item_nm"
					name="classifyFormMap.item_nm">
			</div>
			
			<a href="javascript:void(0)" class="btn btn-default" id="search">查询</a>
			<a href="javascript:void(0);" class="btn btn-default" id="reset">重置</a>
		</form>
	</header>
	<div class="table-responsive">
		<div id="paging" class="pagclass" style="width: 100%; height: 100%;"></div>
	</div>

