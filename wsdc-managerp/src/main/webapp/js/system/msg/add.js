	$(function() {
	 	$("form").validate({
	 		submitHandler : function(form) {// 必须写在验证前面，否则无法ajax提交
	 			ly.ajaxSubmit(form,{// 验证新增是否成功
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
	 		errorPlacement : function(error, element) {// 自定义提示错误位置
	 			$(".l_err").css('display','block');
	 			// element.css('border','3px solid #FFCCCC');
	 			$(".l_err").html(error.html());
	 		},
	 		success: function(label) {// 验证通过后
	 			$(".l_err").css('display','none');
	 		}
	 	});
	 	
	 	$("#checkAll").click("click", function() {
			changeStatus();
		});
	 	
	 	$("#confirm").click("click", function() {
			confirmUsersFun();
		});
	 });
	
	function changeStatus(){
		if($("#checkAll").attr('checked')){
			$("#checkAll").removeAttr('checked');
			//没有设置全部选中 action 参数值为2
			$("#form").attr("action", ctx+"/msg/addEntity.shtml?checkAll=2");
			$(".m-select").removeAttr("style");
			$(".table-responsive").removeAttr("style");
			$("#show_date").css({"display":"none"});
	    }else{
	        $("#checkAll").attr('checked','checked');
	        //设置为全部选中 action 参数值为1
	        $("#form").attr("action", ctx+"/msg/addEntity.shtml?checkAll=1");
	        $(".m-select").css({"display":"none"});
	        $(".table-responsive").css({"display":"none"});
	        $("#show_date").removeAttr("style");
		}
	}
	 
	function confirmUsersFun() {
		var cbox = grid.getSelectedCheckbox();
		if (cbox == "") {
			layer.msg("请选择收件人！！");
			return;
		}
	
		layer.confirm('是否确定选中的收件人？', function(index) {
			var row = grid.selectRow();
			var account_ids = "";
			for(var i=0; i<row.length; i++){
				account_ids += row[i].account_id+",";
			}
			$('#to_id').val(account_ids);
			if ($('#to_id').val() != "") {
				layer.msg('已选择收件人！！');
			} else {
				layer.msg('请重新选择收件人！！');
			}
		});
	}
	 
	 