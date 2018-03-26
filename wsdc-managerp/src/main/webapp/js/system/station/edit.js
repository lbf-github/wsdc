$(function() {
	$("form").validate({
		submitHandler : function(form) {// 必须写在验证前面，否则无法ajax提交
			ly.ajaxSubmit(form, {// 验证编辑是否成功
				type : "post",
				dataType : "json",
				success : function(data) {
					if (data == "success") {
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

		errorPlacement : function(error, element) {// 自定义提示错误位置
			$(".l_err").css('display', 'block');
			$(".l_err").html(error.html());
		},

		success : function(label) {// 验证通过后
			$(".l_err").css('display', 'none');
		}
	});
});

function getStationClassify(id) {
	var url = rootPath + '/stationClassify/classifyList.shtml';
	var data = CommnUtil.ajax(url, null, "json");
	if (data != null) {
		var h = "<option value=''>------请选择------</option>";
		for ( var i = 0; i < data.length; i++) {
			if (parseInt(id, 10) == parseInt(data[i].id, 10)) {
				h += "<option value='" + data[i].id + "' selected='selected'>"
						+ data[i].name + "</option>";
			} else {
				h += "<option value='" + data[i].id + "'>" + data[i].name
						+ "</option>";
			}
		}
		$("#classify_id").html(h);
	} else {
		bootbox.alert("获取信息错误，请联系管理员！");
	}
}
