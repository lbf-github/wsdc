<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf" %>
<script type="text/javascript" src="${ctx}/js/system/eval/list.js"></script>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="replyForm" name="form" class="form-horizontal" method="post" action="${ctx}/eval/replyEval.shtml">
		<section class="panel panel-default">
		<div class="panel-body">
			<div class="form-group">
				<input type="hidden" name="ReplyFormMap.id" value="${map.id }" />
				<input type="hidden" name="ReplyFormMap.rel_id" value="${id }">
				<input type="hidden" value="${item_id }" id="item_id">
				<td>${reply.content }</td>
				<div class="line line-dashed line-lg pull-in"></div>
				<textarea rows="5" cols="98%" placeholder="请输入回复信息..." name="ReplyFormMap.content">${map.content }</textarea>
			</div>
		</div>
		<footer class="panel-footer text-right bg-light lter">
			<c:choose>
				<c:when test="${not empty map.id}">
					<button type="submit" class="btn btn-success btn-s-xs" id="update">修改</button>
				</c:when>
				<c:otherwise>
					<button type="submit" class="btn btn-success btn-s-xs" id="reply">回复</button>
				</c:otherwise>
			</c:choose>
		</footer> 
		</section>
	</form>
</body>
</html>