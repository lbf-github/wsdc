var pageii = null;
var grid = null;

$(function() {
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {//表格列表数据
			colkey : "id",
			name : "id"
		}, {
			colkey : "fr_id",
			name : "发件人id"
		}, {
			colkey : "userName",
			width : "90px",
			name : "发件人昵称"
		}, {
			colkey : "to_id",
			name : "收件人id"
		}, {
			colkey : "nickname",
			width : "90px",
			name : "收件人昵称"
		}, {
			colkey : "title",
			width : "90px",
			name : "标题"
		}, {
			colkey : "content",
			width : "150px",
			name : "内容"
		}, {
			colkey : "r_yn",
			name : "是否已读",
			width : '90px',
			renderData : function(rowindex, data, rowdata, column) {
				return data == "0" ? "<font color='red'>未读</font>" : "已读";
			}
		}, {
			colkey : "create_date",
			name : "创建时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		} ],
		jsonUrl : rootPath + '/msg/findByPage.shtml',
		checkbox : true,
		serNumber : true
	});
	
	$("#search").click("click", function() {
		var searchParams = $("#searchForm").serializeJson();// 初始化传参数
		grid.setOptions({
			data : searchParams
		});
	});
	
	$("#reset").click("click", function() {
		resetT();
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

/**--重置--**/
function resetT() {
	$("#userName").val("");
	$("#nickname").val("");
	$("#title").val("");
	$("#r_yn").val("");
	$("#search").trigger("click");
}

function addFun() {
	pageii = layer.open({
		title : "新增",
		type : 2,
		area : [ "1000px", "80%" ],
		content : rootPath + '/msg/addUI.shtml'
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
		area : [ "1000px", "80%" ],
		content : rootPath + '/msg/editUI.shtml?id=' + cbox
	});
}

function delFun() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择删除项！");
		return;
	}
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/msg/deleteEntity.shtml';
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

	