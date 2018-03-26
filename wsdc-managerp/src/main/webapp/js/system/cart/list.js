var dialog;
var grid;

$(function() {
	grid = lyGrid({
		id : 'paging',
		l_column : [ {
			colkey : "id",
			name : "id",
			hide : true
		}, {
			colkey : "title",
			name : "商品名称",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, {
			colkey : "number",
			name : "数量"
		}, {
			colkey : "t",
			name : "cookie"
		}, {
			colkey : "mobile",
			name : "手机号码"
		}, {
			colkey : "nickname",
			name : "昵称"
		}, {
			colkey : "create_date",
			name : "创建时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}],
		jsonUrl : rootPath + '/cart/findByPage.shtml',
		local:false,//支持本地数据前端分页
		checkbox : false
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
	$("#item_nm").val("");
	$("#mobile").val("");
	$("#nickname").val("");
	$("#search").trigger("click");
}