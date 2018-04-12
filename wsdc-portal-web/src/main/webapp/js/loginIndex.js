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
                        html+="<div><a href=\"<%=basePath_left %>client/editform.do\">修改资料</a></div>";
                        html+="<div><a href=\"<%=basePath_left %>client/pwd.do\">修改密码</a></div>";
                        html+="<div><a href=\"<%=basePath_left %>buy/my_list.do\">我的订单</a></div>";
                        html+="<div><a href=\"<%=basePath_left %>pinglun/my_list.do\">我的点评</a></div>";
                        html+="<div><a href=\""+useHome+"cart/getCart.do?cartId="+_cart+"\">购物车</a></div>";
                        html+="<div><a id=\"exit\" href=\"<%=basePath_left %>client/exit.do\">安全退出</a></div>"
                        $("#singleloginIndex").html(html);
                        var level = data.data.level;
                        var reStatus = data.data.status;
                        if(level == 2 && (reStatus==0 || reStatus==4)){
                            var myShop="";
                            myShop+="<a href=\"http://localhost:8080/\">我的店铺</a>";
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