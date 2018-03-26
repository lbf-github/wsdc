var pageii = null;
var grid = null;

$(function() {
	grid = lyGrid({
		pagId : 'paging',
		l_column : [{
			colkey : "item_nm",
			width : "90px",
			name : "名称",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, {
			colkey : "title",
			width : "150px",
			name : "标题",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, {
			colkey : "subtitle",
			width : '150px',
			name : "副标题",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, {
			colkey : "price_single",
			width : "90px",
			name : "售价"
		}, {
			colkey : "inventory",
			width : "90px",
			name : "库存"
		}, {
			colkey : "day",
			width : "90px",
			name : "第几天"
		}, {
			colkey : "pac_number",
			width : "90px",
			name : "份数"
		}, {
			colkey : "favorite_count",
			width : "50px",
			name : "收藏"
		}, {
			colkey : "share_count",
			width : "50px",
			name : "分享"
		}, {
			colkey : "view_count",
			width : "50px",
			name : "点击"
		}, {
			colkey : "sale_hot",
			name : "是否热卖",
			width : '90px',
			isSort:true,
			renderData : function(rowindex, data, rowdata, column) {
				return data == "0" ? "非热卖" : "热卖";
			}
		}],
		jsonUrl : rootPath + '/item/viewItem.shtml?itemId='+$('#itemId').val(),
		checkbox : true,
		serNumber : true
	});
	$("#deleteItem").click("click", function() {
		var cbox = grid.getSelectedCheckbox();
		if (cbox == "") {
			layer.msg("请选择要删除的商品！！");
			return;
		}
		layer.confirm('是否删除商品？', function(index) {
			var url = rootPath + '/item/deleteItem.shtml';
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
	});
});

function change(){
	var searchParams = $("#searchForm").serializeJson();// 初始化传参数
	grid.setOptions({
		url: rootPath + '/item/viewItem.shtml',
		data : {
			itemId:$('#itemId').val(),
			days : $("#select").val()
		}
	});
}
