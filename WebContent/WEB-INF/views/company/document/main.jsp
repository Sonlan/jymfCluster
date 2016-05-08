<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<script type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
<script>
    $().ready(function() {
    	$("#company-document").addClass("active");        
    });
    
</script>
</head>

<body>
<form:form id="fenye" name="fenye" action="${ctx}/company/document/query" method="post">
    <div id="content_top">
        <table class="jymf_table" >
            <tr class="table_1_tr">
                <td>开始日期&nbsp;&nbsp;&nbsp;
                    <input name="startDate" type="text" class="Wdate" 
                    onclick="WdatePicker()" value="${document.startDate}" 
                    style="width:190px;height:25px;"/>
                <td>     
                <td>结束日期&nbsp;&nbsp;&nbsp;
                    <input name="endDate" type="text" class="Wdate" 
                    onclick="WdatePicker()" value="${document.endDate}"
                     style="width:190px;height:25px;"/>
                <td>   
                <td><input class="center_btn cx_btn" type="submit" value="查 询"/></td>
            </tr>
        </table>
    </div>

    <table id="table_1" class="jymf_table table_border">
        <tr id="table_1_titlebar">
        	<td class="jymf_td row_1" rowspan="1" colspan="1">合同ID</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">配件数量</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">生成时间</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">车牌号</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">首次消费日期</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">消费地区</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">操作</td>
        </tr>
        <c:forEach var="documnet" items="${pageView.records}">       
        <tr class="jymf_tr table_1_tr">
            <td>${documnet.id}</td>
            <td>${documnet.count}</td>
            <td>${documnet.createDate}</td>
            <td>${documnet.carNo}</td>
            <td>${documnet.consTime}</td>
            <td>${documnet.consArea}</td>
            <td align="center">
                <div class="small_btn">
                    <a class="linke" href="${ctx}/company/document/view/${documnet.id}" id="editLink-${documnet.id}">详细</a>
                </div>
            </td>
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