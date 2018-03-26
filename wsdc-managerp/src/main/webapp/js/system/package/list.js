var pageii = null;
var grid = null;

$(function() {
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {//表格列表数据
			colkey : "id",
			name : "id",
			width:'50px'
		}, {
			colkey : "title",
			name : "名称",
			renderData : function(rowindex, data, rowdata, column) {
				return '<a style="cursor:pointer;" href="javascript:void(0)" onclick="addItem('+ rowdata.id + ');">'+data+'</a>';
			}
		}, {
			colkey : "rec_yn",
			name : "是否推荐",
			width : '90px',
			renderData : function(rowindex, data, rowdata, column) {
				return data == "0" ? "不推荐" : "推荐";
			}
		}, {
			colkey : "create_date",
			name : "创建时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}, {
			colkey : "seq",
			name : "排序"
		}, {
			colkey : "create_by",
			name : "创建人"
		}, {
			colkey : "update_date",
			name : "更新时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}, {
			colkey : "update_by",
			name : "更新人"
		} ],
		jsonUrl : rootPath + '/package/findByPage.shtml',
		checkbox : true,
		serNumber : true
	});
	
	$("#addFun").click("click", function() {
		addFun();
	});
	
	$("#editFun").click("click", function() {
		editFun();
	});
	
	$("#delFun").click("click", function() {
		delFun();
	});
 	$("#editNameForm").validate({
 		submitHandler : function(form) {//必须写在验证前面，否则无法ajax提交
 			ly.ajaxSubmit(form,{//验证新增是否成功
 				type : "post",
 				dataType:"json",
 				success : function(data) {
 					if (data=="success") {
 						layer.confirm('修改成功!是否关闭窗口?', function(index) {
 							parent.grid.loadData();
				        	parent.layer.close(parent.pageii);
				        	return false;
 						});
 						$("#form")[0].reset();
 					} else {
 						layer.msg('修改失败！');
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
 	
	$("#search").click("click", function() {
		var searchParams = $("#searchForm").serializeJson();// 初始化传参数
		grid.setOptions({
			data : searchParams
		});
	});
});

function addFun() {
	pageii = layer.open({
		title : "新增",
		type : 2,
		area : [ "600px", "80%" ],
		content : rootPath + '/package/addUI.shtml'
	});
}

function editFun() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox.length > 1 || cbox == "") {
		layer.msg("只能选中一个");
		return;
	}
	pageii = layer.open({
		title : "编辑",
		type : 2,
		area : [ "600px", "80%" ],
		content : rootPath + '/package/editUI.shtml?id=' + cbox
	});
}

function delFun() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择删除项！！");
		return;
	}
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/package/deleteEntity.shtml';
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
}

function addItem(id){
	pageii = layer.open({
		title : "商品包",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/package/viewItem.shtml?id=' + id
	});
}