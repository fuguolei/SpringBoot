<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>设置密码</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.10.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/layer/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/base/common.js"></script>
<style>
*{ margin:0;padding:0;}
body{ font-size:16px; font-family:"微软雅黑"}
.content{ width:700px; margin:150px auto 0 auto;}
section{ display:block}
form select{ padding-left:10px; margin:5px;width:300px; white-space: nowrap;text-overflow:ellipsis; overflow:hidden;}
form input{ width:210px; height:35px;}
.center{ text-align:center}
.margin-top20{ margin-top:20px;}
table{border-collapse:collapse; text-align:center}
table tr:first-child td{height:20px; width:100px; overflow:hidden;  text-overflow:ellipsis;}
table tr td{  width:100px; height:40px;overflow:hidden;  text-overflow:ellipsis;}
.content>form>div{ width:400px;}
h4{ font-weight:normal;}
.btn{ background:#3a73b9;width:285px; height:45px; color:#fff;border:none;font-size:18px; cursor:pointer; margin-top:30px; display:block;
 margin-left: 110px;}
</style>

<script type="text/javascript">
    var re = /^[0-9A-Za-z]{6,10}$/;
    var   re1 =new RegExp(re);
 function active(){
	 var pwdole=$("#oldpwd").val();
	 var pwd1 = $("#pwd1").val();
	 var pwd2 = $("#pwd2").val();
	 if(!re1.test(pwdole)){
         sysAlert("密码为6-10位的数字或字母！");
	     pwd1 = "";
	     return ;
	 }
	 if (pwd1 == "" || pwd1 == null) {
         sysAlert('请输入密码');
	 	return;
	 }
	 if (pwd1 != pwd2) {
         sysAlert('两次密码不一致');
	 	return;
	 }

	 if(!re1.test(pwd1)) {
         sysAlert("密码为6-10位的数字或字母！");
	     pwd1 = "";
	     return ;
	 }
	 if(!re1.test(pwd2)) {
         sysAlert("密码为6-10位的数字或字母！");
	     pwd2 = "";
	     return ;
	 }

    ajax({
        url: "/admin/sys/password/resetPassword",
        data: {
            oldPassword: pwdole,
            newPassword: pwd1
        },
        successCallBack: function(result) {
            sysAlert(result.msg);
            resertPassword();
        }
    });
 }

 function resertPassword(){

	 $("#oldpwd").val("");
	 $("#pwd1").val("");
	 $("#pwd2").val("");
 }


</script>

</head>
<body>
    <div class="content">
    	<form >
                <h4 style="font-size:16px; font-weight:bold;width:300px;text-align:right;">修改当前账号密码</h4>
                <div class="margin-top20">
                     <p style="text-align:right;font-size:14px;"  class="margin-top20"><label><span ></span>输入当前密码：
                    </label><input type="password" placeholder="请填写" id="oldpwd"  ></p>
                    <p style="text-align:right;font-size:14px;"  class="margin-top20"><label><span ></span>输入新密码：
                    </label><input type="password" placeholder="请填写" id="pwd1"  ></p>
                    <p style="text-align:right;font-size:14px;"  class="margin-top20"><label><span ></span>再次输入新密码：
                    </label><input type="password" placeholder="请填写" id="pwd2" ></p>

            	</div>
                </p>
                <input type="button"   onclick="active()" value="确定" class="btn">

         </form>
    </div>

</body>
</html>
<script type="text/javascript">
$(function() {
    resertPassword();
});

</script>
