<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf" %>
<script type="text/javascript" src="${ctx}/js/system/itemProduct/add.js"></script>

<link rel="stylesheet" type="text/css" href="${ctx}/js/ueditor/themes/default/css/ueditor.css"/>
<script type="text/javascript" src="${ctx}/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/js/ueditor/ueditor.all.js"></script>  

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
		action="${ctx}/itemProduct/addEntity.shtml">
		<section class="panel panel-default">
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">产品详情图片</label>
				<div class="col-sm-9" id="detailpic">
					<input type="button" id="bannerUpload" value="上传" class="btn btn-primary marR10"  onclick="itemFileField3.click()">
					<input type="file" name="itemFileField" style="opacity:0;" id="itemFileField3" onchange="uploadItemImg(this,'detailpic','3');"/>
					<input type="hidden" id="detailPicList" name="itemProductFormMap.detailpic">
				</div>
				<div id="itemImage"></div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">产品图片</label>
				<div class="col-sm-9" id="itemPic">
					<input type="button" id="bannerUpload" value="上传" class="btn btn-primary marR10"  onclick="itemFileField2.click()">
					<input type="file" name="itemFileField" style="opacity:0;" id="itemFileField2" onchange="uploadItemImg(this,'itemPic',2);"/>
					<input type="hidden" id="itemPicList" name="itemProductFormMap.itempic">
				</div>
				<div id="wareImage"></div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">产品封面图片</label>
				<div class="col-sm-9" id="coverPic">
					<input type="button" id="bannerUpload" value="上传" class="btn btn-primary marR10"  onclick="itemFileField1.click()">
					<input type="file" name="itemFileField" style="opacity:0;" id="itemFileField1" onchange="uploadItemImg(this,'coverPic',1);"/>
					<input type="hidden" id="coverPicList" name="itemProductFormMap.coverpic">
				</div>
				<div id="coverImage"></div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">产品名称</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" minlength="2" maxlength="20" required
						placeholder="请输入产品名称" name="itemProductFormMap.item_nm" id="item_nm">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">排序</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" digits="true" min="1" max="99999999" 
						placeholder="请输入数字" name="itemProductFormMap.seq" id="seq">
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