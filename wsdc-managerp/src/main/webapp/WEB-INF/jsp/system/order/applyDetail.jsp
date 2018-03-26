<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf" %>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="form" name="form" class="form-horizontal" method="post">
		<section class="panel panel-default">
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">订单号</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" value="${map.sn1 }">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">商品标题</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" value="${map.item_id }">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">申请用户</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" value="${map.user_id }">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">用户姓名</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkPrice" value="${map.name }">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">电话</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkPrice" value="${map.mobile }" >
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">地址</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" value="${map.address }">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">申请原因</label>
				<div class="col-sm-9">
					<textarea rows="4" cols="68" class="form-control m-b valid">${map.remark }</textarea>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">申请时间</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" value="${fn:substring(map.create_date,0,10)}">
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			
			<div class="form-group">
				<label class="col-sm-3 control-label">售后图片</label>
				<div class="col-sm-9">
					<c:if test="${not empty list}">
						<c:forEach items="${list}" var="list">
							<div class="bb004">
								<img src="<%=image_url %>/${list.url }" style="height:150px;width:150px;margin-left:15px" onclick="imgShow($(this))"/>
								<input type="hidden" name="img_id" value="${list.img_id }">
							</div>
						</c:forEach>
					</c:if>
				</div>
			</div>
		</div>
		
		<!-- <footer class="panel-footer text-right bg-light lter">
			<button type="submit" class="btn btn-success btn-s-xs">提交</button>
		</footer>  -->

		</section>
	</form>
<div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:999;width:100%;height:100%;display:none;">
    <a class="close" onclick="$('#outerdiv').fadeOut('fast');"></a>
    <div id="innerdiv" style="position:absolute;"><img id="bigimg" style="border:5px solid #fff;" src="" /></div>
</div> 
</body>
</html>