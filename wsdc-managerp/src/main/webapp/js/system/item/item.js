var pageii = null;
var grid = null;

$(function() {
	grid = lyGrid({
		pagId : 'paging',
		multiselect: true,  
		multiboxonly:true,  
		beforeSelectRow: beforeSelectRow,//js方法  
		l_column : [ {
			colkey : "item_nm",
			width : "90px",
			name : "名称",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, {
			colkey : "title",
			width : "150px",
			name : "标题",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, {
			colkey : "subtitle",
			width : '150px',
			name : "副标题",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, {
			colkey : "price_single",
			width : "90px",
			name : "售价"
		}, {
			colkey : "inventory",
			width : "90px",
			name : "库存"
		}, {
			colkey : "show_yn",
			width : "50px",
			name : "是否前台显示",
			renderData : function(rowindex, data, rowdata, column) {
				return data == "0" ? "显示" : "不显示";
			}
		}, {
			colkey : "favorite_count",
			width : "50px",
			name : "收藏"
		}, {
			colkey : "share_count",
			width : "50px",
			name : "分享"
		}, {
			colkey : "view_count",
			width : "50px",
			name : "点击"
		}, {
			colkey : "sale_hot",
			name : "是否热卖",
			width : '90px',
			renderData : function(rowindex, data, rowdata, column) {
				return data == "0" ? "非热卖" : "热卖";
			}
		}, {
			colkey : "sale_num",
			width : "90px",
			name : "销量"
		}],
		jsonUrl : rootPath + '/item/single.shtml?city_id='+city_id ,
		checkbox : true,
		serNumber : true
	});
	
	$("#add").click("click", function() {
		addItem();
	});
	
	$("#reset").click("click", function() {
		resetT();
	});
	
	$("#search").click("click", function() {
		grid.setOptions({
			url: rootPath + '/item/single.shtml',
			data : {
				name:$('#name').val()
			}
		});
	});
	$("#shows").hide();
});

/**--重置--**/
function resetT() {
	$("#name").val("");
	$("#search").trigger("click");
}

function beforeSelectRow()  
{  
    $("#paging").jqGrid('resetSelection');  
    return(true);  
}

function addPic(id) {
	pageii = layer.open({
		title : "商品图片管理",
		type : 2,
		area : [ "50%", "80%" ],
		content : rootPath + '/item/addPicUI.shtml?id=' + id + '&ispass=addPic'
	});
}

function addItem() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择要添加的商品！！");
		return;
	}
	$("#shows").show();
}

function gradeChange(){
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择要添加的商品！！");
		$("#shows").hide();
		return;
	}else{
		var parts = $("#parts").val();
		var days = $("#select").val();
		layer.confirm('是否将商品添加进套餐？', function(index) {
			if(days == ''){
				layer.msg('请选择天数!');
			}else if(parts == '') {
				layer.msg('请填写份数!');
			}else{
				var url = rootPath + '/item/addSingleItem.shtml';
				var s = CommnUtil.ajax(url,{
					ids:cbox.join(","),
					parts:parts,
					days:days,
					itemId:$("#itemId").val()
				},"json");
				if(s == "success"){
					layer.msg('商品添加成功!');
				}
				parent.layer.close(parent.pageii);
			}
		});
	}
}
