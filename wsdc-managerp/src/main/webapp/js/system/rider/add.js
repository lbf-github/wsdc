	//单独验证某一个input
	// 手机号码验证
	jQuery.validator.addMethod("isMobile", function(value, element) {
	    var length = value.length;
	    var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
	    return this.optional(element) || (length == 11 && mobile.test(value));
	}, "请正确填写您的手机号码");	
	
	// 身份证号码验证
	jQuery.validator.addMethod("isIDCard", function(value, element) {
	    var id_num = /^[1-9]{1}[0-9]{14}$|^[1-9]{1}[0-9]{16}([0-9]|[xX])$/;
	    return this.optional(element) || (id_num.test(value));
	}, "请正确填写身份证号码");	

	$(function() {
	 	$("form").validate({
	 		submitHandler : function(form) {//必须写在验证前面，否则无法ajax提交
	 			ly.ajaxSubmit(form,{//验证新增是否成功
	 				type : "post",
	 				dataType:"json",
	 				success : function(data) {
	 					if (data=="success") {
	 						layer.confirm('添加成功!是否关闭窗口?', function(index) {
	 							parent.grid.loadData();
					        	parent.layer.close(parent.pageii);
					        	return false;
	 						});
	 						$("#form")[0].reset();
	 					} else {
	 						layer.msg('添加失败！');
	 					}
	 				}
	 			});
	 		},
	 		errorPlacement : function(error, element) {//自定义提示错误位置
	 			$(".l_err").css('display','block');
	 			//element.css('border','3px solid #FFCCCC');
	 			$(".l_err").html(error.html());
	 		},
	 		success: function(label) {//验证通过后
	 			$(".l_err").css('display','none');
	 		}
	 	});
	 	
	 	var url = rootPath + '/station/stationList.shtml';
		var data = CommnUtil.ajax(url, null, "json");
		if (data != null) {
			var h = "<option value=''>-------请选择-----</option>";
			for ( var i = 0; i < data.length; i++) {
				h+="<option value='" + data[i].id + "'>"+ data[i].name + "</option>";
			}
			$("#station_id").html(h);
		 } else {
			layer.msg("获取数据出错，请联系管理员！");
		 }
	 });
