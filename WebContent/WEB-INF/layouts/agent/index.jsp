<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>金源茂丰<sitemesh:title/></title>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<link href="${ctx}/static/css/bottom.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/static/css/top.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/static/css/company.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet"/>
	<script src="${ctx}/static/js/jymf.js" type="text/javascript"></script>
	<script src="${ctx}/static/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/jquery-validation/1.11.1/messages_cn.js" type="text/javascript"></script>
      <link href="${ctx}/static/css/jquery.Jcrop.min.css" type="text/css" rel="stylesheet"/> 
	<script type="text/javascript" src="${ctx}/static/jquery/jquery.Jcrop.min.js"></script>	
	<script type="text/javascript" src="${ctx}/static/jquery/jquery.nicescroll.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/showPhone.js"></script>
	<link href="${ctx}/static/css/table.css" type="text/css" rel="stylesheet">
	<sitemesh:head />
<style>
html{overflow-y:auto;}
</style>
</head>

<body>
 	<%@ include file="/WEB-INF/layouts/agent/header.jsp"%> 
	<div id="div_main">
		<sitemesh:body />
	</div>
	<br/>
	<%@ include file="/WEB-INF/layouts/footer.jsp"%>
</body>
</html>