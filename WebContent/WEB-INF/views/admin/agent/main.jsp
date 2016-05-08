<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>

<head>
<script>
$(document).ready(function(){
    // 新增按钮点击
    $("#addBtn").click(function() {
        $(location).attr('href', '${ctx}/admin/agent/add');
    });
    
    $("#admin-agent").addClass("active");
    
    /** 表单验证    */
    $("#fenye").validate({
        rules: {
        	id: {
        		digits:true
            }
        }
    });
});
</script>
</head>
<body>
<form:form id="fenye" name="fenye" modelAttribute="agentMonitor" action="${ctx}/admin/agent/query" method="post">
    <div id="content_top">
        <table class="jymf_table">
            <tr class="table_1_tr">
                <td>代理商名称&nbsp;&nbsp;&nbsp;<input name="name" class="texts" type="text" value="${agent.name}"/></td>
                <td><div style="margin-left: 50px;">&nbsp;</div></td>
                <td>ID&nbsp;&nbsp;&nbsp;<input name="id" class="texts" type="text" value="${agent.id}"/></td>
                <td><input class="center_btn cx_btn" type="submit" value="查 询"/></td>
                <td><input class="center_btn" id ="addBtn" type="button" value="新 增"/></td>
            </tr>
        </table>
    </div> 
    <table id="table_1" class="jymf_table  table_border">
        <tr id="table_1_titlebar">
            <td class="jymf_td row_3" rowspan="1" colspan="1">代理商名称</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">代理商账户</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">ID</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">当前状态</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">代理企业</td>
   <!--          <td class="jymf_td row_1" rowspan="1" colspan="1">标签卷管理</td> -->
            <td class="jymf_td row_1" rowspan="1" colspan="1">操作</td>
        </tr>
        <c:forEach var="agent" items="${pageView.records}">
        <tr class="jymf_tr table_1_tr">
            <td class="jymf_td row_3" >${agent.name}</td>
            <td>${agent.account}</td>
            <td>${agent.id}</td>
            <td>
                <c:if test="${agent.status eq '0'}">正常</c:if>
                <c:if test="${agent.status eq '1'}">挂起</c:if>
            </td>
            <td class="row_5" align="center">
                <div class="small_btn">
                    <a class="linke" href="${ctx}/admin/agentRelation/view/${agent.id}/1" id="viewLink-${agent.id}">查看企业</a>
                </div>
            </td>
            <%-- <td class="row_5" align="center">
                <div class="small_btn">
                    <a class="linke" href="${ctx}/admin/agent/labelindex/${agent.id}" id="editLink-${agent.id}">标签卷</a>
                </div>
            </td> --%>
            <td class="row_5" align="center">
                <div class="small_btn">
                    <a class="linke" href="${ctx}/admin/agent/update/${agent.id}" id="editLink-${agent.id}">修改</a>
                </div>
            </td>
        </tr>
        </c:forEach>
        <c:if test="${pageView.pageNum < pageView.pageSize}">
        <c:forEach begin="${pageView.pageNum}" end="${pageView.pageSize -1 }" step="1"> 
            <tr class="jymf_tr table_1_tr">
                <td>&nbsp;</td>
                <td></td>
                <td></td>
                <td></td>
                <!-- <td></td> -->
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