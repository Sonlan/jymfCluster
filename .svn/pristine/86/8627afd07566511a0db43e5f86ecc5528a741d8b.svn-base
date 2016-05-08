<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<title>登录页面</title>
<link href="${ctx}/static/css/login.css" type="text/css" rel="stylesheet"/>
<link href="${ctx}/static/css/errormessage.css" type="text/css" rel="stylesheet"/>
<link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet"/>
<script src="${ctx}/static/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/messages_cn.js" type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function(){
    /** 表单验证    */
    $("#loginForm").validate({
        rules: {
        	account: {
                required:true
            },
            password: {
            	required:true
            }
        }
    });
});
</script>
</head>

<body>
<div class="top_left"> </div>
<div class="top_right"> </div>

<div class="centre">
<form:form id="loginForm" modelAttribute="admin" action="${ctx}/admin/login" method="post">
    <div class="centre_one"></div>
    <div class="centre_two">
    <c:if test="${not empty message}">
        <div id="message" class="alert alert-error">${message}</div>
    </c:if>
    <span>用户
        <input type="text" class="input" name="account" value="admin"/>
    </span>
    <span>密码
        <input type="password" class="input" name="password"/>
    </span>
</div>
<div>
    <input type="submit" class="centre_four" value=""/>
</div>
</form:form>
</div>
</body>
</html>
