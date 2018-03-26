var pageii = null;
var grid = null;
var exportGrid = lyGrid({
	pagId : 'paging',
	l_column : [ {//表格列表数据
		colkey : "id",
		width : '60px',
		name : "id"
	}, {
		colkey : "name",
		width : '90px',
		name : "姓名"
	}, {
		colkey : "attention_total_y",
		width : '90px',
		name : "总已关注"
	}, {
		colkey : "attention_total_n",
		width : '90px',
		name : "总未关注"
	}, {
		colkey : "attention_today_y",
		width : '90px',
		name : "新增已关注"
	}, {
		colkey : "attention_today_n",
		width : '90px',
		name : "新增未关注"
	}, {
		colkey : "sex",
		name : "性别",
		width : '90px',
		renderData : function(rowindex, data, rowdata, column) {
			if(data == "1"){
				return "男";
			} else if(data == "2"){
				return "女";
			} else{
				return "未知";
			}
		}
	}, {
		colkey : "age",
		width : '90px',
		name : "年龄"
	}, {
		colkey : "area",
		width : '90px',
		name : "派送区域"
	}, {
		colkey : "mobile",
		width : '150px',
		name : "手机号"
	}, {
		colkey : "id_num",
		width : '200px',
		name : "身份证号"
	}, {
		colkey : "station_name",
		width : '150px',
		name : "所属站点"
	}, {
		colkey : "entry_date",
		name : "入职时间",
		width : '150px',
		renderData : function(rowindex, data, rowdata, column) {
			if(data != ''){
				return new Date(data).format("yyyy-MM-dd");
			} else {
				return "<font color='red'>暂无</font>";
			}
		}
	} ],
	jsonUrl : rootPath + '/rider/findByPage.shtml',
	checkbox : true,
	serNumber : true
});

$("#search").click("click", function() {//绑定查询按扭
	var searchParams = $("#searchForm").serializeJson();// 初始化传参数
	exportGrid.setOptions({
		data : searchParams
	});
});

$(function() {
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {//表格列表数据
			colkey : "id",
			width : '60px',
			name : "id"
		}, {
			colkey : "id",
			name : "二维码",
			width : '60px',
			renderData : function( rowindex ,data, rowdata, colkey){      
				return '<a style="cursor:pointer;" href="javascript:void(0)" onclick="viewQRCode(\''+rowdata.name+'\',' + data + '\);">查看</a>';
			}
		}, {
			colkey : "name",
			width : '90px',
			name : "姓名"
		}, {
			colkey : "attention_total_y",
			width : '90px',
			name : "总已关注"
		}, {
			colkey : "attention_total_n",
			width : '90px',
			name : "总未关注"
		}, {
			colkey : "attention_today_y",
			width : '90px',
			name : "新增已关注"
		}, {
			colkey : "attention_today_n",
			width : '90px',
			name : "新增未关注"
		}, {
			colkey : "id",
			name : "操作",
			width : '250px',
			renderData : function( rowindex ,data, rowdata, colkey){
				return '<a style="cursor:pointer;" href="javascript:void(0)" onclick="viewData(\''+data+'\');">查看关注详情</a>&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer;" href="javascript:void(0)" onclick="viewUserOrder(\''+data+'\');">用户订单</a>';
			}
		}, {
			colkey : "sex",
			name : "性别",
			width : '90px',
			renderData : function(rowindex, data, rowdata, column) {
				if(data == "1"){
					return "男";
				} else if(data == "2"){
					return "女";
				} else{
					return "未知";
				}
			}
		}, {
			colkey : "age",
			width : '90px',
			name : "年龄"
		}, {
			colkey : "area",
			width : '90px',
			name : "派送区域"
		}, {
			colkey : "mobile",
			width : '150px',
			name : "手机号"
		}, {
			colkey : "id_num",
			width : '200px',
			name : "身份证号"
		}, {
			colkey : "station_name",
			width : '150px',
			name : "所属站点"
		}, {
			colkey : "entry_date",
			name : "入职时间",
			width : '150px',
			renderData : function(rowindex, data, rowdata, column) {
				if(data != ''){
					return new Date(data).format("yyyy-MM-dd");
				} else {
					return "<font color='red'>暂无</font>";
				}
			}
		} ],
		jsonUrl : rootPath + '/rider/findByPage.shtml',
		checkbox : true,
		serNumber : true
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
	
	$("#addFun").click("click", function() {
		addFun();
	});
	
	$("#editFun").click("click", function() {
		editFun();
	});
	
	$("#delFun").click("click", function() {
		delFun();
	});
	
	var url = rootPath + '/station/stationList.shtml';
	var data = CommnUtil.ajax(url, null, "json");
	if (data != null) {
		var h = "<option value=''>-------请选择-----</option>";
		for ( var i = 0; i < data.length; i++) {
			h+="<option value='" + data[i].id + "'>"+ data[i].name + "</option>";
		}
		$("#station_id").html(h);
	 } else {
		layer.msg("获取数据出错，请联系管理员！");
	 }
});

/**--重置--**/
function resetT() {
	$("#name").val("");
	$("#area").val("");
	$("#mobile").val("");
	$("#station_id").val("");
	$("#search").trigger("click");
}

function addFun() {
	pageii = layer.open({
		title : "新增",
		type : 2,
		area : [ "800px", "80%" ],
		content : rootPath + '/rider/addUI.shtml'
	});
}

function editFun() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox.length > 1 || cbox == "") {
		layer.msg("只能选中一个");
		return;
	}
	pageii = layer.open({
		title : "编辑",
		type : 2,
		area : [ "800px", "80%" ],
		content : rootPath + '/rider/editUI.shtml?id=' + cbox
	});
}

function delFun() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择删除项！！");
		return;
	}
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/rider/deleteEntity.shtml';
		var s = CommnUtil.ajax(url, {
			ids : cbox.join(",")
		}, "json");
		if (s == "success") {
			layer.msg('删除成功');
			grid.loadData();
		} else {
			layer.msg('删除失败');
		}
	});
}

/** --查看用户关注详情 -- **/
function viewData(data) {
	pageii = layer.open({
		title : "关注详情",
		type : 2,
		area : [ "70%", "80%" ],
		content : rootPath + '/attnRecord/viewDataUI.shtml?rider_id='+data
	});
}

/** --查看骑手推广的所有用户订单详情 -- **/
function viewUserOrder(data) {
	pageii = layer.open({
		title : "订单列表",
		type : 2,
		area : [ "70%", "80%" ],
		content : rootPath + '/rider/viewUserOrder.shtml?rider_id='+data
	});
}

/** --查看骑手二维码 -- **/
function viewQRCode(rider_name,rider_id) {
	var url = rootPath + '/rider/viewQRCodeUI.shtml?rider_name=' + encodeURI(encodeURI(rider_name))+"&rider_id="+rider_id;
	pageii = layer.open({
		title : "二维码",
		area: ['350px', '350px'],
		type : 2,
		content : url
	});
}

	