var pageii = null;
var grid = null;
var id = $("#id").val();


$(function() {
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {//表格列表数据
			colkey : "id",
			name : "列表id",
			width:'50px'
		},{
			colkey : "item_id",
			name : "商品id",
			width:'50px'
		}, {
			colkey : "title",
			name : "标题",
			width:'50px',
			renderData : function(rowindex, data, rowdata, column) {
				return '<a style="cursor:pointer;" href="javascript:void(0)" onclick="edit('+ rowdata.id + ');">'+data+'</a>';
			}
		},{
			colkey : "item_nm",
			name : "商品名称",
			width:'50px'
		}],
		jsonUrl : rootPath + '/package/selectItem.shtml?id='+id,
		checkbox : true,
		serNumber : true
	});
	$("#add").click("click", function() {
		pageii = layer.open({
			title : "添加商品",
			type : 2,
			area : [ "80%", "90%" ],
			content : rootPath + '/package/addItem.shtml?id=' + id
		});
	});
	
	$("#delete").click("click", function() {
		var cbox = grid.getSelectedCheckbox();
		if (cbox == "") {
			layer.msg("请选择删除项！！");
			return;
		}
		layer.confirm('是否删除？', function(index) {
			var url = rootPath + '/package/deleteItem.shtml';
			var s = CommnUtil.ajax(url, {
				ids : cbox.join(","),
				id: id
			}, "json");
			if (s == "success") {
				layer.msg('删除成功');
				grid.loadData();
			} else {
				layer.msg('删除失败');
			}
		});
	});
});
function edit(id){
	pageii = layer.open({
		title : "编辑",
		type : 2,
		area : [ "50%", "50%" ],
		content : rootPath + '/package/editName.shtml?id=' + id
	});
}