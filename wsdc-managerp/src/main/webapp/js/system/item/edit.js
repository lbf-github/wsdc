	jQuery.validator.addMethod("checkPrice", function(value, element) {
		var price = 　/^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/;
	    return this.optional(element) || (price.test(value));
	}, "请正确填写金额");
	 
	 $(function() {
		 $("form").validate({
	 		submitHandler : function(form) {//必须写在验证前面，否则无法ajax提交
	 			ly.ajaxSubmit(form,{//验证编辑是否成功
	 				type : "post",
	 				dataType:"json",
	 				success : function(data) {
	 					if (data=="success") {
	 						layer.confirm('更新成功!是否关闭窗口?', function(index) {
	 							parent.grid.loadData();
					        	parent.layer.close(parent.pageii);
					        	return false;
	 						});
	 					} else {
	 						layer.alert('更新失败！', 3);
	 					}
	 				}
	 			});
	 		},
	 		errorPlacement : function(error, element) {//自定义提示错误位置
	 			$(".l_err").css('display','block');
	 			$(".l_err").html(error.html());
	 		},
	 		success: function(label) {//验证通过后
	 			$(".l_err").css('display','none');
	 		}
	 	});
	 });
//图片移除
function removeImgPic3(event){
	$("#detailPicList").val($("#detailPicList").val().replace(","+$(event).next().val(),""));
	$(event).parent().remove();
}
function removeImgPic2(event){
	$("#itemPicList").val($("#itemPicList").val().replace(","+$(event).next().val(),""));
	$(event).parent().remove();
}
function removeImgPic1(event){
	$("#coverPicList").val($("#coverPicList").val().replace(","+$(event).next().val(),""));
	$(event).parent().remove();
}

//商品详情图片上传及校验
function uploadItemImg (obj,detailpic,genre) {
	if (!checkimgfiletype(obj)){
		return;
	}
	ajaxFileUpload(detailpic,genre);
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

function ajaxFileUpload(files,genre){
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
        		if(genre == 3){
        			$("#itemImage").append('<div class="bb004" id="div'+s+a+'" ondrop="drop(event,this)" ondragover="allowDrop(event)" draggable="true" ondragstart="drag(event, this)"><img style="height:150px;width:150px;margin-left:15px" src="'+imgUrl+datajson.msg+'">'+
        					'<div class="bb003" onclick="removeImgPic3(this)"></div><input type="hidden" name="" value="'+datajson.msg+'"/><input name="imgType" type="hidden" value="2" /></div>');
        			$("#detailPicList").val($("#detailPicList").val()+","+datajson.msg);
        		}else if(genre == 2){
        			$("#wareImage").append('<div class="bb004" id="div'+s+a+'" ondrop="drop(event,this)" ondragover="allowDrop(event)" draggable="true" ondragstart="drag(event, this)"><img style="height:150px;width:150px;margin-left:15px" src="'+imgUrl+datajson.msg+'">'+
        					'<div class="bb003" onclick="removeImgPic2(this)"></div><input type="hidden" name="" value="'+datajson.msg+'"/><input name="imgType" type="hidden" value="2" /></div>');
        			$("#itemPicList").val($("#itemPicList").val()+","+datajson.msg);
        		}else if(genre == 1){
        			$("#coverImage").append('<div class="bb004" id="div'+s+a+'" ondrop="drop(event,this)" ondragover="allowDrop(event)" draggable="true" ondragstart="drag(event, this)"><img style="height:150px;width:150px;margin-left:15px" src="'+imgUrl+datajson.msg+'">'+
        					'<div class="bb003" onclick="removeImgPic1(this)"></div><input type="hidden" name="" value="'+datajson.msg+'"/><input name="imgType" type="hidden" value="2" /></div>');
        			$("#coverPicList").val($("#coverPicList").val()+","+datajson.msg);
        		}
        	}
        },
        error:function(data, status, e){ // 服务器响应失败时的处理函数
        	// / console.log(status);
        }
    });
}

function getItemClass(id){
//加载商品分类下拉信息
	var url = rootPath + '/classify/classifyList.shtml';
	var data = CommnUtil.ajax(url, null, "json");
	if (data != null) {
		var h = "<option value=''>-------请选择-----</option>";
		for ( var i = 0; i < data.length; i++) {
			if(parseInt(id,10)==parseInt(data[i].id,10)){
				h+="<option value='" + data[i].id + "'  selected='selected'>"+ data[i].title + "</option>";
			}else{
				h+="<option value='" + data[i].id + "'>"+ data[i].title + "</option>";
			}
		}
		$("#classify_id").html(h);
	} else {
		layer.msg("获取数据出错，请联系管理员!");
	}
}

