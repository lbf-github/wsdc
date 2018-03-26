<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jspf"%>
<script type="text/javascript">
    var key_word = "${key_word}";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/searchRecord/detailList.js"></script>
	<div class="m-b-md">
		<form class="form-inline" role="form" id="searchForm"
			name="searchForm">
			<div class="form-group">
				<label class="control-label"> 
				<span class="h4 font-thin v-middle">手机号码:</span></label> 
				<input class="input-medium ui-autocomplete-input" id="mobile" name="searchRecordFormMap.mobile">
				
				<span class="h4 font-thin v-middle">昵称:</span></label> 
				<input class="input-medium ui-autocomplete-input" id="nickname" name="searchRecordFormMap.nickname">
			</div>
			<a href="javascript:void(0)" class="btn btn-default" id="search">查询</a>
			<a href="javascript:void(0);" class="btn btn-default" id="reset">重置</a>
		</form>
	</div>
	<div class="table-responsive" style="width: 1100px">
		<div id="paging" class="pagclass"></div>
	</div>
