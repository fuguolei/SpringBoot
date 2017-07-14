<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="../common/head.jsp" %>
</head>
<body>
<table id="dg" title="订阅发送列表" style="width: auto;">
</table>
<div id="tb">
    <div class="pageMenu">
        <a href="javascript:refreshPage()" class="easyui-linkbutton" iconCls="icon-reload" plain="true">页面刷新</a>
    </div>
    <div>
        <ul class="search">
            <li><label>案例描述：</label><input type="text" id="txtCase" size="20"/></li>
            <li><label>邮箱描述：</label><input type="text" id="txtUser" size="20"/></li>
            <li class="searchButton"><a href="#" class="easyui-linkbutton" iconcls="icon-search" plain="true">搜索</a>
            </li>
        </ul>
    </div>
</div>

<script type="text/javascript">
    listView.init({
        url: "/admin/biz/bizSubscribeSend/list.json",
        columns: [[
            {field: 'caseDesc', title: '案例描述', width: 100},
            {field: 'userDesc', title: '邮箱描述', width: 100}
        ]]
    });
</script>

<script type="text/javascript">

</script>
</body>
</html>
