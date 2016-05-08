<%@page import="ch.qos.logback.core.Context"%>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="org.jymf.utils.Constants" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<title>合同信息 </title>
<link rel="stylesheet" media="screen" type="text/css" href="${ctx}/static/zoomimage/zoomimage.css" />
<script type="text/javascript" src="${ctx}/static/zoomimage//jquery.js"></script>
<script type="text/javascript" src="${ctx}/static/zoomimage//eye.js"></script>
<script type="text/javascript" src="${ctx}/static/zoomimage//utils.js"></script>
<script type="text/javascript" src="${ctx}/static/zoomimage//zoomimage.js"></script>
<script type="text/javascript" src="${ctx}/static/zoomimage//layout.js"></script>

<script type="text/javascript">
$().ready(function() {
    $("#company-document").addClass("active");
});
</script>
</head>
<body>
<div class="main_div" style="margin-left:200px;">
<form:form id="viewform" >
    <div class="add_top">
        <div class="head_describe">合同基本信息</div>
        <table id="table_2" class="jymf_table2 table_border">
	        <tr class="table_1_tr">
	            <td width="15%">合同ID</td><td width="35%">${document.id}</td>
	     		<td width="15%">配件数量 </td><td width="35%">${document.count}</td>
	        </tr>
	        <tr class="table_1_tr">
                <td>生成时间</td><td>${document.createDate}</td>
                <td>车牌号</td><td>${document.carNo}</td>
            </tr>
            <tr class="table_1_tr">
                <td>首次消费日期</td><td>${document.consTime}</td>
                <td>消费地区</td><td>${document.consArea}</td>
            </tr>
        </table>
        
        <div class="head_describe">配件明细</div>
        <table id="table_1" class="jymf_table table_border">
	        <tr id="table_1_titlebar">
	            <td class="jymf_td row_1" rowspan="1" colspan="1">序号</td>
	            <td class="jymf_td row_1" rowspan="1" colspan="1">追溯码</td>
	            <td class="jymf_td row_1" rowspan="1" colspan="1">产品名称</td>
	            <td class="jymf_td row_1" rowspan="1" colspan="1">生产商</td>
	            <td class="jymf_td row_1" rowspan="1" colspan="1">适用车型</td>
	        </tr>
	        <c:forEach var="label" items="${document.labels}"  varStatus="status">
	        <tr class="jymf_tr table_1_tr">
	            <td>${status.index + 1}</td>
	            <td>${label.id}</td>
	            <td>${label.productName}</td>
	            <td>${label.producer}</td>
	            <td>${label.carType}</td>
	        </c:forEach>
        </table>
    </div>

    <c:if test="${document.imgs[0].fileName ne '' && document.imgs[0].fileName ne null}">
        <div class="head_describe">合&nbsp;同&nbsp;副&nbsp;本</div>
    </c:if>
         
    <table>
        <tr>
           <c:forEach var="imgf" items="${document.imgs}">
               <c:if test="${imgf.fileName ne '' }">
               <td class="jymf_td row_img" rowspan="1" colspan="1">
                   <a class="bwGal"  href="$${imgf.fileName}" title="副本">
                   <img id="img${imgf.no}" 
                        name="imgs" 
                        width="<%=Constants.PHOTO_IMG_WIDTH %>px" 
                        src="${imgf.fileName}"
                        style="margin-top:25px;"
                        title="副本"
                    />
                    </a>
               </td>
               </c:if>
           </c:forEach> 
        </tr>
    </table>

    <br>
    <br>
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