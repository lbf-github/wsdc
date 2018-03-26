<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/common.jspf"%>
<script type="text/javascript" src="${ctx}/js/system/gameType/edit.js"></script>

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
	
	<form id="form" name="form" class="form-horizontal" method="post" action="${ctx}/gameType/editEntity.shtml">
		<input type="hidden" class="form-control checkCust" value="${ad.id}" name="adFormMap.id" id="id">
		<section class="panel panel-default">
		<div class="panel-body">
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">类型名称</label>
				<div class="col-sm-9">
					<input type="text" placeholder="请输入页面路径" minlength="1" maxlength="255" required class="form-control" name="adFormMap.type" value="${ad.game_type }"/>
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">是否热门</label>
				<div class="col-sm-9">
					<div class="btn-group m-r">
						<button data-toggle="dropdown"
								class="btn btn-sm btn-default dropdown-toggle">
							<span class="dropdown-label">否</span><span class="caret"></span>
						</button>
						<ul class="dropdown-menu dropdown-select">
							<li class=""><a href="#"><input type="radio"
															name="adFormMap.hot_yn" value="0" checked="checked">否</a></li>
							<li class="active"><a href="#"><input type="radio"
																  name="adFormMap.hot_yn" value="1">是</a></li>
						</ul>
					</div>
				</div>
			</div>

			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">描述</label>
				<div class="col-sm-9">
					<textarea class="form-control" placeholder="请输入描述" name="adFormMap.description" cols="50" minlength="3" maxlength="100" required>${ad.description }</textarea>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">排序</label>
				<div class="col-sm-9">
					<input class="form-control" type="text" name="adFormMap.sort" placeholder="请输入数字" value="${ad.sort }" digits="true" min="1" max="99999999" />
				</div>
			</div>
		</div>
		<footer class="panel-footer text-right bg-light lter">
			<button type="submit" class="btn btn-success btn-s-xs">提交</button>
		</footer> 
		</section>
		<script type="text/javascript">

		</script>
	</form>
</body>
</html>