var pageii = null;
var grid = null;

$(function() {
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {//表格列表数据
			colkey : "id",
			name : "id"
		},{
			colkey : "game_name",
			name : "游戏名称"
		}, {
			colkey : "game_img",
			name : "游戏图片",
			renderData : function(rowindex, data, rowdata, column) {
				if(data != ''){
					return '<img src="/images/t01e95dd5c230625c49@2x.png" width="100px" height="100px"/>';
				}else{
					return "<font color='red'>暂无</font>";
				}
			}
		},{
			colkey : "player_num",
			name : "玩家数"

		},{
			colkey : "size",
			name : "安装包大小(M）"
		},{
			colkey : "intro",
			name : "游戏简介"

		},{
			colkey : "game_type",
			name : "所属游戏类别"

		},{
			colkey : "url",
			name : "链接地址预留"
		},{
			colkey:"create_date",
			name:"创建时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		},{
			colkey:"hot",
			name:"是否热门"
		},{
			colkey:"newest",
			name:"是否最新"
		},{
			colkey:"sort",
			name:"排序"
		}],
		jsonUrl : rootPath + '/game/findByPage.shtml',
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
	$("#game_name").val("");
	$("#search").trigger("click");
}
function addFun() {
	pageii = layer.open({
		title : "新增",
		type : 2,
		area : [ "40%", "60%" ],
		content : rootPath + '/game/addUI.shtml'
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
		content : rootPath + '/game/editUI.shtml?id=' + cbox
	});
}

function delFun() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择删除项！！");
		return;
	}
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/game/deleteEntity.shtml';
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

	