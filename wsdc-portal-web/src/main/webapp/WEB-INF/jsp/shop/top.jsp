<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path_top = request.getContextPath();
	String basePath_top = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path_top + "/";
	request.setAttribute("thingtype", request.getSession().getAttribute("thingtype"));
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="<%=basePath_top %>js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="<%=basePath_top %>js/layer/layer.js" type="text/javascript"></script>
<script src="<%=basePath_top %>js/layui/layui.js"  type="text/javascript"></script>
<link rel="stylesheet" href="<%=basePath_top %>js/layui/css/layui.css" />
<link class="uiTheme" rel="stylesheet" type="text/css" href="<%=basePath_top %>js/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath_top %>js/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="<%=basePath_top %>js/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath_top %>js/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath_top %>js/jquery-easyui-1.3.3/jquery.cookie.js"></script>

	<link href="<%=basePath_top %>css/StyleSheet.css" rel="stylesheet" type="text/css" />

    <div class="big">
        <img src="<%=basePath_top %>images/top.jpg" style="width:100%" />
    </div>
    <div id="menu" class="big">
        <ul>
            <li><a href="<%=basePath_top %>index.do">首页</a></li>
            <c:forEach var="list"  items="${thingtype}">
            <li><a href="<%=basePath_top %>storeInfo/storeInfoList.do?stypeid=${list.stypeid}">${list.typename }</a></li>
            </c:forEach>
            <%--<li><a href="<%=basePath_top %>hotel/web_list.do">商家</a></li>--%>
            <li><a href="<%=basePath_top %>introduce/call.do?id=2">联系我们</a></li>
        </ul>
    </div>
    <div id="marq" class="big">
        <script language='javascript'>
linkarr = new Array();
picarr = new Array();
textarr = new Array();
var swf_width=1024;
var swf_height=275;
//文字颜色|文字位置|文字背景颜色|文字背景透明度|按键文字颜色|按键默认颜色|按键当前颜色|自动播放时间|图片过渡效果|是否显示按钮|打开方式
var configtg='0xffffff|30|0x025EE6|5|0xffffff|0xC5DDBC|0x000033|2|3|1|_blank';
var files = "";
var links = "";
var texts = "";
//这里设置调用标记
linkarr[1] = "";
picarr[1]  = "<%=basePath_top %>images/jump/1.jpg";
textarr[1] = "";
linkarr[2] = "";
picarr[2]  = "<%=basePath_top %>images/jump/2.jpg";
textarr[2] = "";
linkarr[3] = "";
picarr[3]  = "<%=basePath_top %>images/jump/3.jpg";
textarr[3] = "";
for(i=1;i<picarr.length;i++){
if(files=="") files = picarr[i];
else files += "|"+picarr[i];
}
for(i=1;i<linkarr.length;i++){
if(links=="") links = linkarr[i];
else links += "|"+linkarr[i];
}
for(i=1;i<textarr.length;i++){
if(texts=="") texts = textarr[i];
else texts += "|"+textarr[i];
}
document.write('<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="'+ swf_width +'" height="'+ swf_height +'">');
document.write('<param name="movie" value="images/banner.swf"><param name="quality" value="high">');
document.write('<param name="menu" value="false"><param name=wmode value="opaque">');
document.write('<param name="FlashVars" value="bcastr_file='+files+'&bcastr_link='+links+'&bcastr_title='+texts+'&bcastr_config='+configtg+'">');
document.write('<embed src="<%=basePath_top %>images/banner.swf" wmode="opaque" FlashVars="bcastr_file='+files+'&bcastr_link='+links+'&bcastr_title='+texts+'&bcastr_config='+configtg+'&menu="false" quality="high" width="'+ swf_width +'" height="'+ swf_height +'" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />'); document.write('</object>');
</script>
    </div>

