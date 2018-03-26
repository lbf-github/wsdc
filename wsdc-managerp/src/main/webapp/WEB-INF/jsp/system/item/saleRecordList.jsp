<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/common/common.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/item/saleRecordList.js"></script>
<script type="text/javascript" src="${ctx}/js/My97DatePickerBeta/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	var item_id = "${item_id}";
</script>
	<div class="m-b-md">
		<form class="form-inline" role="form" id="searchForm"
			name="searchForm">
			<div class="form-group">
				<div class="line line-dashed line-lg pull-in"></div>
				<label class="control-label"> 
					<span class="h4 font-thin v-middle">创建时间:</span>
				</label> 
				<input class="Wdate" style="height:34px;" type="text" onClick="WdatePicker()" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" 
						placeholder="请选择开始时间" name="itemSaleRecordFormMap.startTime" id="startTime" />&nbsp;&nbsp;-
				<input class="Wdate" style="height:34px;" type="text" onClick="WdatePicker()" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" 
						placeholder="请选择结束时间" name="itemSaleRecordFormMap.endTime" id="endTime" />
		
				<a href="javascript:void(0)" class="btn btn-default" id="search">查询</a>
				<a href="javascript:void(0);" class="btn btn-default" id="reset">重置</a>
				<a href="javascript:grid.exportData('/item/exportSaleRecord.shtml?item_id=46')" class="btn btn-info" >导出excel</a>
			</div>
		</form>
	</div>
	
	<div class="table-responsive">
		<div id="paging" class="pagclass"></div>
	</div>