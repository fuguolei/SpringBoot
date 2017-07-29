/**
 * Created by fuguolei on 2017/6/7.
 */
function fileInputRegister(input, previewView) {
    input.change(function () {
        if(input[0].files[0])
            uploadImg(input, previewView)
    })
}
function uploadImg(input, preview) {
    var formData = new FormData();
    formData.append('file', input[0].files[0]);
    formData.append('prefix', 'CaseImage');
    preview.attr('alt', 'uploading…');
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
                sysAlert(result.msg)
            }
        },
        error: function (result) {
            sysAlert(result.msg)
        }
    });
}

//图片预览
function imgUploadSuccess(url, preview) {
    preview.attr('src', url).removeAttr('alt');
}

function checkUploadFinish() {
    var isFinish = true;
    $('.preview').each(function () {
        if ($(this).attr('alt') == 'uploading…') {
            sysAlert("图片正在上传中，请稍后提交")
            isFinish = false;
            return false;
        }
    });
    return isFinish;
}
