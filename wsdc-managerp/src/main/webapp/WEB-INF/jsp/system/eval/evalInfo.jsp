<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf" %>
<input type="hidden" value="${item_id }" id="item_id">
<script type="text/javascript" src="${ctx}/js/system/eval/list.js"></script>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="replyForm" name="form" class="form-horizontal" method="post" action="${ctx}/eval/replyEval.shtml">
		<c:if test="${not empty eval.id }">
			<section class="panel panel-default">
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">商品评价</label>
				<div class="col-sm-9">
					<input class="form-control" maxlength="100" value="${eval.stars_item }">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">服务评价</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" name="itemFormMap.title" value="${eval.stars_service }">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">骑手评价</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" name="itemFormMap.subtitle" value="${eval.stars_rider }">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">评价内容</label>
				<div class="col-sm-9">
					<textarea rows="5" cols="88%">${eval.content }</textarea>
				</div>
			</div>

		</div>
		
		<div class="panel-body">
			<div class="form-group">
				<input type="hidden" name="ReplyFormMap.id" value="${reply.id }" />
				<input type="hidden" name="ReplyFormMap.rel_id" value="${id }">
				<div class="line line-dashed line-lg pull-in"></div>
				<textarea rows="5" cols="98%" placeholder="请输入回复信息..." name="ReplyFormMap.content">${reply.content }</textarea>
			</div>
		</div>
		
		<footer class="panel-footer text-right bg-light lter">
			<c:choose>
				<c:when test="${not empty reply.id}">
					<button type="submit" class="btn btn-success btn-s-xs" id="update">修改</button>
				</c:when>
				<c:otherwise>
					<button type="submit" class="btn btn-success btn-s-xs" id="reply">回复</button>
				</c:otherwise>
			</c:choose>
		</footer> 
		</section>
		</c:if>
		<c:if test="${empty eval.id }">
			<div class="panel-body">
			<div class="form-group">
				<td>暂无评价</td>
			</div>
		</div>
		</c:if>
	</form>
</body>
</html>