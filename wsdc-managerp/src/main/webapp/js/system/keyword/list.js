var pageii = null;
var grid = null;
$(function() {
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {//表格列表数据
			colkey : "id",
			name : "id",
			width : '50px'
		}, {
			colkey : "word",
			name : "关键字",
			width : '50px'
		}, {
			colkey : "rel_word",
			name : "联想词",
			width : '90px',
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, {
			colkey : "frequency",
			name : "搜索次数(真实)",
			width : '50px'
		}, {
			colkey : "frequency2",
			name : "搜索次数(配置)",
			width : '50px'
		}, {
			colkey : "seq",
			name : "排序",
			width : '50px'
		},{
			colkey : "create_date",
			name : "创建时间",
			width : '50px',
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		} ],
		jsonUrl : rootPath + '/keyword/findByPage.shtml',
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
	$("#searchForm").click("click", function() {//绑定查询按扭
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
	$("#word").val("");
	$("#search").trigger("click");
}

function addFun() {
	pageii = layer.open({
		title : "新增",
		type : 2,
		area : [ "600px", "80%" ],
		content : rootPath + '/keyword/addUI.shtml'
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
		content : rootPath + '/keyword/editUI.shtml?id=' + cbox
	});
}

function delFun() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择删除项！！");
		return;
	}
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/keyword/deleteEntity.shtml';
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

	