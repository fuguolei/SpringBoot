<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../common/head.jsp"%>
<title>操作日志</title>
</head>
<body>
<table id="dg" title="操作日志" style="width: auto;">
  </table>
  <div id="tb">
   <div class="pageMenu">
    <a href="javascript:refreshPage()" class="easyui-linkbutton" iconCls="icon-reload" plain="true">页面刷新</a>
   </div>
   <div>
    <ul class="search">
     <li><label>姓名：</label><input type="text" id="txtUserName" size="20" /> </li>
     <li><label>账号：</label><input type="text" id="txtAccount" size="20" /> </li>
     <li><label>日期：</label><input id="dateLogTime" class="easyui-datebox" data-options="sharedCalendar:'#cc'"></li>
     <li class="searchButton"><a href="#" class="easyui-linkbutton" iconcls="icon-search" plain="true">搜索</a></li>
    </ul>
   </div>
  </div>



 <script type="text/javascript">
     listView.init({
        url : "/admin/sys/log/list.json" ,
        columns: [[
        
                      {field:'logTime',title:'时间', width: 100, formatter: dateFormat},
                     {field:'name',title:'姓名', width: 100},
                      {field:'account',title:'账号', width: 100},

                      {field:'info',title:'操作', width: 100},
                  ]]
     });
 </script>

</body>
</html>
