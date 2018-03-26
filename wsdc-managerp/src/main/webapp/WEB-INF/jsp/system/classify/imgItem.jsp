<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf" %>
<script type="text/javascript" src="${ctx}/js/system/classify/imgView.js"></script>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="imgForm" name="form" class="form-horizontal" method="post" action="${ctx}/classify/viewCoverImg.shtml">
		<section class="panel panel-default">
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">图片</label>
				<div class="col-sm-9" id="itempic">
					<input type="button"id="bannerUpload" value="上传" class="btn btn-primary marR10"  onclick="fileField.click()">
					<input type="file" name="fileField" style="opacity:0;" id="fileField" onchange="uploadImg(this,'itempic');"/>
				</div>
				<div id="image">
					<input value="${id}"name="classifyImgFormMap.classify_id" type="hidden">
					<c:if test="${not empty list}">
						<c:forEach items="${list}" var="list">
							<div class="bb004">
								<c:if test="${not empty list.img_id}">
									<img src="<%=image_url %>/${list.img_id }" style="height:150px;width:150px;margin-left:15px"/>
								</c:if>
								
								<div class="bb003" onclick="removeImgPic(this)"></div>
								<input type="hidden" name="img_id" value="${list.img_id }">
								<input name="link" value="${list.url }" name="link" style="width:156px;" placeholder="图片链接" type="url"/>
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