<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf" %>
<script type="text/javascript" src="${ctx}/js/system/item/list.js"></script>
<style type="text/css">
</style>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="coverImgForm" name="form" class="form-horizontal" method="post" action="${ctx}/item/updateCoverPicUI.shtml">
		<section class="panel panel-default">
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">商品封面</label>
				<div class="col-sm-9" id="itempic">
					<input type="button" id="bannerUpload" value="上传" class="btn btn-primary marR10"  onclick="itemFileField.click()">
					<input type="file" name="itemFileField" style="opacity:0;" id="itemFileField" onchange="uploadItemImg(this,'itempic');"/>
				</div>
				<div id="itemImage">
					<input value="${id}" type="hidden" name="itemFormMap.item_id">
					<c:if test="${not empty coverList}">
						<c:forEach items="${coverList}" var="list" begin="0">
							<div class="bb004" id="div${list.id}" ondrop="drop(event,this)" ondragover="allowDrop(event)" draggable="true" ondragstart="drag(event, this)">
								<c:if test="${not empty list.img_id}">
									<img src="<%=image_url %>/${list.img_id }" style="height:150px;width:150px;margin-left:15px"/>
								</c:if>
								
								<div class="bb003" onclick="removeImgPic(this)"></div>
								<input type="hidden" name="img_id" value="${list.img_id }">
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