<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf" %>
<script type="text/javascript" src="${ctx}/js/system/topicQuestion/list.js"></script>
<style type="text/css">
</style>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="questionImgForm" name="form" class="form-horizontal" method="post" action="${ctx}/topicQuestion/updateQuestionPicUI.shtml">
		<section class="panel panel-default">
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">图片</label>
				<div class="col-sm-9" id="questionPic">
					<input type="button"id="questionPicUpload" value="上传" class="btn btn-primary marR10"  onclick="fileField.click()">
					<input type="file" name="fileField" style="opacity:0;" id="fileField" onchange="uploadImg(this,'questionPic');"/>
				</div>
				<div id="image">
					<input value="${id}" type="hidden" name="topicQuestionFormMap.id">
					<c:if test="${not empty questionPicList}">
						<c:forEach items="${questionPicList}" var="questionPicList">
							<div class="bb002">
								<c:if test="${not empty questionPicList.img_id}">
									<img src="<%=image_url %>/${questionPicList.img_id }" style="height:100px;width:100px;margin-left:15px"/>
								</c:if>
								
								<div class="bb001" onclick="removeImgPic(this)"></div>
								<input type="hidden" name="img_id" value="${questionPicList.img_id }">
							</div>
						</c:forEach>
					</c:if>
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