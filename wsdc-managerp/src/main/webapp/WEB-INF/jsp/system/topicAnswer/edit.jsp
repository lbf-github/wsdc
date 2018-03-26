<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/common.jspf"%>
<script type="text/javascript" src="${ctx}/js/system/topicAnswer/edit.js"></script>

<style type="text/css">
	.col-sm-3 {
		width: 15%;
		float: left;
	}

	.col-sm-9 {
		width: 85%;
		float: left;
	}
</style>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	
	<form id="form" name="form" class="form-horizontal" method="post"
		action="${ctx}/topicAnswer/editEntity.shtml">
		<input type="hidden" class="form-control" value="${topicAnswer.id}"
			name="topicAnswerFormMap.id" id="id">
		<input type="hidden" class="form-control" value="${topicAnswer.create_by}"
			name="topicAnswerFormMap.create_by" id="create_by">
		
		<section class="panel panel-default">
			
			<div class="panel-body">
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">答案</label>
					<div class="col-sm-9">
						<textarea class="form-control"
							placeholder="请输入答案" name="topicAnswerFormMap.answer" id="answer" minlength="2" maxlength="500" required >${topicAnswer.answer}</textarea>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">是否最佳</label>
					<div class="col-sm-9">
						<div class="btn-group m-r">
							<button data-toggle="dropdown"
								class="btn btn-sm btn-default dropdown-toggle">
								<span class="dropdown-label"><c:if test="${topicAnswer.best_yn eq 0}">否</c:if><c:if test="${topicAnswer.best_yn eq 1}">是</c:if></span> <span class="caret"></span>
							</button>
							<ul class="dropdown-menu dropdown-select">
								<li class=""><a href="#"><input type="radio"
										name="topicAnswerFormMap.best_yn" value="0"<c:if test="${topicAnswer.best_yn eq 0}"> checked="checked"</c:if>>否</a></li>
								<li class="active"><a href="#"><input type="radio"
										name="topicAnswerFormMap.best_yn" value="1" <c:if test="${topicAnswer.best_yn eq 1}"> checked="checked"</c:if>>是</a></li>
							</ul>
						</div>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">是否已读</label>
					<div class="col-sm-9">
						<div class="btn-group m-r">
							<button data-toggle="dropdown"
								class="btn btn-sm btn-default dropdown-toggle">
								<span class="dropdown-label"><c:if test="${topicAnswer.r_yn eq 0}">未读</c:if><c:if test="${topicAnswer.r_yn eq 1}">已读</c:if></span> <span class="caret"></span>
							</button>
							<ul class="dropdown-menu dropdown-select">
								<li class=""><a href="#"><input type="radio"
										name="topicAnswerFormMap.r_yn" value="0"<c:if test="${topicAnswer.r_yn eq 0}"> checked="checked"</c:if>>未读</a></li>
								<li class="active"><a href="#"><input type="radio"
										name="topicAnswerFormMap.r_yn" value="1" <c:if test="${topicAnswer.r_yn eq 1}"> checked="checked"</c:if>>已读</a></li>
							</ul>
						</div>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">点赞数</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" digits="true" min="0" max="99999999"
							placeholder="请输入数字" name="topicAnswerFormMap.favorite_count" id="favorite_count" value="${topicAnswer.favorite_count}"/>
					</div>
				</div>
				
			</div>
			<footer class="panel-footer text-right bg-light lter">
			<button type="submit" class="btn btn-success btn-s-xs">提交</button>
		</footer> 
	</section>
	</form>

</body>
</html>