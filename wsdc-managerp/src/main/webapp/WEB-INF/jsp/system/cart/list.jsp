<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/cart/list.js"></script>
	<div class="m-b-md">
		<form class="form-inline" role="form" id="searchForm"
			name="searchForm">
			<div class="form-group">
				<label class="control-label"> <span
					class="h4 font-thin v-middle">商品名称:</span></label> <input
					class="input-medium ui-autocomplete-input" id="item_nm"
					name="cartFormMap.item_nm">
					
				<label class="control-label"> <span
					class="h4 font-thin v-middle">手机号码:</span></label> <input
					class="input-medium ui-autocomplete-input" id="mobile"
					name="cartFormMap.mobile">
					
				<label class="control-label"> <span
					class="h4 font-thin v-middle">昵称:</span></label> <input
					class="input-medium ui-autocomplete-input" id="nickname"
					name="cartFormMap.nickname">
			</div>
			<a href="javascript:void(0)" class="btn btn-default" id="search">查询</a>
			<a href="javascript:void(0);" class="btn btn-default" id="reset">重置</a>
		</form>
	</div>
	<div class="table-responsive" style="width: 1100px">
		<div id="paging" class="pagclass"></div>
	</div>
