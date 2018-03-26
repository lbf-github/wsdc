var dialog;
var grid;

$(function() {
	grid = lyGrid({
		id : 'paging',
		l_column : [ {
			colkey : "id",
			width : '90px',
			name : "订单id"
		}, {
			colkey : "create_date",
			name : "订单创建时间",
			width : '150px',
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}, {
			colkey : "item_nm",
			name : "产品名称",
			width : '250px',
			renderData : function( rowindex ,data, rowdata, colkey){
				return '<a style="cursor:pointer;" href="javascript:void(0)" onclick="viewItem(\''+ rowdata.item_id + '\');">'+"<span title='" + data + "'>" + data + "</span>"+'</a>';
			}
		}, {
			colkey : "title",
			name : "商品标题",
			width : '250px',
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, {
			colkey : "units",
			name : "商品规格",
			width : '90px',
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, {
			colkey : "item_num",
			name : "商品数量",
			width : '90px'
		}, {
			colkey : "money",
			name : "交易额",
			width : '90px'
		},  {
			colkey : "name",
			name : "收货人姓名",
			width : '90px',
		}, {
			colkey : "mobile",
			name : "联系电话",
			width : '100px',
		}, {
			colkey : "sn1",
			width : '250px',
			name : "父编号",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, {
			colkey : "pay_no",
			width : '250px',
			name : "微信订单号"
		} ],
		jsonUrl : rootPath + '/rider/findUserOrderByPage.shtml?rider_id='+rider_id,
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
		reset();
	});
	
});

/**--重置--**/
function reset() {
	$("#startTime").val("");
	$("#endTime").val("");
	$("#search").trigger("click");
}

/**--查看商品信息--**/
function viewItem(id){
	pageii = layer.open({
		title : "查看",
		type : 2,
		area : [ "800px", "80%" ],
		content : rootPath + '/item/editUI.shtml?id=' + id+"&type=view"
	});
}