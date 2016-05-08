<%@page import="ch.qos.logback.core.Context"%>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="org.jymf.utils.Constants" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>企业信息修改 </title>
<script type="text/javascript">
$().ready(function() {
    $("#company-update").addClass("active");
});
</script>
</head>
<body>
<div class="main_div" style="margin-left:200px;">
<form:form id="viewform" modelAttribute="company" >
    <div class="add_top">
        <div class="head_describe">               
            <c:if test="${company.workMode eq '2'}">商城基本信息</c:if>
            <c:if test="${company.workMode ne '2'}">企业基本信息</c:if>                   
        </div>
        
        <table id="table_2" class="jymf_table2 table_border" width="350px">
        <tr class="table_1_tr">
            <td width="90px">公司名称</td>
            <td width="250px">${company.name}</td>
     		<td width="90px">                
                <c:if test="${company.workMode eq '2'}">商&nbsp;城&nbsp;编&nbsp;号&nbsp;</c:if> 
                <c:if test="${company.workMode ne '2'}">企&nbsp;业&nbsp;编&nbsp;号&nbsp;</c:if>
             </td>
             <td>${company.id}</td>
        </tr>
        
        <tr class="table_1_tr">
             <td>                
                <c:if test="${company.workMode eq '2'}">商&nbsp;城&nbsp;主&nbsp;页&nbsp;</c:if> 
                <c:if test="${company.workMode ne '2'}">企&nbsp;业&nbsp;主&nbsp;页&nbsp;</c:if> 
            </td>
            <c:if test="${company.tel ne null && company.tel ne ''}">
	            <td>${company.url}</td>
	            <td>举&nbsp;报&nbsp;电&nbsp;话
	                <input type="text" class="text readonly" readonly="readonly" name="tel" value="${company.tel}"/>
	            </td>
            </c:if>
            <c:if test="${company.tel eq null || company.tel eq ''}">
                <td colspan="3">${company.url}</td>
            </c:if>
        </tr>
        
        <tr class="table_1_tr">
            <td>企业描述</td>
            <td colspan="3">${company.depict.depicts}</td>
        </tr>
    </table>
    </div>

	<c:if test="${company.comImgs[0].fileName ne ''}">
    	<div class="head_describe">企&nbsp;业&nbsp;图&nbsp;片</div>
    </c:if>
    
    <table>
        <tr>
           <c:forEach var="imgf" items="${company.comImgs}">
               <c:if test="${imgf.fileName ne '' }">
               <td class="jymf_td row_3" rowspan="1" colspan="1">
                   <img id="img${imgf.no}" 
                        name="imgs" 
                        width="<%=Constants.PHOTO_IMG_WIDTH %>px" 
                        src="${imgf.fileName}"
                        style="margin-top:25px;"
                    />
               </td>
               </c:if>
           </c:forEach> 
        </tr>
    </table>
    
    <div class="add_btn">
        <table>
            <tr>
                <td>
                    <input class="ok_button" type="button" value="" 
                           onclick="javascript:window.location.href='javascript:history.go(-1)'"/>
                </td>
            </tr>    
        </table>
    </div>
</form:form>
</div>
</body>
</html>