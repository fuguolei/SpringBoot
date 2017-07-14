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
    <title>案例</title>
    <script src="/js/prefixfree.min.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="shortcut icon" href="/favicon.ico">
</head>
<body>
<div id="other-wrap">
    <header class="home_head project_BG" style="background-image:url('/img/BG_head.jpg');height:700px;">
        <img src="/img/BG_head.jpg" style="display:none">
        <div class="head_public head_about allProjects head_list">
            <a class="logo">
                <span>OUR PROJECTS</span>
                <span>PROJECTS</span>
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
    <div class="nav">
        <nav id="navigate">
            <a href="#" class="active"><span>全部</span><i>ALL</i></a>
            <a href="#"><span>品牌设计</span><i>BRANDING</i></a>
            <a href="#"><span>UI／UX设计</span><i>UI／UX DESIGN</i></a>
        </nav>
    </div>
    <section class="list_container">
        <div class="home_product clear">

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
<script src="/js/public.js?1"></script>
<script src="/js/head.js"></script>
<script>
    (function () {
        var typeObj = ['', 'Branding', 'UiUx'], page = {
                'type': '${type}',
                'pageNo': 1,
                'pageSize': 10
            }, navTop = 0, is_trunPage = true,
            navigate = $('#navigate a'), content = $('.home_product');
        if (typeObj.indexOf(page['type']) != -1) {
            navigate.eq(typeObj.indexOf(page['type'])).addClass('active').siblings('a').removeClass('active');
        }
        render();
        $('#navigate a').on('click', function () {
            page['pageNo'] = 1;
            page['type'] = typeObj[$(this).index()];
            $(this).addClass('active').siblings('a').removeClass('active');
            render();
            return false;
        })
        $(function () {
            navTop = $('.nav').offset().top;
            var allProjects = $('.allProjects');
            var diff = allProjects.offset().top;
            var navigate = $('#navigate');
            allProjects.css({'position':'fixed','top':0});
            $(window).on('scroll', function () {
                var top = $(this).scrollTop();
                if (navTop - diff <= top) {
                    navigate.addClass('navActive');
                } else {
                    navigate.removeClass('navActive');
                }
                shiftUp(top);
                if (agreeShiftBottom()) {
                    is_trunPage = false;
                    page['pageNo'] = page['pageNo'] + 1;
                    render();
                }
            })
        })
        $(window).on('resize', function () {
            navTop = $('.nav').offset().top;
            diff = $('.allProjects').offset().top;
        })
        function render() {
            // loading().show();
            ajax({
                url: '/case/list.json',
                data: {'type': page['type'], 'rows': page['pageSize'], 'page': page['pageNo']},
                success: function (data) {
                    page['totalCount'] = data.total;
                    page['totalPage'] = pageSet(page)['totalPage']//{pageNo: 1, pageSize: 2, totalCount: 5, totalPage: 3}
                    var rows = data['rows'], arr = [];
                    if (rows) {
                        for (var i = 0; i < rows.length; i++) {
                            arr.push('<div class="product_item shiftUp">');
                            arr.push('<a href="/case/detail.html?id=' + rows[i]['id'] + '" style="background-image:url(' + rows[i]['listImg'] + ')">');
                            // arr.push('<img src="' + rows[i]['listImg'] + '"/>');
                            arr.push('<div class="product_font">');
                            arr.push('<h2>' + rows[i]['name'] + '</h2>');
                            arr.push('<div><span class="arrowIcon"></span></div>');
                            arr.push('<span>' + rows[i]['type']['desc'] + '</span>');
                            arr.push('</div>');
                            arr.push('</a>');
                            arr.push('</div>');
                        }
                    } else {
                        arr.push("<div>没有内容</div>");
                    }
                    if (page['pageNo'] > 1) {
                        content.append(arr.join(''));
                    } else {
                        content.html(arr.join(''));
                    }
                    is_trunPage = page['pageNo'] == page['totalPage'] ? false : true;
                    // loading().fadeOut();
                    shiftUp($(document).scrollTop())
                }
            });
        }

        function shiftUp(top) {
            var height = $(window).height();
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
        }

        function agreeShiftBottom() {
            return is_trunPage && $(document).scrollTop() > $(document).height() - $(window).height() * 2;
        }
    })()
</script>
</body>
</html>
