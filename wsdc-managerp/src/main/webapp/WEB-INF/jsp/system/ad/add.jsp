<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf" %>
<script type="text/javascript" src="${ctx}/js/system/ad/add.js"></script>
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
	<form id="form" name="form" class="form-horizontal" method="post" action="${ctx}/ad/addEntity.shtml">
		<section class="panel panel-default">
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">图片</label>
				<div class="col-sm-9" id="adPic">
					<input type="button"id="adUpload" value="上传" class="btn btn-primary marR10"  onclick="fileField.click()">
					<input id="removeImg" type="button" value="移除" class="btn btn-danger marR10" disabled>
					<input type="file" name="fileField" style="opacity:0;" id="fileField" onchange="uploadImg(this);"/>
				</div>
				<div id="ad_img"></div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">选择地区</label>
				<div class="col-sm-9" id="area">
					<select id="province_id" onchange="getCities();" name="adFormMap.province_id" class="form-control m-b"
						tabindex="-1" required>
					</select>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">mca</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" name="adFormMap.mca" placeholder="请输入页面路径" minlength="1" maxlength="255" required/>
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">页面位置</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" name="adFormMap.type" digits="true" min="1" max="99999999" placeholder="请输入所属位置(数字)"/>
				</div>
			</div>	
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label" >标题</label>
				<div class="col-sm-9">
					<input name="adFormMap.title" class="form-control" minlength="3" maxlength="20" required placeholder="请输入标题名称"/>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label" >链接地址</label>
				<div class="col-sm-9">
					<input name="adFormMap.url" class="form-control" type="url" placeholder="请输入链接地址"/>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">跳转模式</label>
				<div class="col-sm-9">
						<select class="form-control" name="adFormMap.link"  style="width:130px;" required>
							<option value="">请选择</option>
							<option value="1">直接跳转</option>
							<option value="2">其他</option>
						</select>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">是否前台显示</label>
				<div class="col-sm-9">
						<select class="form-control" name="adFormMap.show_yn"  style="width:130px;" required>
							<option value="">请选择</option>
							<option value="0">显示</option>
							<option value="1">不显示</option>
						</select>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">描述</label>
				<div class="col-sm-9">
					<textarea class="form-control" name="adFormMap.description" cols="50" minlength="3" maxlength="100" required placeholder="请输入描述详情"></textarea>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">排序</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" name="adFormMap.seq" digits="true" min="1" max="99999999" placeholder="请输入数字(数字越大越靠前)"/>
				</div>
			</div>
		</div>
		<footer class="panel-footer text-right bg-light lter">
			<button type="submit" class="btn btn-success btn-s-xs">提交</button>
		</footer> 
		</section>
	</form>
	<script type="text/javascript">
	</script>
</body>
</html>