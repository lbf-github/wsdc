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
			colkey : "mobile",
			name : "手机号码"
		}, {
			colkey : "nickname",
			name : "昵称"
		}, {
			colkey : "key_word",
			width : "90px",
			name : "搜索词"
		}, {
			colkey : "search_date",
			name : "搜索时间",
			width : '150px',
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}],
		jsonUrl : rootPath + '/searchRecord/findDetailByPage.shtml?key_word='+key_word,
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
	$("#mobile").val("");
	$("#nickname").val("");
	$("#search").trigger("click");
}