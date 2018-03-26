<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/common.jspf"%>
<script type="text/javascript" src="${ctx}/js/system/userHealth/edit.js"></script>
<script type="text/javascript">
	$(function(){
		$('#reason').val('${userHealth.reason}');
		$('#problems').val('${userHealth.problems}');
		$('#advice').val('${userHealth.advice}');
	});
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
</style>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	
	<form id="form" name="form" class="form-horizontal" method="post"
		action="${ctx}/userHealth/editEntity.shtml">
		<input type="hidden" class="form-control" value="${userHealth.id}"
			name="userHealthFormMap.id" id="id">
		
		<section class="panel panel-default">
			
			<div class="panel-body">
					
				<div class="form-group">
					<label class="col-sm-3 control-label">BMI</label>
					<div class="col-sm-9">
						<input type="text" class="form-control checkNumber"
							placeholder="请输入BMI" name="userHealthFormMap.bmi" id="bmi" value="${userHealth.bmi}" required />
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">肥胖率</label>
					<div class="col-sm-9">
						<input type="text" class="form-control checkNumber"
							placeholder="请输入肥胖率" name="userHealthFormMap.fat_rate" id="fat_rate" value="${userHealth.fat_rate}" required/>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">体型</label>
					<div class="col-sm-9">
						<input type="text" class="form-control"
							placeholder="请输入体型" name="userHealthFormMap.shape" id="shape" value="${userHealth.shape}" required/>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">理想体重</label>
					<div class="col-sm-9">
						<input type="text" class="form-control checkNumber"
							placeholder="请输入理想体重(单位:kg)" name="userHealthFormMap.ideal_weight" id="ideal_weight" value="${userHealth.ideal_weight}" required/>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">差距体重</label>
					<div class="col-sm-9">
						<input type="text" class="form-control checkNumber"
							placeholder="请输入差距体重(单位:kg)" name="userHealthFormMap.between_weight" id="between_weight" value="${userHealth.between_weight}" required/>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">目标体重</label>
					<div class="col-sm-9">
						<input type="text" class="form-control checkNumber"
							placeholder="请输入目标体重(单位:kg)" name="userHealthFormMap.dest_weight" id="dest_weight" value="${userHealth.dest_weight}" required/>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">原因</label>
					<div class="col-sm-9">
						<textarea class="form-control"
							placeholder="请输入原因" name="userHealthFormMap.reason" id="reason" minlength="1" maxlength="500" required></textarea>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">存在的问题</label>
					<div class="col-sm-9">
						<textarea class="form-control"
							placeholder="请输入存在的问题" name="userHealthFormMap.problems" id="problems" minlength="1" maxlength="500" required></textarea>
					</div>
				</div>
				
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">建议</label>
					<div class="col-sm-9">
						<textarea class="form-control"
							placeholder="请输入建议" name="userHealthFormMap.advice" id="advice"  minlength="1" maxlength="500" required></textarea>
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