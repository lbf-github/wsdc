var dialog;
var grid;

$(function() {
	var topic_id = $("#topic_id").val();
	grid = lyGrid({
		id : 'paging',
		l_column : [ {
			colkey : "id",
			width : '50px',
			name : "id"
		}, {
			colkey : "id",
			width : "50px",
			name : "图片",
			renderData : function( rowindex ,data, rowdata, colkey){
				return '<a style="cursor:pointer;" href="javascript:void(0)" onclick="addPic('+ data + ');">查看</a>';
			}
		}, {
			colkey : "id",
			name : "点赞人",
			width : '60px',
			renderData : function( rowindex ,data, rowdata, colkey){
				return '<a style="cursor:pointer;" href="javascript:void(0)" onclick="toLike('+ data + ');">查看</a>';
			}
		},{
			colkey : "answer",
			name : "答案",
			width : '400px',
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, {
			colkey : "best_yn",
			name : "是否最佳",
			width : '90px',
			renderData : function(rowindex, data, rowdata, column) {
				return data == "0" ? "否" : "是";
			}
		}, {
			colkey : "r_yn",
			name : "是否已读",
			width : '90px',
			renderData : function(rowindex, data, rowdata, column) {
				return data == "0" ? "未读" : "已读";
			}
		}, {
			colkey : "favorite_count",
			name : "点赞数"
		}, {
			colkey : "create_date",
			name : "创建时间",
			width : '150px',
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}, {
			colkey : "create_by",
			name : "创建人"
		}, {
			colkey : "update_date",
			name : "更新时间",
			width : '150px',
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}, {
			colkey : "update_by",
			name : "更新人"
		} ],
		jsonUrl : rootPath + '/topicAnswer/findByPage.shtml?topic_id='+topic_id,
		checkbox : true,
		serNumber : true
	});
	
	$("#addAnswer").click("click", function() {
		addFun(topic_id);
	});
	
	$("#editAnswer").click("click", function() {
		editFun();
	});
	
	$("#deleteAnswer").click("click", function() {
		delFun();
	});
});

function addFun(topic_id) {
	pageii = layer.open({
		title : "新增",
		type : 2,
		area : [ "600px", "80%" ],
		content : rootPath + '/topicAnswer/addUI.shtml?topic_id='+topic_id
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
		content : rootPath + '/topicAnswer/editUI.shtml?id=' + cbox
	});
}

function delFun() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择删除项！");
		return;
	}
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/topicAnswer/deleteEntity.shtml';
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
function toLike(id){
	pageii = layer.open({
		title : "点赞人",
		type : 2,
		area : [ "60%", "80%" ],
		content : rootPath + '/topicAnswer/toLike.shtml?id=' + id
	});
}