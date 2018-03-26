var dialog;
var grid;

$(function() {
	grid = lyGrid({
		id : 'paging',
		l_column : [ {
			colkey : "id",
			width : '40px',
			name : "id"
		}, {
			colkey : "create_date",
			name : "订单创建时间",
			width : '200px',
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
		/*}, {
			colkey : "title",
			name : "商品标题",
			width : '250px',
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}*/
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
		}, {
			colkey : "freight",
			name : "运费",
			width : '90px',
			renderData : function( rowindex ,data, rowdata, colkey){
				if(data == "" || typeof(data) == "undefined"){
					return "0";
				}else{
					return data;
				}
			}
			
		/*}, {
			colkey : "deliver_mode",
			name : "运送方式",
			width : '90px'*/
		}, {
			colkey : "status",
			width : '90px',
			name : "订单状态",
			renderData : function(rowindex,data, rowdata, column) {
				if(rowdata.group != undefined){
					return rowdata.group;
				}else{
					if (data == 2 ) return  '<font color="gray">待评价</font>';
					if (data == 3 ) return  '<font color="blue">已完成</font>';
					if(typeof(rowdata.sub_status) == "undefined"){
						if (data == -1 ) return  '<font color="red">取消</font>';
						if (data == 0 ) return  '<font color="green">待付款</font>';
						if (data == 1 ) return  '<a style="cursor:pointer;" href="javascript:void(0)" onclick="handle(\''+ rowdata.sn1 + '\');">待收货</a>';
						if (data == 4 ) return  '<a style="cursor:pointer;" href="javascript:void(0)" onclick="handle(\''+ rowdata.sn1 + '\');">售后</a>';
					}
					if(data == 1 || data == 4){
						return '<a style="cursor:pointer;" href="javascript:void(0)" onclick="handle(\''+ rowdata.sn1 + '\');">'+rowdata.sub_status+'</a>';
					}
//					if(typeof(rowdata.sub_status) != "undefined" && data == 2){
//						return  '<font color="gray">待评价</font>';
//					}
//					if(typeof(rowdata.sub_status) != "undefined" && data == 3){
//						return  '<font color="blue">已完成</font>';
//					}
				}
				
			}
		},  {
			colkey : "name",
			name : "收货人姓名",
			width : '90px',
		}, {
			colkey : "mobile",
			name : "联系电话",
			width : '150px',
		}, {
			colkey : "address",
			name : "地址",
			width : '350px',
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		/*}, {
			colkey : "delivery_date",
			name : "配送日期",
			width : '150px'*/
		}, {
			colkey : "delivery_date2",
			name : "配送日期",
			width : '150px'
		}, {
			colkey : "delivery",
			name : "配送时间段",
			width : '90px'
		}, {
			colkey : "id",
			width : '70px',
			name : "评价",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  '<a style="cursor:pointer;" href="javascript:void(0)" onclick="toEval(\''+ rowdata.item_id + '\',\''+ rowdata.user_id + '\');">用户评价</a>';
			}
		},{
			colkey : "memo",
			name : "留言",
			width : '150px',
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, {
			colkey : "sn1",
			width : '250px',
			name : "订单编号",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		},{
			colkey : "pay_no",
			width : '250px',
			name : "微信订单号",
		},{
			colkey : "openId",
			width : '250px',
			name : "微信open_id",
		} ],
		jsonUrl : rootPath + '/order/findByPage.shtml',
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
	$("#name").val("");
	$("#mobile").val("");
	$("#sn1").val("");
	$("#status").val("");
	$("#item_nm").val("");
	$("#title").val("");
	$("#startTime").val("");
	$("#endTime").val("");
	$("#search").trigger("click");
}

function handle(id){
	pageii = layer.open({
		title : "订单操作",
		type : 2,
		area : [ "50%", "80%" ],
		content : rootPath + '/order/handle.shtml?id=' + id
	});
}

function viewItem(id){
	pageii = layer.open({
		title : "查看",
		type : 2,
		area : [ "800px", "80%" ],
		content : rootPath + '/item/editUI.shtml?id=' + id+"&type=view"
	});
}

function toEval(item_id,user_id){
	pageii = layer.open({
		title : "查看",
		type : 2,
		area : [ "800px", "80%" ],
		content : rootPath + '/eval/toEval.shtml?item_id=' + item_id+"&user_id="+user_id
	});
}
