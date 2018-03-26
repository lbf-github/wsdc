var pageii = null;
var grid = null;

$(function() {
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {//表格列表数据
			colkey : "id",
			name : "id",
			width:'50px'
		}, /*{
			colkey : "id",
			name : "封面",
			renderData : function(rowindex, data, rowdata, column) {
				return '<a style="cursor:pointer;" href="javascript:void(0)" onclick="imgItem('+ rowdata.id + ');">封面</a>&nbsp;&nbsp;<a style="cursor:pointer;" href="javascript:void(0)" onclick="gotoImg('+ rowdata.id + ');">图片</a>';
			}
		},*/ {
			colkey : "title",
			name : "名称",
			renderData : function(rowindex, data, rowdata, column) {
				return '<a style="cursor:pointer;" href="javascript:void(0)" onclick="addItem('+ rowdata.id + ');">'+data+'</a>'
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
		jsonUrl : rootPath + '/classify/findByPage.shtml',
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
		content : rootPath + '/classify/addUI.shtml'
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
		content : rootPath + '/classify/editUI.shtml?id=' + cbox
	});
}

function delFun() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择删除项！！");
		return;
	}
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/classify/deleteEntity.shtml';
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
		title : "商品列表",
		type : 2,
		area : [ "70%", "80%" ],
		content : rootPath + '/classify/view.shtml?id=' + id
	});
}

function imgItem(id){
	pageii = layer.open({
		title : "商品分类封面管理",
		type : 2,
		area : [ "50%", "80%" ],
		content : rootPath + '/classify/imgItem.shtml?id=' + id + '&ispass=cover'
	});
}

function gotoImg(id){
	pageii = layer.open({
		title : "商品分类截图管理",
		type : 2,
		area : [ "50%", "80%" ],
		content : rootPath + '/classify/imgItem.shtml?id=' + id + '&ispass=img'
	});
}