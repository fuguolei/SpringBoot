//以下为修改jQuery Validation插件兼容Bootstrap的方法，没有直接写在插件中是为了便于插件升级
$.validator.setDefaults({
    highlight: function (element) {
        $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
    },
    success: function (element) {
        element.closest('.form-group').removeClass('has-error').addClass('has-success');
    },
    errorElement: "span",
    errorPlacement: function (error, element) {
        if (element.is(":radio") || element.is(":checkbox")) {
            error.appendTo(element.parent().parent().parent());
        } else {
            error.appendTo(element.parent());
        }
    },
    errorClass: "help-block m-b-none",
    validClass: "help-block m-b-none"


});

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
            } else if (result.status != 200) {
                if (userConfig.errorCallback)
                    userConfig.errorCallback(result);
                else
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

function sysAlert(msg, title, type) {
    swal({
        title: title ? title : "提示",
        text: msg,
        type: type ? type : "warning"
    });
}

function dataDeleteAlert(fun) {
    swal({
            title: "您确定要删除这条信息吗",
            text: "删除后将无法恢复，请谨慎操作！",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "删除",
            closeOnConfirm: true,
            closeOnCancel: true
        },
        function (isConfirm) {
            fun(isConfirm)
        });
}

//table
$.fn.tableInit = function (userConfig) {
    if (!userConfig)
        userConfig = {};
    $(this).bootstrapTable({
        sidePagination: 'server',
        search: true,
        pagination: userConfig.pagination ? userConfig.pagination : true,
        showRefresh: true,
        showToggle: true,
        showColumns: true,
        uniqueId: userConfig.id ? userConfig.id : 'id',
        iconSize: 'outline',
        pageList: userConfig.pageList ? userConfig.pageList : '[5, 10, 25, 50, 100]',
        queryParamsType: 'pageSize',
        method: 'post',
        contentType: 'application/x-www-form-urlencoded',
        rowStyle: userConfig.rowStyle ? userConfig.rowStyle : rowStyle,
        responseHandler: userConfig.responseHandler ? userConfig.responseHandler : responseHandler,
        toolbar: userConfig.toolbar ? userConfig.toolbar : '#dataTableEventsToolbar',
        icons: {
            refresh: 'glyphicon-repeat',
            toggle: 'glyphicon-list-alt',
            columns: 'glyphicon-list'
        }
    })
}


function responseHandler(result) {
    if (result.status == 200) {
        var pageData = result.data.rows;
        for (var i = 0; i < pageData.length; i++) {
            pageData[i].operation = getOperation(pageData[i].id)
        }
        return {
            "total": result.data.total,
            "rows": pageData
        }
    } else {
        return {
            "total": 0,
            "rows": []
        }
    }
}


function rowStyle(row, index) {
    if (index % 2 === 0) {
        return {
            classes: 'active'
        };
    }
    return {};
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

function goBack() {
    window.history.go(-1);
}