<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${ctx}/static/jquery-validation/1.11.1/additional.js"></script>

<title>查看证书</title>
<script>

    $(document).ready(function(){
    	$("#company-certificate").addClass("active");
        $("#addform").validate({
            rules: {
                name:{
                    required:true,
                }
            } 
        });
    });
</script>
</head>
<body>
<div style="margin:20px 300px auto 300px;">
<form:form id="addform" modelAttribute="certificate" action="${ctx}/company/certificate/edit" method="post" enctype ="multipart/form-data" >
	<div class="head_describe">查看证书</div>
    <table border="0px" cellpadding="0" cellspacing="0" >
        <tr>
            <td style="padding:8px;">名&nbsp;&nbsp;称
                <input name="name" type="text" class="text" readonly="readonly" value="${certificate.name}"/>
                <span class="must">*</span>
            </td>
        </tr>
        <tr>
        	<td style="padding:8px;">检验检疫证 </td>
            <td style="padding:8px;">进口报关单 </td>
        </tr>
        <tr>
        	<td>
        		<img style="margin-top:25px;" id="img1" name="imgs" width="200px" src="${certificate.jyz}" />
        	</td>
        	<td>
        		<img style="margin-top:25px;" id="img2" name="imgs" width="200px" src="${certificate.bgd}" />
        	</td>
        </tr>
    </table>
</form:form>
</div>
</body>
</html>