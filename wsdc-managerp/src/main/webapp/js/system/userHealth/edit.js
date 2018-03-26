jQuery.validator.addMethod("checkNumber", function(value, element) {
	var name = 　 /^\d+(?:\.\d{1,2})?$/;
    return this.optional(element) || (name.test(value));
}, "请输入正确的数字");	 


$(function() {
	 $("form").validate({
 		submitHandler : function(form) {//必须写在验证前面，否则无法ajax提交
 			ly.ajaxSubmit(form,{//验证编辑是否成功
 				type : "post",
 				dataType:"json",
 				success : function(data) {
 					if (data=="success") {
 						layer.confirm('更新成功!是否关闭窗口?', function(index) {
 							parent.grid.loadData();
				        	parent.layer.close(parent.pageii);
				        	return false;
 						});
 					} else {
 						layer.alert('更新失败！', 3);
 					}
 				}
 			});
 		},
 		errorPlacement : function(error, element) {//自定义提示错误位置
 			$(".l_err").css('display','block');
 			$(".l_err").html(error.html());
 		},
 		success: function(label) {//验证通过后
 			$(".l_err").css('display','none');
 		}
 	});
 });
	 
