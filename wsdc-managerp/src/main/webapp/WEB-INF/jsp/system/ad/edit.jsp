<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/common.jspf"%>
<script type="text/javascript" src="${ctx}/js/system/ad/edit.js"></script>

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
	
	<form id="form" name="form" class="form-horizontal" method="post" action="${ctx}/ad/editEntity.shtml">
		<input type="hidden" class="form-control checkCust" value="${ad.id}" name="adFormMap.id" id="id">
		<section class="panel panel-default">
		<div class="panel-body">

			<div class="form-group">
				<label class="col-sm-3 control-label">图片</label>
				<div class="col-sm-9"  id="adPic">
					<c:if test="${not empty ad.img_id }">
						<input id="adUpload" type="button" value="上传" class="btn btn-primary marR10" onclick="fileField.click()" disabled=false>
						<input id="removeImg" type="button" value="移除" class="btn btn-danger marR10">
						<input type="file" name="fileField" style="opacity:0;" id="fileField" onchange="uploadImg(this);"/>
					</c:if>
					<c:if test="${empty ad.img_id }">
						<input id="adUpload" type="button" value="上传" class="btn btn-primary marR10" onclick="fileField.click()">
						<input id="removeImg" type="button" value="移除" class="btn btn-danger marR10" disabled>
						<input type="file" name="fileField" style="opacity:0;" id="fileField" onchange="uploadImg(this);"/>
					</c:if>
				</div>
				<div id="ad_img">
					<c:if test="${not empty ad.img_id }">
						<img style="height:200px;width:200px;margin-left:180px" src="<%=image_url %>${ad.img_id }">
					</c:if>

					<input value="${ad.img_id }" name="adFormMap.img_id" type="hidden">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">选择地区</label>
				<div class="col-sm-9" id="area">
					<select id="province_id" required onchange="getCities();" name="adFormMap.province_id" class="form-control m-b"
						tabindex="-1">
					</select>
					<select id="city_id" required name="adFormMap.city_id" class="form-control m-b city_id" tabindex="-1"></select>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">mca</label>
				<div class="col-sm-9">
					<input type="text" placeholder="请输入页面路径" minlength="1" maxlength="255" required class="form-control" name="adFormMap.mca" value="${ad.mca }"/>
				</div>
			</div>
		
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">页面位置</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" name="adFormMap.type" digits="true" min="1" max="99999999" placeholder="请输入所属位置(数字)" value="${ad.type }"/>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label" >标题</label>
				<div class="col-sm-9">
					<input class="form-control" placeholder="请输入标题" name="adFormMap.title" value="${ad.title }"  minlength="3" maxlength="20" required />
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label" >链接地址</label>
				<div class="col-sm-9">
					<input class="form-control" placeholder="请输入链接地址" name="adFormMap.url" value="${ad.url }" type="url" />
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">跳转模式</label>
				<div class="col-sm-9">
						<select class="form-control" name="adFormMap.link"  style="width:130px;" required>
							<c:if test="${ad.link eq 1 }">
								<option value="">请选择</option>
								<option value="1" selected="selected">直接跳转</option>
								<option value="2">其他</option>
							</c:if>
							<c:if test="${ad.link eq 2 }">
								<option value="">请选择</option>
								<option value="1" >直接跳转</option>
								<option value="2" selected="selected">其他</option>
							</c:if>
						</select>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">是否显示</label>
				<div class="col-sm-9">
						<select class="form-control" name="adFormMap.show_yn"  style="width:130px;" required>
							<c:if test="${ad.show_yn eq 0 }">
								<option value="">请选择</option>
								<option value="0" selected="selected">显示</option>
								<option value="1">不显示</option>
							</c:if>
							<c:if test="${ad.show_yn eq 1 }">
								<option value="">请选择</option>
								<option value="0" >显示</option>
								<option value="1" selected="selected">不显示</option>
							</c:if>
						</select>
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
					<input class="form-control" type="text" name="adFormMap.seq" placeholder="请输入数字" value="${ad.seq }" digits="true" min="1" max="99999999" />
				</div>
			</div>
		</div>
		<footer class="panel-footer text-right bg-light lter">
			<button type="submit" class="btn btn-success btn-s-xs">提交</button>
		</footer> 
		</section>
		<script type="text/javascript">
			getProvince("${ad.province_id}");
			getCity("${ad.province_id}","${ad.city_id}");
		</script>
	</form>
</body>
</html>