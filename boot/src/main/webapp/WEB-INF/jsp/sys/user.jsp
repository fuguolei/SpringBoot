<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="../common/head.jsp" %>
    <title>用户管理</title>
    <link rel="stylesheet" type="text/css" href="/css/sys/user.css">
</head>
<body>
<table id="dg" title="用户管理" style="width: auto;">
</table>
<div id="tb">
    <div class="pageMenu">
        <a href="#" onclick="openAddUserDialog(-1);" class="easyui-linkbutton" iconcls="icon-add" plain="true">添加用户</a>
        <a href="javascript:refreshPage()" class="easyui-linkbutton" iconCls="icon-reload" plain="true">页面刷新</a>
    </div>
    <div>
        <ul class="search">
            <li><label>账号：</label><input type="text" id="searchUserAccount" size="20"/></li>
            <li><label>姓名：</label><input type="text" id="searchUserName" size="20"/></li>
            <li class="searchButton"><a href="#" class="easyui-linkbutton" iconcls="icon-search" plain="true">搜索</a>
            </li>
        </ul>
    </div>
</div>

<script type="text/javascript">
    listView.init({
        url: "/admin/sys/user/list.json",
        columns: [[
            {field: 'account', title: '账号', width: 100},
            {field: 'name', title: '姓名', width: 100},
            {
                field: 'id', title: "操作", width: 100, formatter: function (value, row, index) {
                return getItemButton(index, "编辑", "openAddUserDialog")
                    + getItemButton(index, "删除", "deleteUser");
            }
            }
        ]]
    });
</script>


<!-- 添加用户 -->
<div id="dlg-buttons">
    <a href="javascript:addUser()" class="easyui-linkbutton" iconCls="icon-ok">确定</a>
    <a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
<div id="dlg" class="easyui-dialog" style="width:800px;height:450px;padding:0px 20px;" closed="true"
     buttons="#dlg-buttons">
    <form method="post" class="bootstrap-frm" id="addUserForm">
        <input type="hidden" id="txtUserId">
        <label id="labelUserAccount"> <span>账号:</span> <input type="text" id="txtUserAccount" class="easyui-validatebox"
                                                              required="true"></label>
        <label> <span>姓名:</span> <input type="text" id="txtUserName" class="easyui-validatebox" required="true"></label>
        <label> <span>密码:</span> <input type="text" id="txtUserPassword"
                                        required="true"></label>

    </form>
</div>
<!--添加用户 end -->

<script type="text/javascript">
    var re = /^[0-9A-Za-z]{6,10}$/;
    var re1 = new RegExp(re);
    function addUser() {
        var url
        var password = $("#txtUserPassword").val();
        if ($("#txtUserId").val()) {
            url = "/admin/sys/user/edit.json";
        }
        else {
            if (!password) {
                sysAlert("密码不能为空");
                return;
            }
            url = "/admin/sys/user/add.json";
        }
        if (password && !re1.test(password)) {
            sysAlert("密码为6-10位的数字或字母！");
            return;
        }
        $("#addUserForm").form("submit", {
            url: url,
            onSubmit: function (params) {
                var isValid = $(this).form('validate');
                if (isValid) {
                    params.id = $("#txtUserId").val();
                    params.name = $("#txtUserName").val();
                    if (password)
                        params.password = password;
                    params.account = $("#txtUserAccount").val();
                }
                return isValid;
            },
            success: function (text) {
                resultDataProcess("addUserForm", text);
            }
        });
    }

    //添加用户 start
    function openAddUserDialog(index) {
        $("#dlg").dialog("open").dialog("setTitle", index < 0 ? "添加用户" : "编辑用户");
        var row = null;
        if (index >= 0) {
            row = listView.getRow(index);
        }
        $("#txtUserId").val(row != null ? row.id : '');
        $("#txtUserAccount").val(row != null ? row.account : '');
        if (index < 0) {
            $("#labelUserAccount").show();
        }
        else {
            $("#labelUserAccount").hide();
        }
        $("#txtUserPassword").val('');
        $("#txtUserName").val(row != null ? row.name : '');
    }
    //添加用户End

    //删除角色
    function deleteUser(index) {
        var row = listView.getRow(index);
        $.messager.confirm('系统提示', '是否要删除 ' + row.name + " 用户?", function (r) {
            if (r) {
                ajax({
                    url: "/admin/sys/user/delete.json",
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

</body>
</html>
