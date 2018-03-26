 $(function() {
 	$("form").validate({
 		submitHandler : function(form) {//必须写在验证前面，否则无法ajax提交
 			ly.ajaxSubmit(form,{//验证新增是否成功
 				type : "post",
 				dataType:"json",
 				success : function(data) {
 					if (data=="success") {
 						layer.confirm('编辑成功!是否关闭窗口?', function(index) {
 							parent.grid.loadData();
				        	parent.layer.close(parent.pageii);
				        	return false;
 						});
 						$("#form")[0].reset();
 					} else {
 						layer.msg('编辑失败！');
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
 	
 	$("#removeImg").click(function(event){
 		 $("#adUpload").removeAttr("disabled");
		 $("#ad_img").children().remove();
		 $("#removeImg").attr('disabled', 'disabled');
	 });
 });
 
//获取省信息
function getProvince(id){
 	var url = rootPath + '/dataDic/findByResKey.shtml?resKey=PROVINCE';
 	var data = CommnUtil.ajax(url, null,"json");
 	if (data != null) {
 		var h = "<option value=''>------请选择------</option>";
 		for ( var i = 0; i < data.length; i++) {
 			if(parseInt(id,10)==parseInt(data[i].id,10)){
 				h+="<option value='" + data[i].id + "' selected='selected'>"
 								+ data[i].name + "</option>";
 			}else{
 				h+="<option value='" + data[i].id + "'>"+ data[i].name + "</option>";
 			}
 		}
 		$("#province_id").html(h);
 	} else {
 		bootbox.alert("获取菜单信息错误，请联系管理员！");
 	}
 }

 //获取市信息
 function getCity(province_id,city_id){
 	var url = rootPath + '/dataDic/findByPid.shtml?parentId='+province_id;
 	var data = CommnUtil.ajax(url, null,"json");
 	if (data != null) {
 		var h = "<option value=''>------请选择------</option>";
 		for ( var i = 0; i < data.length; i++) {
 			if(parseInt(city_id,10)==parseInt(data[i].id,10)){
 				h+="<option value='" + data[i].id + "' selected='selected'>"
 								+ data[i].name + "</option>";
 			}else{
 				h+="<option value='" + data[i].id + "'>"+ data[i].name + "</option>";
 			}
 		}
 		$("#city_id").html(h);
 	} else {
 		bootbox.alert("获取菜单信息错误，请联系管理员！");
 	}
 }

 /** -- 动态获取城市  -- **/  
 function getCities(){
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
 			$("#area").append('<select name="itemFormMap.city_id" class="form-control m-b city_id" tabindex="-1" required></select>');
 			$(".city_id").html(h);
 		 } else {
 			layer.msg("获取数据出错，请联系管理员！");
 		 }
 	}else{
 		return false;
 	}
 }
 
function uploadImg (obj) {
	if (!checkimgfiletype(obj)){
		return;
	}
	ajaxFileUpload("adPic");
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
        success:function(data){        // 服务器响应成功时的处理函数
        	var datajson = eval("(" + data.replace(/<\/?[^>]*>/g,'') + ")");
        	if (datajson.code == "1000" ) {
        		$("#ad_img").append('<img style="height:200px;width:200px;margin-left:180px" src="'+imgUrl+datajson.msg+'"><input type="hidden" name="adFormMap.img_id" value="'+datajson.msg+'"/>');
        		$("#adUpload").attr("disabled","disabled");
        		$("#removeImg").removeAttr("disabled");
        	}
        },
        error:function(data, status, e){ // 服务器响应失败时的处理函数
        	//  console.log(status);
        }
    });
}