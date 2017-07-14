<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>首页</title>
    <script src="/js/prefixfree.min.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="shortcut icon" href="/favicon.ico">
</head>
<body>
<div id="other-wrap">
    <header class="home_head absolute home_BG" style="background-image:url('/img/BG_IMG.jpg');">
        <img src="/img/BG_IMG.jpg" style="display:none" />
        <div class="overhide">
            <div class="head_info">
                <a class="logo" href="#">
                    <img src="/img/footer_logo.png" alt="">
                </a>
                <h1>专注品牌和互联网产品设计</h1>
                <p>Focus on the brand and internet product design</p>
            </div>
            <div class="slideBottom">
                <p>向下滑动</p>
            </div>
        </div>
        <a href="#" class="menu-btn">
            <span class="menu-btn_def"></span>
            <div class="menu-background"></div>
        </a>
        <div class="menu-overlay-outer">
            <div class="menu-overlay"></div>
        </div>
        <aside id="menu" class="fiexd menu">
            <div class="menu-scroll">
                <div class="mCustomScrollBox">
                    <div class="menu_inner">
                        <div class="menu_link-outer">
                            <a href="/index.html" class="menu_link j_matrixLeft">
                                <span class="zn">首页</span>
                                <span class="en">HOME</span>
                            </a>
                        </div>
                        <div class="menu_link-outer">
                            <a href="/case/index.html" class="menu_link j_matrixLeft">
                                <span class="zn">作品</span>
                                <span class="en">PROJECTS</span>
                            </a>
                        </div>
                        <div class="menu_link-outer">
                            <a href="/about.html" class="menu_link j_matrixLeft">
                                <span class="zn">我们</span>
                                <span class="en">ABOUT</span>
                            </a>
                        </div>
                        <div class="menu_row clear j_matrixLeft">
                            <div class="menu_row-left">
                                <a href="mailto:info@gogocbd.com" class="menu_emial">info@gogocbd.com</a>
                                <a href="tel:15210507863" class="menu_tel">152 1050 7863</a>
                                <div class="menu_row--share clear">
                                    <a target="_blank" href="http://www.instagram.com"
                                       class="icon-instagram menu_share"></a>
                                    <a target="_blank" href="http://www.facebook.com" class="icon-facebook menu_share"></a>
                                    <a target="_blank" href="http://www.twitter.com" class="icon-twitter menu_share"></a>
                                    <a target="_blank" href="http://www.weibo.com" class="icon-sina menu_share"></a>
                                </div>
                            </div>
                            <div class="menu_row-right">
                                <img src="/img/QRCode.png" alt="">
                            </div>
                        </div>
                        <div class="menu_subscribe j_matrixLeft">
                            <p>订阅最新资讯</p>
                            <div>
                                <input type="text" placeholder="EMAIL">
                                <a href="#" class="arrowIcon subMail"></a>
                            </div>
                        </div>
                        <div class="footer j_matrixLeft">
                            © CENTERBRAND.2017 蜀ICP备17012634号-1
                        </div>
                        <div class="footer font_fl j_matrixLeft" style="margin-top:0;padding-top:0;">
                            <span>Powered by <a href="http://www.igalaxy.com.cn/" target="_blank" style="text-decoration:underline;">iGalaxy</a></span>
                        </div>
                    </div>
                </div>
            </div>
        </aside>
    </header>
    <div id="box" class="fiexd">
        <section class="home_cont">
            <div class="wrap">
                <div class="home_row row_first">
                    <a href="/case/index.html?type=Branding"><img src="/img/index_pp.jpg" class="img"/></a>
                </div>
                <div class="home_row row_second">
                    <a href="/case/index.html?type=UiUx"><img src="/img/index_ui.jpg" class="img"/></a>
                </div>
                <div class="home_product clear">
                    <c:forEach items="${caseList}" var="item">
                        <div class="product_item shiftUp">
                            <a href="/case/detail.html?id=${item.id}" style="background-image:url(${item.listImg})">
                                <div class="product_font">
                                    <h2>${item.name}</h2>
                                    <span class="arrowIcon"></span>
                                    <span>${item.type.desc}</span>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                    <div class="head_public moreProjects">
                        <a class="logo" href="/case/index.html">
                            <span>MORE<label> PROJECTS</label></span>
                            <i class="arrowIcon absolute"></i>
                        </a>
                    </div>
                </div>
            </div>
        </section>
        <footer>
            <div class="wrap_foot clear">
                <div class="foot_left">
                    <div class="logo_fl">
                        <img src="/img/footer_logo.png" alt="">
                        <span><i>|</i>申博</span>
                    </div>
                    <p class="compay">互联网品牌设计公司</p>
                    <div class="font_fl">
                        <span>COPYRIGHT © CENTERBRAND 2017 </span>
                        <span>蜀ICP备17012634号-1</span>
                    </div>
                    <div class="font_fl" style="margin-top:10px;">
                        <span>Powered by <a href="http://www.igalaxy.com.cn/" target="_blank" style="text-decoration:underline;">iGalaxy</a></span>
                    </div>
                </div>
                <div class="foot_right">
                    <div class="menu_row clear">
                        <div class="menu_row-left">
                            <a href="mailto:info@gogocbd.com" class="menu_emial">info@gogocbd.com</a>
                            <a href="tel:15210507863" class="menu_tel">152 1050 7863</a>
                            <div class="menu_row--share clear">
                                <a target="_blank" href="http://www.instagram.com"
                                   class="icon-instagram menu_share"></a>
                                <a target="_blank" href="http://www.facebook.com" class="icon-facebook menu_share"></a>
                                <a target="_blank" href="http://www.twitter.com" class="icon-twitter menu_share"></a>
                                <a target="_blank" href="http://www.weibo.com" class="icon-sina menu_share"></a>
                            </div>
                        </div>
                        <div class="menu_row-right">
                            <img src="/img/QRCode.png" alt="">
                        </div>
                    </div>
                    <div class="menu_subscribe">
                        <p>订阅最新资讯</p>
                        <div>
                            <input type="text" placeholder="EMAIL">
                            <a href="#" class="arrowIcon subMail"></a>
                        </div>
                    </div>
                    <div class="font_fl">
                        <span>COPYRIGHT © CENTERBRAND 2017 </span>
                        <span>蜀ICP备17012634号-1</span>
                    </div>    
                    <div class="font_fl" style="margin-top:10px;">
                        <span>Powered by <a href="http://www.igalaxy.com.cn/" target="_blank" style="text-decoration:underline;">iGalaxy</a></span>
                    </div>
                </div>
            </div>
        </footer>
    </div>
</div>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="/js/webfont.js"></script>
<script src="/js/public.js"></script>
<script src="/js/head.js"></script>
<script>
    $(function () {
        change_homeRowFirst();
        $(document.body).css('margin-bottom', $('#box').height());//给浏览器加滚动条
        var sub='${unSubscribe}';
        if(sub){
            messageShow('${unSubscribe.msg}')
        }
    })
    $('.slideBottom p').on('click', function () {//点击向下滑动
        $('body,html').animate({scrollTop: $(window).height()}, 500);
    })
    $(window).on('scroll', function () {
        var top = $(this).scrollTop();
        var height = $(window).height();
        if (top > height) {
            $(document.body).addClass('bottom')
            $('#box').removeClass('fiexd').addClass('absolute').css('top', height)
        } else {
            $(document.body).removeClass('bottom')
            $('#box').removeClass('absolute').addClass('fiexd').css('top', 0)
        }
        shiftUp(top);
    })
    $(window).on('resize', function () {
        change_homeRowFirst();
    })
    function fontLoad(){
        WebFont.load({
            custom: {
                families: ['PingFangSC-Light'],
                urls: ["/css/font.css"]
            }
        });
    }
</script>
</body>
</html>
