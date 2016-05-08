<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<script>
$(document).ready(function(){
    // 新增按钮点击
    $("#addBtn").click(function() {
        $(location).attr('href', '${ctx}/company/inspectDevice/add');
    });
    
    $("#company-inspect-device").addClass("active");
});

</script>
</head>
<body>
<form:form id="fenye" name="fenye" modelAttribute="inspectDevice" action="${ctx}/company/inspectDevice/query" method="post">
    <div id="content_top">
        <table class="jymf_table">
            <tr class="table_1_tr">
                <td>终端设备号&nbsp;&nbsp;&nbsp;
                    <input name="deviceId" id="deviceId" type="text" class="texts" value="${inspectDevice.deviceId}"/>
                </td>
                <td><input class="center_btn cx_btn" type="submit" value="查 询"/></td>
                <c:if test="${companyuser.authority eq '1'}">
                    <td><input class="center_btn" id ="addBtn" type="button" value="新 增"/></td>
                </c:if>
            </tr>
        </table>
    </div> 
    <table id="table_1" class="jymf_table table_border">
        <tr id="table_1_titlebar">
            <td class="jymf_td row_3" rowspan="1" colspan="1">终端设备号</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">责任人</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">状态</td>
            <c:if test="${companyuser.authority eq '1'}">
                <td class="jymf_td row_1" rowspan="1" colspan="1">操作</td>
            </c:if>
        </tr>
        <c:forEach var="device" items="${pageView.records}">
        <tr class="jymf_tr table_1_tr">
            <td>${device.deviceId}</td>
            <td>${device.operator}</td>
            <td>
                <c:if test="${device.status eq '0'}">挂起</c:if>
                <c:if test="${device.status eq '1'}">启用</c:if>
            </td>
            <c:if test="${companyuser.authority eq '1'}">
	            <td class="row_5" align="center">
	                <div class="small_btn">
	                    <a class="linke" href="${ctx}/company/inspectDevice/update/${device.deviceId}" 
	                       id="editLink-${device.deviceId}">修改</a>
	                </div>
	            </td>
            </c:if>
        </tr>
        </c:forEach>
        <c:if test="${pageView.pageNum < pageView.pageSize }">
            <c:forEach begin="${pageView.pageNum}" end="${pageView.pageSize -1 }" step="1"> 
                <tr class="jymf_tr table_1_tr">
                    <td>&nbsp;</td>
                    <td></td>
                    <td></td>
                    <c:if test="${companyuser.authority eq '1'}">
                        <td></td>
                    </c:if>
                </tr> 
            </c:forEach> 
        </c:if>
    </table>
    <div><%@include file="../../common/webfenye.jsp" %></div>
</form:form>
</body>
</html>