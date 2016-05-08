<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<link href="${ctx}/static/css/admin.css" type="text/css" rel="stylesheet"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>企业首页</title>
<script>
$().ready(function() {
    $("#company-welcome").addClass("active");
});
</script>
</head>

<body>
<div style="border:1px white solid;height: 500px;margin-top: 50px;">
    <div>
        <div class="home_page"> 
        <c:forEach var="menu" items="${companyuser.menus}">
            <c:if test="${companyuser.authority >= menu.authority }">
               	<a class="${menu.css}" href="${ctx}${menu.link}"></a>
           	</c:if>
        </c:forEach> 
        </div>
    </div>  
</div>
</body>
</html>