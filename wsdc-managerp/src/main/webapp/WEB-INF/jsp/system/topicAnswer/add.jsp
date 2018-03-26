<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf" %>
<script type="text/javascript" src="${ctx}/js/system/topicAnswer/add.js"></script>

<style type="text/css">

	.col-sm-3 {
		width: 15%;
		float: left;
	}

	.col-sm-9 {
		width: 85%;
		float: left;
	}

	label[class^="btn btn-default"]{
		margin-top: -4px;
	}

</style>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="form" name="form" class="form-horizontal" method="post"
		action="${ctx}/topicAnswer/addEntity.shtml">
		<input type="hidden" name="topicAnswerFormMap.topic_id" value="${topic_id}" />
		<section class="panel panel-default">
		<div class="panel-body">
			
			<div class="form-group">
				<label class="col-sm-3 control-label">答案</label>
				<div class="col-sm-9">
					<textarea class="form-control" minlength="2" maxlength="500" required 
						placeholder="请输入答案内容" name="topicAnswerFormMap.answer" id="answer" ></textarea>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">是否最佳</label>
				<div class="col-sm-9">
					<div class="btn-group m-r">
						<button data-toggle="dropdown"
							class="btn btn-sm btn-default dropdown-toggle">
							<span class="dropdown-label">否</span> <span class="caret"></span>
						</button>
						<ul class="dropdown-menu dropdown-select">
							<li class=""><a href="#"><input type="radio"
									name="topicAnswerFormMap.best_yn" value="0" checked="checked">否</a></li>
							<li class="active"><a href="#"><input type="radio"
									name="topicAnswerFormMap.best_yn" value="1">是</a></li>
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
							<span class="dropdown-label">未读</span> <span class="caret"></span>
						</button>
						<ul class="dropdown-menu dropdown-select">
							<li class=""><a href="#"><input type="radio"
									name="topicAnswerFormMap.r_yn" value="0" checked="checked">未读</a></li>
							<li class="active"><a href="#"><input type="radio"
									name="topicAnswerFormMap.r_yn" value="1">已读</a></li>
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