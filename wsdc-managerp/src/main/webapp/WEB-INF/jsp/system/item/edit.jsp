<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/common.jspf"%>
<script type="text/javascript" src="${ctx}/js/system/item/edit.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/js/ueditor/themes/default/css/ueditor.css"/>
<script type="text/javascript" src="${ctx}/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/js/ueditor/ueditor.all.js"></script>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	
	<form id="form" name="form" class="form-horizontal" method="post"
		action="${ctx}/item/editEntity.shtml">
		<input type="hidden" class="form-control" value="${item.id}"
			name="itemFormMap.id" id="id">
		
		<section class="panel panel-default">
		
			<%-- <div class="form-group">
				<label class="col-sm-3 control-label">商品详情图片</label>
				<div class="col-sm-9" id="detailpic">
					<input type="button" id="bannerUpload" value="上传" class="btn btn-primary marR10"  onclick="itemFileField3.click()">
					<input type="file" name="itemFileField" style="opacity:0;" id="itemFileField3" onchange="uploadItemImg(this,'detailpic','3');"/>
					<input type="hidden" id="detailPicList" name="itemFormMap.detailpic" value="${list3 }">
				</div>
				<div id="itemImage">
					<c:if test="${not empty detailPicList}">
						<c:forEach var="list" items="${detailPicList}">
							<div class="bb004" id="div${list.id}" ondrop="drop(event,this)" ondragover="allowDrop(event)" draggable="true" ondragstart="drag(event, this)">
								<img src="<%=image_url %>/${list.img_id }" style="height:150px;width:150px;margin-left:15px" onclick="imgShow($(this))"/>
								<div class="bb003" onclick="removeImgPic3(this)"></div>
								<input type="hidden" name="img_id" value="${list.img_id }">
							</div>
						</c:forEach>
					</c:if>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">商品图片</label>
				<div class="col-sm-9" id="itemPic">
					<input type="button" id="bannerUpload" value="上传" class="btn btn-primary marR10"  onclick="itemFileField2.click()">
					<input type="file" name="itemFileField" style="opacity:0;" id="itemFileField2" onchange="uploadItemImg(this,'itemPic','2');"/>
					<input type="hidden" id="itemPicList" name="itemFormMap.itempic" value="${list2 }">
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
				<label class="col-sm-3 control-label">商品封面图片</label>
				<div class="col-sm-9" id="coverPic">
					<input type="button" id="bannerUpload" value="上传" class="btn btn-primary marR10"  onclick="itemFileField1.click()">
					<input type="file" name="itemFileField" style="opacity:0;" id="itemFileField1" onchange="uploadItemImg(this,'coverPic','1');"/>
					<input type="hidden" id="coverPicList" name="itemFormMap.coverpic" value="${list1 }">
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
			</div> --%>
			
			<div class="panel-body">
				<%-- <div class="form-group">
					<label class="col-sm-3 control-label">商品名称</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" minlength="2" maxlength="20" required
							placeholder="请输入商品名称" name="itemFormMap.item_nm" id="item_nm" value="${item.item_nm}"/>
					</div>
				</div> --%>
				
				<div class="form-group">
					<label class="col-sm-3 control-label">选择商品分类</label>
					<div class="col-sm-9" id="area">
						<select id="classify_id" required name="itemFormMap.classify_id" class="form-control m-b"
							tabindex="-1">
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-3 control-label">标题</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" minlength="2" maxlength="100" required
							placeholder="请输入商品标题" name="itemFormMap.title" id="title" value="${item.title}"/>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">副标题</label>
					<div class="col-sm-9">
						<input type="text" class="form-control " minlength="2" maxlength="100" required
							placeholder="请输入商品副标题" name="itemFormMap.subtitle" id="subtitle" value="${item.subtitle}"/>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">售价(元)</label>
					<div class="col-sm-9">
						<input type="text" class="form-control checkPrice"
							placeholder="请输入商品售价" name="itemFormMap.price_single" id="price_single" value="${item.price_single}"/>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">原价(元)</label>
					<div class="col-sm-9">
						<input type="text" class="form-control checkPrice"
							placeholder="请输入商品原价" name="itemFormMap.price_original" id="price_original" value="${item.price_original}"/>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">计量单位</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" minlength="1" maxlength="10" required
							placeholder="请输入计量单位" name="itemFormMap.units" id="units" value="${item.units}"/>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">份数</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" digits="true" min="1" max="99999999" 
							placeholder="请输入份数" name="itemFormMap.pac_num" id="pac_num" value="${item.pac_num}"/>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">开团类型</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" digits="true" min="0" max="99999999" 
							placeholder="请输入开团人数(例如:数字3代表3人团,数字0,1代表不支持开团)" name="itemFormMap.tuan_num" id="tuan_num" value="${item.tuan_num}"/>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">开团价格</label>
					<div class="col-sm-9">
						<input type="text" class="form-control checkPrice"
							placeholder="请输入开团价格" name="itemFormMap.tuan_price" id="tuan_price" value="${item.tuan_price}"/>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">开团有效期(秒)</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" digits="true" min="1" max="99999999"
							placeholder="请输入开团有效期(默认86400,即24小时)" name="itemFormMap.tuan_expire" id="tuan_expire" value="${item.tuan_expire}"/>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">库存</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" digits="true" min="1" max="99999999"
							placeholder="请输入商品库存" name="itemFormMap.inventory" id="inventory" value="${item.inventory}"/>
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
							placeholder="请输入商品状态(保留,选填)" name="itemFormMap.status" id="status" value="${item.status}"/>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">收藏量</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" digits="true" min="0" max="99999999"
							placeholder="请输入商品收藏量" name="itemFormMap.favorite_count" id="favorite_count" value="${item.favorite_count}"/>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">分享量</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" digits="true" min="0" max="99999999"
							placeholder="请输入商品分享量" name="itemFormMap.share_count" id="share_count" value="${item.share_count}"/>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">点击量</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" digits="true" min="0" max="99999999"
							placeholder="请输入商品点击量" name="itemFormMap.view_count" id="view_count" value="${item.view_count}"/>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">是否热卖</label>
					<div class="col-sm-9">
						<div class="btn-group m-r">
							<button data-toggle="dropdown"
								class="btn btn-sm btn-default dropdown-toggle">
								<span class="dropdown-label"><c:if test="${item.sale_hot eq 0}">否</c:if><c:if test="${item.sale_hot eq 1}">是</c:if></span> <span class="caret"></span>
							</button>
							
							<ul class="dropdown-menu dropdown-select">
								<li class=""><a href="#"><input type="radio"
										name="itemFormMap.sale_hot" value="0"<c:if test="${item.sale_hot eq 0}"> checked="checked"</c:if>>不推荐</a></li>
								<li class="active"><a href="#"><input type="radio"
										name="itemFormMap.sale_hot" value="1" <c:if test="${item.sale_hot eq 1}"> checked="checked"</c:if>>推荐</a></li>
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
								<span class="dropdown-label"><c:if test="${item.from_sale eq 0}">否</c:if><c:if test="${item.from_sale eq 1}">是</c:if></span> <span class="caret"></span>
							</button>
							
							<ul class="dropdown-menu dropdown-select">
								<li class=""><a href="#"><input type="radio"
										name="itemFormMap.from_sale" value="0"<c:if test="${item.from_sale eq 0}"> checked="checked"</c:if>>否</a></li>
								<li class="active"><a href="#"><input type="radio"
										name="itemFormMap.from_sale" value="1" <c:if test="${item.from_sale eq 1}"> checked="checked"</c:if>>是</a></li>
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
								<span class="dropdown-label"><c:if test="${item.rec_yn eq 0}">否</c:if><c:if test="${item.rec_yn eq 1}">是</c:if></span> <span class="caret"></span>
							</button>
							
							<ul class="dropdown-menu dropdown-select">
								<li class=""><a href="#"><input type="radio"
										name="itemFormMap.rec_yn" value="0"<c:if test="${item.rec_yn eq 0}"> checked="checked"</c:if>>否</a></li>
								<li class="active"><a href="#"><input type="radio"
										name="itemFormMap.rec_yn" value="1" <c:if test="${item.rec_yn eq 1}"> checked="checked"</c:if>>是</a></li>
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
								<span class="dropdown-label"><c:if test="${item.show_yn eq 0}">显示</c:if><c:if test="${item.show_yn eq 1}">不显示</c:if></span> <span class="caret"></span>
							</button>
							
							<ul class="dropdown-menu dropdown-select">
								<li class=""><a href="#"><input type="radio"
										name="itemFormMap.show_yn" value="0"<c:if test="${item.show_yn eq 0}"> checked="checked"</c:if>>显示</a></li>
								<li class="active"><a href="#"><input type="radio"
										name="itemFormMap.show_yn" value="1" <c:if test="${item.show_yn eq 1}"> checked="checked"</c:if>>不显示</a></li>
							</ul>
						</div>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">销量</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" digits="true" min="1" max="99999999"
							placeholder="请输入商品销量" name="itemFormMap.sale_num" id="sale_num" value="${item.sale_num}"/>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">排序</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" digits="true" min="1" max="99999999"
							placeholder="请输入数字" name="itemFormMap.seq" id="seq" value="${item.seq}"/>
					</div>
				</div>
				
			</div>
			<c:if test="${ empty type }">
				<footer class="panel-footer text-right bg-light lter">
					<button type="submit" class="btn btn-success btn-s-xs">提交</button>
				</footer> 
			</c:if>
	</section>
	</form>
	<script type="text/javascript">
		getItemClass(${classify_id})
	</script>
</body>
</html>