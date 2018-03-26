var pageii = null;
var grid = null;
var exportGrid = lyGrid({
	pagId : 'paging',
	l_column : [ {//表格列表数据
		colkey : "id",
		width : "90px",
		name : "id"
	},{
		colkey : "id",
		width : "250px",
		name : "图片管理",
		renderData : function( rowindex ,data, rowdata, colkey){
			return '<a style="cursor:pointer;" href="javascript:void(0)" onclick="toCommentImg('+ data + ');">编辑</a>';
		}
	/*}, {
		colkey : "item_code",
		width : "250px",
		name : "商品编号",
		renderData : function( rowindex ,data, rowdata, colkey){
			return  "<span title='" + data + "'>" + data + "</span>";
		}*/
	}/*, {
		colkey : "province_name",
		width : "90px",
		name : "省"
	}, {
		colkey : "city_name",
		width : "90px",
		name : "市"
	}*/, {
		colkey : "title",
		width : "250px",
		name : "标题"
	}, {
		colkey : "subtitle",
		width : '250px',
		name : "副标题"
	}, {
		colkey : "price_single",
		width : "90px",
		name : "售价"
	}, {
		colkey : "price_original",
		width : "90px",
		name : "原价"
	}, {
		colkey : "units",
		width : '90px',
		name : "计量单位"
	}, {
		colkey : "pac_num",
		width : "90px",
		name : "份数"
	},{
		colkey : "id",
		name : "商品评价",
		width : "90px",
		renderData : function( rowindex ,data, rowdata, colkey){
			return '<a style="cursor:pointer;" href="javascript:void(0)" onclick="toComment('+ data + ');">查看</a>';
		}
	}, {
		colkey : "tuan_num",
		width : "90px",
		name : "团人数"
	}, {
		colkey : "tuan_price",
		width : "90px",
		name : "团价格"
	}, {
		colkey : "tuan_expire",
		width : "90px",
		name : "有效期(秒)"
	}, {
		colkey : "inventory",
		width : "90px",
		name : "库存"
	}, {
		colkey : "status",
		width : "90px",
		name : "状态"
	}, {
		colkey : "favorite_count",
		width : "90px",
		name : "收藏量"
	}, {
		colkey : "share_count",
		width : "90px",
		name : "分享量"
	}, {
		colkey : "view_count",
		width : "90px",
		name : "点击量"
	}, {
		colkey : "sale_hot",
		name : "是否热卖",
		width : '90px'
	}, {
		colkey : "from_sale",
		name : "是否下架",
		width : '90px'
	}, {
		colkey : "rec_yn",
		name : "是否推荐",
		width : '90px'
	}, {
		colkey : "show_yn",
		width : "90px",
		name : "是否前台显示"
	}, {
		colkey : "sale_num",
		width : "90px",
		name : "销量"
	}, {
		colkey : "seq",
		width : "90px",
		name : "排序"
	}, {
		colkey : "create_date",
		width : "90px",
		name : "创建时间"
	}, {
		colkey : "create_by",
		width : "90px",
		name : "创建人"
	}, {
		colkey : "update_date",
		width : "90px",
		name : "更新时间"
	}, {
		colkey : "update_by",
		width : "90px",
		name : "更新人"
	} ],
	jsonUrl : rootPath + '/item/findByPage.shtml',
	checkbox : true,
	serNumber : true
});

$("#search").click("click", function() {
	var searchParams = $("#searchForm").serializeJson();// 初始化传参数
	exportGrid.setOptions({
		data : searchParams
	});
});

