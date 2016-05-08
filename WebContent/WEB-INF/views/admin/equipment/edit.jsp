<%@page import="ch.qos.logback.core.Context"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${ctx}/static/css/zhezhao.css" type="text/css"
	rel="stylesheet" />
<link href="${ctx}/static/css/errormessage.css" type="text/css"
	rel="stylesheet" />

<title>设备管理更新页面</title>
<script>
	$(document).ready(function() {
		$("#admin-equipment").addClass("active");
		/** 表单验证    */
		$("#editForm").validate({
			rules : {
				id : {
					required : true,
					digits : true
				},
				name : {
					required : true
				}
			}
		});
		
	   if ($("#workMode").val() == 2) {
            document.getElementById("product").style.display = "block";
        } else {
            document.getElementById("product").style.display = "none";
        }
	});
	//显示遮罩层
	function showdiv() {
		document.getElementById("bg").style.display = "block";
		document.getElementById("showCompany").style.display = "block";
		document.getElementById("ifm_ID").src = "${ctx}/admin/dialog/querySelect";
	}

	//显示遮罩层-商户
	function showProduct() {
		document.getElementById("bg").style.display = "block";
		document.getElementById("showCompany").style.display = "block";
		var companyId = $('#companyId').val();
		document.getElementById("ifm_ID").src = '${ctx}/admin/dialog/selectProduct/'
				+ companyId;
	}

	//隐藏遮罩层
	function hidediv() {
		document.getElementById("bg").style.display = 'none';
		document.getElementById("showCompany").style.display = 'none';
	}

	function setCompany(id, name, workMode) {
		$("#companyId").attr("value", id);
		$("#companyName").attr("value", name);
		$("#workMode").attr("value", workMode);
		
		if (workMode == 2) {
			document.getElementById("product").style.display = "block";
		} else {
			document.getElementById("product").style.display = "none";
		}
	}

	function setProduct(productId,name) {
		$("#productId").attr("value", productId);
		$("#productName").attr("value", name);
	}
</script>
</head>
<body>
<div style="margin-left: 200px; margin-top: 50px;">
<form:form id="editForm" modelAttribute="equipment" action="${ctx}/admin/equipment/update" method="post">
	<input id="companyId" type="hidden" name="companyId" value="${equipment.companyId}" />
	<input id="workMode" type="hidden" name="workMode" value="${equipment.workMode}" />
	<input id="productId" type="hidden" name="productId" value="${equipment.productId}" />
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-error">${message}</div>
	</c:if>
	<table border="0px" cellpadding="0" cellspacing="0">
		<tr>
			<td>设&nbsp;备&nbsp;编&nbsp;号 
			    <input class="text readonly" name="id" type="text" maxlength="13" 
			           value="${equipment.id}" readonly="readonly" /> 
			    <span class="must">*</span>
			</td>
		</tr>
		<tr>
			<td>所&nbsp;属&nbsp;企&nbsp;业 
			    <input id="companyName"	class="text" name="name" type="text" 
			           value="${equipment.name }" readonly="readonly" /> 
				<span class="must">*</span>
			</td>
			<td>
			    <span style="margin-left: 100px;"> 
			        <input type="button" class="center_btn" value="选择" onclick="showdiv();" />
			    </span>
			</td>
		</tr>
		<tr>
			<td>操&nbsp;作&nbsp;权&nbsp;限 
			    <form:select path="limit" class="select" items="${limitMap}" />
			</td>
		</tr>
		<tr>
			<td>设&nbsp;备&nbsp;状&nbsp;态 
			    <form:select path="devStatus" class="select" items="${devStatusMap}" />
			</td>
		</tr>
	</table>

	<div id="product" style="display: none;">
		<table border="0px" cellpadding="0" cellspacing="0">
			<tr>
				<td><span class="must">商城模式下使用</span></td>
				<td></td>
			</tr>
			<tr>
				<td>商&nbsp;户&nbsp;名&nbsp;称 
				    <input id="productName" class="text" name="productName" type="text"
					       value="${equipment.productName}" readonly="readonly" />
				</td>
				<td>
				    <span style="margin-left: 113px;"> 
				        <input type="button" class="center_btn" onclick="showProduct();" value="选择" />
				    </span>
				</td>
			</tr>
		</table>
	</div>

	<div style="margin-top: 30px;">
		<table>
			<tr>
				<td><input class="ok_button" type="submit" value=" " /></td>
				<td>
				    <input class="cancer_btn" type="button" value=""
					       onclick="javascript:window.location.href='javascript:history.go(-1)'" />
				</td>
			</tr>
		</table>
	</div>
</form:form>
</div>

<div id="bg" class="bg" style="display: none"></div>
<div id="showCompany" class="showCompany" style="display: none">
	<div class="show_top">
		<input type="button" onclick="hidediv();" value="" />
	</div>
	<iframe id="ifm_ID" width="100%" height="85%" frameborder="no" scrolling="no"></iframe>
</div>
</body>
</html>