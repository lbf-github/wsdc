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
			colkey : "key_word",
			name : "搜索词"
		}, {
			colkey : "search_count",
			name : "搜索次数"
		}, {
			colkey : "key_word",
			name : "操作",
			renderData : function( rowindex ,data, rowdata, colkey){
				return '<a style="cursor:pointer;" href="javascript:void(0)" onclick="viewData(\''+data+'\');">查看详情</a>';
			}
		} ],
		jsonUrl : rootPath + '/searchRecord/findByPage.shtml',
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
	$("#key_word").val("");
	$("#search").trigger("click");
}

function viewData(data) {
	pageii = layer.open({
		title : "搜索详情",
		type : 2,
		area : [ "70%", "80%" ],
		content : rootPath + '/searchRecord/viewDataUI.shtml?key_word='+data
	});
}

