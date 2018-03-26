var dialog;
var grid;
$(function(){
	var sn1 = $("#sn1").val();
	grid = lyGrid({
		id : 'paging',
		l_column : [{
			colkey : "sn1",
			width : '120px',
			name : "订单编号",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, /*{
			colkey : "status",
			name : "状态",
			width : '90px',
			renderData : function(rowindex,data, rowdata, column) {
				if (data == -1 ) return  '取消';
				if (data == 0 ) return  '待付款';
				if (data == 1 ) return  '待收货';
				if (data == 2 ) return  '待评价';
				if (data == 3 ) return  '备货中';
				if (data == 4 ) return  '备货失败';
				if (data == 5 ) return  '骑手接单中';
				if (data == 6 ) return  '接单失败';
				if (data == 7 ) return  '配送中';
				if (data == 8 ) return  '配送失败';
				if (data == 9 ) return  '资料审核中';
				if (data == 10 ) return  '审核失败';
				if (data == 11 ) return  '退款中';
				if (data == 12 ) return  '退款失败';
				if (data == 13 ) return  '退款成功';
				if (data == 14 ) return  '配送完成';
			}
		}, */{
			colkey : "update_date",
			name : "物流记录时间",
			width : '150px',
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		},{
			colkey : "remark",
			name : "物流记录",
			width : '120px',
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}],
		jsonUrl : rootPath + '/order/orderStatus.shtml?id='+sn1,
		local:false,//支持本地数据前端分页
		checkbox : false
	});
	
	$("#apply").click("click", function() {
		pageii = layer.open({
			title : "申请售后详情",
			type : 2,
			area : [ "80%", "80%" ],
			content : rootPath + '/order/apply.shtml?sn1='+sn1
		});
	});
	
 	$("#form").validate({
 		submitHandler : function(form) {//必须写在验证前面，否则无法ajax提交
 			if($("#remark").val().trim() == ""){
 				$("#remark").val($("#status").val());
 			}
 			ly.ajaxSubmit(form,{//验证新增是否成功
 				type : "post",
 				dataType:"json",
 				success : function(data) {
 					if (data=="success") {
 						layer.confirm('提交成功!是否关闭窗口?', function(index) {
 							parent.grid.loadData();
				        	parent.layer.close(parent.pageii);
				        	return false;
 						});
 						$("#form")[0].reset();
 					} else {
 						layer.msg('提交失败！');
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
})

function change(){
	if($("#status").val() == -1 || $("#status").val() == 0 || $("#status").val() == 2 || $("#status").val() == 3){
		$('#status_s').hide();
	}
	if($("#status").val() == 1){
		("#status_s").empty();
	}
}
