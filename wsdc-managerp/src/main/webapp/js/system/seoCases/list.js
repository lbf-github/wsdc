var pageii = null;
var grid = null;

$(function() {
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {//表格列表数据
			colkey : "id",
			name : "id"
		}, {
			colkey : "title",
			name : "标题",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, {
			colkey : "seq",
			name : "排序"
		}, {
			colkey : "create_date",
			width : "150px",
			name : "创建时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}, {
			colkey : "create_by",
			name : "创建人"
		}, {
			colkey : "update_date",
			name : "更新时间",
			width : "150px",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}, {
			colkey : "update_by",
			name : "更新人"
		} ],
		jsonUrl : rootPath + '/seoCases/findByPage.shtml',
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
	
	$("#search").click("click", function() {
		var searchParams = $("#searchForm").serializeJson();// 初始化传参数
		grid.setOptions({
			data : searchParams
		});
	});
	
	$("#reset").click("click", function() {
		resetT();
	});
});

/**--重置--**/
function resetT() {
	$("#title").val("");
	$("#search").trigger("click");
}

function addFun() {
	pageii = layer.open({
		title : "新增",
		type : 2,
		area : [ "70%", "80%" ],
		content : rootPath + '/seoCases/addUI.shtml'
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
		area : [ "70%", "80%" ],
		content : rootPath + '/seoCases/editUI.shtml?id=' + cbox
	});
}

function delFun() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择删除项！");
		return;
	}
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/seoCases/deleteEntity.shtml';
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

	