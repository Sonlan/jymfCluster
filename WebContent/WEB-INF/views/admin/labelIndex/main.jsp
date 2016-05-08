<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta name="renderer" content="webkit"> 
<script>
    $().ready(function() {
        /** 新增按钮点击 */
        $("#addBtn").click(function() {
            $(location).attr('href', '${ctx}/admin/labelIndex/add');
        });
        $("#admin-label").addClass("active");
    });
</script>
</head>
<body>
<form:form id="fenye" name="fenye" action="${ctx}/admin/labelIndex/query" method="post">
    <div id="content_top">
        <table class="jymf_table">
            <tr class="table_1_tr">
                <td>企业名称&nbsp;&nbsp;&nbsp;<input name="name" class="texts" type="text" value="${labelIndex.name}"/></td>
                <td><input class="center_btn cx_btn" type="submit" value="查 询"/></td>
                <td><input class="center_btn" id ="addBtn" type="button" value="新 增"/></td>
            </tr>
        </table>
    </div> 
    <table id="table_1" class="jymf_table table_border">
        <tr id="table_1_titlebar">
            <td class="jymf_td row_3" rowspan="1" colspan="1">所属企业</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">企业ID</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">起始标签号</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">结束标签号</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">标签个数</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">追溯模式</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">当前状态</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">创建日期</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">操作</td>
        </tr>
        <c:forEach var="labelIndex" items="${pageView.records}">
        <tr class="jymf_tr table_1_tr">
            <td class="row_3">
            	<div class="td_name_1">
                    	<a title="${labelIndex.name}">${labelIndex.name}</a>
                </div>
            
            </td>
            <td>${labelIndex.companyId}</td>
            <td>${labelIndex.startTid}</td>
            <td>${labelIndex.endTid}</td>
            <td>${labelIndex.count}</td>
            <td>
             	<c:set var ="mode" value="${labelIndex.workMode }"/>
				${workModelMap[fn:trim(mode)]}
            </td>
            <td>
                <c:if test="${labelIndex.status eq '0'}">无效</c:if>
                <c:if test="${labelIndex.status eq '1'}">有效</c:if>
            </td>
            <td><fmt:formatDate value="${labelIndex.createDate}" type="both" pattern="yyyy/MM/dd HH:mm:ss"/></td>
            <td class="row_5" align="center">
                <div class="small_btn">
                    <a class="linke" 
                       href="${ctx}/admin/labelIndex/update/${labelIndex.id}" 
                       id="editLink-${labelIndex.id}">修改</a>
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