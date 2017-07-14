<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: fuguolei
  Date: 2017/6/6
  Time: 下午10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zn">
<head>
    <meta charset="UTF-8">
    <title>案例详情</title>
    <script src="/js/prefixfree.min.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="shortcut icon" href="/favicon.ico">
</head>
<body>
<div id="other-wrap">
    <header class="home_head" style="background-image:url(${detail.topImg});height:600px;">
        <img src="${detail.topImg}" style="display:none;">
        <div class="head_public head_about head_show">
            <a class="logo" href="/case/index.html">
                <span>PROJECTS/RELATE</span>
                <i class="arrowIcon absolute"></i>
            </a>
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
                                    <a target="_blank" href="http://www.facebook.com"
                                       class="icon-facebook menu_share"></a>
                                    <a target="_blank" href="http://www.twitter.com"
                                       class="icon-twitter menu_share"></a>
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
    <section class="show_container">
        <div class="show_title">
            <h2>${detail.name}</h2>
            <span>${detail.type.desc}</span>
        </div>
        <div id="show_content">
            <c:forEach items="${detail.list}" var="item">
                <c:choose>
                    <c:when test="${item.type.name == 'Image'}">
                        <div class="show_pic">
                            <img src="${item.value}"/>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="show_info">
                            <p>${item.value}</p>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <div class="projects clear">
            <div class="head_public allProjects">
                <a class="logo" href="/case/index.html">
                    <span>OUR PROJECTS</span>
                    <i class="arrowIcon absolute"></i>
                </a>
            </div>
            <div class="clear"></div>
            <ul class="show_list clear">
                <li>
                    <a href="/case/detail.html?id=${list[0].id}" style="background-image:url(${list[0].listImg})">
                        <img src="${list[0].listImg}" style="display:none;">
                        <div class="show_font">
                            <h2>${list[0].name}</h2>
                            <span class="arrowIcon"></span>
                            <span class="text">${list[0].type.desc}</span>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="/case/detail.html?id=${list[1].id}" style="background-image:url(${list[1].listImg})">
                        <img src="${list[1].listImg}" style="display:none;">
                        <div class="show_font">
                            <h2>${list[1].name}</h2>
                            <span class="arrowIcon"></span>
                            <span class="text">${list[1].type.desc}</span>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="/case/detail.html?id=${list[2].id}" style="background-image:url(${list[2].listImg})">
                        <img src="${list[2].listImg}" style="display:none;">
                        <div class="show_font">
                            <h2>${list[2].name}</h2>
                            <span class="arrowIcon"></span>
                            <span class="text">${list[2].type.desc}</span>
                        </div>
                    </a>
                </li>
            </ul>
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
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="/js/webfont.js"></script>
<script src="/js/public.js"></script>
<script src="/js/head.js"></script>
<script>
    var height=0;
    $(function () {
        height=$(window).height();
    })
    $(window).on('resize', function () {
    })
    $(window).on('scroll', function () {
        var top = $(this).scrollTop();
        height = $(window).height();
        $('.shiftUp').each(function () {
            if ($(this).css('opacity') == 0) {
                var etop = $(this).offset().top;
                if (top + height - 90 >= etop) {
                    var This = $(this), i = 90, o = 0;
                    var timer = setInterval(function () {
                        This.css({'visibility': 'inherit', 'opacity': o += 0.2});
                        This.css({'transform': 'translateY(' + (i /= 2) + '%) translate3D( 0,0,0)'});
                        if (Number(i.toFixed(1)) == 0) {
                            clearInterval(timer);
                            This.css({'transform': 'matrix(1, 0, 0, 1,0,0)'})
                        }
                    }, 20)
                }
            }
        })
    });
    function fontLoad(){
        WebFont.load({
            custom: {
                families: ['PingFangSC-Light'],
                urls: ["/css/font.css"]
            },
            active: function () {
                $('.show_info').addClass('PingFangSC-Light');
            },
            inactive: function () {
                $('.show_info').addClass('PingFangSC-Light');
            }
        });
    }
</script>
</body>
</html>