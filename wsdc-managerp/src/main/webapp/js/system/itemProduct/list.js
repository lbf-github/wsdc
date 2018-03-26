var pageii = null;
var grid = null;

$(function() {
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {//表格列表数据
			colkey : "id",
			width : "90px",
			name : "id",
		},{
			colkey : "item_nm",
			width : "90px",
			name : "商品名称",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, {
			colkey : "seq",
			width : "90px",
			name : "排序"
		}, {
			colkey : "create_date",
			width : "90px",
			name : "创建时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}, {
			colkey : "create_by",
			width : "90px",
			name : "创建人"
		}, {
			colkey : "update_date",
			width : "90px",
			name : "更新时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}, {
			colkey : "update_by",
			width : "90px",
			name : "更新人"
		}, {
			colkey : "id",
			name : "操作",
			width : "90px",
			renderData : function( rowindex ,data, rowdata, colkey){
				return '<a style="cursor:pointer;" href="javascript:void(0)" onclick="addItem('+ data + ');">新增商品</a>';
			}
		} ],
		jsonUrl : rootPath + '/itemProduct/findByPage.shtml',
		checkbox : true,
		serNumber : true
	});
	
	$("#addFun").click("click", function() {
		addFun();
	});
	
	$("#editFun").click("click", function() {
		editFun();
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
	
 	$("#imgForm").validate({
 		submitHandler : function(form) {//必须写在验证前面，否则无法ajax提交
 			ly.ajaxSubmit(form,{//验证新增是否成功
 				type : "post",
 				dataType:"json",
 				success : function(data) {
 					if (data=="success") {
 						layer.confirm('添加成功!是否关闭窗口?', function(index) {
 							parent.grid.loadData();
				        	parent.layer.close(parent.pageii);
				        	return false;
 						});
 						$("#form")[0].reset();
 					} else {
 						layer.msg('添加失败！');
 					}
 				}
 			});
 		},
 		errorPlacement : function(error, element) {//自定义提示错误位置
 			$(".l_err").css('display','block');
 			//element.css('border','3px solid #FFCCCC');
 			$(".l_err").html(error.html());
 		},
 		success: function(label) {//验证通过后
 			$(".l_err").css('display','none');
 		}
 	});
 	
 	$("#coverImgForm").validate({
 		submitHandler : function(form) {//必须写在验证前面，否则无法ajax提交
 			ly.ajaxSubmit(form,{//验证新增是否成功
 				type : "post",
 				dataType:"json",
 				success : function(data) {
 					if (data=="success") {
 						layer.confirm('添加成功!是否关闭窗口?', function(index) {
 							parent.grid.loadData();
				        	parent.layer.close(parent.pageii);
				        	return false;
 						});
 						$("#form")[0].reset();
 					} else {
 						layer.msg('添加失败！');
 					}
 				}
 			});
 		},
 		errorPlacement : function(error, element) {//自定义提示错误位置
 			$(".l_err").css('display','block');
 			//element.css('border','3px solid #FFCCCC');
 			$(".l_err").html(error.html());
 		},
 		success: function(label) {//验证通过后
 			$(".l_err").css('display','none');
 		}
 	});
});

/** --重置-- **/
function resetT() {
	$("#item_nm").val("");
	$("#search").trigger("click");
}

/** --新增产品页面跳转 -- **/
function addFun() {
	pageii = layer.open({
		title : "新增",
		type : 2,
		area : [ "70%", "80%" ],
		content : rootPath + '/itemProduct/addUI.shtml'
	});
}

/** --编辑产品页面跳转 -- **/
function editFun() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox.length > 1 || cbox == "") {
		layer.msg("只能选中一个");
		return;
	}
	pageii = layer.open({
		title : "编辑",
		type : 2,
		area : [ "70%", "80%" ],
		content : rootPath + '/itemProduct/editUI.shtml?id=' + cbox
	});
}

/** --完善商品信息页面跳转 -- **/
function addItem(id) {
	pageii = layer.open({
		title : "商品新增",
		type : 2,
		area : [ "70%", "80%" ],
		content : rootPath + '/item/addUI.shtml?product_id=' + id 
	});
}
