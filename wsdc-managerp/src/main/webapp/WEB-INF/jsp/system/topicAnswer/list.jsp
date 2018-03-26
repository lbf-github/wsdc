<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<%@include file="/common/common.jspf" %>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/topicAnswer/list.js"></script>
	
	<div class="m-select">
		<div class="line line-dashed line-lg pull-in"></div>
		<input id="addAnswer" type="button" class="j-select-sure" value="添加"/>
		<input id="editAnswer" type="button" class="j-select-sure" value="编辑"/>
		<input id="deleteAnswer" type="button" class="j-select-sure" value="删除"/>
		<div class="line line-dashed line-lg pull-in"></div>
	</div>
	
	<input id="topic_id" value="${id}" type="hidden" name="topicQuestionFormMap.id">
	
	<div class="table-responsive">
		<div id="paging" class="pagclass"></div>
	</div>
