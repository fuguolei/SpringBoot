/**
 *  靳龙
 */
var userType = {
    salesUser: 1,
    businessUser: 2
};

Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

function ajax(userConfig) {
    if (typeof contextUrl != "undefined") {
        userConfig.url = contextUrl + userConfig.url;
    }
    var config = {
        type: "POST",
        cache: false,
        dataType: "json",
        success: function (result) {
            if (result.success && userConfig.successCallBack) {
                userConfig.successCallBack(result);
            } else if (!result.success && userConfig.errorCallBack) {
                userConfig.errorCallBack(result);
            } else {
                sysAlert(result.msg);
            }
        },
        error: function (msg) {
            alert("系统错误,请与管理员联系");
        }
    }
    $.extend(config, userConfig);
    $.ajax(config);
}

function sysAlert(msg) {
    $.messager.alert('提示', msg);
}


function closeDialog() {
    $(".easyui-dialog").dialog("close");
}

function clearFormValue(formId) {
    $("#" + formId + " input[type=text]").val("");
    $("#" + formId + " input[type=hidden]").val("");
}

function resultDataProcess(formId, data) {
    var obj = null;
    if (typeof data == "string") {
        data = data.replace('<audio controls="controls" style="display: none;"></audio>', "");
        obj = jQuery.parseJSON(data);
    } else {
        obj = data;
    }

    if (obj.success) {
        if (formId != null) {
            clearFormValue(formId);
        }

        closeDialog();
        listView.api().datagrid("reload");
    }
    sysAlert(obj.msg);
}

function dateFormat(value, row, idnex) {
    return new Date(value).Format("yy-MM-dd hh:mm:ss");
}

//名字取反了
function dateTimeFormat(value, row, idnex) {
    return new Date(value).Format("yy-MM-dd");
}


function enumFormat(value, row, idnex) {
    return value.desc;
}

function getOrdDiscountTypeEnum(value) {
    switch (value) {
        case 1:
        case "1":
            return "NoDiscount";
            break;
        case 2:
        case "2":
            return "ReliefOffers";
            break;
        case 3:
        case 3:
            return "Discount";
            break;
    }

}

function refreshPage() {
    location.reload();
}

function getSearchParas() {
    var params = {};
    $(".search").find("input[type=text]").each(function () {
        var key = $(this).attr("id");
        var value = $(this).val();
        params[key] = value;
    });
    $(".search").find("input[type=hidden]").each(function () {
        var key = $(this).attr("id");
        var value = $(this).val();
        if (!key) {
            key = $(this).attr("name");
        }
        params[key] = value;
    });

    $(".search").find(".easyui-datebox").each(function () {
        var key = $(this).attr("id");
        var value = $(this).datetimebox("getValue")
        params[key] = value;
    });

    $(".search").find(".easyui-combobox").each(function () {
        var key = $(this).attr("id");
        var value = $(this).combobox("getValue")
        params[key] = value;
    });
    return JSON.stringify(params);
}

$(function () {
    if ($("#btnExportExcel").length > 0) {
        var html = new Array();
        var pathname = location.pathname;
        var path = path = pathname.substr(0, pathname.lastIndexOf('/') + 1);
        html.push('<form method="post" action="');
        html.push(path);
        html.push('excel.html" target="_blank" id="exportExcel">');
        html.push('<input type="hidden" id="search" name="search" />');
        html.push("</form>");
        $(document.body).append(html.join(""));
        $("#btnExportExcel").click(function () {
            $("#search").val(getSearchParas());
            $("#exportExcel").submit();
        });
    }

});

$.fn.formSubmit = function (userConfig) {
    if (!$(this).form('validate'))
        return;

    var config = {
        type: "POST",
        cache: false,
        dataType: "json",
        success: function (result) {
            if (result.success && userConfig.success) {
                userConfig.success(result);
            } else if (!result.success && userConfig.error) {
                userConfig.error(result);
            } else {
                sysAlert(result.msg);
            }
        },
        error: function (msg) {
            alert("系统错误,请与管理员联系");
        }
    }
    if (userConfig.data)
        $.extend(userConfig.data, $(this).serializeObject())
    else
        userConfig.data = $(this).serializeObject()
    $.extend(config, userConfig);
    $.ajax(config);
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