<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf"%>
<script type="text/javascript" src="${ctx}/js/system/msg/add.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/cust/list.js"></script>
<script type="text/javascript" src="${ctx}/js/My97DatePickerBeta/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
    var ctx = "${ctx}";
</script>
<style type="text/css">
.col-sm-3 {
	width: 15%;
	float: left;
}

.col-sm-9 {
	width: 85%;
	float: left;
}

label[class ^="btn btn-default"] {
	margin-top: -4px;
}
</style>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<header class="panel-heading">
		<div class="doc-buttons">
			<c:forEach items="${res}" var="key">
				${key.description}
			</c:forEach>
		</div>
		
		<label>选择全部</label>
		<input type="radio" id="checkAll" >
		
		<div class="m-select">
			<label>请选择收件人<font style="color:red">(必选):</font></label>
			<input id="confirm" type="button" class="j-select-sure" value="确定"/>
		</div>
	</header>
		
	<div class="table-responsive">
		<div id="paging" class="pagclass"></div>
	</div>
	
	<form id="form" name="form" class="form-horizontal" method="post" action="${ctx}/msg/addEntity.shtml?checkAll=2">
		<input class="form-control" type="hidden" name="msgFormMap.to_id" id="to_id">	
		<div class="line line-dashed line-lg pull-in"></div>
		<div class="form-group">
			<div class="col-sm-9">
				<input type="text" class="form-control" minlength="2"
					maxlength="30" required placeholder="请输入标题(2-30字)"
					name="msgFormMap.title" id="title">
			</div>
		</div>

		<div class="line line-dashed line-lg pull-in"></div>
		<div class="form-group">
			<div class="col-sm-9">
				<textarea type="text" class="form-control" minlength="2"
					maxlength="500" required placeholder="请输入内容(2-500字)"
					name="msgFormMap.content" id="content"></textarea>
			</div>
		</div>
		
		<div class="form-group" id="show_date" style="display:none;">
			<div class="col-sm-9">
				<input class="Wdate" type="text" onClick="WdatePicker()" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" 
					placeholder="请设置消息过期时间" name="msgFormMap.expiry_date" id="expiry_date" />
			</div>
		</div>
		
		<section class="panel panel-default">
		<footer class="panel-footer text-right bg-light lter">
		<button type="submit" class="btn btn-success btn-s-xs">提交</button>
		</footer> </section>
	</form>
</body>
</html>