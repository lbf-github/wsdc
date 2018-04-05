<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>网上点餐系统</title>
    <meta http-equiv="Content-Type" content="text/thml; charset=utf-8"/>
    <meta name="keywords" content="网上点餐,外面">
    <meta name="description" content="这是一个网上点餐系统">

    <style type="text/css">
        *{margin: 0px;padding: 0px;}
        body{background:url("${ctx}/images/bg.png");font-size: 12px;font-family: "微软雅黑";color: #666;}
        .top{width: 100%;height: 40px;margin: 30px 0;}
        .top .t-headr{width:1000px;height: 40px;margin: 0 auto;}
        .top .t-headr .t-logo{float:left;margin-top: 8px;}
        .top .t-headr .t-desc{float: right;line-height: 40px;font-size: 14px;color: #ffffff;}

        /*地图*/
        .content{width: 1000px;height: 500px; background: red;margin: 0 auto;position: relative;}
        .content .c-title{height: 60px;background: #ff9900;text-align: center;line-height: 60px;font-size: 22px;font-weight: 300;color: #ffffff;}
        .content .c-map{width: 1000px;height: 450px;background: #ffffff;}

        /*搜索框*/
        #search{width: 610px;height: 56px;box-shadow: 0px 2px 20px -6px #000;position: absolute;top: 106px;left: 190px;}
        #search .s-text{width: 490px;height: 56px;line-height: 56px;border: none;outline: none;font-size: 16px;font-family: "微软雅黑";padding-left: 10px;float: left;}
        #search .s-btn{width: 100px;height: 56px;border: none;font-size: 16px;font-family: "微软雅黑";outline: none;cursor: pointer;background: #f74342;color: #fff;float: right;}
        #search .s-btn:hover{background: #f51b1b;}

        #singlelogin{float: left;}
        #singlelogin-t{float: right;}
        a{text-decoration: none;color: #ffffff;}

    </style>
</head>
<body>
    <!--top start-->
    <div class="top">
        <div class="t-headr">
            <div class="t-logo">
                <a href="#">
                    <img src="${ctx}/images/logo2.png" alt="欢迎使用网上点餐系统" width="220" height="23">
                </a>
            </div>
            <div class="t-desc">
                <div id="singlelogin">
                    <a href="http://localhost:8089/wsdc-sso">登录</a>
                    <span>|</span>
                    <a href="http://localhost:8089/wsdc-sso">注册</a>
                </div>
                <span>　　</span>
                <div id="singlelogin-t"><a href="#">我要开店</a></div>

            </div>
        </div>
    </div>
    <!--top end-->
    <!--content start-->
    <div class="content">
        <div class="c-title">网上点餐系统给力呀！</div>

        <div class="c-map" id="container">

        </div>
        <div id="search">
            <input type="text" class="s-text" id="tipinput"/>
            <input type="button" class="s-btn" value="搜  索"/>
        </div>
    </div>
    <!--content end-->

    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.4&key=e6567bd4ab9eeecb1e7b931d2c77006e&plugin=AMap.Autocomplete,AMap.PlaceSearch"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/login.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.cookie.js"></script>
    <script type="text/javascript">
        //地图加载
        var map = new AMap.Map("container", {
            resizeEnable: true
        });
        //输入提示
        var autoOptions = {
            input: "tipinput"
        };
        var auto = new AMap.Autocomplete(autoOptions);
        var placeSearch = new AMap.PlaceSearch({
            map: map
        });  //构造地点查询类
        AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
        function select(e) {
            placeSearch.setCity(e.poi.adcode);
            placeSearch.search(e.poi.name);  //关键字查询查询
            location.href="http://localhost:8081/wsdcp/index";



        }




    </script>

</body>
</html>
