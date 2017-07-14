<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="../common/head.jsp" %>
</head>
<body>

<div class="panel-header">
    <div class="panel-title">角色菜单管理</div>
    <div class="panel-tool"></div>
</div>
<div class="datagrid-toolbar">
    <div class="pageMenu">
        <a href="#" onclick="goBack();" class="easyui-linkbutton" plain="true">返回</a>
        <a href="javascript:refreshPage()" class="easyui-linkbutton" iconCls="icon-reload" plain="true">页面刷新</a>
    </div>
</div>

<div region="center" collapsible="false" style="width:100%;margin-top:5px;margin-left:5px;" border="false">
    <div style="float: left;margin-left: 10px;" id="rightC">
        <strong><span style="color:#ff0000;"><ul id="tree"></ul></span></strong>
    </div>
</div>
<script type="text/javascript">
    //var roleiId = ${roleId}
    var isLoad = false;
    $("#tree").tree({
        url: '/admin/sys/role/res/getAllRoleTree.json?roleId=${roleId}',
        method: 'POST',
        animate: true,
        checkbox: true,
        cascadeCheck: false,
        //层叠选中
        lines: true,
        //显示虚线效果
        onBeforeCheck: function (node, checked) {
            if (!isLoad) return true;
            var isSuccess = false;

            if (node.text == "导出Excel") {
                var nodePar = $("tree").tree("getParent", node.target);
                if (!nodePar.checked) {
                    sysAlert("请先添加菜单权限");
                    return isSuccess;
                }
                if (checked) {
                    ajax({
                        url: "/sys/role/res/addRoleResourceExcel.html",
                        async: false,
                        data: {
                            id: nodePar.attributes.id,
                            excel: node.attributes.excel
                        },
                        successCallBack: function (result) {
                            isSuccess = result.success;
                        }
                    });
                } else {
                    ajax({
                        url: "/sys/role/res/deleteRoleResourceExcel.html",
                        async: false,
                        data: {
                            id: nodePar.attributes.id,
                            excel: node.attributes.excel
                        },
                        successCallBack: function (result) {
                            isSuccess = result.success;
                        }
                    });
                }
                return isSuccess;
            }
            var type = 0;
            if(typeof(node.attributes.permission)=="undefined")
                type = 1;
            if (checked) {
                ajax({
                    url: "/admin/sys/role/res/addRoleResource.json",
                    async: false,
                    data: {
                        roleId: ${roleId},
                        type: type,
                        id: node.attributes.id
                    },
                    successCallBack: function (result) {
                        isSuccess = result.success;
                    }
                });
            } else {
                ajax({
                    url: "/admin/sys/role/res/deleteRoleResource.json",
                    async: false,
                    data: {
                        roleId: ${roleId},
                        type: type,
                        id: node.attributes.id
                    },
                    successCallBack: function (result) {
                        isSuccess = result.success;
                    }
                });
            }
            return isSuccess;
        }, onLoadSuccess: function () {
            isLoad = true;
        }
    });


    //角色人员管理
    function goBack() {
        window.location.href = "/admin/sys/role/index.html";
    }
</script>
</body>
</html>
