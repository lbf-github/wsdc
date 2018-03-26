var pageii = null;
var grid = null;

$(function() {
	
	grid = lyGrid({
		pagId : 'paging',
		l_column : [{
			colkey : "title",
			width : "150px",
			name : "标题",
			renderData : function( rowindex ,data, rowdata, colkey){
				return '<a style="cursor:pointer;" href="javascript:void(0)" onclick="editItem('+ rowdata.item_id + ');">' +
					"<span title='" + data + "'>" + data + "</span>" + '</a>';
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
			width : "50px",
			name : "售价"
		}, {
			colkey : "inventory",
			width : "90px",
			name : "库存"
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
			colkey : "seq",
			width : "50px",
			name : "排序"
		}, {
			colkey : "sale_hot",
			name : "热卖",
			width : '50px',
			renderData : function(rowindex, data, rowdata, column) {
				return data == "0" ? "非热卖" : "热卖";
			}
		}],
		jsonUrl : rootPath + '/classify/viewItem.shtml?itemId='+$('#itemId').val(),
		checkbox : true,
		serNumber : true
	});
	
	$("#search").click("click", function() {//绑定查询按扭
		var searchParams = $("#searchForm").serializeJson();// 初始化传参数
		grid.setOptions({
			data : searchParams
		});
	});
	
	$("#reset").click("click", function() {
		resetT();
	});
	
	/** --删除商品分类 -- **/
	$("#deleteItemClassify").click("click", function() {
		var cbox = grid.getSelectedCheckbox();
		if (cbox == "") {
			layer.msg("请选择要删除的商品！！");
			return;
		}
		layer.confirm('是否删除商品？', function(index) {
			var url = rootPath + '/classify/deleteItem.shtml';
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


/**--重置--**/
function resetT() {
	$("#item_nm").val("");
	$("#province_id").val("");
	$("#city_id").val("");
	$("#city").css({"display":"none"});
	$("#search").trigger("click");
}

/** --编辑商品信息 -- **/
function editItem(id){
	pageii = layer.open({
		title : "编辑",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/item/editUI.shtml?id=' + id
	});
}
