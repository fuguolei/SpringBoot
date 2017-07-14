 <%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>申博设计</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
	function openTab(text,url,iconCls){
		if($("#tabs").tabs("exists",text)){
			$("#tabs").tabs("select",text);
		}else{
			var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='${pageContext.request.contextPath}"+url+"'></iframe>";
			$("#tabs").tabs("add",{
				title:text,
				iconCls:iconCls,
				closable:true,
				content:content
			});
		}
	}
</script>
</head>
<body class="easyui-layout">
<div region="north" style="height: 78px;background-color: #E0ECFF">
	<table style="padding: 5px" width="100%">
		<tr>
			<td width="50%">
				<img src="${pageContext.request.contextPath}/images/a1.png" height="60" width="212" />
			</td>
			<td valign="bottom" align="right" width="50%">
				<font size="3">&nbsp;&nbsp;<strong>欢迎：${currentUser.name}</strong><a href="${pageContext.request.contextPath}/admin/logout.html"  style="margin-left:20px; color:#000;">安全退出</a></font>
			</td>
		</tr>
	</table>
</div>


<div region="center">
	<div class="easyui-tabs" fit="true" border="false" id="tabs" >
		<div title="首页" data-options="iconCls:'icon-home'" >
			<div align="center" style="padding-top: 100px;" style="border:1px solid red;"><font color="red" size="6">欢迎使用申博设计后台管理系统</font></div>
		</div>
	</div>
</div>

<div region="west" style="width: 200px" title="导航菜单" split="true" style="background: green;">
	<div class="easyui-accordion" data-options="fit:true,border:false" >
		 <c:forEach var="level1" items="${menus}">
		    <div title="${level1.name}"  data-options="selected:true,iconCls:'${level1.iconCls}'" style="padding:10px;">
                <c:forEach var="level2" items="${level1.list}">
                    	<a href="javascript:openTab('${level2.name}','${level2.url}','icon-lxr')" class="easyui-linkbutton"
                    			data-options="plain:true,iconCls:'${level2.iconCls}'" style="width: 150px;">${level2.name}</a>
                </c:forEach>

		    </div>
		</c:forEach>

		
	</div>
</div>

<div region="south" style="height: 25px;padding: 5px;" align="center">
	Copyrights  申博设计    Powered by&nbsp;<a href="http://www.igalaxy.com.cn"  style="color:#3a73b9">iGalaxy</a>
</div>
</body>
</html>





