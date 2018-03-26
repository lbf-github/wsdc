<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf" %>
<script type="text/javascript" src="${ctx}/js/system/item/detailPic.js"></script>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="imgForm" name="form" class="form-horizontal" method="post" action="${ctx}/item/detailPic.shtml">
		<section class="panel panel-default">
		<div class="panel-body">
			<div class="form-group">
			<label class="col-sm-3 control-label">图片</label>
				<div class="col-sm-9" id="">
					<input type="file" name="" style="opacity:0;" id="" onchange=""/>
					<input type="file" name="" style="opacity:0;" id="" onchange=""/>
				</div>
			<div id="111" style="position:fixed;">
				<label class="col-sm-3 control-label"></label>
				<div class="col-sm-9" id="itempic">
					<input type="button"id="bannerUpload" value="上传" class="btn btn-primary marR10"  onclick="fileField.click()">
					<input type="file" name="fileField" style="opacity:0;" id="fileField" onchange="uploadImg(this,'itempic');"/>
				</div></div>
				<div id="image">
					<input value="${id}"name="itemFormMap.item_id" type="hidden">
					<c:if test="${not empty list}">
						<c:forEach items="${list}" var="list">
							<div class="bb004" id="div${list.id}" ondrop="drop(event,this)" ondragover="allowDrop(event)" draggable="true" ondragstart="drag(event, this)">
								<c:if test="${not empty list.img_id}">
									<img src="<%=image_url %>/${list.img_id }" style="height:150px;width:150px;margin-left:15px" onclick="imgShow($(this))"/>
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
	<div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:999;width:100%;height:100%;display:none;">
    <a class="close" onclick="$('#outerdiv').fadeOut('fast');"></a>
    <div id="innerdiv" style="position:absolute;"><img id="bigimg" style="border:5px solid #fff;" src="" /></div>
</div> 
</body>
</html>