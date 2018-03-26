$(function() {
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
})

function removeImgPic(event){
	$(event).parent().remove();
}

function uploadImg (obj,questionPic) {
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
        		$("#image").append('<div class="bb004" id="div'+s+a+'" ondrop="drop(event,this)" ondragover="allowDrop(event)" draggable="true" ondragstart="drag(event, this)"><img style="height:150px;width:150px;margin-left:15px" src="'+imgUrl+datajson.msg+'" onclick="imgShow($(this))">'+
        		'<div class="bb003" onclick="removeImgPic(this)"></div><input type="hidden" name="img_id" value="'+datajson.msg+'"/></div>');
        	}
        },
        error:function(data, status, e){ // 服务器响应失败时的处理函数
        	// / console.log(status);
        }
    });
}