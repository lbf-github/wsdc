<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf" %>
<script type="text/javascript" src="${ctx}/js/system/item/add.js"></script>
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
		action="${ctx}/item/addEntity.shtml">
		<input type="hidden" class="form-control" value="${product_id}"
			name="itemFormMap.product_id" id="product_id">
		<section class="panel panel-default">
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">选择商品分类</label>
				<div class="col-sm-9" id="area">
					<select id="classify_id" name="itemFormMap.classify_id" class="form-control m-b"
						tabindex="-1" required>
					</select>
				</div>

			</div>
			<!-- <div class="form-group">
				<label class="col-sm-3 control-label">商品详情图片</label>
				<div class="col-sm-9" id="detailpic">
					<input type="button" id="bannerUpload" value="上传" class="btn btn-primary marR10"  onclick="itemFileField3.click()">
					<input type="file" name="itemFileField" style="opacity:0;" id="itemFileField3" onchange="uploadItemImg(this,'detailpic','3');"/>
					<input type="hidden" id="detailPicList" name="itemFormMap.detailpic">
				</div>
				<div id="itemImage"></div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">商品图片</label>
				<div class="col-sm-9" id="itemPic">
					<input type="button" id="bannerUpload" value="上传" class="btn btn-primary marR10"  onclick="itemFileField2.click()">
					<input type="file" name="itemFileField" style="opacity:0;" id="itemFileField2" onchange="uploadItemImg(this,'itemPic',2);"/>
					<input type="hidden" id="itemPicList" name="itemFormMap.itempic">
				</div>
				<div id="wareImage"></div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">商品封面图片</label>
				<div class="col-sm-9" id="coverPic">
					<input type="button" id="bannerUpload" value="上传" class="btn btn-primary marR10"  onclick="itemFileField1.click()">
					<input type="file" name="itemFileField" style="opacity:0;" id="itemFileField1" onchange="uploadItemImg(this,'coverPic',1);"/>
					<input type="hidden" id="coverPicList" name="itemFormMap.coverpic">
				</div>
				<div id="coverImage"></div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">商品名称</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" minlength="2" maxlength="20" required
						placeholder="请输入商品名称" name="itemFormMap.item_nm" id="item_nm">
				</div>
			</div> 
			
			<div class="line line-dashed line-lg pull-in"></div>	-->
			<div class="form-group">
				<label class="col-sm-3 control-label">标题</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" minlength="2" maxlength="100" required
						placeholder="请输入商品标题" name="itemFormMap.title" id="title">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">副标题</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" minlength="2" maxlength="100" required
						placeholder="请输入商品副标题" name="itemFormMap.subtitle" id="subtitle">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">售价(元)</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkPrice" 
						placeholder="请输入商品售价" name="itemFormMap.price_single" id="price_single">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">原价(元)</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkPrice"
						placeholder="请输入商品原价" name="itemFormMap.price_original" id="price_original">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">计量单位</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" minlength="1" maxlength="10" required
						placeholder="请输入计量单位" name="itemFormMap.units" id="units">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">份数</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" digits="true" min="1" max="99999999" 
						placeholder="请输入份数" name="itemFormMap.pac_num" id="pac_num" value="1">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">开团类型</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" digits="true" min="0" max="99999999" 
						placeholder="请输入开团数(例如:数字3代表3人团,数字0或者1代表不支持开团)" name="itemFormMap.tuan_num" id="tuan_num" value="0">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">开团价格</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkPrice"
						placeholder="请输入开团价格" name="itemFormMap.tuan_price" id="tuan_price" value="0.00">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">开团有效期(秒)</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" digits="true" min="1" max="99999999" 
						placeholder="请输入开团有效期(默认86400,即24小时)" name="itemFormMap.tuan_expire" id="tuan_expire" value="86400">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">库存</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" digits="true" min="1" max="99999999" 
						placeholder="请输入商品库存" name="itemFormMap.inventory" id="inventory">
				</div>
			</div>
			
			<!-- <div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">详情</label>
				<textarea name="itemFormMap.detail" id="detail" style="height:300px;width:80%;float:left"></textarea>
   				<script type="text/javascript">
					var ue = UE.getEditor('detail');
				</script>
			</div> -->
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">商品状态</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" digits="true" min="1" max="99999999"
						placeholder="请输入商品商品状态(保留,选填)" name="itemFormMap.status" id="status">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">是否热卖</label>
				<div class="col-sm-9">
					<div class="btn-group m-r">
						<button data-toggle="dropdown"
							class="btn btn-sm btn-default dropdown-toggle">
							<span class="dropdown-label">否</span> <span class="caret"></span>
						</button>
						<ul class="dropdown-menu dropdown-select">
							<li class=""><a href="#"><input type="radio"
									name="itemFormMap.sale_hot" value="0" checked="checked">否</a></li>
							<li class="active"><a href="#"><input type="radio"
									name="itemFormMap.sale_hot" value="1">是</a></li>
						</ul>
					</div>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">是否下架</label>
				<div class="col-sm-9">
					<div class="btn-group m-r">
						<button data-toggle="dropdown"
							class="btn btn-sm btn-default dropdown-toggle">
							<span class="dropdown-label">否</span> <span class="caret"></span>
						</button>
						<ul class="dropdown-menu dropdown-select">
							<li class=""><a href="#"><input type="radio"
									name="itemFormMap.from_sale" value="0" checked="checked">否</a></li>
							<li class="active"><a href="#"><input type="radio"
									name="itemFormMap.from_sale" value="1">是</a></li>
						</ul>
					</div>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">是否推荐</label>
				<div class="col-sm-9">
					<div class="btn-group m-r">
						<button data-toggle="dropdown"
							class="btn btn-sm btn-default dropdown-toggle">
							<span class="dropdown-label">否</span> <span class="caret"></span>
						</button>
						<ul class="dropdown-menu dropdown-select">
							<li class=""><a href="#"><input type="radio"
									name="itemFormMap.rec_yn" value="0" checked="checked">否</a></li>
							<li class="active"><a href="#"><input type="radio"
									name="itemFormMap.rec_yn" value="1">是</a></li>
						</ul>
					</div>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">是否前台显示</label>
				<div class="col-sm-9">
					<div class="btn-group m-r">
						<button data-toggle="dropdown"
							class="btn btn-sm btn-default dropdown-toggle">
							<span class="dropdown-label">显示</span> <span class="caret"></span>
						</button>
						<ul class="dropdown-menu dropdown-select">
							<li class=""><a href="#"><input type="radio"
									name="itemFormMap.show_yn" value="0" checked="checked">显示</a></li>
							<li class="active"><a href="#"><input type="radio"
									name="itemFormMap.show_yn" value="1">不显示</a></li>
						</ul>
					</div>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">销量</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" digits="true" min="1" max="99999999" 
						placeholder="请输入商品商品销量" name="itemFormMap.sale_num" id="sale_num">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">排序</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" digits="true" min="1" max="99999999" 
						placeholder="请输入数字" name="itemFormMap.seq" id="seq">
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