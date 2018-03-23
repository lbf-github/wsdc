var Wsdc = {

    checkLogin : function(){

        var _ticket = $.cookie("login_token");
        if(!_ticket){
            return ;
        }

        $.ajax({
            url : "http://localhost:8089/wsdc-sso/user/token1/" + _ticket,
            dataType : "jsonp",
            type : "GET",
            success : function(data){
                if(data.status=true){
                    var account = data.data.account;
                    var html = "欢迎，"+account;
                    $("#singlelogin").html(html);
                }
            }
        });
    }
}

$(function(){
    // 查看是否已经登录，如果已经登录查询登录信息

    Wsdc.checkLogin();
});