<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="../common/head.jsp" %>
</head>
<body>
<table id="dg" title="角色列表" style="width: auto;">
</table>
<div id="tb">
    <div class="pageMenu">
        <shiro:hasPermission name="sys:role:create">
            <a href="#" onclick="openAddRoleDialog();" class="easyui-linkbutton" iconcls="icon-add"
               plain="true">添加角色</a>
        </shiro:hasPermission>
        <a href="javascript:refreshPage()" class="easyui-linkbutton" iconCls="icon-reload" plain="true">页面刷新</a>
    </div>
    <div>
        <ul class="search">
            <li><label>角色名称：</label><input type="text" id="txtRoleName" size="20"/></li>
            <li><label>角色描述：</label><input type="text" id="txtRoleDesc" size="20"/></li>
            <li class="searchButton"><a href="#" class="easyui-linkbutton" iconcls="icon-search" plain="true">搜索</a>
            </li>
        </ul>
    </div>
</div>

<!-- 添加角色 Start-->
<div id="dlg-buttons">
    <a href="javascript:addRole()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
<div id="dlg" class="easyui-dialog" style="width:500px;height:300px;padding:10px 20px;" closed="true"
     buttons="#dlg-buttons">

    <form method="post" class="bootstrap-frm" id="addRoleForm">
        <label> <span>角色名称:</span> <input type="text" id="txtAddName" class="easyui-validatebox"
                                          required="true"></label>
        <label> <span>角色描述:</span> <input type="text" id="txtAddDesc" class="easyui-validatebox"
                                          required="true"></label>
    </form>
</div>
<!-- 添加角色 End-->

<!-- 编辑角色 Start-->
<div id="editButtons">
    <a href="javascript:editRole()" class="easyui-linkbutton" iconCls="icon-ok">更新</a>
    <a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
<div id="editRole" class="easyui-dialog" style="width:500px;height:300px;padding:10px 20px;" closed="true"
     buttons="#editButtons">
    <form method="post" class="bootstrap-frm" id="editRoleForm">
        <input type="hidden" id="txtEditId"/>
        <label> <span>角色名称:</span> <input type="text" id="txtEditName" class="easyui-validatebox"
                                          required="true"></label>
        <label> <span>角色描述:</span> <input type="text" id="txtEditDesc" class="easyui-validatebox"
                                          required="true"></label>
    </form>
</div>
<!-- 编辑角色 End-->

<script type="text/javascript">
    listView.init({
        url: "/admin/sys/role/list.json",
        columns: [[
            {field: 'name', title: '角色名称', width: 100},
            {field: 'description', title: '角色描述', width: 100},
            {
                field: 'id', title: '操作', width: 100, formatter: function (value, row, index) {
                return getItemButton(index, "编辑", "openEditRoleDialog")
                    + getItemButton(index, "删除", "deleteRole")
                    + getItemButton(index, "人员管理", "managerUser")
                    + getItemButton(index, "菜单管理", "managerResource");
            }
            },
        ]]
    });
</script>

<script type="text/javascript">

    //添加角色
    function openAddRoleDialog() {
        $("#dlg").dialog("open").dialog("setTitle", "添加角色");
    }

    function addRole() {
        $("#addRoleForm").form("submit", {
            url: "/admin/sys/role/addRole.json",
            onSubmit: function (params) {
                var isValid = $(this).form('validate');
                if (isValid) {
                    params.name = $("#txtAddName").val();
                    params.description = $("#txtAddDesc").val();
                }
                return isValid;
            },
            success: function (text) {
                resultDataProcess("addRoleForm", text);
            }
        });
    }

    //更新角色
    function openEditRoleDialog(index) {
        $("#editRole").dialog("open").dialog("setTitle", "编辑角色");
        var row = listView.getRow(index);
        $("#txtEditId").val(row.id);
        $("#txtEditName").val(row.name);
        $("#txtEditDesc").val(row.description);
    }

    //删除角色
    function editRole() {
//        $.ajax({
//            url: "/admin/sys/role/editRole.json",
//            method:'POST',
//            data: {
//                name: $("#txtEditName").val(),
//                description: $("#txtEditDesc").val(),
//                id: $("#txtEditId").val()
//            },
//            success: resultDataProcess("addRoleForm", text)
//    })
        $("#editRoleForm").form("submit", {
            url: "/admin/sys/role/editRole.json",
            onSubmit: function (params) {
                var isValid = $(this).form('validate');
                if (isValid) {
                    params.name = $("#txtEditName").val();
                    params.description = $("#txtEditDesc").val();
                    params.id = $("#txtEditId").val();
                }
                return isValid;
            },
            success: function (text) {
                resultDataProcess("addRoleForm", text);
            }
        });
    }

    function deleteRole(index) {
        var row = listView.getRow(index);
        $.messager.confirm('系统提示', '是否要删除 ' + row.name + " 角色?", function (r) {
            if (r) {
                ajax({
                    url: "/admin/sys/role/deleteRole.json",
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

    //角色人员管理
    function managerUser(index) {
        var row = listView.getRow(index);
        window.location.href = "/admin/sys/role/user/index.html?roleId=" + row.id;
    }

    //角色菜单管理
    function managerResource(index) {
        var row = listView.getRow(index);
        window.location.href = "/admin/sys/role/res/index.html?roleId=" + row.id;
    }
</script>

<script type="text/javascript">

</script>
</body>
</html>
