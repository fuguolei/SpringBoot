<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="../common/head.jsp" %>
</head>
<body>
<table id="dg" title="订阅列表" style="width: auto;">
</table>
<div id="tb">
    <div class="pageMenu">
        <a href="#" onclick="openAddDialog(-1);" class="easyui-linkbutton" iconcls="icon-add"
           plain="true">添加Email</a>
        <a href="javascript:refreshPage()" class="easyui-linkbutton" iconCls="icon-reload" plain="true">页面刷新</a>
    </div>
    <div>
        <ul class="search">
            <li><label>邮箱：</label><input type="text" id="txtEmail" size="20"/></li>
            <li><label>订阅：</label>
                <select id="txtStatus" class="easyui-combobox">
                    <option value="">请选择</option>
                    <option value="1">订阅</option>
                    <option value="0">取消订阅</option>
                </select>
            </li>
            <li class="searchButton"><a href="#" class="easyui-linkbutton" iconcls="icon-search" plain="true">搜索</a>
            </li>
        </ul>
    </div>
</div>

<!-- 添加案例 Start-->
<div id="dlg-buttons">
    <a href="javascript:addEmail()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
<div id="dlg" class="easyui-dialog" style="width:500px;height:300px;padding:10px 20px;" closed="true"
     buttons="#dlg-buttons">

    <form method="post" class="bootstrap-frm" id="addEmailForm">
        <input type="hidden" id="txtAddId">
        <label> <span>Email:</span>
            <input type="text" id="txtAddName" class="easyui-validatebox"
                   required="true">
        </label>
    </form>
</div>
<!-- 添加案例 End-->

<script type="text/javascript">
    listView.init({
        url: "/admin/biz/bizSubscribeEmail/list.json",
        columns: [[
            {field: 'email', title: 'Email', width: 100},
            {
                field: 'status', title: '状态', width: 100, formatter: function (value, row, index) {
                return row.status.desc;
            }
            },
            {
                field: 'id', title: '操作', width: 100, formatter: function (value, row, index) {
                return getItemButton(index, "删除", "deleteEmail")
//                +getItemButton(index, "编辑", "openAddDialog")
            }
            },
        ]]
    });
</script>

<script type="text/javascript">

    //添加案例
    function openAddDialog(index) {
        $("#dlg").dialog("open").dialog("setTitle", index < 0 ? "添加Email" : "编辑Email");
        var row = null;
        if (index >= 0) {
            row = listView.getRow(index);
        }
        $("#txtAddId").val(row != null ? row.id : '');
        $("#txtAddName").val(row != null ? row.email : '');
    }

    function addEmail() {
        var url = "/admin/biz/bizSubscribeEmail/add.json";
        if ($("#txtAddId").val())
            url = "/admin/biz/bizSubscribeEmail/edit.json";
        $("#addEmailForm").form("submit", {
            url: url,
            onSubmit: function (params) {
                var isValid = $(this).form('validate');
                if (isValid) {
                    params.id = $("#txtAddId").val();
                    params.email = $("#txtAddName").val();
                }
                return isValid;
            },
            success: function (text) {
                resultDataProcess("addEmailForm", text);
            }
        });
    }

    function deleteEmail(index) {
        var row = listView.getRow(index);
        $.messager.confirm('系统提示', '是否要删除 ' + row.email + " ?", function (r) {
            if (r) {
                ajax({
                    url: "/admin/biz/bizSubscribeEmail/delete.json",
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
</script>

<script type="text/javascript">

</script>
</body>
</html>
