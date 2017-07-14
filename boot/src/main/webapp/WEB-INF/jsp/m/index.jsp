<%--
  Created by IntelliJ IDEA.
  User: fuguolei
  Date: 2017/6/13
  Time: 下午5:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zn">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content=""/>
    <title>首页</title>
    <script src="/js/prefixfree.min.js"></script>
    <link rel="stylesheet" href="/css/reset.css?1">
    <link rel="stylesheet" href="/css/mobile.css">
    <link rel="shortcut icon" href="/favicon.ico">
</head>
<body>
<div id="other-wrap">
    <header class="home_head j_head clear">
        <div class="left" onclick="location.href='/case/index.html?type=Branding'">
            <img src="/img/mHomepp.jpg">
        </div>
        <div class="right" onclick="location.href='/case/index.html?type=UiUx'">
            <img src="/img/mHomeUI.jpg">
        </div>
        <div class="logo">
            <img src="/img/logo121.jpg">
            <span>互联网品牌设计公司</span>
        </div>
    </header>
    <footer>
        <div class="row foot_contact">
            <div class="row_left">
                <dl>
                    <dt>EMAIL:</dt>
                    <dd>info@gogocbd.com</dd>
                </dl>
                <dl>
                    <dt>PHONE:</dt>
                    <dd>152 1050 7863</dd>
                </dl>
            </div>
            <div class="row_right">
                <img src="/img/QRCode129.png">
            </div>
        </div>
        <div class="row foot_share">
            <a target="_blank" href="http://www.facebook.com"></a>
            <a target="_blank" href="http://www.instagram.com"></a>
            <a target="_blank" href="http://www.twitter.com"></a>
            <a target="_blank" href="http://www.weibo.com"></a>
        </div>
        <div class="foot_logo">
            <img src="/img/m_logo.png"/>
        </div>
        <div class="foot_record">
            <p>COPYRIGHT © CENTERBRAND 2017 </p>
            <p>蜀ICP备17012634号-1</p>
            <p>Powered by <a href="http://www.igalaxy.com.cn/" target="_blank" style="text-decoration:underline;">iGalaxy</a></p>
        </div>
    </footer>
</div>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="/js/mPublic.js"></script>
<!-- <script src="/js/webfont.js"></script> -->
<script type="text/javascript">
$(function(){
    var sub='${unSubscribe}';
    if(sub){
        addFullBg().show();
        alertPrompt(0,'${unSubscribe.msg}');
    }
})
    // WebFont.load({
    //     custom: {
    //         families: ['PingFangSC-Light'],
    //         urls: ["/css/font.css"]
    //     }
    // });
</script>
</body>
</html>