$(function() {
	//加载商品分类下拉信息
	var url = rootPath + '/classify/classifyList.shtml';
	var data = CommnUtil.ajax(url, null, "json");
	if (data != null) {
		var h = "<option value=''>-------请选择-----</option>";
		for ( var i = 0; i < data.length; i++) {
			h+="<option value='" + data[i].id + "'>"+ data[i].title + "</option>";
		}
		$("#classify_id").html(h);
	 } else {
		layer.msg("获取数据出错，请联系管理员!");
	}

	
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {//表格列表数据
			colkey : "id",
			width : "90px",
			name : "id",
		},{
			colkey : "id",
			width : "250px",
			name : "图片管理",
			renderData : function( rowindex ,data, rowdata, colkey){
				return '<a style="cursor:pointer;" href="javascript:void(0)" onclick="toCommentImg('+ data + ');">编辑</a>';
			}
		}/*, {
			colkey : "item_code",
			width : "250px",
			name : "商品编号",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}*/
		/*}, {
			colkey : "province_name",
			width : "90px",
			name : "省"
		}, {
			colkey : "city_name",
			width : "90px",
			name : "市"
		}*/, {
			colkey : "title",
			width : "250px",
			name : "标题",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, {
			colkey : "subtitle",
			width : '250px',
			name : "副标题",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, {
			colkey : "price_single",
			width : "90px",
			name : "售价"
		}, {
			colkey : "price_original",
			width : "90px",
			name : "原价"
		}, {
			colkey : "units",
			width : '90px',
			name : "计量单位",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  "<span title='" + data + "'>" + data + "</span>";
			}
		}, {
			colkey : "pac_num",
			width : "90px",
			name : "份数"
		},{
				colkey : "id",
				name : "商品评价",
				width : "90px",
				renderData : function( rowindex ,data, rowdata, colkey){
					return '<a style="cursor:pointer;" href="javascript:void(0)" onclick="toComment('+ data + ');">查看</a>';
				}
			}, {
			colkey : "tuan_num",
			width : "90px",
			name : "团人数",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  data == "" ? "0" : data;
			}
		}, {
			colkey : "tuan_price",
			width : "90px",
			name : "团价格",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  data == "" ? "0.00" : data;
			}
		}, {
			colkey : "tuan_expire",
			width : "90px",
			name : "有效期(秒)",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  data == "" ? "86400" : data;
			}
		}, {
			colkey : "id",
			width : "90px",
			name : "销量详情",
			renderData : function( rowindex ,data, rowdata, colkey){
				return '<a style="cursor:pointer;" href="javascript:void(0)" onclick="saleDetails('+ data + ');">查看</a>';
			}
		}, {
			colkey : "inventory",
			width : "90px",
			name : "库存"
		}, {
			colkey : "status",
			width : "90px",
			name : "状态"
		}, {
			colkey : "favorite_count",
			width : "90px",
			name : "收藏量",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  data == "" ? "0" : data;
			}
		}, {
			colkey : "share_count",
			width : "90px",
			name : "分享量",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  data == "" ? "0" : data;
			}
		}, {
			colkey : "view_count",
			width : "90px",
			name : "点击量",
			renderData : function( rowindex ,data, rowdata, colkey){
				return  data == "" ? "0" : data;
			}
		}, {
			colkey : "sale_hot",
			name : "是否热卖",
			width : '90px',
			renderData : function(rowindex, data, rowdata, column) {
				return data == "0" ? "非热卖" : "热卖";
			}
		}, {
			colkey : "from_sale",
			name : "是否下架",
			width : '90px',
			renderData : function(rowindex, data, rowdata, column) {
				return data == "0" ? "未下架" : "已下架";
			}
		}, {
			colkey : "rec_yn",
			name : "是否推荐",
			width : '90px',
			renderData : function(rowindex, data, rowdata, column) {
				return data == "0" ? "不推荐" : "推荐";
			}
		}, {
			colkey : "show_yn",
			width : "120px",
			name : "是否前台显示",
			renderData : function(rowindex, data, rowdata, column) {
				return data == "0" ? "显示" : "不显示";
			}
		}, {
			colkey : "sale_num",
			width : "90px",
			name : "累计销量"
		}, {
			colkey : "seq",
			width : "90px",
			name : "排序"
		}, {
			colkey : "create_date",
			width : "150px",
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
			width : "150px",
			name : "更新时间",
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}, {
			colkey : "update_by",
			width : "90px",
			name : "更新人"
		} ],
		jsonUrl : rootPath + '/item/findByPage.shtml',
		checkbox : true,
		serNumber : true
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
	
	$("#confirm").click("click", function() {
		confirmClassifyFun();
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

/**--重置--**/
function resetT() {
	$("#item_nm").val("");
	$("#title").val("");
	$("#show_yn").val("");
	$("#sale_hot").val("");
	$("#from_sale").val("");
	$("#rec_yn").val("");
	$("#province_id").val("");
	$("#city_id").val("");
	$("#city").css({"display":"none"});
	$("#pac_num").val("");
	$("#search").trigger("click");
}


function addFun() {
	pageii = layer.open({
		title : "新增",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/item/addUI.shtml'
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
		area : [ "80%", "80%" ],
		content : rootPath + '/item/editUI.shtml?id=' + cbox
	});
}

function delFun() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择删除项！！");
		return;
	}
	
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/item/deleteEntity.shtml';
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

function confirmClassifyFun() {
	var classify_id = $("#classify_id").val();
	if(classify_id == ""){
		layer.msg("请先选择分类信息！！");
		return;
	}
	
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择需要分类的商品！！");
		return;
	}
	
	layer.confirm('是否确定对该商品分类？', function(index) {
		var url = rootPath + '/classifyItem/addEntity.shtml';
		var s = CommnUtil.ajax(url, {
			ids : cbox.join(","), 
			classify_id : classify_id
		}, "json");
		if (s == "success") {
			layer.msg('分类成功');
			grid.loadData();
		} else {
			layer.msg('分类失败');
		}
	});
}

