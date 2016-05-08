<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${ctx}/static/css/company.css" type="text/css" rel="stylesheet"/>
<title>修改密码</title>
<script type="text/javascript">
$().ready(function() {
    $("#agent-editPassWord").addClass("active");



/** 表单验证    */
$("#pwdform").validate({
    rules: {
        oldPwd: {
            required:true,
            remote:"${ctx}/agent/checkOldPwd"
        },
        newPwd:{
        	required:true
        },
        plainPwd:{
        	equalTo:"#newPwd"
        }
    },
    messages:{
    	oldPwd: {
            remote:"密码错误"
        },
        plainPwd:{
        	equalTo:"您两次输入的新密码不一致，请确认"
        }
    }
});
});
</script>
</head>
<body>
<form id="pwdform" name="pwdform" action="${ctx}/agent/updatePwd" method="post" >
<div style="margin: 50px 0px 0px 40%;">
	<div style="color: red">温馨提示：密码修改成功后,将自动进入登陆页面！</div><br>
	<div>请输入当前密码&nbsp;&nbsp;<input type="password" class="text" name="oldPwd"></div><br>
	<div>请输入新的密码&nbsp;&nbsp;<input type="password" class="text" name="newPwd" id="newPwd"></div><br>
	<div style="color: red">区分大小写，只能使用字母、数字、特殊字符!</div><br>
	<div>请重复新的密码&nbsp;&nbsp;<input type="password" class="text" name="plainPwd"></div>
	<div><input style="margin:20px 0px 0px 15%;" type="submit" class="ok_button" value=" "/></div>
</div>
</form>
</body>
</html>