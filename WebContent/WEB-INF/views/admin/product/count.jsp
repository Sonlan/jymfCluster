<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<script type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
<script>
$().ready(function() {      
    $("#admin-company").addClass("active");
    
    /** 返回按钮点击 */
    $("#backBtn").click(function() {
        $(location).attr('href', '${ctx}/admin/company/main');
    });
});
</script>
</head>

<body>
<form:form id="fenye" name="fenye" modelAttribute="companyCount" action="${ctx}/admin/product/count/query" method="post">
    <div id="content_top">
        <table class="jymf_table" >
            <tr class="table_1_tr">
                <td>开始日期&nbsp;&nbsp;&nbsp;
	                <input name="startDate" type="text" class="Wdate" onclick="WdatePicker()" value="${companyCount.startDate}" />
                <td>     
                <td>结束日期&nbsp;&nbsp;&nbsp;
                    <input name="endDate" type="text" class="Wdate" onclick="WdatePicker()" value="${companyCount.endDate}"/>
                <td>   
                <td><input class="center_btn cx_btn" type="submit" value="查 询"/></td>
                <td><input class="center_btn cx_btn" id ="backBtn" type="button" value="返 回" /></td>
            </tr>
        </table>
    </div> 
             
    <table id="table_1" class="jymf_table table_border">
        <tr id="table_1_titlebar">
            <td class="jymf_td row_3" rowspan="1" colspan="1">名称</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">ID</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">激活数量</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">包数量</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">出库数量</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">入库数量</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">销售数量</td>
        </tr>
        <c:forEach var="companyCount" items="${pageView.records}">
        <tr class="jymf_tr table_1_tr">
            <td class="row_3">
                <div class="td_name_1">
                    <a title="${companyCount.productName}">${companyCount.productName}</a>
                </div>
            </td>
            <td>${companyCount.productId}</td>
            <td>${companyCount.activeCnt}</td>
            <td>${companyCount.packageCnt}</td>
            <td>${companyCount.outCnt}</td>
            <td>${companyCount.inCnt}</td>
            <td>${companyCount.salesCnt}</td>
        </tr>
        </c:forEach>
        <c:if test="${pageView.pageNum < pageView.pageSize }">
            <c:forEach begin="${pageView.pageNum}" end="${pageView.pageSize -1 }" step="1"> 
                <tr class="jymf_tr table_1_tr">
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