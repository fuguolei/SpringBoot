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
    <meta name="viewport" content=""/>
    <title>案例详情</title>
    <script src="/js/prefixfree.min.js"></script>
    <link rel="stylesheet" href="/css/reset.css">
    <link rel="stylesheet" href="/css/mobile.css">
    <link rel="shortcut icon" href="/favicon.ico">
</head>
<body>
<div id="other-wrap">
    <section class="show_head headPadding">
        <img src="" id="headimg"/>
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
            <img src="${detail.topImg}" class="img100"/>
        </div>
        <div class="row_show">
            <h2 class="h2">${detail.name}</h2>
            <p class="description">${detail.type.desc}</p>
        </div>
        <c:forEach items="${detail.list}" var="item"><c:choose><c:when test="${item.type.name == 'Image'}">
            <div class="show_img"><img src="${item.value}"/></div>
        </c:when><c:otherwise>
            <div class="show_info">${item.value}</div>
        </c:otherwise></c:choose></c:forEach>
        <div class="m_shiftPage">
            <a href="/case/detail.html?id=${detail.id}&go=prev&type=${type}">
                <img src="/img/m_prev.png">
            </a>
            <a href="/case/detail.html?id=${detail.id}&go=next&type=${type}">
                <img src="/img/m_next.png">
            </a>
        </div>
    </section>
</div>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="/js/mPublic.js"></script>
<script src="/js/webfont.js"></script>
<script type="text/javascript">
    $(function () {
        var type = '${detail.type}';
        $('#headimg').attr('src', '/img/m_' + type + '.png');
    })
    $('.nav a').on('click', function () {
        $(this).addClass('active').siblings('a').removeClass('active');
        return false;
    })
    function fontLoad() {
        WebFont.load({
            custom: {
                families: ['PingFangSC-Regular1'],
                urls: ["/css/font.css"]
            },
            active: function () {
                $('.show_info').addClass('PingFangSC-Regular1');
            },
            inactive: function () {

            }
        });
    }
</script>
</body>
</body>
</html>