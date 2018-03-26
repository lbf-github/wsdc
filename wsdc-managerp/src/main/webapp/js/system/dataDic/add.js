$(function() {
	// 异步加载所有数据列表
	$("form").validate({
		submitHandler : function(form) {// 必须写在验证前面，否则无法ajax提交
			ly.ajaxSubmit(form,{//验证新增是否成功
				type : "post",
				dataType : "json",
				success : function(data) {
					if (data == "success") {
						layer.confirm('添加成功!是否关闭窗口?', function(index) {
							parent.grid.loadData();
				        	parent.layer.close(parent.pageii);
				        	return false;
						});
 						$("#form")[0].reset();
					} else {
						layer.alert('添加失败！', 3);
					}
				}
			});
		},
//		rules : {
//			"dataDicFormMap.name" : {
//				required : true,
//				remote : { // 异步验证是否存在
//					type : "POST",
//					url : rootPath + '/dataDic/isExist.shtml',
//					data : {
//						name : function() {
//							return $("#name").val();
//						}
//					}
//				}
//			}
//		},
//		messages : {
//			"dataDicFormMap.name" : {
//				required : "名称不能为空",
//				remote : "该名称已存在"
//			}
//		},
		
		errorPlacement : function(error, element) {// 自定义提示错误位置
			$(".l_err").css('display', 'block');
			$(".l_err").html(error.html());
		},
		
		success : function(label) {// 验证通过后
			$(".l_err").css('display', 'none');
		}
	});
	
	var url = rootPath + '/dataDic/diclists.shtml';
	var data = CommnUtil.ajax(url, null, "json");
	if (data != null) {
		var h = "<option value='0'>------顶级目录------</option>";
		for ( var i = 0; i < data.length; i++) {
			h+="<option value='" + data[i].id + "'>"+ data[i].name + "</option>";
		}
		$("#parentId").html(h);
	} else {
		layer.msg("获取数据出错，请联系管理员！");
	}
});
