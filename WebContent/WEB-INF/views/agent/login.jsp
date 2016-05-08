<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link href="${ctx}/static/css/login.css" type="text/css" rel="stylesheet"/>
<link href="${ctx}/static/css/errormessage.css" type="text/css" rel="stylesheet"/>
<link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet"/>
<script src="${ctx}/static/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/messages_cn.js" type="text/javascript"></script>
<title>代理商登陆</title>

<script>
$(document).ready(function(){
    /** 表单验证    */
    $("#login").validate({
        rules: {
        	account: {
        		required:true
            },
		    pwd: {
				required:true
		    }
        }
    });
});
</script>
</head>
<body style="font-size:13px;">
<div class="top_left"> </div>
<div class="top_right"> </div>

<div class="centre">
<form:form id="login" action="${ctx}/agent/login" method="post">
    <div class="centre_one"></div>
    <div style="margin-left: 160px;margin-top: 30px;">
    	<c:if test="${not empty message}">
        <div style="color: red"  id="message" class="alert alert-error">${message}</div>
    </c:if>
    </div>
    <div class="centre_two">
    
    <span>用户
        <input type="text" class="input" name="account" id="account"/>
    </span>
    <span>密码
        <input type="password" class="input" name="pwd" id="pwd"/>
    </span>
</div>
<div>
    <input type="submit" class="centre_four" value=""/>
</div>
</form:form>
</div>
</body>
</html>