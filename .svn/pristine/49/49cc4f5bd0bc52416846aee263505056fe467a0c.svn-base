<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>金源茂丰>>企业选择</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="${ctx}/static/css/bottom.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/css/top.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/css/company.css" type="text/css" rel="stylesheet"/>
<script src="${ctx}/static/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="${ctx}/static/js/jymf.js" type="text/javascript"></script>
<script>   
function setview(id,name){
    window.opener.$('#companyId').val(id);
    window.opener.$('#companyName').val(name);
    window.opener.location.reload();
    window.close();
}
</script>
</head>

<body>
<form:form id="fenye" name="fenye" modelAttribute="company" action="${ctx}/admin/dialog/querySelect" method="post">
    <div id="content_top">
        <table class="jymf_table">
            <tr class="table_1_tr">
                <td>企业名称&nbsp;<input name="name" type="text" value="${company.name}"/></td>
                <td>企业ID&nbsp;<input name="id" type="text" value="${company.id}"/></td>
                <td><input class="center_btn cx_btn" type="submit" value="查 询"/></td>
                <td><input class="center_btn cx_btn" type="button" value="返 回" onclick="window.close()"/></td>
            </tr>
        </table>
    </div> 
    
    <table id="table_1" class="jymf_table table_border">
        <tr id="table_1_titlebar">
            <td class="jymf_td row_3" rowspan="1" colspan="1">企业名称</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">企业代码</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">工作模式</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">操作</td>
        </tr>
        <c:forEach var="company" items="${pageView.records}">
        <tr class="jymf_tr table_1_tr">
            <td class="jymf_td row_3" >
           		<div class="td_name_1">
                    	<a title="${company.name}">${company.name}</a>
                	</div>
            </td>
            <td>${company.id}</td>
            <td>
                <c:if test="${company.workMode eq '0'}">产品追溯模式</c:if>
                <c:if test="${company.workMode eq '1'}">厂家追溯模式</c:if>
                <c:if test="${company.workMode eq '2'}">商城追溯模式</c:if>
                <c:if test="${company.workMode eq '3'}">云南汽配模式</c:if>
            </td>
            <td>
                <a href="javascript:setview('${company.id}','${company.name}');">选择</a>
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
        </tr> 
        </c:forEach> 
        </c:if>
    </table>
    <div><%@include file="../../common/webfenye.jsp" %></div>
</form:form>
</body>
</html>