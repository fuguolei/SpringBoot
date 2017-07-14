<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="../common/head.jsp" %>
    <style type="text/css">
        .combo .combo-text {
            margin: 0 !important;
            padding: 0 !important;
        }
    </style>
</head>
<body>
<table id="dg" title="案例列表" style="width: auto;">
</table>
<div id="tb">
    <div class="pageMenu">
        <a href="#" onclick="openAddCaseDialog(-1);" class="easyui-linkbutton" iconcls="icon-add"
           plain="true">添加案例</a>
        <a href="javascript:sendSubscribe()" class="easyui-linkbutton" iconCls="icon-ok" plain="true">发送订阅</a>
        <a href="javascript:refreshPage()" class="easyui-linkbutton" iconCls="icon-reload" plain="true">页面刷新</a>
    </div>
    <div>
        <ul class="search">
            <li><label>案例名称：</label><input type="text" id="txtCaseName" size="20"/></li>
            <li><label>案例类型：</label> <select id="selCaseType" class="easyui-combobox">
                <option value="">请选择</option>
                <option value="1">品牌设计</option>
                <option value="0">UI/UX设计</option>
            </select></li>
            <li class="searchButton"><a href="#" class="easyui-linkbutton" iconcls="icon-search" plain="true">搜索</a>
            </li>
        </ul>
    </div>
</div>

<!-- 添加案例 Start-->
<div id="dlg-buttons">
    <a href="javascript:addCase()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
<div id="dlg" class="easyui-dialog" style="width:500px;height:300px;padding:10px 20px;" closed="true"
     buttons="#dlg-buttons">

    <form method="post" class="bootstrap-frm" id="addCaseForm">
        <input type="hidden" id="txtAddId">
        <label> <span>案例名称:</span> <input type="text" id="txtAddName" class="easyui-validatebox"
                                          required="true"></label>
        <label> <span>案例类型:</span>
            <select id="selAddType">
                <option value="Branding">品牌设计</option>
                <option value="UiUx">UI/UX设计</option>
            </select>
        </label>
        <label> <span>案例描述:</span>
            <textarea id="txtAddDesc" rows="5" cols="20"></textarea>
        </label>
        <label class="clear"> <span>创建时间:</span>
            <input id="txtAddTime" type="text"
                   class="easyui-datetimebox"
                   required="false">
        </label>
        <label> <span>列表图片:</span>
            <input type="file" id="fileAddListImage"
                   required="true">
            <img id="imgAddListImage" class="preview" alt="">
        </label>
        <label> <span>顶部图片:</span>
            <input type="file" id="fileAddTopImage"
                   required="true">
            <img id="imgAddTopImage" class="preview">
        </label>
    </form>
</div>
<!-- 添加案例 End-->

</div>
<!-- 编辑案例 End-->

<script type="text/javascript">
    listView.init({
        url: "/admin/biz/case/list.json",
        columns: [[
            {
                field: 'ck', checkbox: true, formatter: function (value, row, index) {
                return 'checked';
            }
            },
            {field: 'name', title: '案例名称', width: 100},
            {
                field: 'type', title: '案例类型', width: 100, formatter: function (value, row, index) {
                return row.type.desc;
            }
            },
            {field: 'listImg', title: '列表图片', width: 100},
            {field: 'topImg', title: '顶部图片', width: 100},
            {field: 'createTime', title: '创建时间', width: 100},
            {
                field: 'id', title: '操作', width: 100, formatter: function (value, row, index) {
                return getItemButton(index, "编辑", "openAddCaseDialog")
                    + getItemButton(index, "详情", "gotoDetail")
                    + getItemButton(index, "删除", "deleteCase")
            }
            },
        ]]
    });
</script>

