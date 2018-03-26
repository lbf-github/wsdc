<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<%@include file="/common/common.jspf" %>
	<input type="hidden" value="${id }" id="item_id">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/eval/list.js"></script>

	<header class="panel-heading">
		<div class="doc-buttons">
			<input class="btn btn-danger marR10" id="delEval" value="删除" type="button"/>
		</div>
	</header>
	<div class="table-responsive" style="width: 100%; height: 100%;">
		<div id="paging" class="pagclass"></div>
	</div>

