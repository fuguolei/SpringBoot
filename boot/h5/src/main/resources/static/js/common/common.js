function ajax(userConfig) {
    if (typeof contextUrl != "undefined") {
        userConfig.url = contextUrl + userConfig.url;
    }
    var config = {
        type: "POST",
        cache: false,
        dataType: "json",
        success: function (result) {
            console.log(result)
            if (result.status == 200 && userConfig.successCallback) {
                userConfig.successCallback(result);
            } else if (result.status != 200 && userConfig.errorCallback) {
                userConfig.errorCallback(result);
            } else {
                sysAlert(result.msg, "操作失败", "error");
            }
        },
        error: function (msg) {
            sysAlert("系统错误,请与管理员联系", "操作失败", "error");
        }
    }
    $.extend(config, userConfig);
    $.ajax(config);
}

$.fn.formSubmit = function (userConfig) {
    var form = $(this);
    if (!form.valid())
        return;
    if (userConfig.data)
        $.extend(userConfig.data, form.serializeObject())
    else
        userConfig.data = form.serializeObject()
    ajax({
        url: userConfig.url,
        data: userConfig.data,
        successCallback: function () {
            if (userConfig.success)
                userConfig.success();
            form[0].reset();
        },
        errorCallback: userConfig.error
    });
}

$.fn.serializeObject = function () {
    var obj = new Object();
    $.each(this.serializeArray(), function (index, param) {
        if (!(param.name in obj)) {
            obj[param.name] = param.value;
        }
    });
    return obj;
};