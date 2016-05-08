<%@page import="ch.qos.logback.core.Context"%>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="org.jymf.utils.Constants" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript">
$(document).ready(function(){
    $("#monitor-product").addClass("active");
});
</script>

<title>商户配置修改 </title>
</head>
<body>
<div class="main_div" style="margin-left:200px;">
<form:form id="editform" modelAttribute="product" >
    <input type="hidden" name="id" value="${product.id}"/>
    <div class="add_top">
        <div class="head_describe">商户基本信息</div>
    </div>
       
    <table id="table_2" class="jymf_table2 table_border" width="300px">
        <tr class="table_1_tr">
            <td width="60px">商户名称</td><td>${product.name}</td>
            <td width="60px">商户地址</td><td>${product.origin}</td>
        </tr>
        
        <c:if test="${product.url ne '' }">
	        <tr class="table_1_tr">
	            <td>商户主页</td><td colspan="3">${product.url}</td>
	        </tr>
        </c:if>
        
        <c:if test="${product.description ne '' }">
	        <tr class="jymf_tr table_1_tr">
	            <td>商户描述</td><td colspan="3">${product.description}</td>
	        </tr>
        </c:if>
    </table>
    
    
    <c:if test="${product.isInfoImg}">
    <div class="add_center">
        <div class="head_describe">商户描述图片</div>
        <table>
            <tr>
               <c:forEach var="imgf" items="${product.productImgs}">
               <c:if test="${imgf.no > '0'}">
               <c:if test="${imgf.fileName ne '' }">
                   <td class="jymf_td row_img" rowspan="1" colspan="1">
                       <img style="margin-top:25px;" id="img${imgf.no}" 
                            name="imgs" 
                            width="<%=Constants.PHOTO_IMG_WIDTH %>px" 
                            src="${imgf.fileName}"
                        />
                   </td>
               </c:if>
               </c:if>
               </c:forEach> 
            </tr>
        </table>
    </div>
    </c:if>
    
    <div class="add_btn">
        <table>
            <tr>
                <td>
                    <input class="ok_button" type="button" 
                           onclick="javascript:window.location.href='javascript:history.go(-1)'"/>
                </td>
            </tr>    
        </table>
    </div>
    </form:form>
</div>

</body>
</html>