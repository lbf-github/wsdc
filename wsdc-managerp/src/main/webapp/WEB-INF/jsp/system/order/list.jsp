<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jspf" %>
<script type="text/javascript" src="${ctx}/js/My97DatePickerBeta/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/order/list.js"></script>
	<div class="m-b-md">
		<form class="form-inline" role="form" id="searchForm"
			name="searchForm">
			<div class="form-group">
				<div class="line line-dashed line-lg pull-in"></div>
				<label class="control-label"> 
					<span class="h4 font-thin v-middle">创建时间:</span>
				</label> 
				<input class="Wdate" style="height:34px;" type="text" onClick="WdatePicker()" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" 
						placeholder="请选择开始时间" name="orderFormMap.startTime" id="startTime" />&nbsp;&nbsp;-
				<input class="Wdate" style="height:34px;" type="text" onClick="WdatePicker()" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" 
						placeholder="请选择结束时间" name="orderFormMap.endTime" id="endTime" />
				
				<label class="control-label"> 
					&nbsp;&nbsp;<span class="h4 font-thin v-middle">产品名称:</span>
				</label> 
				<input class="input-medium ui-autocomplete-input" id="item_nm" name="orderFormMap.item_nm"> 
				
				<label class="control-label"> 
					&nbsp;&nbsp;<span class="h4 font-thin v-middle">商品标题:</span>
				</label> 
				<input class="input-medium ui-autocomplete-input" id="title" name="orderFormMap.title"> 
				
				<label class="control-label"> 
					&nbsp;&nbsp;<span class="h4 font-thin v-middle">客户姓名:</span>
				</label> 
				<input class="input-medium ui-autocomplete-input" id="name" name="orderFormMap.name">
					
				<div class="line line-dashed line-lg pull-in"></div>
				<label class="control-label"> 
					<span class="h4 font-thin v-middle">手机号码:</span>
				</label>
				<input class="input-medium ui-autocomplete-input" id="mobile" name="orderFormMap.mobile">
				
				<label class="control-label"> 
					&nbsp;&nbsp;<span class="h4 font-thin v-middle">订单编号:</span>
				</label>
				<input class="input-medium ui-autocomplete-input" id="sn1" name="orderFormMap.sn1">

				<label class="control-label"> 
					&nbsp;&nbsp;<span class="h4 font-thin v-middle">订单状态:</span>
				</label> 
				<select id="status" style="height:26px;width:100px;" name="orderFormMap.status">
					<option value="">请选择</option>
					<option value="-1">取消</option>
					<option value="0">待付款</option>
					<option value="1">待收货</option>
					<option value="2">待评价</option>
					<option value="3">已完成</option>
					<option value="4">售后</option>
				</select>
				<div style="float:right;">
					<a href="javascript:void(0)" class="btn btn-default" id="search">查询</a>
					<a href="javascript:void(0);" class="btn btn-default" id="reset">重置</a>
					<a href="javascript:grid.exportData('/order/export.shtml')" class="btn btn-info" >导出excel</a>
				</div>
			</div>
		</form>
	</div>
	
	<div class="table-responsive">
		<div id="paging" class="pagclass"></div>
	</div>
