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
			colkey : "name",
			name : "骑手姓名"
		}, {
			colkey : "open_id",
			name : "open_id"
		}, {
			colkey : "user_id",
			name : "user_id"
		}, {
			colkey : "attention_yn",
			width : "90px",
			name : "是否关注",
			renderData : function(rowindex,data, rowdata, column) {
				return data == "0" ? "未关注" : "已关注";
			}
		}, {
			colkey : "create_date",
			name : "创建时间",
			width : '250px',
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}],
		jsonUrl : rootPath + '/attnRecord/findDetailByPage.shtml?rider_id='+rider_id,
		local:false,//支持本地数据前端分页
		checkbox : false
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
});

/**--重置--**/
function resetT() {
	$("#rider_id").val("");
	$("#attention_yn").val("");
	$("#search").trigger("click");
}