<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/common.jspf"%>
<script type="text/javascript" src="${ctx}/js/system/itemProduct/edit.js"></script>

<link rel="stylesheet" type="text/css" href="${ctx}/js/ueditor/themes/default/css/ueditor.css"/>
<script type="text/javascript" src="${ctx}/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/js/ueditor/ueditor.all.js"></script>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	
	<form id="form" name="form" class="form-horizontal" method="post"
		action="${ctx}/itemProduct/editEntity.shtml">
		<input type="hidden" class="form-control" value="${itemProduct.id}"
			name="itemProductFormMap.id" id="id">
		
		<section class="panel panel-default">
		
			<div class="form-group">
				<label class="col-sm-3 control-label">产品详情图片</label>
				<div class="col-sm-9" id="detailpic">
					<input type="button" id="bannerUpload" value="上传" class="btn btn-primary marR10"  onclick="itemFileField3.click()">
					<input type="file" name="itemFileField" style="opacity:0;" id="itemFileField3" onchange="uploadItemImg(this,'detailpic','3');"/>
					<input type="hidden" id="detailPicList" name="itemProductFormMap.detailpic" value="${list3 }">
				</div>
				<div id="itemImage">
					<c:if test="${not empty detailPicList}">
						<c:forEach var="list" items="${detailPicList}">
							<div class="bb004" id="div${list.id}" ondrop="drop(event,this)" ondragover="allowDrop(event)" draggable="true" ondragstart="drag(event, this)">
								<c:if test="${not empty list.img_id}">
									<img src="<%=image_url %>/${list.img_id }" style="height:150px;width:150px;margin-left:15px" onclick="imgShow($(this))"/>
								</c:if>
								
								<div class="bb003" onclick="removeImgPic3(this)"></div>
								<input type="hidden" name="img_id" value="${list.img_id }">
							</div>
						</c:forEach>
					</c:if>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">产品图片</label>
				<div class="col-sm-9" id="itemPic">
					<input type="button" id="bannerUpload" value="上传" class="btn btn-primary marR10"  onclick="itemFileField2.click()">
					<input type="file" name="itemFileField" style="opacity:0;" id="itemFileField2" onchange="uploadItemImg(this,'itemPic','2');"/>
					<input type="hidden" id="itemPicList" name="itemProductFormMap.itempic" value="${list2 }">
				</div>
				<div id="wareImage">
					<c:if test="${not empty itemPicList}">
						<c:forEach var="list" items="${itemPicList}">
							<div class="bb004" id="div${list.id}" ondrop="drop(event,this)" ondragover="allowDrop(event)" draggable="true" ondragstart="drag(event, this)">
								<img src="<%=image_url %>/${list.img_id }" style="height:150px;width:150px;margin-left:15px" onclick="imgShow($(this))"/>
								<div class="bb003" onclick="removeImgPic2(this)"></div>
								<input type="hidden" name="img_id" value="${list.img_id }">
							</div>
						</c:forEach>
					</c:if>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">产品封面图片</label>
				<div class="col-sm-9" id="coverPic">
					<input type="button" id="bannerUpload" value="上传" class="btn btn-primary marR10"  onclick="itemFileField1.click()">
					<input type="file" name="itemFileField" style="opacity:0;" id="itemFileField1" onchange="uploadItemImg(this,'coverPic','1');"/>
					<input type="hidden" id="coverPicList" name="itemProductFormMap.coverpic" value="${list1 }">
				</div>
				<div id="coverImage">
					<c:if test="${not empty coverPicList}">
						<c:forEach var="list" items="${coverPicList}">
							<div class="bb004" id="div${list.id}" ondrop="drop(event,this)" ondragover="allowDrop(event)" draggable="true" ondragstart="drag(event, this)">
								<img src="<%=image_url %>/${list.img_id }" style="height:150px;width:150px;margin-left:15px" onclick="imgShow($(this))"/>
								<div class="bb003" onclick="removeImgPic1(this)"></div>
								<input type="hidden" name="img_id" value="${list.img_id }">
							</div>
						</c:forEach>
					</c:if>
				</div>
			</div>
			
			<c:if test="${ empty type }">
				<footer class="panel-footer text-right bg-light lter">
					<button type="submit" class="btn btn-success btn-s-xs">提交</button>
				</footer> 
			</c:if>
	</section>
	</form>
	<!-- <script type="text/javascript">
		$(function(){
			$('#detail').val('${item.detail}');
			ue.ready(function() {
				ue.setContent('${item.detail}');
			});
		});
	</script> -->
</body>
</html>