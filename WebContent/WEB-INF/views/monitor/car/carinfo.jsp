<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<link href="${ctx}/static/css/company.css" type="text/css" rel="stylesheet"/>
<script>
$().ready(function(){
$("#monitor-carinfo").addClass("active");
});
</script>
</head>

<body>
<form:form id="fenye" name="fenye" modelAttribute="carInfo" action="${ctx}/monitor/carinfo" method="post">
<div id="content_top">
    <table class="jymf_table" >
        <tr class="table_1_tr">
            <td>车牌号&nbsp;&nbsp;
                <input name="carno" type="text" class="texts" value="${carInfo.carno}"/>
            </td>
            <td><div style="margin-left: 50px;">&nbsp;</div></td>
            <td>发动机号&nbsp;&nbsp;
                <input name="engineno" type="text" class="texts" value="${carInfo.engineno}"/>
		    </td>
		    <td><div style="margin-left: 50px;">&nbsp;</div></td>
		    <td>车架号&nbsp;&nbsp;
                <input name="vin" type="text" class="texts" value="${carInfo.vin}"/>
		    </td>
		    <td><input class="center_btn cx_btn" type="submit" value="查 询"/></td>
	    </tr>
	</table>
</div>
<table id="table_1" class="jymf_table table_border">
	<tr id="table_1_titlebar">
		<td class="jymf_td row_3" rowspan="1" colspan="1">追溯码</td>
		<td class="jymf_td row_1" rowspan="1" colspan="1">维修企业名称</td>
		<td class="jymf_td row_1" rowspan="1" colspan="1">车牌号</td>
		<td class="jymf_td row_1" rowspan="1" colspan="1">发动机号</td>
		<td class="jymf_td row_1" rowspan="1" colspan="1">车架号</td>
		<td class="jymf_td row_1" rowspan="1" colspan="1">建立时间</td>
		<td class="jymf_td row_5" rowspan="1" colspan="1">详细</td>
	</tr>
    <c:forEach var="cInfo" items="${pageView.records}">
    <tr class="jymf_tr table_1_tr">
        <td>${cInfo.labelId}</td>
        <td>${cInfo.companyName}</td>
        <td>${cInfo.carno}</td>
        <td>${cInfo.engineno}</td>
        <td>${cInfo.vin}</td>
        <td>${cInfo.createDate}</td>
        <td align="center">
            <div class="small_btn">
                <a class="linke" href="${ctx}/monitor/carInfo/document/${cInfo.id}" id="editLink-${carInfo.id}">详细</a>
            </div>
        </td>
    </tr>
    </c:forEach>
    <c:if test="${pageView.pageNum < pageView.pageSize }">
        <c:forEach begin="${pageView.pageNum}" end="${pageView.pageSize -1 }" step="1"> 
        <tr>
		    <td>&nbsp;</td>
            <td></td>
            <td></td>
		    <td></td>
		    <td></td>
            <td></td>
            <td></td>
	    </tr> 
        </c:forEach> 
    </c:if>
</table>
<div><%@include file="../../common/webfenye.jsp" %></div>
</form:form>
</body>
</html>