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
        /** 表单验证    */
        $("#addform").validate({
            rules: {
                id: {
                    required:true,
                    idFormat:true,
                    byteMaxLength:20,
                    remote:"${ctx}/company/user/checkId"
                }
            } ,
            messages:{
                id: {
                	remote:"用户ID重复"
                }
            }
        });
    });
    
</script>
</head>
<body>
<div style="margin-left:30%;margin-top: 10%;">
<form:form id="addform" modelAttribute="user" action="${ctx}/company/user/add" method="post">
    <table border="0px" cellpadding="0" cellspacing="0">
        <tr>
        	<td style="padding:8px">用 户 ID</td>
            <td>
                <input id="id" name="id" type="text"  class="text" maxlength="20"/>
                <span class="must">*</span>
            </td>
        </tr>
        <tr>
        	<td style="padding:8px">权&nbsp;&nbsp;限</td>
            <td>
                <form:select path="authority" class="select" items="${authorityMap}"/>
            </td>
        </tr>
    </table>
    
    <div style="margin-top: 30px;">
        <table>
            <tr>
                <td><input type="submit" class="ok_button"  value=" "/></td>
                <td>
                    <input class="cancer_btn" type="button" value="" 
                           onclick="javascript:window.location.href='javascript:history.go(-1)'"/>
                </td>
            </tr>    
        </table>
    </div>
    <input type="hidden" name="token" value="${token}" />
</form:form>
</div>
</body>
</html>