function addPic(id) {
	pageii = layer.open({
		title : "商品图片管理",
		type : 2,
		area : [ "50%", "80%" ],
		content : rootPath + '/item/addPicUI.shtml?id=' + id + '&ispass=addPic'
	});
}

function addCoverPic(id) {
	pageii = layer.open({
		title : "商品封面管理",
		type : 2,
		area : [ "50%", "80%" ],
		content : rootPath + '/item/addPicUI.shtml?id=' + id + '&ispass=addCoverPic'
	});
}

function addDetailPic(id){
	pageii = layer.open({
		title : "商品详情图片管理",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/item/addPicUI.shtml?id=' + id + '&ispass=addDetailPic'
	});
}

function addItem(id,pac_num,city_id) {
	pageii = layer.open({
		title : "套餐商品新增",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/item/addPicUI.shtml?id=' + id + '&ispass=addItem&pacnum='+pac_num+ '&city_id='+city_id
	});
}

function viewItem(id) {
	pageii = layer.open({
		title : "套餐商品查看",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/item/addPicUI.shtml?id=' + id + '&ispass=viewItem'
	});
}

function saleDetails(id) {
	pageii = layer.open({
		title : "销量详情",
		type : 2,
		area : [ "70%", "80%" ],
		content : rootPath + '/item/saleList.shtml?item_id=' + id
	});
}

//图片移除
function removeImgPic(event){
	$(event).parent().remove();
}

//商品封面图片上传及校验
function uploadItemImg (obj,questionPic) {
	if (!checkimgfiletype(obj)){
		return;
	}
	ajaxFileUpload(questionPic);
}

function checkimgfiletype(obj) {
	var extStart = obj.value.lastIndexOf(".");
    var ext = obj.value.substring(extStart, obj.value.length).toUpperCase();
    if (ext != ".PNG" && ext != ".GIF" && ext != ".JPG" && ext != ".JPEG") {
        alert("图片限于JPG, JPEG, PNG, GIF格式");
        return false;
    }
    return true;
}

