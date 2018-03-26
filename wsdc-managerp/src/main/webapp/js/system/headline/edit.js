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
  			$("#area").append('<select name="headlineFormMap.city_id" class="form-control m-b city_id" tabindex="-1" required></select>');
  			$(".city_id").html(h);
  		 } else {
  			layer.msg("获取数据出错，请联系管理员！");
  		 }
  	}else{
  		return false;
  	}
}
	 
