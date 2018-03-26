var pageii = null;
var grid = null;

$(function() {
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {//表格列表数据
			colkey : "id",
			name : "id",
		}, {
			colkey : "mobile",
			name : "手机"
		}, {
			colkey : "password",
			name : "密码"
		}, {
			colkey : "reg_from",
			name : "注册来源",
			width : '90px',
			renderData : function(rowindex, data, rowdata, column) {
				return data == "0" ? "微信" : "后台";
			}
		}, {
			colkey : "status",
			name : "状态",
			width : '90px',
			renderData : function(rowindex, data, rowdata, column) {
				return data == "0" ? "正常" : "禁用";
			}
		}, {
			colkey : "create_date",
			name : "创建时间",
			width : '190px',
			renderData : function(rowindex, data, rowdata, column) {
				if(data != ''){
					return new Date(data).format("yyyy-MM-dd");
				} else {
					return "<font color='red'>未知</font>";
				}
			}
		} ],
		jsonUrl : rootPath + '/custAccount/findByPage.shtml',
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
});

function addFun() {
	pageii = layer.open({
		title : "新增",
		type : 2,
		area : [ "600px", "80%" ],
		content : rootPath + '/custAccount/addUI.shtml'
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
		content : rootPath + '/custAccount/editUI.shtml?id=' + cbox
	});
}

function delFun() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择删除项！！");
		return;
	}
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/custAccount/deleteEntity.shtml';
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

	