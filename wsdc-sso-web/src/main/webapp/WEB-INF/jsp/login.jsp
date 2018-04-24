<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page trimDirectiveWhitespaces="true" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <title>登录</title>
    <link type="text/css" rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/sweetalert/sweetalert.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style type="text/css">
        * {
            margin: 0px;
            padding: 0px;

        }

        body {
            font-size: 12px;
            font-family: "微软雅黑";
            color: #666;
            margin: 50px 0;
            text-align: center;
        }

        .top {
            margin: 0 auto;
            width: 281px;
            height: 151px;
        }
        .messages{
            width: 300px;
            height: 48px;
            margin: 10px;
        }
        .messages .messages-input{
            width: 300px;
            height: 48px;
            border-radius: 4px;
            color: #333;
            border: 1px solid #ddd;
        }
        #myTabContent{
            width: 300px;
            height: 400px;
            margin: 0 auto;
        }
        #myTab{
            width: 184px;
            height: 50px;
            margin: 0 auto;
        }
        .login-btn{
            border-radius: 4px;
            background: #4cd96f;
            color: #fff;
            cursor: pointer;
            text-align: center;
            font-size: 16px;
            line-height: 42px;
        }
        .messages-btn{
            color: #ccc;
            position: absolute;top: 12px;left: 225px;
            padding: 0;
            border-radius: 6px;
            color: #2395ff;
            background: transparent;
            text-align: center;
            font-size: 14px;
            outline: 0;
            border: none;
        }
        .inp {
            border: 1px solid gray;
            padding: 0 10px;
            width: 200px;
            height: 30px;
            font-size: 18px;
        }
        .btn {
            border: 1px solid gray;
            width: 100px;
            height: 30px;
            font-size: 18px;
            cursor: pointer;
        }
        #embed-captcha {
            width: 300px;
            margin: 0 auto;
        }
        .show {
            display: block;
        }
        .hide {
            display: none;
        }
        #notice {
            color: red;
        }
        /* 以下遮罩层为demo.用户可自行设计实现 */
        #mask {
            display: none;
            position: fixed;
            text-align: center;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            overflow: auto;
        }
        /* 可自行设计实现captcha的位置大小 */
        .popup-mobile {
            position: relative;
        }
        #popup-captcha-mobile {
            position: fixed;
            display: none;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            -webkit-transform: translate(-50%, -50%);
            z-index: 9999;
        }
    </style>

</head>

<body>
<div class="top">
    <img src="${ctx}/images/logo1.jpg" width="280" height="150" />
</div>
<div class=""></div>

<ul id="myTab" class="nav nav-tabs">
    <li class="active">
        <a href="#message" data-toggle="tab">
            短信登录
        </a>
    </li>
    <li>
        <a href="#password" data-toggle="tab">
            密码登录
        </a>
    </li>
</ul>
<div id="myTabContent" class="tab-content">
    <div class="tab-pane fade in active" id="message">
        <form id="formMessage">
            <div class="messages" style="position: relative;">
                <input id="phone" name="phone" type="tel" maxlength="11" placeholder="手机号" class="messages-input" />
                <button type="button" class="messages-btn" onclick="valdate1()">获取验证码</button>
            </div>
            <div class="messages">
                <input type="text" id="code" name="code" maxlength="8" placeholder="验证码" class="messages-input"/>
            </div>
            <div class="messages">

                温馨提示：未注册帐号的手机号，登录时将自动注册，且代表您已同意《用户服务协议》

            </div>
            <div id="hiddenVal"></div>
            <div class="messages">
                <input type="button" id="messageSubmit"  value="登录" class="messages-input login-btn"/>
            </div>
        </form>
    </div>
    <div class="tab-pane fade popup" id="password">
        <form id="formPassword">
            <div class="messages" style="position: relative;">
                <input  type="tel" maxlength="11" placeholder="手机号" name="tel" class="messages-input" />
            </div>
            <div class="messages">
                <input type="password" maxlength="8" placeholder="密码" name="password" class="messages-input"/>
            </div>

            <div class="messages">
                <input type="button" id="popup-submit"  value="登录" class="messages-input login-btn"/>
            </div>
            <div class="popup">
                <div id="popup-captcha"></div>
            </div>
        </form>
    </div>

