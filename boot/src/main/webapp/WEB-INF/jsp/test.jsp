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
    <script type="text/javascript">
        //监听本地图片提交
        //        $(function () {
        //            $("#test").change(function () {
        //                //校验图片  校验不通过，直接返回
        //                $("#Form").attr("action", "upload");
        //                $("#Form").attr("target", "fileFrame");
        //                $("#Form").submit();
        //            });
        //        });

        function uploadImg(preview) {
            var formData = new FormData();
            formData.append("file", $("#test")[0].files[0]);
            formData.append("name", $('name').val());
            formData.append("prefix", 2);
            $.ajax({
                url: '/sys/stgdata/upload',
                type: 'POST',
                data: formData,
                // 告诉jQuery不要去处理发送的数据
                processData: false,
                // 告诉jQuery不要去设置Content-Type请求头
                contentType: false,
                beforeSend: function () {
                    console.log("正在进行，请稍候");
                },
                success: function (result) {
                    if (result.success) {
                        imgUploadSuccess(result.data.url, preview, result.data.id);
                    } else {
                        alert(result.msg)
                    }
                },
                error: function (result) {
                    console.log("error");
                }
            });
        }
        //图片预览
        function imgUploadSuccess(url, preview, stgDataId) {
            $('#' + preview).attr('src', url);
            $('#' + preview).css('display', 'block');
            $("#id").val(stgDataId);
        }
    </script>
</head>
<body>
<div>
    <form action="submit" name="Form" id="Form" method="post" enctype="multipart/form-data">
        <br><input type="text" name="name" id="name" maxlength="32" placeholder="这里输入法定代表人" title="法定代表人"/></td>
        <br><input type="file" id="test" name="test"/>
        <br><input type="hidden" name="id" id="id"/>
        <br><img id="TEST_IMG" style="width:200px;height:100px;display: none;" alt="">
        <br>
    </form>
    <button id="button" onclick="uploadImg('TEST_IMG');">提交</button>
</div>
</body>
</html>