<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/common.jspf"%>
<script type="text/javascript" src="${ctx}/js/system/topicQuestion/edit.js"></script>

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
		action="${ctx}/topicQuestion/editEntity.shtml">
		<input type="hidden" class="form-control" value="${topicQuestion.id}"
			name="topicQuestionFormMap.id" id="id">
		
		<section class="panel panel-default">
			
			<div class="panel-body">
				<div class="form-group">
					<label class="col-sm-3 control-label">标题</label>
					<div class="col-sm-9">
						<input type="text" class="form-control"
							placeholder="请输入标题" name="topicQuestionFormMap.title" id="title" value="${topicQuestion.title}" minlength="2" maxlength="20" required />
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">问题</label>
					<div class="col-sm-9">
						<textarea class="form-control"
							placeholder="请输入问题" name="topicQuestionFormMap.question" id="question" minlength="2" maxlength="500" required >${topicQuestion.question}</textarea>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">是否推荐</label>
					<div class="col-sm-9">
						<div class="btn-group m-r">
							<button data-toggle="dropdown"
								class="btn btn-sm btn-default dropdown-toggle">
								<span class="dropdown-label"><c:if test="${topicQuestion.rec_yn eq 0}">否</c:if><c:if test="${topicQuestion.rec_yn eq 1}">是</c:if></span> <span class="caret"></span>
							</button>
							<ul class="dropdown-menu dropdown-select">
								<li class=""><a href="#"><input type="radio"
										name="topicQuestionFormMap.rec_yn" value="0"<c:if test="${topicQuestion.rec_yn eq 0}"> checked="checked"</c:if>>否</a></li>
								<li class="active"><a href="#"><input type="radio"
										name="topicQuestionFormMap.rec_yn" value="1" <c:if test="${topicQuestion.rec_yn eq 1}"> checked="checked"</c:if>>是</a></li>
							</ul>
						</div>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">类型</label>
					<div class="col-sm-9">
						<div class="btn-group m-r">
							<button data-toggle="dropdown"
								class="btn btn-sm btn-default dropdown-toggle">
								<span class="dropdown-label"><c:if test="${topicQuestion.type eq 0}">健康问答</c:if><c:if test="${topicQuestion.rec_yn eq 1}">其他</c:if></span> <span class="caret"></span>
							</button>
							<ul class="dropdown-menu dropdown-select">
								<li class=""><a href="#"><input type="radio"
										name="topicQuestionFormMap.type" value="0"<c:if test="${topicQuestion.type eq 0}"> checked="checked"</c:if>>健康问答</a></li>
								<li class="active"><a href="#"><input type="radio"
										name="topicQuestionFormMap.type" value="1" <c:if test="${topicQuestion.type eq 1}"> checked="checked"</c:if>>其他</a></li>
							</ul>
						</div>
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