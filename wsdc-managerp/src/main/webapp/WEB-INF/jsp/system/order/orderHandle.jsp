<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf" %>
<input value="${sn1 }" type="hidden" id="sn1"/>
<script type="text/javascript" src="${ctx}/js/system/order/handle.js"></script>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="form" name="form" class="form-horizontal" method="post" action="${ctx}/order/addSupport.shtml">
		<section class="panel panel-default">
		<c:forEach items="${list}" var="list" begin="0" end="0">
			<input value="${list.status }" type="hidden"/>
			<input name="orderStatusFormMap.sn1" value="${list.sn1 } " type="hidden"/>
			<input name="orderStatusFormMap.order_id" value="${list.id } " type="hidden"/>
			<input name="orderStatusFormMap.status" value="${list.status }" type="hidden"/>
			<div class="panel-body">
				<div class="form-group">
					<label class="col-sm-3 control-label">物流状态</label>
					<div class="col-sm-9">
						<!-- <input type="text" class="form-control" minlength="2" maxlength="100" required placeholder="请输入商品标题" name="itemFormMap.title" id="title"> -->
						<c:if test="${list.status == 1 }">
							<select class="form-control m-b valid" style="width:22%" id="status" id="status">
								<%-- <option value="-1" <c:if test='${list.status == -1}'>selected="selected"</c:if> >取消</option> 
								<option value="0" <c:if test='${list.status == 0}'>selected="selected"</c:if> >待付款</option>--%>
								<%-- <option value="1" <c:if test='${list.status == 1}'>selected="selected"</c:if> >待收货</option>
								<option value="2" <c:if test='${list.status == 2}'>selected="selected"</c:if> >待评价</option>
								<option value="3" <c:if test='${list.status == 3}'>selected="selected"</c:if> >已完成</option> 
								<option value="4" <c:if test='${list.status == 4}'>selected="selected"</c:if> >售后</option>--%>
								<option value="备货中" <c:if test='${list.sub_status == 3}'>selected="selected"</c:if> >备货中</option>
								<option value="备货失败" <c:if test='${list.sub_status == 4}'>selected="selected"</c:if> >备货失败</option>
								<option value="骑手接单中" <c:if test='${list.sub_status == 5}'>selected="selected"</c:if> >骑手接单中</option>
								<option value="接单失败" <c:if test='${list.sub_status == 6}'>selected="selected"</c:if> >接单失败</option>
								<option value="配送中" <c:if test='${list.sub_status == 7}'>selected="selected"</c:if> >配送中</option>
								<option value="配送成功" <c:if test='${list.sub_status == 14}'>selected="selected"</c:if> >配送成功</option>
								<option value="配送失败" <c:if test='${list.sub_status == 8}'>selected="selected"</c:if> >配送失败</option>
							</select>
						</c:if>
						<c:if test="${list.status == 4 }">
							<select class="form-control m-b valid" style="width:22%" id="status" id="status">
								<option value="资料审核中" <c:if test='${list.sub_status == 9}'>selected="selected"</c:if> >资料审核中</option>
								<option value="审核失败" <c:if test='${list.sub_status == 10}'>selected="selected"</c:if> >审核失败</option>
								<option value="退款中" <c:if test='${list.sub_status == 11}'>selected="selected"</c:if> >退款中</option>
								<option value="退款失败" <c:if test='${list.sub_status == 12}'>selected="selected"</c:if> >退款失败</option>
								<option value="退款成功" <c:if test='${list.sub_status == 13}'>selected="selected"</c:if> >退款成功</option>
							</select>
						</c:if>
					</div>
				</div>
				
				<%-- <div id="status_s" style="display: block;">
					<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
						<label class="col-sm-3 control-label">订单子状态</label>
						<div class="col-sm-9">
							<!-- <input type="text" class="form-control" minlength="2" maxlength="100" required placeholder="请输入商品副标题" name="itemFormMap.subtitle" id="subtitle"> -->
							<select class="form-control m-b valid" style="width:17%" name="orderFormMap.sub_status" id="sub_status">
								<!-- <option value="1">拼团中</option>
								<option value="2">拼团失败</option> -->
								<option value="3" <c:if test='${list.sub_status == 3}'>selected="selected"</c:if> >备货中</option>
								<option value="4" <c:if test='${list.sub_status == 4}'>selected="selected"</c:if> >备货失败</option>
								<option value="5" <c:if test='${list.sub_status == 5}'>selected="selected"</c:if> >骑手接单中</option>
								<option value="6" <c:if test='${list.sub_status == 6}'>selected="selected"</c:if> >接单失败</option>
								<option value="7" <c:if test='${list.sub_status == 7}'>selected="selected"</c:if> >配送中</option>
								<option value="8" <c:if test='${list.sub_status == 8}'>selected="selected"</c:if> >配送失败</option>
								<option value="9" <c:if test='${list.sub_status == 9}'>selected="selected"</c:if> >资料审核中</option>
								<option value="10" <c:if test='${list.sub_status == 10}'>selected="selected"</c:if> >审核失败</option>
								<option value="11"<c:if test='${list.sub_status == 11}'>selected="selected"</c:if> >退款中</option>
								<option value="12" <c:if test='${list.sub_status == 12}'>selected="selected"</c:if> >退款失败</option>
								<option value="13" <c:if test='${list.sub_status == 13}'>selected="selected"</c:if> >退款成功</option>
							</select>
						</div>
					</div>
				</div> --%>
				<div class="line line-dashed line-lg pull-in"></div>
				<!-- <div class="form-group">
				<div id="remark" name="orderStatusFormMap.remark" style="display: none"></div> -->
					<label class="col-sm-3 control-label">物流记录</label>
					<div class="col-sm-9">
						<textarea rows="5" cols="85" name="orderStatusFormMap.remark" id="remark" class="form-control m-b valid"></textarea>
					</div>
				</div>
			</div>
		</c:forEach>
		<footer class="panel-footer text-right bg-light lter">
			<c:forEach items="${list}" var="list" begin="0" end="0">
				<c:if test="${list.status == 4 }">
					<button type="button" id="apply" class="btn btn-primary marR10">查看售后申请详情</button>
				</c:if>
			</c:forEach>
			<button type="submit" class="btn btn-success btn-s-xs">提交</button>
		</footer> 
		</section>
	</form>
	<div class="table-responsive">
		<div id="paging" class="pagclass"></div>
	</div>

</body>
</html>