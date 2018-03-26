<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/common.jspf"%>
<script type="text/javascript" src="${ctx}/js/system/rider/edit.js"></script>
<script type="text/javascript" src="${ctx}/js/My97DatePickerBeta/My97DatePicker/WdatePicker.js"></script>

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
	
	<form id="form" name="form" class="form-horizontal" method="post"
		action="${ctx}/rider/editEntity.shtml">
		<input type="hidden" class="form-control" value="${rider.id}"
			name="riderFormMap.id" id="id">
		
		<section class="panel panel-default">
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">所属站点</label>
				<div class="col-sm-9">
					<select id="station_id" name="riderFormMap.station_id"
						class="form-control m-b">
					</select>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="panel-body">
				<div class="form-group">
					<label class="col-sm-3 control-label">姓名</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" minlength="2" maxlength="20" required
							placeholder="请输入骑手姓名" name="riderFormMap.name" id="name" value="${rider.name}"/>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">性别</label>
					<div class="col-sm-9">
						<div class="btn-group m-r">
							<button data-toggle="dropdown"
								class="btn btn-sm btn-default dropdown-toggle">
								<span class="dropdown-label">
									<c:if test="${rider.sex eq 2}">女</c:if>
									<c:if test="${rider.sex eq 1}">男</c:if>
									<c:if test="${rider.sex eq 0}">未知</c:if>
								</span> <span class="caret"></span>
							</button>
							<ul class="dropdown-menu dropdown-select">
								<li class=""><a href="#"><input type="radio"
										name="riderFormMap.sex" value="2"<c:if test="${rider.sex eq 2}"> checked="checked"</c:if>>女</a></li>
								<li class="active"><a href="#"><input type="radio"
										name="riderFormMap.sex" value="1" <c:if test="${rider.sex eq 1}"> checked="checked"</c:if>>男</a></li>
								<li class=""><a href="#"><input type="radio"
										name="riderFormMap.sex" value="0"<c:if test="${rider.sex eq 0}"> checked="checked"</c:if>>未知</a></li>
							</ul>
						</div>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">年龄</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" digits="true" min="1" max="999"
							placeholder="请输入数字" name="riderFormMap.age" id="age" value="${rider.age}"/>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">配送地区</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" minlength="2" maxlength="100"
							placeholder="请输入配送地区" name="riderFormMap.area" id="area" value="${rider.area}"/>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
					<label class="col-sm-3 control-label">手机号码</label>
					<div class="col-sm-9">
						<input type="text" class="form-control isMobile"
							placeholder="请输入手机号码" value="${rider.mobile}"
							name="riderFormMap.mobile" id="mobile">
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
					<div class="form-group">
					<label class="col-sm-3 control-label">身份证号码</label>
					<div class="col-sm-9">
						<input type="text" class="form-control isIDCard"
							placeholder="请输入身份证号码" value="${rider.id_num}"
							name="riderFormMap.id_num" id="id_num">
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">入职时间</label>
					<div class="col-sm-9">
						<input class="Wdate" type="text" onClick="WdatePicker()" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"  
							placeholder="请选择入职时间" name="riderFormMap.entry_date" id="entry_date" value="${rider.entry_date}" />
					</div>
				</div>
			</div>
			<footer class="panel-footer text-right bg-light lter">
			<button type="submit" class="btn btn-success btn-s-xs">提交</button>
		</footer> 
	</section>
	</form>
	<script type="text/javascript">
		showStation("${rider.station_id}");
	</script>
</body>
</html>