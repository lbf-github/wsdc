var Wsdc = {

    checkLogin : function(){

        var useHome="http://localhost:8081/wsdcp/";

        var _ticket = $.cookie("login_token");
        var _cart = $.cookie("cart_token");
        if(!_ticket){
            return ;
        }
        debugger;
        $.ajax({
            url : "http://localhost:8089/wsdc-sso/user/token1/" + _ticket,
            dataType : "jsonp",
            type : "GET",
            success : function(data){
                if(data.status=true){
                    if(data.data!=null){

                        var account = data.data.account;
                        var html = "<div>欢迎，"+account+"</div>";
                        html+="<div><a href=\""+useHome+"protalUser/editform.do?tokenId="+_ticket+"\">修改资料</a></div>";
                        html+="<div><a href=\""+useHome+"protalUser/toEditPwd.do?tokenId="+_ticket+"\">修改密码</a></div>";
                        html+="<div><a href=\""+useHome+"order/toOrder.do?tokenId="+_ticket+"\">我的订单</a></div>";
                        // html+="<div><a href=\"<%=basePath_left %>pinglun/my_list.do\">我的评论</a></div>";
                        html+="<div><a href=\""+useHome+"address/myAddresslist.do?tokenId="+_ticket+"\">我的地址</a></div>";
                        html+="<div><a href=\""+useHome+"cart/getCart.do?cartId="+_cart+"&tokenId="+_ticket+"\">我的购物车</a></div>";
                        html+="<div><a id=\"exit\" href=\"javascript:void(0)\">安全退出</a></div>"
                        $("#singleloginIndex").html(html);
                        var level = data.data.level;
                        var reStatus = data.data.status;
                        if(level == 2 && (reStatus==0 || reStatus==4)){
                            var myShop="";
                            myShop+="<a href=\""+useHome+"order/toStoreOrder.do?tokenId="+_ticket+"\">我的店铺</a>";
                            $("#singlelogin-t").html(myShop);
                        }
                        if(level == 1 && reStatus == 2){
                            var myShop="审核中";
                            $("#singlelogin-t").html(myShop);

                        }


                    }

                }
            }
        });
    }
}

$(function(){
    // 查看是否已经登录，如果已经登录查询登录信息

    Wsdc.checkLogin();
});