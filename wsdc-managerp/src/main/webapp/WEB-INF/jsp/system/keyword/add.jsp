<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf" %>
<script type="text/javascript" src="${ctx}/js/system/keyword/add.js"></script>
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
	<form id="form" name="form" class="form-horizontal" method="post" action="${ctx}/keyword/addEntity.shtml">
		<section class="panel panel-default">
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">关键词</label>
				<div class="col-sm-9">
					<input class="form-control" placeholder="请输入关键词(1-20字)" name="keywordFormMap.word" minlength="1" maxlength="20" required />
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">联想词</label>
				<div class="col-sm-9">
					<textarea class="form-control" placeholder="请输入联想词(1-2000字,多个联想词请以'|'隔开)" name="keywordFormMap.rel_word" minlength="0" maxlength="2000" ></textarea>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">搜索次数(配置)</label>
				<div class="col-sm-9">
					<input class="form-control" placeholder="请输入数字(用于配置)" name="keywordFormMap.frequency2" digits="true" min="1" max="99999999" />
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">排序</label>
				<div class="col-sm-9">
					<input class="form-control" placeholder="请输入数字 (数字越大越靠前)" name="keywordFormMap.seq" digits="true" min="1" max="99999999" />
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