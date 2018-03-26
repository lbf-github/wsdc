var pageii = null;
var grid = null;
$(function() {
	grid = lyGrid({
		id : 'paging',
		l_column : [ {
			colkey : "id",
			name : "id",
			hide : true
		}, {
			colkey : "name",
			name : "菜单名称",
			align : 'left',
			isSort : true
		}, {
			colkey : "type",
			name : "菜单类型",
			width : "70px",
		}, {
			colkey : "resKey",
			name : "唯一KEY"
		}, {
			colkey : "resUrl",
			name : "URL地址"
		},{
			colkey : "ishide",
			name : "是否隐藏",
			renderData : function(rowindex, data, rowdata, column) {
				if(data=="0"){
					return "否";
				}else if(data=="1"){
					return "是";
				}
			}
		}, {
			colkey : "description",
			width : "100px",
			name : "描述"
		} ],
		jsonUrl : rootPath + '/resources/treelists.shtml',
		checkbox : true,
		usePage : false,
		records : "treelists",
		treeGrid : {
			type:1,
			tree : true,
			hide:false,
			name : 'name',
			id: "id",
			pid: "parentId"
		}
	});
	$("#seach").click("click", function() {// 绑定查询按扭
		var searchParams = $("#searchForm").serializeJson();
		grid.setOptions({
			data : searchParams
		});
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
	
	$("#recover").click("click", function() {
		recoverFun();
	});
	
	$("#backup").click("click", function() {
		backupFun();
	});
	
	$("#lyGridUp").click("click", function() {// 上移
		var jsonUrl=rootPath + '/background/resources/sortUpdate.shtml';
		grid.lyGridUp(jsonUrl);
	});
	
	$("#lyGridDown").click("click", function() {// 下移
		var jsonUrl=rootPath + '/background/resources/sortUpdate.shtml';
		grid.lyGridDown(jsonUrl);
	});
});
function editFun() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox.length > 1 || cbox == "") {
		layer.alert("只能选中一个");
		return;
	}
	pageii = layer.open({
		title : "编辑",
		type : 2,
		area : [ "600px", "80%" ],
		content : rootPath + '/resources/editUI.shtml?id=' + cbox
	});
}

function addFun() {
	pageii = layer.open({
		title : "新增",
		type : 2,
		area : [ "600px", "80%" ],
		content : rootPath + '/resources/addUI.shtml'
	});
}

function delFun() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.alert("请选择删除项！！");
		return;
	}
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/resources/deleteEntity.shtml';
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

function recoverFun() {
	var cbox = grid.getSelectedCheckbox();
	layer.confirm('是否确定还原菜单数据？', function(index) {
		var url = rootPath + '/resources/recover.shtml';
		var s = CommnUtil.ajax(url, {
			ids : cbox.join(",")
		}, "json");
		if (s == "success") {
			layer.msg('还原成功');
			grid.loadData();
		} else {
			layer.msg('还原失败');
		}
	});
}

function backupFun() {
	var cbox = grid.getSelectedCheckbox();
	layer.confirm('是否确定备份菜单数据？', function(index) {
		var url = rootPath + '/resources/backup.shtml';
		var s = CommnUtil.ajax(url, {
			ids : cbox.join(",")
		}, "json");
		if (s == "success") {
			layer.msg('备份成功');
			grid.loadData();
		} else {
			layer.msg('备份失败');
		}
	});
}
