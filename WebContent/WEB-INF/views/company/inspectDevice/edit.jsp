<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>企业稽查终端添加页面</title>
<script>
    $(document).ready(function(){
    	$("#company-inspect-device").addClass("active");
    });
</script>
</head>
<body>
<div style="margin-left:200px;margin-top: 50px;">
<form:form id="editform" modelAttribute="inspectDevice" action="${ctx}/company/inspectDevice/update" method="post">
    
    <table border="0px" cellpadding="0" cellspacing="0">
        <tr>
            <td>设&nbsp;备&nbsp;编&nbsp;号
                <input id="deviceId" name="deviceId" value="${inspectDevice.deviceId}" type="text" class="text readonly" readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <td>责&nbsp;&nbsp;任&nbsp;&nbsp;人&nbsp;
                <input id="operator" name="operator" type="text" value="${inspectDevice.operator}" class="text" maxlength="10"/>
            </td>
        </tr>
        <tr>
            <td>设&nbsp;备&nbsp;状&nbsp;态
                <form:select path="status" class="select" items="${statusMap}"/>
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