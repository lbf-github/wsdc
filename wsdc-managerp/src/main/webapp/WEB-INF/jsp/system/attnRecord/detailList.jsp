<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jspf"%>
<script type="text/javascript">
    var rider_id = "${rider_id}";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/attnRecord/detailList.js"></script>
	<div class="m-b-md">
		<form class="form-inline" role="form" id="searchForm"
			name="searchForm">
			<div class="form-group">
				<span class="h4 font-thin v-middle">是否关注:</span></label> 
				<select id="attention_yn" style="height:26" name="attnRecordFormMap.attention_yn">
					<option value="">请选择</option>
					<option value="0">未关注</option>
					<option value="1">已关注</option>
				</select>
			</div> 
			<a href="javascript:void(0)" class="btn btn-default" id="search">查询</a>
			<a href="javascript:void(0);" class="btn btn-default" id="reset">重置</a>
		</form>
	</div>
	<div class="table-responsive" style="width: 1100px">
		<div id="paging" class="pagclass"></div>
	</div>
