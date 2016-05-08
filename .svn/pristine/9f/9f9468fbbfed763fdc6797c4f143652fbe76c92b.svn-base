<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<title>标签卷添加页面</title>
<script>
$(document).ready(function() {
    $("#admin-label").addClass("active");

    /** 表单验证    */
    $("#addform").validate({
        rules: {
        	startTid: {
                required:true,
                digits:true,
                minlength:16,
                remote: "${ctx}/admin/labelIndex/startChk"
            },
            endTid: {
                required:true,
                digits:true,
                minlength:16,
                remote: "${ctx}/admin/labelIndex/endChk"
            },
            name:{
            	required:true
            }
        },
        messages:{
        	startTid: {
        		digits:"请输入有效追溯凭证码",
                minlength:"请输入有效追溯凭证码",
                remote:"起始标签有错误，请重新输入"
            },
            endTid: {
            	digits:"请输入有效追溯凭证码",
                minlength:"请输入有效追溯凭证码",
                remote:"结束标签有错误，请重新输入"
            }
        }
    });
});

</script>
</head>
<body>
<div style="margin-left:200px;margin-top: 50px;">
<form:form id="addform" modelAttribute="labelIndex" action="${ctx}/admin/labelIndex/add" method="post">
<input id="companyId" type="hidden" name="companyId" value="${labelIndex.companyId}"/>
    
<table border="0px" cellpadding="0" cellspacing="0">
    <c:if test="${not empty message}">
        <div id="message" class="alert alert-error">${message}</div>
    </c:if>
    
    <tr>
        <td>起&nbsp;始&nbsp;标&nbsp;签
            <input class="text" name="startTid" type="text" maxlength="22" value="${labelIndex.startTid}"/>
            <span class="must">*</span>
        </td>
    </tr>
    <tr>
        <td>结&nbsp;束&nbsp;标&nbsp;签
            <input class="text" name="endTid" type="text" maxlength="22" value="${labelIndex.endTid}"/>
            <span class="must">*</span>
        </td>
    </tr>

</table>
<div style="margin-top: 30px;">
    <table>
        <tr>
            <td><input id="addBtn" class="ok_button" type="submit" value=" "/></td>
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