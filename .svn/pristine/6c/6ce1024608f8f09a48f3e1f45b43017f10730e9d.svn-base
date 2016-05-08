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
    $("#agent-company").addClass("active");
});
</script>
<title>产品类型查看 </title>
</head>
<body>
<div class="main_div" style="margin-left:200px;">
<form:form id="viewform" modelAttribute="product" >
    <div class="add_top">
        <div class="head_describe">商品基本信息</div>
        <table id="table_2" class="jymf_table2 table_border" width="300px">
            <tr class="jymf_tr table_1_tr">
                <td width="60px">商品名称</td><td>${product.name}</td>
                <td width="60px">原产国家</td><td>${product.origin}</td>
            </tr>
            <tr class="jymf_tr table_1_tr">
                <td>商品类型</td><td colspan="3">${product.productPara.gx[0].value}</td>
            </tr>
            <tr class="jymf_tr table_1_tr">
                <td>容&nbsp;&nbsp;量</td><td>${product.productPara.gx[1].value}</td>
                <td>葡萄品种</td><td>${product.productPara.gx[2].value}</td>
            </tr>
            <tr class="jymf_tr table_1_tr">
                <td>酒精含量</td><td>${product.productPara.gx[3].value}</td>
                <td>年&nbsp;&nbsp;份</td><td>${product.productPara.gx[4].value}</td>
            </tr>
            <tr class="jymf_tr table_1_tr">
                <td>色&nbsp;&nbsp;泽</td><td>${product.productPara.gx[5].value}</td>
                <td>醒酒时间</td><td>${product.productPara.gx[6].value}</td>
            </tr>
            <tr class="jymf_tr table_1_tr">
                <td>品尝温度</td><td>${product.productPara.gx[7].value}</td>
                <td>进&nbsp;口&nbsp;商</td><td>${product.productPara.gx[8].value}</td>
            </tr>
            <tr class="jymf_tr table_1_tr">
                <td>商品特点</td><td colspan="3">${product.description}</td>
            </tr>
            <tr class="jymf_tr table_1_tr">
                <td>APP&nbsp;查询结果描述</td><td colspan="3">您所查询的是${product.queryDes}</td>
            </tr>
        </table>
    </div>
    <c:if test="${product.productImgs[0].fileName ne '' }">
	    <div class="add_center">
	        <div class="head_describe">商品概述图片</div>
	        <table>
	            <tr>
		            <td>
		                <img style="margin-top:25px;" id="img0" 
		                     name="imgs" 
		                     width="<%=Constants.PHOTO_IMG_WIDTH %>px" 
		                     src="${product.productImgs[0].fileName}"
		                 />
		            </td>
	            </tr>
	        </table>
	    </div>
    </c:if>
    
    <c:if test="${product.isInfoImg}">
	    <div class="add_center">
	        <div class="head_describe">商品描述图片</div>
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