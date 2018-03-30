var pageii = null;
var grid = null;
$(function() {
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {//表格列表数据
			colkey : "account",
			name : "账号"
		},  {
			colkey : "nickName",
			name : "昵称"
		}, {
			colkey : "userName",
			name : "姓名"
		},{
			colkey : "sex",
			name : "性别",
			width : '90px',
			renderData : function(rowindex, data, rowdata, column) {
				if(data == "0"){
					return "男";
				} else if(data == "1"){
					return "女";
				} else{
					return "未知";
				}
			}
		}, {
			colkey : "tel",
			name : "手机号"
		}, {
			colkey : "email",
			name : "邮箱"
		}, {
			colkey : "status",
			name : "状态",
			width : '90px',
			renderData : function(rowindex, data, rowdata, column) {
				if(data == 0){
					return "正常"
				}else if(data == 1){
					return "拉黑"
				}else if(data == 2){
					return "待审核"
				}else if(data == 3){
					return "禁用"
				}

			}
		},  {
			colkey : "birth",
			name : "生日",
			renderData : function(rowindex, data, rowdata, column) {
				if(data != ''){
					return new Date(data).format("yyyy-MM-dd");
				} else {
					return "<font color='red'>暂无</font>";
				}
			}
		},{
            colkey : "createdate",
            name : "创建时间",
            renderData : function(rowindex, data, rowdata, column) {
                if(data != ''){
                    return new Date(data).format("yyyy-MM-dd");
                } else {
                    return "<font color='red'>暂无</font>";
                }
            }
        }
		],
		jsonUrl : rootPath + '/custprotal/findByPage.shtml',
		checkbox : true,
		serNumber : true
	});

    $("#search").click("click", function() {// 绑定查询按扭
        var searchParams = $("#searchForm").serializeJson();// 初始化传参数
        grid.setOptions({
            data : searchParams
        });
    });

    $("#reset").click("click", function() {
        resetT();
    });

    function resetT() {
        $("#account").val("");
        $("#search").trigger("click");
    }

    // 还原
	$("#recover").click("click", function() {
        recoverFun();
	});
	//拉黑
	$("#editFun").click("click", function() {
		editFun();
	});
	//禁用
	$("#delFun").click("click", function() {
		delFun();
	});
});





function editFun() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择拉黑项！！");
		return;
	}
	layer.confirm('是否拉黑？', function(index) {
		var url = rootPath + '/custprotal/shieldingUI.shtml';
		var s = CommnUtil.ajax(url, {
			ids : cbox.join(",")
		}, "json");
		if (s == "success") {
			layer.msg('拉黑成功');
			grid.loadData();
		} else {
			layer.msg('拉黑失败');
		}
	});
}


function delFun() {
    var cbox = grid.getSelectedCheckbox();
    if (cbox == "") {
        layer.msg("请选择禁用项！！");
        return;
    }
    layer.confirm('是否禁用？', function(index) {
        var url = rootPath + '/custprotal/disableUI.shtml';
        var s = CommnUtil.ajax(url, {
            ids : cbox.join(",")
        }, "json");
        if (s == "success") {
            layer.msg('禁用成功');
            grid.loadData();
        } else {
            layer.msg('禁用失败');
        }
    });
}


function recoverFun() {
    var cbox = grid.getSelectedCheckbox();
    if (cbox == "") {
        layer.msg("请选择还原项！！");
        return;
    }
    layer.confirm('是否还原？', function(index) {
        var url = rootPath + '/custprotal/restoreUI.shtml';
        var s = CommnUtil.ajax(url, {
            ids : cbox.join(",")
        }, "json");
        if (s == "success") {
            layer.msg('还原成功');
            grid.loadData();
        } else {
            layer.msg('还原失败');
        }
    });
}

	