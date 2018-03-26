var pageii = null;
var grid = null;

$(function() {
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {
			colkey : "user_id",
			width : "90px",
			name : "用户id"
		},{
			colkey : "favorite_type",
			width : "70px",
			name : "收藏类型",
			renderData : function(rowindex, data, rowdata, column) {
				if(data == 1) return '商品收藏';
				else return '';
			}
		},{
			colkey : "rel_id",
			width : "200px",
			name : "商品",
			renderData : function(rowindex, data, rowdata, column) {
				return data == '' ? "<span style='color:red'>此商品已删除</span>" : data;
			}
		},{
			colkey : "favorite_date",
			width : "90px",
			name : "收藏时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}],
		jsonUrl : rootPath + '/favorite/findByPage.shtml',
	//	checkbox : true,
		serNumber : true
	});
});