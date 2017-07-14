<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="../common/head.jsp" %>
</head>
<body>
<table id="dg" title="案例详情列表" style="width: auto;">
</table>
<div id="tb">
    <div class="pageMenu">
        <a href="#" onclick="openAddItemDialog(-1);" class="easyui-linkbutton" iconcls="icon-add"
           plain="true">添加案例详情</a>
        <a href="javascript:refreshPage()" class="easyui-linkbutton" iconCls="icon-reload" plain="true">页面刷新</a>
        <a href="#" onclick="goBack();" class="easyui-linkbutton" iconCls="icon-back" plain="true">返回</a>
    </div>
    <div>
        <ul class="search">
            <li><label>类型：</label>
                <select id="selType" class="easyui-combobox">
                    <option value="">请选择</option>
                    <option value="1">图片</option>
                    <option value="0">文字</option>
                </select>
            </li>
            <li><label>内容：</label><input type="text" id="txtValue" size="20"/></li>
            <li class="searchButton"><a href="#" class="easyui-linkbutton" iconcls="icon-search" plain="true">搜索</a>
            </li>
        </ul>
    </div>
</div>

<!-- 修改排序 Start-->
<div id="dlg-buttons1">
    <a href="javascript:setSort()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
<div id="dlg1" class="easyui-dialog" style="width:500px;height:300px;padding:10px 20px;" closed="true"
     buttons="#dlg-buttons1">

    <form method="post" class="bootstrap-frm" id="sortForm">
        <input type="hidden" id="txtSortId"/>
        <label> <span>排序:</span>
            <input type="text" id="txtSort" class="easyui-validatebox"
                   required="true"/>
        </label>
    </form>
</div>
<!-- 修改排序 End-->


<!-- 添加详情 Start-->
<div id="dlg-buttons">
    <a href="javascript:addItem()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
<div id="dlg" class="easyui-dialog" style="width:500px;height:300px;padding:10px 20px;" closed="true"
     buttons="#dlg-buttons">

    <form method="post" class="bootstrap-frm" id="addItemForm">
        <input type="hidden" id="txtAddId"/>
        <label> <span>类型:</span>
            <select id="selAddType" onchange="selectChange(this.value);">
                <option value="Image">图片</option>
                <option value="Text">文字</option>
            </select>
        </label>
        <label> <span>内容:</span>
            <textarea id="txtAddValue" rows="5" cols="20"></textarea>
        </label>
        <input type="file" id="fileAddValue"
               required="true"/>
        <img id="imgAddValue" class="preview"/>
    </form>
</div>
<!-- 添加详情 End-->

<script type="text/javascript">
    function selectChange(value) {
        if (value == 'Image') {
            $('#fileAddValue').show();
            $('#imgAddValue').show();
            $('#txtAddValue').hide();
        } else {
            $('#fileAddValue').hide();
            $('#imgAddValue').hide();
            $('#txtAddValue').show();
        }
    }
    listView.init({
        url: "/admin/biz/caseItem/list.json?bizCaseId=${bizCaseId}",
        columns: [[
            {
                field: 'type', title: '类型', width: 100, formatter: function (value, row, index) {
                return row.type.desc;
            }
            },
            {field: 'value', title: '内容', width: 100},
            {
                field: 'id', title: '操作', width: 100, formatter: function (value, row, index) {
                return getItemButton(index, "编辑", "openAddItemDialog")
                    + getItemButton(index, "排序", "openSortDialog")
                    + getItemButton(index, "删除", "deleteItem")
            }
            },
        ]]
    });
</script>

<script type="text/javascript">

    $(initFileUpload());
    function initFileUpload() {
        fileInputRegister($('#fileAddValue'), $('#imgAddValue'));
    }

    function openAddItemDialog(index) {
        $("#dlg").dialog("open").dialog("setTitle", index < 0 ? "添加详情" : "编辑详情");
        var row = null;
        if (index >= 0) {
            row = listView.getRow(index);
        }
        $("#txtAddId").val(row != null ? row.id : '');
        $("#fileAddValue").val('');
        $("#selAddType").val(row != null ? row.type.name : 'Image');
        selectChange($("#selAddType").val());
        if ($("#selAddType").val() == 'Image') {
            $("#imgAddValue").attr('src', row != null ? row.value : '');
            $("#txtAddValue").val('');
        } else {
            $("#imgAddValue").attr('src', '');
            $("#txtAddValue").val(row != null ? row.value : '');
        }
    }

    function addItem() {
        if (!checkUploadFinish())
            return;
        var url = "/admin/biz/caseItem/add.json";
        if ($("#txtAddId").val())
            url = "/admin/biz/caseItem/edit.json";
        $("#addItemForm").form("submit", {
            url: url,
            onSubmit: function (params) {
                var isValid = $(this).form('validate');
                if (isValid) {
                    params.bizCaseId = ${bizCaseId};
                    params.id = $("#txtAddId").val();
                    params.type = $("#selAddType").val();
                    if (params.type == 'Image')
                        params.value = $("#imgAddValue").attr('src');
                    else
                        params.value = $("#txtAddValue").val();
                }
                return isValid;
            },
            success: function (text) {
                resultDataProcess("addItemForm", text);
            }
        });
    }

    function deleteItem(index) {
        var row = listView.getRow(index);
        $.messager.confirm('系统提示', '是否要删除 ' + row.value + " ?", function (r) {
            if (r) {
                ajax({
                    url: "/admin/biz/caseItem/delete.json",
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

    function openSortDialog(index) {
        $("#dlg1").dialog("open").dialog("setTitle", "排序");
        var row = listView.getRow(index);
        $("#txtSortId").val(row.id);
        $("#txtSort").val(row.sort);
    }

    function setSort() {
        $("#sortForm").form("submit", {
            url: "/admin/biz/caseItem/sort.json",
            onSubmit: function (params) {
                var isValid = $(this).form('validate');
                if (isValid) {
                    params.id = $("#txtSortId").val();
                    params.sort = $("#txtSort").val();
                }
                return isValid;
            },
            success: function (text) {
                resultDataProcess("sortForm", text);
            }
        });
    }
    function goBack() {
        window.location.href = "/admin/biz/case/index.html";
    }
</script>

<script type="text/javascript">
    //注册键盘事件
    document.onkeydown = function(e) {
        //捕捉回车事件
        var ev = (typeof event!= 'undefined') ? window.event : e;
        if(ev.keyCode == 13 && document.activeElement.id == "txtSort") {
            return false;//禁用回车事件
        }
    }
</script>
</body>
</html>
