var pageii = null;
var grid = null;

$(function() {
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {//表格列表数据
			colkey : "id",
			width : "30px",
			name : "id",
		},{
			colkey : "user_id",
			width : "90px",
			name : "用户"
		},{
			colkey : "type",
			width : "70px",
			name : "评价图片",
			renderData : function(rowindex, data, rowdata, column) {
				if(data == 0) return '<a href="javascript:void(0)" onclick="imgEval('+ rowdata.id + ');">评价图片</a>';
				else return '';
			}
		}, {
			colkey : "rel_type",
			width : "50px",
			name : "类型",
			renderData : function(rowindex, data, rowdata, column) {
				if (data == 0) return '商品';
			}
		}, {
			colkey : "content",
			width : '250px',
			name : "内容",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, {
			colkey : "stars_item",
			width : "50px",
			name : "商品评价"
		}, {
			colkey : "stars_service",
			width : "50px",
			name : "服务评价"
		}, {
			colkey : "stars_rider",
			width : '50px',
			name : "骑手评价"
		}, {
			colkey : "create_date",
			width : "90px",
			name : "评价时间 ",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}, {
			colkey : "r_yn",
			width : "50px",
			name : "是否已读",
			renderData : function(rowindex,data, rowdata, column) {
				if (data == 0) return '未读';
				if (data == 1) return '已读';
			}
		}, {
			colkey : "type",
			width : "50px",
			name : "评价类型",
			renderData : function(rowindex,data, rowdata, column) {
				if (data == 0) return '评价';
				if (data == 1) return '回复';
			}
		} ,{
			colkey : "id",
			width : "50px",
			name : "回复评价",
			renderData : function(rowindex,data, rowdata, column) {
				return '<a href="javascript:void(0)" onclick="reply('+ data + ');">回复评价</a>';
			}
		} ],
		jsonUrl : rootPath + '/eval/findByPage.shtml?item_id='+$("#item_id").val(),
		checkbox : true
		//serNumber : true
	});
	
	$("#delEval").click("click", function() {
		var cbox = grid.getSelectedCheckbox();
		if (cbox == "") {
			layer.msg("请选择删除项！！");
			return;
		}
		
		layer.confirm('是否删除？', function(index) {
			var url = rootPath + '/eval/deleteEval.shtml';
			var s = CommnUtil.ajax(url, {
				ids : cbox.join(",")
			}, "json");
			if (s == "success") {
				layer.msg('删除成功');
				grid.loadData();
			} else {
				layer.msg('删除失败');
			}
		});
	});
	
 	$("#replyForm").validate({
 		submitHandler : function(form) {//必须写在验证前面，否则无法ajax提交
 			ly.ajaxSubmit(form,{//验证新增是否成功
 				type : "post",
 				dataType:"json",
 				success : function(data) {
 					if (data=="success") {
 						layer.confirm('回复成功!是否关闭窗口?', function(index) {
 							parent.grid.loadData();
				        	parent.layer.close(parent.pageii);
				        	return false;
 						});
 						$("#form")[0].reset();
 					} else {
 						layer.msg('回复失败！');
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


function imgEval(id) {
	pageii = layer.open({
		title : "评价图片",
		type : 2,
		area : [ "50%", "80%" ],
		content : rootPath + '/eval/evalImg.shtml?id='+id+'&ispass=img'
	});
}

function reply(id) {
	pageii = layer.open({
		title : "回复商品评价",
		type : 2,
		area : [ "50%", "80%" ],
		content : rootPath + '/eval/evalImg.shtml?id=' + id+'&ispass=reply'
	});
}