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
		 
		 $("#removeImg").click(function(event){
			 $("#activityUpload").removeAttr("disabled");
			 $("#activity_img").children().remove();
			 $("#removeImg").attr('disabled', 'disabled');
		});
	 });
	 
	 function uploadlogo (obj) {
		if (!checkimgfiletype(obj)){
			return;
		}
		ajaxFileUpload("activityPic");
	}

	function checkimgfiletype(obj) {
		var extStart = obj.value.lastIndexOf(".");
	    var ext = obj.value.substring(extStart, obj.value.length).toUpperCase();
	    if (ext != ".PNG" && ext != ".GIF" && ext != ".JPG") {
	        alert("图片限于JPG, PNG, GIF格式");
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
	        	if (datajson.code == "1000" ) {
	        		$("#activity_img").append('<img style="height:100px;width:100px;margin-left:100px" src="'+imgUrl+datajson.msg+'"><input type="hidden" name="activityFormMap.img_id" value="'+datajson.msg+'"/>');
	        		$("#activityUpload").attr("disabled","disabled");
	        		$("#removeImg").removeAttr("disabled");
	        	}
	        },
	        error:function(data, status, e){ // 服务器响应失败时的处理函数
	        	//  console.log(status);
	        }
	    });
	}