<script type="text/javascript">

    $(initFileUpload());
    function initFileUpload() {
        fileInputRegister($('#fileAddListImage'), $('#imgAddListImage'));
        fileInputRegister($('#fileAddTopImage'), $('#imgAddTopImage'));
    }
    //添加案例
    function openAddCaseDialog(index) {
        $("#dlg").dialog("open").dialog("setTitle", index < 0 ? "添加案例" : "编辑案例");
        var row = null;
        if (index >= 0) {
            row = listView.getRow(index);
        }
        $("#txtAddId").val(row != null ? row.id : '');
        $("#txtAddName").val(row != null ? row.name : '');
        $("#selAddType").val(row != null ? row.type.name : 'Branding');
        $("#txtAddDesc").val(row != null ? row.description : '');
//        $("#txtAddTime").val(row != null ? row.createTime : '');
        $("#txtAddTime").datebox('setValue', row != null ? row.createTime : '')
        $("#fileAddListImage").val(null);
//        $("#fileAddListImage").attr('class', row == null ? 'easyui-validatebox' : '')
        $("#fileAddTopImage").val('');
//        $("#fileAddTopImage").attr('class', row == null ? 'easyui-validatebox' : '')
        $("#imgAddListImage").attr('src', row != null ? row.listImg : '')
        $("#imgAddTopImage").attr('src', row != null ? row.topImg : '')
    }

    function addCase() {
        if (!checkUploadFinish())
            return false;
        var url = "/admin/biz/case/add.json";
        if ($("#txtAddId").val())
            url = "/admin/biz/case/edit.json";
        $("#addCaseForm").form("submit", {
            url: url,
            onSubmit: function (params) {
                if (!$("#txtAddDesc").val()) {
                    sysAlert("描述不能为空");
                    return false;
                }
                if ((!$("#txtAddId").val()) && (!$("#imgAddListImage").attr('src'))) {
                    sysAlert("列表图片不能为空");
                    return false;
                }
                if ((!$("#txtAddId").val()) && (!$("#imgAddTopImage").attr('src'))) {
                    sysAlert("详情顶部图片不能为空");
                    return false;
                }
                var isValid = $(this).form('validate');
                if (isValid) {
                    params.id = $("#txtAddId").val();
                    params.name = $("#txtAddName").val();
                    params.type = $("#selAddType").val();
                    params.description = $("#txtAddDesc").val();
                    params.createTime = $("#txtAddTime").datetimebox("getValue");
                    params.listImg = $("#imgAddListImage").attr('src');
                    params.topImg = $("#imgAddTopImage").attr('src');
                }
                return isValid;
            },
            success: function (text) {
                resultDataProcess("addCaseForm", text);
            }
        });
    }

    //删除角色
    function deleteCase(index) {
        var row = listView.getRow(index);
        $.messager.confirm('系统提示', '是否要删除 ' + row.name + " 案例?", function (r) {
            if (r) {
                ajax({
                    url: "/admin/biz/case/delete.json",
                    data: {
                        id: row.id
                    },
                    successCallBack: function (data) {
                        resultDataProcess(null, data);
                    }
                });
            }
        });
    }

    //进入详情
    function gotoDetail(index) {
        var row = listView.getRow(index);
        window.location.href = "/admin/biz/caseItem/index.html?bizCaseId=" + row.id;
    }

    function sendSubscribe() {
        var checkedItems = $('#dg').datagrid('getChecked');
        var ids = [];

        $.each(checkedItems, function (index, item) {
            ids.push(item.id);
        });
        if (ids.length <= 0) {
            sysAlert('请先勾选要发送的案例');
            return;
        }
        $.messager.progress({
            title: '请稍后',
            msg: '订阅发送中...'
        })
        ajax({
            url: "/admin/biz/case/subscribeSend.json",
            data: {
                ids: ids
            },
            successCallBack: function (data) {
                resultDataProcess(null, data);
                $.messager.progress('close');
            },
            errorCallBack: function (data) {
                $.messager.progress('close');
                sysAlert(data.msg);
            }
        });
    }
</script>

<script type="text/javascript">

</script>
</body>
</html>
