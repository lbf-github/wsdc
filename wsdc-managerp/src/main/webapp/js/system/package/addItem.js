var pageii = null;
var grid = null;
var id = $("#id").val();

$(function() {
	$("#shows").hide();
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {//表格列表数据
			colkey : "id",
			name : "id",
			width:'50px'
		},{
			colkey : "item_nm",
			width : '150px',
			name : "产品名称",
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
		},  {
			colkey : "price_single",
			width : "90px",
			name : "售价"
		}, {
			colkey : "inventory",
			width : "90px",
			name : "库存"
		}, {
			colkey : "sale_hot",
			name : "是否热卖",
			width : '90px',
			renderData : function(rowindex, data, rowdata, column) {
				return data == "0" ? "非热卖" : "热卖";
			}
		}],
		jsonUrl : rootPath + '/package/selectItemList.shtml?id='+id,
		checkbox : true,
		serNumber : true
	});
	
	$("#add").click("click", function() {
		var cbox = grid.getSelectedCheckbox();
		if (cbox.length > 1 || cbox == "") {
			layer.msg("只能选中一个!!");
			return;
		}else{
			$("#shows").show();
		}
	});
	
	$("#append").click("click", function() {
		var cbox = grid.getSelectedCheckbox();
		if(cbox.length > 1 || cbox == ""){
			layer.msg("请选择一种商品!!");
			$("#shows").hide();
			return;
		}
		layer.confirm('是否添加？', function(index) {
			var name = $("#name").val();
			var url = rootPath + '/package/addList.shtml';
			var s = CommnUtil.ajax(url, {
				ids : cbox.join(","),
				id: id,
				name:name
			}, "json");
			if (s == "success") {
				$("#name").val("");
				$("#shows").hide();
				layer.msg('添加成功');
				grid.loadData();
			} else {
				layer.msg('添加失败');
			}
		});
	});
});