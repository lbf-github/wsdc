<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/activity/list.js"></script>

	<header class="panel-heading">
		<div class="doc-buttons">
			<c:forEach items="${res}" var="key">
				<span>${key.description}</span>
			</c:forEach>
		</div>
		<form class="form-inline" role="form" id="searchForm">
			<div class="form-group">
				<label>活动标题:</label>
				<input class="input-medium ui-autocomplete-input" id="title" name="activityFormMap.title" />
			</div>
			
			<div class="form-group">
				<label>是否热门:</label>
				<select id="hot_yn" style="height:26" name="activityFormMap.hot_yn">
					<option value="">请选择</option>
					<option value="0">非热门</option>
					<option value="1">热门</option>
				</select>
			</div>
			
			<div class="form-group" id="province">
				<label>选择地区:</label>
				<select id="province_id" style="height:26" onchange="getCities();" name="activityFormMap.province_id"></select>
			</div>
			
			<div class="form-group" id="city" style="display:none;"></div>
			
			<a href="javascript:void(0)" class="btn btn-default" id="search">查询</a>
			<a href="javascript:void(0);" class="btn btn-default" id="reset">重置</a>
		</form>
	</header>
	<div class="table-responsive">
		<div id="paging" class="pagclass"></div>
	</div>
