<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf"%>
<script type="text/javascript" src="${ctx}/js/system/dataDic/edit.js"></script>
<style type="text/css">
	#but button {
		margin-bottom: 5px;
		margin-right: 5px;
	}
	
	.col-sm-3 {
		width: 15%;
		float: left;
	}

	.col-sm-9 {
		width: 85%;
		float: left;
	}

	label[class^="btn btn-default"] {
		margin-top: -4px;
	}
</style>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="form" name="form" class="form-horizontal" method="post"
		action="${pageContext.request.contextPath}/dataDic/editEntity.shtml">
		<input type="hidden" value="${dataDic.id}" name="dataDicFormMap.id"
			id="id">
		<section class="panel panel-default">
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">名称</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" minlength="2" maxlength="20" required
						placeholder="请输入名称" name="dataDicFormMap.name" id="name"
						value="${dataDic.name}">
				</div>
			</div>

			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">标识</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" minlength="2" maxlength="50" required
						placeholder="请输入标识信息" name="dataDicFormMap.resKey" id="resKey"
						value="${dataDic.resKey}">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">上级资源</label>
				<div class="col-sm-9">
					<select id="parentId" name="dataDicFormMap.parentId"
						class="form-control m-b">
					</select>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">是否隐藏</label>
				<div class="col-sm-9">
					<input id="gritter-light" type="checkbox"
						<c:if test="${dataDic.ishide eq 1}"> checked="checked"</c:if>
						name="dataDicFormMap.ishide" id="ishide"
						class="ace ace-switch ace-switch-5" value="1">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">描述</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" minlength="1" maxlength="100" required
						placeholder="请输入描述" name="dataDicFormMap.description"
						id="description" value="${dataDic.description}">
				</div>
			</div>
		</div>
		<footer class="panel-footer text-right bg-light lter">
		<button type="submit" class="btn btn-success btn-s-xs">提交</button>
		</footer> </section>
	</form>
	<script type="text/javascript">
		byRes("${dataDic.parentId}");
	</script>
</body>
</html>