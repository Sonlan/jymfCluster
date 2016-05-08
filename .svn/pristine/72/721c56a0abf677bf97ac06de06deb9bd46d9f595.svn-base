<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<script>
    $().ready(function() {
    	$("#agent-company").addClass("active");
        
        /** 返回按钮点击 */
        $("#backBtn").click(function() {
            $(location).attr('href', '${ctx}/agent/product/salesCntQuery/${productback}');
        });
    });
    
</script>
</head>

<body>
<form:form id="fenye" name="fenye" action="${ctx}/agent/product/queryopt" method="post">
    <div id="content_top" style="margin-left: 88%">
        <table class="jymf_table">
            <tr class="table_1_tr">
                <td><input class="center_btn cx_btn" id ="backBtn" type="button" value="返 回"/></td>
            </tr>
        </table>
    </div>
    <input type="hidden" name="productId" value="${optAction.productId}">   
    <table id="table_1" class="jymf_table table_border">
        <tr id="table_1_titlebar">
        	<td class="jymf_td row_1" rowspan="1" colspan="1">追溯码</td>
            <td class="jymf_td row_6" rowspan="1" colspan="1">公司名称</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">产品名称</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">销售日期</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">车牌号</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">首次查询日期</td>
        </tr>
        <c:forEach var="optAction" items="${pageView.records}">
        <tr class="jymf_tr table_1_tr">
            <td>${optAction.labelId}</td>
            <td>${optAction.companyName}</td>
            <td>
                <div class="td_name_1">
                    <a title="${optAction.productName}">${optAction.productName}&nbsp;${optAction.numberId}</a>
                </div>
            </td>
            <td> <fmt:formatDate value="${optAction.timestamp}" type="both" pattern="yyyy/MM/dd"/></td>
            <td>
                 <a title="${optAction.optCarno}">${optAction.optCarno}</a>
            </td>
            <td><fmt:formatDate value="${optAction.timeFirst}" type="both" pattern="yyyy/MM/dd"/></td>
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
                </tr> 
            </c:forEach> 
        </c:if>
    </table>
    <div><%@include file="../../../common/webfenye.jsp" %></div>
</form:form>
</body>
</html>