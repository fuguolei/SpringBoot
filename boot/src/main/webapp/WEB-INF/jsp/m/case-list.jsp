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
    <title>案例</title>
    <script src="/js/prefixfree.min.js"></script>
    <link rel="stylesheet" href="/css/reset.css">
    <link rel="stylesheet" href="/css/mobile.css">
    <link rel="shortcut icon" href="/favicon.ico">
</head>
<body>
<div id="other-wrap">
    <section class="case_head headPadding">
        <img src="/img/m_peojects.png"/>
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
    <nav class="nav clear" id="navigate">
        <a href="" class="active">全部</a>
        <a href="">品牌设计</a>
        <a href="">UI／UX 设计</a>
    </nav>
    <section class="container_case" id="content">

    </section>
</div>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="/js/mPublic.js"></script>
<script type="text/javascript">
    $(function () {
        var typeObj = ['', 'Branding', 'UiUx'], page = {
                'type': '${type}',
                'pageNo': 1,
                'pageSize': 10
            }, navTop = $('#navigate').offset().top, is_trunPage = true,
            navigate = $('#navigate a'), content = $('#content');
        if (typeObj.indexOf(page['type']) != -1) {
            navigate.eq(typeObj.indexOf(page['type'])).addClass('active').siblings('a').removeClass('active');
        }
        render();
        $('.nav a').on('click', function () {
            page['pageNo'] = 1;
            page['type'] = typeObj[$(this).index()];
            console.log(page)
            $(this).addClass('active').siblings('a').removeClass('active');
            render();
            return false;
        })
        $(window).on('scroll', function () {
            var top = $(this).scrollTop();
            if (agreeShiftBottom()) {
                is_trunPage = false;
                page['pageNo'] = page['pageNo'] + 1;
                render();
            }
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
                            arr.push('<div class="row_case row598" onclick="location.href=\'/case/detail.html?id=' + rows[i]['id'] + '&type=' + rows[i]['type']['name'] + '\'">');
                            arr.push('<div class="img"><img src="' + rows[i]['listImg'] + '"/></div>');
                            arr.push('<h2 class="h2">' + rows[i]['name'] + '</h2>');
                            arr.push('<p class="description">' + rows[i]['type']['desc'] + '</p>');
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
                }
            });
        }

        function agreeShiftBottom() {
            return is_trunPage && $(document).scrollTop() >= $(document).height() - $(window).height() * 1.5;
        }
    })
</script>
</body>
</html>
