<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/common.jspf"%>
<script type="text/javascript" src="${ctx}/js/system/activity/edit.js"></script>

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
		action="${ctx}/activity/editEntity.shtml">
		<input type="hidden" class="form-control" value="${activity.id}"
			name="activityFormMap.id" id="id">
		
		<section class="panel panel-default">
			
			<div class="panel-body">
				<div class="form-group">
					<label class="col-sm-3 control-label">活动封面</label>
					<div class="col-sm-9"  id="activityPic">
						<c:if test="${not empty activity.img_id }">
							<input id="activityUpload" type="button" value="上传" class="btn btn-primary marR10" onclick="fileField.click()" disabled=false>
							<input id="removeImg" type="button" value="移除" class="btn btn-danger marR10">
							<input type="file" name="fileField" style="opacity:0;" id="fileField" onchange="uploadlogo(this);"/>
						</c:if>
						<c:if test="${empty activity.img_id }">
							<input id="activityUpload" type="button" value="上传" class="btn btn-primary marR10" onclick="fileField.click()">
							<input id="removeImg" type="button" value="移除" class="btn btn-danger marR10" disabled>
							<input type="file" name="fileField" style="opacity:0;" id="fileField" onchange="uploadlogo(this);"/>
						</c:if>
					</div>
					<div id="activity_img">
						<c:if test="${not empty activity.img_id }">
							<img style="height:100px;width:100px;margin-left:100px" src="<%=image_url %>${activity.img_id }">
						</c:if>
						
						<input value="${activity.img_id }" name="activityFormMap.img_id" type="hidden">
					</div>
				</div>
			
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">活动名称</label>
					<div class="col-sm-9">
						<input type="text" class="form-control"
							placeholder="请输入活动名称" name="activityFormMap.title" id="title" value="${activity.title}" minlength="2" maxlength="10" required />
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">活动链接</label>
					<div class="col-sm-9">
						<input type="text" class="form-control checkUrl"
							placeholder="请输入活动链接" name="activityFormMap.url" id="url" value="${activity.url}" type="url"/>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">是否热门</label>
					<div class="col-sm-9">
						<div class="btn-group m-r">
							<button data-toggle="dropdown"
								class="btn btn-sm btn-default dropdown-toggle">
								<span class="dropdown-label"><c:if test="${activity.hot_yn eq 0}">否</c:if><c:if test="${activity.hot_yn eq 1}">是</c:if></span> <span class="caret"></span>
							</button>
							<ul class="dropdown-menu dropdown-select">
								<li class=""><a href="#"><input type="radio"
										name="activityFormMap.hot_yn" value="0"<c:if test="${activity.hot_yn eq 0}"> checked="checked"</c:if>>否</a></li>
								<li class="active"><a href="#"><input type="radio"
										name="activityFormMap.hot_yn" value="1" <c:if test="${activity.hot_yn eq 1}"> checked="checked"</c:if>>是</a></li>
							</ul>
						</div>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">排序</label>
					<div class="col-sm-9">
						<input type="text" class="form-control checkSeq" digits="true" min="1" max="99999999"
							placeholder="请输入数字" name="activityFormMap.seq" id="seq" value="${activity.seq}"/>
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