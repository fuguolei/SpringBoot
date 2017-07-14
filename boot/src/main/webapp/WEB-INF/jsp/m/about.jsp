<%--
  Created by IntelliJ IDEA.
  User: fuguolei
  Date: 2017/6/23
  Time: 下午2:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zn">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content=""/>
    <title>关于我们</title>
    <script src="/js/prefixfree.min.js"></script>
    <link rel="stylesheet" href="/css/reset.css">
    <link rel="stylesheet" href="/css/mobile.css">
    <link rel="shortcut icon" href="/favicon.ico">
</head>
<body>
<div id="other-wrap">
    <section class="about_head headPadding">
        <img src="/img/m_about.png"/>
        <a href="#" class="menu-btn">
            <span class="menu_default"></span>
            <span class="menu_clse"></span>
        </a>
        <div class="menu-overlay-outer">
            <div class="menu-overlay"></div>
        </div>
        <aside id="menu" class="menu">
            <div class="menu_inner">
                <div class="j_matrixLeft" onclick="location.href='/index.html'">
                    <span>首页</span>
                    <span>HOME</span>
                </div>
                <div class="j_matrixLeft" onclick="location.href='/case/index.html'">
                    <span>作品</span>
                    <span>PROJECTS</span>
                </div>
                <div class="j_matrixLeft" onclick="location.href='/about.html'">
                    <span>我们</span>
                    <span>ABOUT</span>
                </div>
            </div>
        </aside>
    </section>
    <section class="container">
        <div class="row">
            <img src="/img/BG_about.jpg" class="img100" />
        </div>
        <div class="center_cont">
            <div class="row_about">
                <h2>PROFILE</h2>
                <div class="info_about PingFangSC_Regular">
                    申博(CENTER BRAND)-专注做互联网品牌设计的公司，为更多品牌创造独特的视觉价值。我们集结了拥有资深经验的优秀设计师，坚持和用户一起思考，把握品牌精准定位，用专业的力量，有效推动产品的最大价值化体现。
                </div>
            </div>
            <div class="row_about">
                <h2>TEAM</h2>
                <div class="info_about PingFangSC_Regular">
                    申博团队成员都是经历过创业公司和成熟互联网公司的一群强复合型的设计人员。涉足过广告公关行业、时尚杂志、设计工作室、艺术动画、游戏、美食、金融等领域，来自于腾讯、去哪儿网、央视、4A广告公司、时尚集团等公司。
                </div>
            </div>
        </div>
        <div class="row_about">
            <div class="center_cont"><h2>SERVICES</h2></div>
            <div class="services_about first_serviceAbout clear shiftCenter">
                <div class="left_about info_service">
                    <img src="/img/m_about_1.jpg">
                    <!-- <h3><span>—</span><br/>交互原型设计</h3> -->
                    <!-- <p>信息构架、高保真原型、界面布局、快速原型、产品原型</p> -->
                </div>
                <div class="right_about img_service">
                    <img src="/img/image_1.jpg" alt="">
                </div>
            </div>
            <div class="services_about second_serviceAbout clear shiftCenter">
                <div class="left_about info_service">
                    <img src="/img/m_about_2.jpg"/>
                    <!--<h3><span>—</span><br/>产品视觉设计</h3>-->
                    <!--<p>视觉设计<br/>设计规范<br/>品牌形象<br/>图标设计<br/>前端架构及开发</p>-->
                </div>
                <div class="right_about img_service">
                    <img src="/img/m_about_2-1.png" alt="">
                </div>
            </div>
            <div class="services_about third_serviceAbout clear shiftCenter">
                <div class="left_about img_service">
                    <img src="/img/image_3.png" alt="">
                </div>
                <div class="right_about info_service">
                    <img src="/img/m_about_31.jpg"/>
                    <!--<h3><span>—</span><br/>网站设计开发</h3>-->
                    <!--<p>品牌网站设计开发<br/>展示营销网站<br/>电子商务网站<br/>手机网站／响应式网站<br/>前端开发与网站测试</p>-->
                </div>
            </div>
            <div class="services_about fourth_serviceAbout clear shiftCenter">
                <div class="left_about info_service">
                    <img src="/img/m_about_4.jpg"/>
                    <!--<h3><span>—</span><br/>移动产品设计</h3>-->
                    <!--<p>移动界面设计<br/>微信公众平台<br/>html5设计开发<br/>移动网站设计开发<br/>iOS／Android界面设计</p>-->
                </div>
                <div class="right_about img_service">
                    <img src="/img/image_4.jpg" alt="">
                </div>
            </div>
            <div class="services_about fifth_serviceAbout clear">
                <div class="left_about img_service">
                    <img src="/img/image_5.png?1" alt="">
                </div>
                <div class="right_about info_service">
                    <img src="/img/m_about_5.jpg"/>
                    <!--<h3><span>—</span><br/>网站设计开发</h3>-->
                    <!--<p>品牌网站设计开发<br/>展示营销网站<br/>电子商务网站<br/>手机网站／响应式网站<br/>前端开发与网站测试</p></div>-->
                </div>
            </div>
        </div>
    </section>
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
<script type="text/javascript">
    $(function(){
        var height = $(window).height();
        $(window).on('scroll', function () {
            var top = $(this).scrollTop();
            $('.shiftCenter').each(function () {
                var This = $(this);
                if (This.attr('data') != 'ok') {
                    var etop = $(this).offset().top;
                    if (top + height - height/6 >= etop) {
                        This.attr('data', 'ok');
                        left(This.children('.left_about'))
                        right(This.children('.right_about'))
                    }
                }
            })
        })
        $(window).on('resize', function () {
            height = $(window).height();
        })
    })
    function left(This){
        var j=-100;
        var timer=setInterval(function(){
            j+=3;
            if(j>0){
                This.css({'transform':'translateX(0%)','opacity':1})
                clearInterval(timer);return;
            }
            This.css({'transform':'translateX('+j+'%)','opacity':1})
        },10)
    }
    function right(This){
        var j=100;
        var timer=setInterval(function(){
            j-=3;
            if(j<0){
                This.css({'transform':'translateX(0%)','opacity':1})
                clearInterval(timer);return;
            }
            This.css({'transform':'translateX('+j+'%)','opacity':1})
        },10)
    }
</script>
</body>
</html>