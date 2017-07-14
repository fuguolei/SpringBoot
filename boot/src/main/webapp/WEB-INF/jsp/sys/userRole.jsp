<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../common/head.jsp"%>
<title>角色人员管理</title>
</head>
<body>
<table id="dg" title="角色人员管理" style="width: auto;">
  </table>
  <div id="tb">
   <div class="pageMenu">
    <a href="#" onclick="openAddUserRoleDialog();" class="easyui-linkbutton" iconcls="icon-add" plain="true">添加用户</a>
     <a href="#" onclick="goBack();" class="easyui-linkbutton"  plain="true">返回</a>
     <a href="javascript:refreshPage()" class="easyui-linkbutton" iconCls="icon-reload" plain="true">页面刷新</a>
   </div>
   <div>
    <ul class="search">
     <li><label>姓名：</label><input type="text" id="txtUserName" size="20" /> </li>
     <li><label>账号：</label><input type="text" id="txtUserAccount" size="20" /> </li>
     <li class="searchButton"><a href="#" class="easyui-linkbutton" iconcls="icon-search" plain="true">搜索</a></li>
    </ul>
   </div>
  </div>



 <script type="text/javascript">
     listView.init({
        url : "/admin/sys/role/user/list.json?roleId=${roleId}" ,
        columns: [[
                     {field:'userName',title:'姓名', width: 100},
                     {field:'roleName',title:'角色', width: 100},
                      {field:'account',title:'账号', width: 100},
                     {field:'id',title:'操作', width: 100, formatter: function(value,row,index) {
                        return getItemButton(index, "删除", "deleteUserRole");
                     }},
                  ]]
     });
 </script>


 <!-- 添加用户 Start-->
 <div id="dlg-buttons">
 <a href="javascript:addRoleUser()" class="easyui-linkbutton" iconCls="icon-ok" >保存</a>
 <a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel" >关闭</a>
 </div>
<div id="dlg" class="easyui-dialog" style="width:350px;height:150px;padding:10px 20px;" closed="true" buttons="#dlg-buttons" >

    <form method="post" id="addUserRoleForm">
             <label> <span style="width: 100px; text-align: right; display: inline-block; margin: 20px 20px 0 0;">用户:</span>
                 <input id="selAddUserId" class="easyui-combobox" name="selId" data-options="valueField:'userId',textField:'userName'">
             </label>
    </form>
</div>
 <!-- 添加角色 End-->


 <script type="text/javascript">

    //添加角色
    function openAddUserRoleDialog() {
        $("#dlg").dialog("open").dialog("setTitle","添加用户");
        $('#selAddUserId').combobox("clear")
        $('#selAddUserId').combobox('reload','/admin/sys/role/user/getNoUserInRoleList.html?roleId=${roleId}');
    }

    function addRoleUser() {
      $("#addUserRoleForm").form("submit", {
            url: "/admin/sys/role/user/addUserRole.html",
            onSubmit: function(params) {
             var isValid = $('#selAddUserId').combobox("getValue") != "";
              if (!isValid) {
                sysAlert("请选择用户");
              } else {
                params.userId =  $('#selAddUserId').combobox("getValue");
                params.roleId = ${roleId};
              }
              return isValid;
            },
            success: function(text){
              resultDataProcess(null, text);
            }
        });
    }

    //删除
    function deleteUserRole(index) {
      var row = listView.getRow(index);
      $.messager.confirm('系统提示', '是否要从该角色中删除 ' + row.userName + " 用户?", function(r){
        if (r){
          ajax({
            url: "/admin/sys/role/user/deleteUserRole.html",
            data: {
              id: row.id
            },
            successCallBack: function(data) {
              resultDataProcess(null,data);
            }
          });
        }
      });
    }

    //角色人员管理
    function goBack() {
      window.location.href = "/admin/sys/role/index.html";
    }
 </script>

 <script type="text/javascript">

 </script>
</body>
</html>
