<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/rider/list.js"></script>

	<header class="panel-heading">
		<div class="doc-buttons">
			<c:forEach items="${res}" var="key">
				${key.description}
			</c:forEach>
		</div>
		
		<div class="m-b-md">
		<form class="form-inline" role="form" id="searchForm"
			name="searchForm">
			<div class="form-group">
				<label class="control-label"> <span
					class="h4 font-thin v-middle">姓名:</span></label> <input
					class="input-medium ui-autocomplete-input" id="name"
					name="riderFormMap.name">
					
				<label class="control-label"> <span
					class="h4 font-thin v-middle">派送区域:</span></label> <input
					class="input-medium ui-autocomplete-input" id="area"
					name="riderFormMap.area">
					
				<label class="control-label"> <span
					class="h4 font-thin v-middle">手机号码:</span></label> <input
					class="input-medium ui-autocomplete-input" id="mobile"
					name="riderFormMap.mobile">
					
				<label class="control-label"> <span
					class="h4 font-thin v-middle">所属站点:</span></label>
					<select id="station_id" name="riderFormMap.station_id"></select>
			</div>
			<a href="javascript:void(0)" class="btn btn-default" id="search">查询</a>
			<a href="javascript:void(0);" class="btn btn-default" id="reset">重置</a>
			<a href="javascript:exportGrid.exportData('/rider/exportRider.shtml')" class="btn btn-info" >导出excel</a>
		</form>
	</div>
	</header>
	<div class="table-responsive">
		<div id="paging" class="pagclass"></div>
	</div>

