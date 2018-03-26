<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/searchRecord/list.js"></script>
	<div class="m-b-md">
		<form class="form-inline" role="form" id="searchForm"
			name="searchForm">
			<div class="form-group">
				<label class="control-label"> <span
					class="h4 font-thin v-middle">搜索词:</span></label> <input
					class="input-medium ui-autocomplete-input" id="key_word"
					name="searchRecordFormMap.key_word">
			</div>
			<a href="javascript:void(0)" class="btn btn-default" id="search">查询</a>
			<a href="javascript:void(0);" class="btn btn-default" id="reset">重置</a>
		</form>
	</div>
	
	<div class="table-responsive" style="width: 1100px">
		<div id="paging" class="pagclass"></div>
	</div>
