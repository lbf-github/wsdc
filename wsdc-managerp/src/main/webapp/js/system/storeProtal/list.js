var pageii = null;
var grid = null;
$(function() {
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {//表格列表数据
			colkey : "storeName",
			name : "商家名称"
		},  {
			colkey : "address",
			name : "商家地址"
		}, {
			colkey : "startprice",
			name : "起送价"
		}, {
			colkey : "transportprice",
			name : "配送费"
		}, {
			colkey : "transporttime",
			name : "配送时间"
		},{
			colkey : "cheapennotice",
			name : "优惠公告"
            },{
            colkey : "newusernotice",
            name : "新用户公告"
        },{
			colkey : "tel",
			name : "联系电话"
		},{
            colkey : "status",
            name : "状态",
            width : '90px',
            renderData : function(rowindex, data, rowdata, column) {
                if(data == 0){
                    return "正常"
                }else if(data == 2){
                    return "待审核"
                }else if(data == 1){
                    return "拉黑"
                }else if(data == 4){
                    return "正常(店面关门中)"
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
		jsonUrl : rootPath + '/storeprotal/findByPage.shtml',
		checkbox : true,
		serNumber : true
	});

    $("#search").click("click", function() {// 绑定查询按扭
        var searchParams = $("#searchForm").serializeJson();// 初始化传参数
        // var searchParams = $("#storeName").val();
        // alert(searchParams);
        grid.setOptions({
            data : searchParams
        });
    });

    $("#reset").click("click", function() {
        resetT();
    });

    function resetT() {
        $("#storeName").val("");
        $("#search").trigger("click");
    }

    // 审核通过
	$("#auditFun").click("click", function() {
        auditFun();
	});

});










function auditFun() {
    var cbox = grid.getSelectedCheckbox();
    if (cbox == "") {
        layer.msg("请选择审核项！！");
        return;
    }
    layer.confirm('是否审核通过？', function(index) {
        var url = rootPath + '/storeprotal/audit.shtml';
        var s = CommnUtil.ajax(url, {
            ids : cbox.join(",")
        }, "json");
        if (s == "success") {
            layer.msg('审核通过');
            grid.loadData();
        } else {
            layer.msg('审核失败');
        }
    });
}

	