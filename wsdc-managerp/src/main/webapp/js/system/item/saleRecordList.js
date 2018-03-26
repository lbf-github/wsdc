var pageii = null;
var grid = null;

$(function() {
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {//表格列表数据
			colkey : "id",
			width : "90px",
			name : "id",
		},{
			colkey : "item_id",
			width : "90px",
			name : "商品 id"
		}, {
			colkey : "title",
			width : "90px",
			name : "商品标题"
		}, {
			colkey : "price_single",
			width : "120px",
			name : "售价"
		}, {
			colkey : "price_original",
			width : "120px",
			name : "原价"
		}, {
			colkey : "create_date",
			width : "120px",
			name : "时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd");
			}
		}, {
			colkey : "sale_total",
			width : "90px",
			name : "销量"
		} ],
		jsonUrl : rootPath + '/item/findSaleRecordByPage.shtml?item_id='+item_id,
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
});

/**--重置--**/
function resetT() {
	$("#startTime").val("");
	$("#endTime").val("");
	$("#search").trigger("click");
}
