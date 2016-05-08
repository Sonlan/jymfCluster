<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>

<head>
<meta name="renderer" content="webkit"> 
<script>
$(document).ready(function(){
    // 新增按钮点击
    $("#addBtn").click(function() {
        $(location).attr('href', '${ctx}/admin/monitor/add');
    });
    
    $("#admin-monitor").addClass("active");
    
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
<form:form id="fenye" name="fenye" modelAttribute="monitor" action="${ctx}/admin/monitor/query" method="post">
    <div id="content_top">
        <table class="jymf_table">
            <tr class="table_1_tr">
                <td>部门名称&nbsp;&nbsp;&nbsp;<input name="name" class="texts" type="text" value="${monitor.name}"/></td>
                <td><div style="margin-left: 50px;">&nbsp;</div></td>
                <td>组织代码&nbsp;&nbsp;&nbsp;<input name="id" class="texts" type="text" value="${monitor.id}"/></td>
                <td><input class="center_btn cx_btn" type="submit" value="查 询"/></td>
                <td><input class="center_btn" id ="addBtn" type="button" value="新 增"/></td>
            </tr>
        </table>
    </div> 
    <table id="table_1" class="jymf_table  table_border">
        <tr id="table_1_titlebar">
            <td class="jymf_td row_3" rowspan="1" colspan="1">监管部门</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">监管账户</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">组织代码</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">当前状态</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">监管企业</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">操作</td>
        </tr>
        <c:forEach var="monitor" items="${pageView.records}">
        <tr class="jymf_tr table_1_tr">
            <td class="jymf_td row_3" >${monitor.name}</td>
            <td>${monitor.account}</td>
            <td>${monitor.id}</td>
            <td>
                <c:if test="${monitor.status eq '0'}">正常</c:if>
                <c:if test="${monitor.status eq '1'}">挂起</c:if>
            </td>
            <td class="row_5" align="center">
                <div class="small_btn">
                    <a class="linke" href="${ctx}/admin/relation/view/${monitor.id}/1" id="viewLink-${monitor.id}">查看企业</a>
                </div>
            </td>
            <td class="row_5" align="center">
                <div class="small_btn">
                    <a class="linke" href="${ctx}/admin/monitor/update/${monitor.id}" id="editLink-${monitor.id}">修改</a>
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