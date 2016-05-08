<%@page import="ch.qos.logback.core.Context"%>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ page import="org.jymf.utils.Constants" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>企业信息 </title>
<script type="text/javascript">
$().ready(function() {
    $("#monitor-company").addClass("active");
});
</script>
</head>
<body>
<div class="main_div" style="margin-left:200px;">
<form:form id="viewform" modelAttribute="company" >
    <div class="add_top">
        <div class="head_describe">
            <c:if test="${company.workMode ne '2'}">企业基本信息</c:if>
            <c:if test="${company.workMode eq '2'}">商城基本信息</c:if>
        </div>
        
        <table id="table_2" class="jymf_table2 table_border">
        <tr class="table_1_tr">
            <td width="90px">企业名称</td>
            <td width="250px">${company.name}</td>
     		<td width="90px">
            	<c:if test="${company.workMode ne '2'}">企业编号</c:if>
                <c:if test="${company.workMode eq '2'}">商城编号</c:if> 
             </td>
             <td>${company.id}</td>
        </tr>
        <tr class="table_1_tr">
            <td>工商注册号</td><td>${company.licenseNum}</td>
            <td>注册地</td><td>${company.address}</td>
        </tr>   
        <tr class="table_1_tr">
            <td>
                <c:if test="${company.workMode ne '2'}">企业主页</c:if>
                <c:if test="${company.workMode eq '2'}">商城主页</c:if> 
            </td>
            <td colspan="3">${company.url}</td>
        </tr>   
        <tr class="table_1_tr">
            <td>经营范围</td><td colspan="3">${company.scope}</td>
        </tr>     
        <c:if test="${company.depict.depicts ne '' && company.depict.depicts ne null}">
        <tr class="table_1_tr">
            <td>企业描述</td>
            <td colspan="3">${company.depict.depicts}</td>
        </tr>
        <tr class="table_1_tr">
            <td>维修质量评分</td>
            <td colspan="3"><fmt:formatNumber type="number" value="${company.rate1}" maxFractionDigits="1"/></td>
        </tr>
        <tr class="table_1_tr">
            <td>维修配件评分</td>
            <td colspan="3"><fmt:formatNumber type="number" value="${company.rate2}" maxFractionDigits="1"/></td>
        </tr>
        <tr class="table_1_tr">
            <td>评分人数</td>
            <td colspan="3">${company.rateCnt}</td>
        </tr>
        </c:if>
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
</form:form>
</div>
</body>
</html>