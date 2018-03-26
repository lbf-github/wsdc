<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/common/common.jspf" %>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/item/item.js"></script>
<style type="text/css">
	.class1 {display:inline;} 
	.class2 {display:inline;} 
</style>
<script type="text/javascript">
	var city_id = "${city_id}";
</script>
	<header class="panel-heading">
		<div class="class2">
			<label class="control-label">
				<span class="h4 font-thin v-middle">名称:</span>
			</label>
			<input style="border: 1px solid" id="name">
		</div>
		<div class="class2">
		<a href="javascript:void(0)" class="btn btn-default" id="search">查询</a></div>
		<a href="javascript:void(0);" class="btn btn-default" id="reset">重置</a>
		
		<div class="m-select">
			<input id="add" type="button" class="j-select-sure" value="新增"/>
			<div class="class1" id="shows">
				<input value="${id}" type="hidden" id="itemId">
				<select id="select">
					<c:forEach items="${pacList}" var="day" varStatus="status" >
			           	<option value="${day+1}">第${day+1}天</option>
	        		</c:forEach>  
        		</select>
				份数：<input onkeyup="value=value.replace(/[^\d]/g,'')" id="parts" style="border: 1px solid"/>
				<input id="add" type="button" class="j-select-sure" value="确定" onclick="gradeChange()"/>
			</div></br>
		</div>
	</header>
	
	<div class="table-responsive">
		<div id="paging" class="pagclass"></div>
	</div>

