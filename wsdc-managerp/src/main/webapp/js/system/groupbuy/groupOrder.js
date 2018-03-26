var pageii = null;
var grid = null;
var id = $("#id").val();
$(function() {
	grid = lyGrid({
		pagId : 'paging',
		local:false,
		checkbox : false,
		l_column : [ {//表格列表数据
			colkey : "id",
			name : "id"
		}, {
			colkey : "item_id",
			name : "拼团产品"/*,
			renderData : function(rowindex, data, rowdata, column) {
				return '<a style="cursor:pointer;" href="javascript:void(0)" onclick="toOrderList(\''+ rowdata.create_uid + '\');">'+data+'</a>'
			}*/
		}, {
			colkey : "tuan_uid",
			name : "拼团人"
		}, {
			colkey : "status",
			name : "支付状态",
			renderData : function(rowindex, data, rowdata, column) {
				if(data == 1) return '待支付';
				if(data == 2) return '支付成功';
				if(data == 3) return '支付失败';
			}
		}, {
			colkey : "behavior",
			name : "拼团方式",
			renderData : function(rowindex, data, rowdata, column) {
				if(data == 0) return '开团';
				if(data == 1) return '参团';
			}
		},   {
			colkey : "start_time",
			name : "拼团开始时间",
			renderData : function(rowindex,data, rowdata, column) {
				if(data != '') {
					return new Date(data).format("yyyy-MM-dd hh:mm:ss");
				}
				if(data == undefined || data == ''){
					return '';
				}
			}
		},  {
			colkey : "end_time",
			name : "拼团结束时间",
			renderData : function(rowindex,data, rowdata, column) {
				if(data != '') {
					return new Date(data).format("yyyy-MM-dd hh:mm:ss");
				}
				if(data == undefined || data == ''){
					return '';
				}
			}
		},{
			colkey : "pay_no",
			name : "支付流水号",
			width : "250px",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		} ],
		jsonUrl : rootPath + '/group/groupOrder.shtml?id='+id,
		serNumber : true
	});
});