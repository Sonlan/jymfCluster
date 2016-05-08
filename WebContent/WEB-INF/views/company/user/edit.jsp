<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${ctx}/static/jquery-validation/1.11.1/additional.js"></script>

<title>企业用户添加页面</title>
<script>
    $(document).ready(function(){
    	$("#company-user").addClass("active");
    });
    
</script>
</head>
<body>
<div style="margin-left:200px;margin-top: 50px;">
<form:form id="editform" modelAttribute="user" action="${ctx}/company/user/update" method="post">
    <table border="0px" cellpadding="0" cellspacing="0">
        <tr>
            <td>用户ID
                <input id="id" name="id" type="text"  class="text readonly" readonly="readonly" value="${user.id}"/>
            </td>
        </tr>
        <tr>
            <td>权&nbsp;&nbsp;限
                <form:select path="authority" name="authority" class="select" items="${authorityMap}"/>
            </td>
        </tr>
        <tr>
            <td>状&nbsp;&nbsp;态
                <form:select path="status" name="status" class="select" items="${statusMap}"/>
            </td>
        </tr>
    </table>
    
    <div style="margin-top: 30px;">
        <table>
            <tr>
                <td><input class="ok_button" type="submit" value=" "/></td>
                <td>
                    <input class="cancer_btn" type="button" value="" 
                           onclick="javascript:window.location.href='javascript:history.go(-1)'"/>
                </td>
            </tr>    
        </table>
    </div>
</form:form>
</div>
</body>
</html>