var dialog;
var grid;

$(function() {
	var id = $("#answer_id").val();
	grid = lyGrid({
		id : 'paging',
		l_column : [ {
			colkey : "id",
			name : "id"
		}, {
			colkey : "user_id",
			width : "90px",
			name : "用户名"
		}, {
			colkey : "type",
			name : "类型",
			renderData : function( rowindex ,data, rowdata, colkey){
				if(data == 1) return '健康问答答案';
			}
		} ],
		jsonUrl : rootPath + '/topicAnswer/findLikeByPage.shtml?answer_id='+id,
		checkbox : true,
		serNumber : true
	});
	
});