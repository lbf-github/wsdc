var pageii = null;
var grid = null;

$(function() {
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {//表格列表数据
			colkey : "id",
			width : '90px',
			name : "id",
		}, {
			colkey : "id",
			width : "90px",
			name : "评估状态",
			renderData : function( rowindex ,data, rowdata, colkey){
				var status = rowdata["status"];
				if(status == "1"){
					return '<a style="cursor:pointer;" href="javascript:void(0)" onclick="editData('+ data + ');">评估</a>';
				}else if(status == "2"){
					return "<font color='green'>已评估</font>&nbsp;&nbsp;&nbsp;&nbsp;<a style='cursor:pointer;' href='javascript:void(0)' onclick='editData("+ data + ");'>编辑</a>";
				} else {
					return "<font color='red'>临时</font>";
				}
			}
		}, {
			colkey : "id",
			width : "250px",
			name : "设置套餐",
			renderData : function( rowindex ,data, rowdata, colkey){
				if(typeof(rowdata['box_id']) == "undefined"){
					return '<a style="cursor:pointer;" href="javascript:void(0)" onclick="addBox('+ data + ');">新增套餐</a>';
				}else {
					return "<font color='blue'>已设置套餐:"+rowdata['title']+"</font>";
				}
			}
		}, {
			colkey : "box_id",
			width : "150px",
			name : "私人定制",
			renderData : function( rowindex ,data, rowdata, colkey){
				if(typeof(rowdata['box_id']) != "undefined"){
					return '<a style="cursor:pointer;" href="javascript:void(0)" onclick="addItem(' + data + ',' + rowdata['pac_num'] + ',' + rowdata['city_id'] + ');">新增</a>&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer;" href="javascript:void(0)" onclick="viewItem('+ data + ');">查看</a>';
				}else{
					return "<font color='red'>暂未设置套餐</font>";
				}
			}
		}, {
			colkey : "user_id",
			width : '90px',
			name : "用户id"
		}, {
			colkey : "nickname",
			width : '90px',
			name : "昵称",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, {
			colkey : "sex",
			name : "性别",
			width : '90px',
			renderData : function(rowindex, data, rowdata, column) {
				return data == "1" ? "汉纸" : "妹纸";
			}
		}, {
			colkey : "characters",
			width : '90px',
			name : "身份特质"
		}, {
			colkey : "birth",
			name : "生日",
			width : '90px',
			renderData : function(rowindex,data, rowdata, column) {
				if(data != ''){
					return new Date(data).format("yyyy-MM-dd");
				} else {
					return "<font color='red'>暂无</font>";
				}
			}
		}, {
			colkey : "height",
			width : '90px',
			name : "身高"
		}, {
			colkey : "weight",
			width : '90px',
			name : "体重"
		}, {
			colkey : "disease_history",
			width : '90px',
			name : "疾病史",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, {
			colkey : "habits",
			width : '90px',
			name : "生活习惯",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, {
			colkey : "eat_count",
			width : '90px',
			name : "用餐次数"
		}, {
			colkey : "fruit_yn",
			width : '180px',
			name : "是否每天吃300克以上水果"
		}, {
			colkey : "drink",
			width : '90px',
			name : "饮水量",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, {
			colkey : "eat_habits",
			width : '90px',
			name : "用餐习惯",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, {
			colkey : "mobile",
			width : '150px',
			name : "手机号",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, {
			colkey : "bmi",
			width : '90px',
			name : "BMI"
		}, {
			colkey : "fat_rate",
			width : '90px',
			name : "肥胖率"
		}, {
			colkey : "shape",
			width : '90px',
			name : "体型"
		}, {
			colkey : "ideal_weight",
			width : '90px',
			name : "理想体重",
			renderData : function( rowindex ,data, rowdata, colkey){
				if(typeof(rowdata['ideal_weight']) != "undefined"){
					return  data + "kg";
				}else{
					return "<font color='red'>暂无</font>"
				}
			}
		}, {
			colkey : "between_weight",
			width : '90px',
			name : "差距体重",
			renderData : function( rowindex ,data, rowdata, colkey){
				if(typeof(rowdata['between_weight']) != "undefined"){
					return  data + "kg";
				}else{
					return "<font color='red'>暂无</font>"
				}
			}
		}, {
			colkey : "dest_weight",
			width : '90px',
			name : "目标体重",
			renderData : function( rowindex ,data, rowdata, colkey){
				if(typeof(rowdata['dest_weight']) != "undefined"){
					return  data + "kg";
				}else{
					return "<font color='red'>暂无</font>"
				}
			}
		}, {
			colkey : "reason",
			width : '150px',
			name : "原因",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, {
			colkey : "problems",
			width : '150px',
			name : "存在的问题",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, {
			colkey : "advice",
			width : '150px',
			name : "建议",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, {
			colkey : "create_date",
			name : "创建时间",
			width : '90px',
			renderData : function(rowindex,data, rowdata, column) {
				if(data != ''){
					return new Date(data).format("yyyy-MM-dd hh:mm:ss");
				} else {
					return "<font color='red'>暂无</font>";
				}
			}
		}, {
			colkey : "create_by",
			width : '90px',
			name : "创建人"
		}, {
			colkey : "update_date",
			name : "更新时间",
			width : '90px',
			renderData : function(rowindex,data, rowdata, column) {
				if(data != ''){
					return new Date(data).format("yyyy-MM-dd hh:mm:ss");
				} else {
					return "<font color='red'>暂无</font>";
				}
			}
		}, {
			colkey : "update_by",
			width : '90px',
			name : "更新人"
		} ],
		jsonUrl : rootPath + '/userHealth/findByPage.shtml',
		checkbox : true,
		serNumber : true
	});
	
	$("#searchForm").click("click", function() {//绑定查询按扭
		var searchParams = $("#searchForm").serializeJson();// 初始化传参数
		grid.setOptions({
			data : searchParams
		});
	});
	
	$("#delFun").click("click", function() {
		delFun();
	});
	
	$("#reset").click("click", function() {
		resetT();
	});
});

/**--重置--**/
function resetT() {
	$("#nickname").val("");
	$("#search").trigger("click");
}

function editData(id) {
	pageii = layer.open({
		title : "新增健康报告",
		type : 2,
		area : [ "50%", "80%" ],
		content : rootPath + '/userHealth/editUI.shtml?id=' + id
	});
}

function addBox(id) {
	pageii = layer.open({
		title : "新增套餐",
		type : 2,
		area : [ "1000px", "80%" ],
		content : rootPath + '/item/addBoxUI.shtml?id=' + id
	});
}

function addItem(box_id,pac_num,city_id) {
	pageii = layer.open({
		title : "套餐商品新增",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/item/addPicUI.shtml?id=' + box_id + '&ispass=addItem&pacnum='+pac_num+ '&city_id='+city_id
	});
}

function viewItem(box_id) {
	pageii = layer.open({
		title : "套餐商品查看",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/item/addPicUI.shtml?id=' + box_id + '&ispass=viewItem'
	});
}

function editData(id) {
	pageii = layer.open({
		title : "编辑评估信息",
		type : 2,
		area : [ "800px", "80%" ],
		content : rootPath +'/userHealth/editUI.shtml?id=' + id
	});
}

function delFun() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择删除项！！");
		return;
	}
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/userHealth/deleteEntity.shtml';
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

	