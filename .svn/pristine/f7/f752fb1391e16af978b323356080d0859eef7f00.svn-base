<%@page import="ch.qos.logback.core.Context"%>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${ctx}/static/css/zhezhao.css" type="text/css" rel="stylesheet"/>
<title>标签卷更新页面</title>
<script>
$(document).ready(function() {
    $("#admin-label").addClass("active");
    
    /** 表单验证    */
    $("#editform").validate({
        rules: {
            startTid: {
                required:true,
                digits:true,
                minlength:16,
                remote: "${ctx}/agent/labelIndex/startChk"
            },
            endTid: {
                required:true,
                digits:true,
                minlength:16,
                remote: "${ctx}/agent/labelIndex/endChk"
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
//显示遮罩层
function showdiv() { 
    document.getElementById("bg").style.display ="block";
    document.getElementById("showCompany").style.display ="block";
    document.getElementById("ifm_ID").src="${ctx}/admin/dialog/querySelect";
}
//隐藏遮罩层
function hidediv() {
    document.getElementById("bg").style.display ='none';
    document.getElementById("showCompany").style.display ='none';
}

function setCompany(id,name){
    $("#companyId").attr("value",id);
    $("#companyName").attr("value",name);
}
</script>
</head>
<body>
<div style="margin-left:200px;margin-top: 50px;">
<form:form id="editform" modelAttribute="labelIndex" action="${ctx}/agent/labelIndex/update" method="post">
<input id="companyId" name="companyId" type="hidden" value="${labelIndex.companyId}"/>
<input name="id" type="hidden" value="${labelIndex.id}"/>
<table border="0px" cellpadding="0" cellspacing="0">
    <tr>
        <td>起&nbsp;始&nbsp;标&nbsp;签
            <input class="text" name="startTid" type="text" value="${labelIndex.startTid}" maxlength="22" />
            <span class="must">*</span>
        </td>
    </tr>
    <tr>
        <td>结&nbsp;束&nbsp;标&nbsp;签
            <input class="text" name="endTid" type="text" value="${labelIndex.endTid}" maxlength="22" />
            <span class="must">*</span>
        </td>
    </tr>
    <tr>
        <td>所&nbsp;属&nbsp;企&nbsp;业
            <input id="companyName" class="text" name="name" type="text" value="${labelIndex.name}" readonly="readonly"/>
            <span class="must">*</span>
        </td>
        <td>
            <span style="margin-left: 100px;">
                <input type="button" class="center_btn" onclick="showdiv();" value="选择"/>
            </span>
        </td>
    </tr>
    <tr>
        <td>标&nbsp;签&nbsp;状&nbsp;态
           <form:select path="status" class="select" items="${statusMap}" value="${labelIndex.status}"/>
        </td>
    </tr>
</table>
<div style="margin-top: 30px;">
    <div style="float: left"><input class="ok_button" type="submit" value=" "/></div>
    <div style="float: left"><input class="cancer_btn" type="button" value=""  
		 onclick="javascript:window.location.href='javascript:history.go(-1)'"/></div>
</div>
</form:form>
</div>
<!-- 遮罩层 -->
<div id="bg" class="bg" style="display: none"></div>
<div id="showCompany" class="showCompany" style="display: none"> 
    <div class="show_top"><input type="button" onclick="hidediv();" value=""/></div>
    <iframe id="ifm_ID" width="100%" height="85%" frameborder="no" scrolling="no" ></iframe>
</div>
</body>
</html>