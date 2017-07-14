<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录</title>

    <script type="text/javascript"
            src="/js/jquery-1.10.0.js"></script>
    <script type="text/javascript" src="/js/jquery.validate-1.13.1.js">
    </script>
    <script type="text/javascript" src="/js/layer/layer.js"></script>
    <style>
        * {
            margin: 0px;
            padding: 0
        }

        body {
            font-size: 16px;
            font-family: "微软雅黑"
        }

        .logo {
            width: 100%;
            border-bottom: 1px solid #d9dadc;
            overflow: hidden;
            min-width: 1200px;
            padding-bottom: 5px;
        }

        .logo-inside {
            width: 1200px;
            margin: 0 auto;
            color: #898989;
            padding: 20px 0
        }

        .content {
            width: 1200px;
            margin: 0 auto;
            overflow: hidden;
            min-width: 1200px;
            padding-top: 68px;
            padding-top: -moz-calc((100vh - 105px - 42px - 412px) / 2);
            padding-top: -webkit-calc((100vh - 105px - 42px - 412px) / 2);
            padding-top: calc((100vh - 105px - 42px - 412px) / 2);
        }

        .left {
            float: left;
        }

        .right {
            float: right
        }

        .border-bottom {
            border-bottom: 1px solid #d9dadc;
        }

        .username {
            background: url(/images/user.png) no-repeat 7px 8px;
            width: 16px;
            height: 18px;
        }

        .password {
            background: url(/images/password.png) no-repeat 7px 8px;
            width: 18px;
            height: 18px;
        }

        label.error {
            float: left;
            display: block;
            width: 300px;
            color: red;
            font-size: 14px;
            padding-top: 5px;
        }

        .btn {
            background: #3a73b9;
            width: 285px;
            height: 45px;
            color: #fff;
            border: none;
            font-size: 18px;
            cursor: pointer;
            margin-top: 30px;
        }

        .hint {
            font-size: 12px;
            color: #cacaca;
            display: block;
            text-decoration: underline;
            cursor: pointer;
            margin-top: 14px;
        }

        .footer {
            position: fixed;
            width: 100%;
            border: 1px solid #d9dadc;
            height: 40px;
            line-height: 40px;
            text-align: center;
            color: #898989;
            overflow: hidden;
            bottom: 0;
            min-width: 1200px;
            background: #fff;
        }

        .input-border {
            height: 35px;
            width: 280px;
            border: 1px solid #dbdbdb;
            padding-left: 30px;
            float: left
        }

        .code-border {
            border: 1px solid #dbdbdb;
            width: 115px;
            margin-right: 10px;
            height: 40px;
        }

        .underline {
            text-decoration: underline
        }

        .font-size13 {
            font-size: 13px;
            cursor: pointer;
            padding-left: 5px;
        }

        .p1 {
            margin-top: 30px;
            float: left;
            height: 40px;
        }

        .right-block {
            width: 430px;
            height: 410px;
            border: 1px solid #a2bdde;
        }

        .h4 {
            font-weight: normal;
            font-size: 27px;
        }
    </style>
</head>
<body>
<div>
    <div class="logo">
        <div class="logo-inside"><img src="/images/logo.png" style="vertical-align:middle; margin-right:20px;"
                                      height="60" width="60"><span>申博设计后台管理系统</span></div>
    </div>
    <div class="content">
        <div class="left"><img src="/images/login-img1.jpg" style="width: 608px; height: 412px;"></div>
        <div class="right right-block">
            <div style="margin:30px 0 0 70px;">
                <form id="loginForm" method="post" action="/admin/login">
                    <h4 class="h4">登录</h4>
                    <p class="p1">
                        <input type="text" placeholder="用户名" id="j_username"
                               name="j_username" class="username input-border"/>
                        </br>
                    </p>
                    <p style="clear:both"></p>

                    <p class="p1"><input type="password" placeholder="密码" class="password input-border" id="j_password"
                                         name="j_password"/></br>
                    </p>
                    <p style="clear:both"></p>

                    <p class="p1"><input class="code-border text"
                                         type="text" value="" name="imageCode"
                                         id="imageCode">
                        <img style="width:47%;display:inline;" id="kaptcha" src="/kaptcha/image" title="点击更换"
                             onclick="javascript:refreshCaptcha();"/>
                        </br>
                    </p>
                    <p style="clear:both"></p>
                    <p id="errorMessage" style="color:red;display: none;"></p>
                    <p style="clear:both"></p>


                </form>
                <p><input type="submit" onclick="login()" value="登  录" class="btn" name="submit"></p>

                <a class="hint">忘记密码，请联系管理员</a>
            </div>
        </div>

    </div>
    <div class="footer">Copyrights 申博设计 Powered by&nbsp;<a href="http://www.igalaxy.com.cn" style="color:#3a73b9">iGalaxy</a>
    </div>

</div>
</body>
</html>
<script>

    $(document).ready(function () {

        $(".hint").click(function () {
            layer.alert("<div style='text-align:center'>忘记密码请联系当前管理员</br>联系电话：xxxxxx</div>")

        })

    })
    function login() {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "/admin/login.json",
            data: {
                account: $("#j_username").val(),
                password: $("#j_password").val(),
                captcha: $("#imageCode").val()
            },
            success: function (result) {
                if (result.success) {
                    location.href = '/admin/index.html';
                } else {
                    $("#errorMessage").css("display", "inline");
                    $("#errorMessage").text(result.msg)
                }
            },
            error: function (result) {
                $("#errorMessage").css("display", "inline");
                $("#errorMessage").text('其他错误')
            }

        });
    }

    function refreshCaptcha() {
        $("#kaptcha").attr("src", "/kaptcha/image");
    }

</script>