<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/msg/list.js"></script>

	<header class="panel-heading">
	<div class="doc-buttons">
		<c:forEach items="${res}" var="key">
			<span>${key.description}</span>
		</c:forEach>
		
		<form class="form-inline" role="form" id="searchForm"
			name="searchForm">
			<div class="form-group">
				<label class="control-label"> 
				<span class="h4 font-thin v-middle">发件人昵称:</span></label> 
				<input class="input-medium ui-autocomplete-input" id="userName" name="msgFormMap.userName">
					
				<label class="control-label"> 
					<span class="h4 font-thin v-middle">收件人昵称:</span>
				</label> 
				<input class="input-medium ui-autocomplete-input" id="nickname" name="msgFormMap.nickname">
				
				<label class="control-label"> 
				<span class="h4 font-thin v-middle">标题:</span></label> 
				<input class="input-medium ui-autocomplete-input" id="title" name="msgFormMap.title">
				
				<label class="control-label"> 
				<span class="h4 font-thin v-middle">是否已读:</span></label>
				<select id="r_yn" name="msgFormMap.r_yn"  style="height:26">
					<option value="">请选择</option>
					<option value="0">未读</option>
					<option value="1">已读 </option>
				</select> 
			</div>
			<a href="javascript:void(0)" class="btn btn-default" id="search">查询</a>
			<a href="javascript:void(0);" class="btn btn-default" id="reset">重置</a>
		</form>
	</div>
	</header>
	<div class="table-responsive">
		<div id="paging" class="pagclass"></div>
	</div>