</div>



<script src="${ctx}/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/js/gt.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-validation/jquery.form.js"></script>
<script src="${ctx}/js/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/sweetalert/sweetalert.min.js"></script>
<script src="${ctx}/js/jquery.easing.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jQuery.time.js" type="text/javascript"></script>

<script type="text/javascript">


    var handlerPopup = function (captchaObj) {
        // 成功的回调

        captchaObj.onSuccess(function () {
            var validate = captchaObj.getValidate();
            $.ajax({
                url: "pc-geetest/validate", // 进行二次验证
                type: "post",
                dataType: "json",
                data: {
                    geetest_challenge: validate.geetest_challenge,
                    geetest_validate: validate.geetest_validate,
                    geetest_seccode: validate.geetest_seccode
                },
                success: function (data) {
                    if (data && (data.status === "success")) {
                        success_submit();
                    } else {
                        $(document.body).html('<h1>登录失败</h1>');
                    }
                }
            });
        });
        $("#popup-submit").click(function () {
            captchaObj.show();
        });
        // 将验证码加到id为captcha的元素里
        captchaObj.appendTo("#popup-captcha");
        // 更多接口参考：http://www.geetest.com/install/sections/idx-client-sdk.html
    };
    // 验证开始需要向网站主后台获取id，challenge，success（是否启用failback）
    $.ajax({
        url: "pc-geetest/register?t=" + (new Date()).getTime(), // 加随机数防止缓存
        type: "get",
        dataType: "json",
        success: function (data) {
            // 使用initGeetest接口
            // 参数1：配置参数
            // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它做appendTo之类的事件
            initGeetest({
                gt: data.gt,
                challenge: data.challenge,
                product: "popup", // 产品形式，包括：float，embed，popup。注意只对PC版验证码有效
                offline: !data.success // 表示用户后台检测极验服务器是否宕机，一般不需要关注
                // 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
            }, handlerPopup);
        }
    });
    function success_submit(){
        $("#formPassword").ajaxSubmit({
            type:'post',
            url: 'doLogin',
            success: function(data){
                var redirectUrl = "${redirect}";
                if(data.success){
                    swal({
                            title: '提示',
                            text: data.message,
                            confirmButtonText: "确定"
                        },
                        function(){
                            if (redirectUrl == "") {
                                location.href = "http://localhost:8081/wsdcp";
                            } else {
                                location.href = redirectUrl;
                            }
                        });
                }else{
                    swal(data.message);
                }
            }
        });
    }


    function valdate1(){
        debugger;
        var phoneNum=$("#phone").val();
        $.ajax({
            url:"phone/Validation",
            data:{"phone":phoneNum},
            type:"post",
            dataType:"json",
            async:true,
            success:function(data){

                if(data!=null){
                    var str="<input type=\"hidden\" id=\"reCode\" name=\"reCode\" value=\""+data+"\" />";
                    $("#hiddenVal").html(str);
                    alert("获取验证码成功");
                }else{
                    alert("未知错误");
                }


            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                alert("服务器错误");
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
                alert(errorThrown);
            }
        })
    }


    $("#messageSubmit").click(function () {
        var phone = $("#phone").val();
        var code = $("#code").val();
        var recode = $("#reCode").val();

        $("#formPassword").ajaxSubmit({
            type:'post',
            url: 'doRegister',
            data:{
                "phone":phone,
                "code":code,
                "recode":recode
            },
            success: function(data){
                var redirectUrl = "${redirect}";
                if(data.success){
                    swal({
                            title: '提示',
                            text: data.message,
                            confirmButtonText: "确定"
                        },
                        function(){
                            if (redirectUrl == "") {
                                location.href = "http://localhost:8081/wsdcp";
                            } else {
                                location.href = redirectUrl;
                            }
                        });
                }else{
                    swal(data.message);
                }
            }
        });

    });

</script>

</body>

</html>
