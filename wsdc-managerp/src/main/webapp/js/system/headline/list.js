var pageii = null;
var grid = null;

$(function() {
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {//表格列表数据
			colkey : "id",
			name : "id"
		}, {
			colkey : "title",
			name : "内容",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, {
			colkey : "url",
			name : "活动链接",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, {
			colkey : "province_name",
			width : "90px",
			name : "省"
		}, {
			colkey : "city_name",
			width : "90px",
			name : "市"
		}, {
			colkey : "create_date",
			width : "150px",
			name : "创建时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}, {
			colkey : "create_by",
			name : "创建人"
		}, {
			colkey : "update_date",
			name : "更新时间",
			width : "150px",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}, {
			colkey : "update_by",
			name : "更新人"
		}, {
			colkey : "seq",
			name : "排序"
		} ],
		jsonUrl : rootPath + '/headline/findByPage.shtml',
		checkbox : true,
		serNumber : true
	});
	
	//加载省下拉信息
	var url = rootPath + '/dataDic/findByResKey.shtml?resKey=PROVINCE';
	var data = CommnUtil.ajax(url, null, "json");
	if (data != null) {
		var h = "<option value=''>-------请选择-----</option>";
		for ( var i = 0; i < data.length; i++) {
			h+="<option value='" + data[i].id + "'>"+ data[i].name + "</option>";
		}
		$("#province_id").html(h);
	 } else {
		layer.msg("获取数据出错，请联系管理员！");
	 }
	
	$("#addFun").click("click", function() {
		addFun();
	});
	
	$("#editFun").click("click", function() {
		editFun();
	});
	
	$("#delFun").click("click", function() {
		delFun();
	});
	
	$("#search").click("click", function() {
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
	$("#title").val("");
	$("#province_id").val("");
	$("#city_id").val("");
	$("#city").css({"display":"none"});
	$("#search").trigger("click");
}

/** -- 动态获取城市  -- **/  
function getCities(){
	$("#city").removeAttr("style");
	$(".city_id").remove();
	var province_id = $("#province_id").val();
	if (province_id != '' && province_id > 0){	
		var url = rootPath + '/dataDic/findByPid.shtml?parentId='+province_id;
		var data = CommnUtil.ajax(url, null, "json");
		if (data != null) {
			var h = "<option value=''>-------请选择-----</option>";
			for ( var i = 0; i < data.length; i++) {
				h+="<option value='" + data[i].id + "'>"+ data[i].name + "</option>";
			}
			$("#city").append('<select id="city_id" name="headlineFormMap.city_id" style="height:26" class="city_id"></select>');
			$(".city_id").html(h);
		 } else {
			layer.msg("获取数据出错，请联系管理员！");
		 }
	}else{
		return false;
	}
}

function addFun() {
	pageii = layer.open({
		title : "新增",
		type : 2,
		area : [ "600px", "80%" ],
		content : rootPath + '/headline/addUI.shtml'
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
		area : [ "600px", "80%" ],
		content : rootPath + '/headline/editUI.shtml?id=' + cbox
	});
}

function delFun() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择删除项！");
		return;
	}
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/headline/deleteEntity.shtml';
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

	