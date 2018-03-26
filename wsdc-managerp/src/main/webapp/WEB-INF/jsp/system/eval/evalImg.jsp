<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf" %>
<style type="text/css">
</style>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	
	<form id="imgForm" name="form" class="form-horizontal" method="post" action="${ctx}/item/updatePicUI.shtml">
		<section class="panel panel-default">
		<div class="panel-body">
			<div class="form-group">
				<div id="image">
					<input value="${id}" type="hidden" name="itemFormMap.item_id">
					<c:if test="${not empty list}">
						<c:forEach items="${list}" var="list">
							<div class="bb002">
								<c:if test="${not empty list.img_id}">
									<img src="<%=image_url %>/${list.img_id }" style="width:150px;margin-left:15px"/>
								</c:if>
								
								<input type="hidden" name="img_id" value="${list.img_id }">
							</div>
						</c:forEach>
					</c:if>
					<c:if test="${empty list}">
						<span>没有评价图片</span>
					</c:if>
				</div>
			</div>
		</div>

		</section>
	</form>
</body>
</html>