function ajaxFileUpload(files){
	var arrayObj = new Array();
	var _files = $("#" + files).find("input[type='file']");
	_files.each(function(){
        arrayObj.push($(this).attr("id"));
    });
	
    $.ajaxFileUpload({
        // 处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
    	url: rootPath+'/qiniuUpload/upload.shtml',
        secureuri : false,                       // 是否启用安全提交,默认为false
        fileElementId : arrayObj,                // 文件选择框的id属性
        dataType : 'file',                       // 服务器返回的格式,可以是json或xml等
        success : function(data){        // 服务器响应成功时的处理函数
        	var datajson = eval("(" + data.replace(/<\/?[^>]*>/g,'') + ")");
        	var s = 10+parseInt(Math.random()*90);
        	var a = String.fromCharCode(Math.floor( Math.random() * 26) + "a".charCodeAt(0));
        	if (datajson.code == "1000" ) {
        		$("#itemImage").append('<div class="bb004" id="div'+s+a+'" ondrop="drop(event,this)" ondragover="allowDrop(event)" draggable="true" ondragstart="drag(event, this)"><img style="height:150px;width:150px;margin-left:15px" src="'+imgUrl+datajson.msg+'">'+
        		'<div class="bb003" onclick="removeImgPic(this)"></div><input type="hidden" name="img_id" value="'+datajson.msg+'"/><input name="imgType" type="hidden" value="2" /></div>');
        	}
        },
        error:function(data, status, e){ // 服务器响应失败时的处理函数
        	// / console.log(status);
        }
    });
}
function allowDrop(ev){  
	ev.preventDefault();  
}  
  
var srcdiv = null;  
function drag(ev,divdom){  
	srcdiv=divdom;  
	ev.dataTransfer.setData("text/html",divdom.innerHTML);  
}  
  
function drop(ev,divdom){  
	ev.preventDefault();  
	if(srcdiv != divdom){  
		srcdiv.innerHTML = divdom.innerHTML;  
		divdom.innerHTML=ev.dataTransfer.getData("text/html");  
	}  
} 

//商品图片上传及校验
function uploadCoverImg (obj,questionPic) {
	if (!checkimgfiletype2(obj)){
		return;
	}
	ajaxFileUpload2(questionPic);
}

function checkimgfiletype2(obj) {
	var extStart = obj.value.lastIndexOf(".");
    var ext = obj.value.substring(extStart, obj.value.length).toUpperCase();
    if (ext != ".PNG" && ext != ".GIF" && ext != ".JPG" && ext != ".JPEG") {
        alert("图片限于JPG, JPEG, PNG, GIF格式");
        return false;
    }
    return true;
}

function ajaxFileUpload2(files){
	var arrayObj = new Array();
	var _files = $("#" + files).find("input[type='file']");
	_files.each(function(){
        arrayObj.push($(this).attr("id"));
    });
	
    $.ajaxFileUpload({
        // 处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
    	url: rootPath+'/qiniuUpload/upload.shtml',
        secureuri : false,                       // 是否启用安全提交,默认为false
        fileElementId : arrayObj,                // 文件选择框的id属性
        dataType : 'file',                       // 服务器返回的格式,可以是json或xml等
        success : function(data){        // 服务器响应成功时的处理函数
        	var datajson = eval("(" + data.replace(/<\/?[^>]*>/g,'') + ")");
        	var s = 10+parseInt(Math.random()*90);
        	var a = String.fromCharCode(Math.floor( Math.random() * 26) + "a".charCodeAt(0));
        	if (datajson.code == "1000" ) {
        		$("#coverImage").append('<div class="bb004" id="div'+s+a+'" ondrop="drop(event,this)" ondragover="allowDrop(event)" draggable="true" ondragstart="drag(event, this)"><img style="height:150px;width:150px;margin-left:15px" src="'+imgUrl+datajson.msg+'">'+
        		'<div class="bb003" onclick="removeImgPic(this)"></div><input type="hidden" name="img_id" value="'+datajson.msg+'"/><input name="imgType" type="hidden" value="1"/></div>');
        	}
        },
        error:function(data, status, e){ // 服务器响应失败时的处理函数
        	// / console.log(status);
        }
    });
}

function toComment(id){
	pageii = layer.open({
		title : "商品评价查看",
		type : 2,
		area : [ "80%", "80%" ],
		content : rootPath + '/eval/list.shtml?id=' + id + '&ispass=viewItem'
	});
}
function toCommentImg(id) {
	pageii = layer.open({
		title: "商品图片管理",
		type: 2,
		area: ["80%", "80%"],
		content: rootPath + '/itemProduct/editUI.shtml?id=' + id
	})
}