<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>企业登录页面</title>
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
        	id: {
                required:true
            },
            password: {
                required:true
            },
            companyId: {
                required:true,
                digits:true
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
    <form id="loginForm" action="${ctx}/company/login" method="post">
        <div class="centre_one"></div>
        <div class="centre_two">
            <c:if test="${not empty message}">
                <div id="message" class="alert alert-error">${message}</div>
            </c:if>
            <span>用&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;
                <input type="text" class="input" name="id"/>
            </span>
            &nbsp;&nbsp;&nbsp;
            <span>密&nbsp;&nbsp;&nbsp;码&nbsp;&nbsp;
                <input type="password" class="input" name="password"/>
            </span>
            <div class="zd">组织代码&nbsp;
                <input type="text" class="input" name="companyId"/>
            </div>
        </div>
        <div>
            <input type="submit" class="centre_four" value=""/>
        </div>
    </form>
    </div>
</body>
</html>
