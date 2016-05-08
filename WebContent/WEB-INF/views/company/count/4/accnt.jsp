<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<script type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
<script>
$().ready(function() {      
    $("#company-count").addClass("active");
});
</script>
</head>

<body>
<form:form id="fenye" name="fenye" modelAttribute="acInfo" action="${ctx}/company/count/queryaccnt" method="post">
    <div id="content_top">
        <table class="jymf_table" >
            <tr class="table_1_tr">
                <td>开始日期&nbsp;&nbsp;&nbsp;
	                <input name="startDate" type="text" class="Wdate" 
	                onclick="WdatePicker()" value="${acInfo.startDate}" 
	                style="width:190px;height:25px;"/>
                <td>     
                <td>结束日期&nbsp;&nbsp;&nbsp;
                    <input name="endDate" type="text" class="Wdate" 
                    onclick="WdatePicker()" value="${acInfo.endDate}"
                     style="width:190px;height:25px;"/>
                <td>   
                <td><input class="center_btn cx_btn" type="submit" value="查 询"/></td>
            </tr>
        </table>
    </div> 
    
    <table id="table_1" class="jymf_table table_border">
        <tr id="table_1_titlebar">
            <td class="jymf_td row_1" rowspan="1" colspan="1">产品ID</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">产品名称</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">销售区域</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">可能窜货量</td>
        </tr>
        <c:forEach var="acInfo" items="${pageView.records}">
        <tr class="jymf_tr table_1_tr">
            <td>${acInfo.productId}</td>
            <td>${acInfo.productName}</td>
            <td>${acInfo.salesAreaName}</td>
            <td>${acInfo.acCnt}</td>
        </tr>
        </c:forEach>
        <c:if test="${pageView.pageNum < pageView.pageSize }">
            <c:forEach begin="${pageView.pageNum}" end="${pageView.pageSize -1 }" step="1"> 
                <tr class="jymf_tr table_1_tr">
                    <td>&nbsp;</td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr> 
            </c:forEach> 
        </c:if>
    </table>
    <div><%@include file="../../../common/webfenye.jsp" %></div>
</form:form>
</body>
</html>