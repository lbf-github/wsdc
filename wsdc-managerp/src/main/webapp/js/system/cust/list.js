var pageii = null;
var grid = null;
$(function() {
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {//表格列表数据
			colkey : "mobile",
			name : "账号"
		},  {
			colkey : "nickname",
			name : "昵称"
		}, {
			colkey : "userName",
			name : "姓名"
		},{
			colkey : "sex",
			name : "性别",
			width : '90px',
			renderData : function(rowindex, data, rowdata, column) {
				if(data == "1"){
					return "男";
				} else if(data == "2"){
					return "女";
				} else{
					return "未知";
				}
			}
		}, {
			colkey : "shareCode",
			name : "分享码"
		}, {
			colkey : "ringLetter",
			name : "环信id"
		}, {
			colkey : "latitude",
			name : "纬度"
		}, {
			colkey : "longitude",
			name : "经度"
		}, {
			colkey : "area",
			name : "地域"
		}, {
			colkey : "signature",
			name : "个性签名"
		}, {
			colkey : "reg_from",
			name : "注册来源",
			width : '90px',
			renderData : function(rowindex, data, rowdata, column) {
				return data == "0" ? "微信" : "app";
			}
		}, {
			colkey : "status",
			name : "状态",
			width : '90px',
			renderData : function(rowindex, data, rowdata, column) {
				return data == "0" ? "正常" : "禁用";
			}
		},  {
			colkey : "birth",
			name : "生日",
			renderData : function(rowindex, data, rowdata, column) {
				if(data != ''){
					return new Date(data).format("yyyy-MM-dd");
				} else {
					return "<font color='red'>暂无</font>";
				}
			}
		} ],
		jsonUrl : rootPath + '/cust/findByPage.shtml',
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
		content : rootPath + '/cust/addUI.shtml'
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
		content : rootPath + '/cust/editUI.shtml?id=' + cbox
	});
}

function delFun() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择删除项！！");
		return;
	}
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/cust/deleteEntity.shtml';
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

	