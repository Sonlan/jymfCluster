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
        /** 新增按钮点击 */
        $("#addBtn").click(function() {
            $(location).attr('href', '${ctx}/company/certificate/add');
        });
        $("#company-certificate").addClass("active");
    });
    
</script>
</head>

<body>
<form:form id="fenye" name="fenye" modelAttribute="certificate" action="${ctx}/company/certificate/query" method="post">
    <div id="content_top">
        <table class="jymf_table" >
            <tr class="table_1_tr">
                <td>名称&nbsp;&nbsp;&nbsp;
                    <input name="name" class="texts" type="text" value="${certificate.name}"/>
                </td>
                <td><input class="center_btn cx_btn" type="submit" value="查 询"/></td>
                <td><input class="center_btn" id ="addBtn" type="button" value="新 增"/></td>
            </tr>
        </table>
    </div> 
    
    <table id="table_1" class="jymf_table table_border">
        <tr id="table_1_titlebar">
            <td class="jymf_td row_3" rowspan="1" colspan="1">ID</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">名称</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">创建时间</td>
            <td class="jymf_td row_5" rowspan="1" colspan="1">操作</td>
        </tr>
        <c:forEach var="certificate" items="${pageView.records}">
        <tr class="jymf_tr table_1_tr">
            <td>${certificate.id}</td>
            <td>${certificate.name}</td>
            <td><fmt:formatDate value="${certificate.createDate}" type="both" pattern="yyyy/MM/dd HH:mm:ss"/> </td>
            <td align="center">
                <div class="small_btn">
                	<c:if test="${certificate.active == false }">
                		<a class="linke" href="${ctx}/company/certificate/edit/${certificate.id}">修改</a>	
                	</c:if>
                	<c:if test="${certificate.active == true }">
                		<a class="linke" href="${ctx}/company/certificate/view/${certificate.id}" >查看</a>	
                	</c:if>
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
                </tr> 
            </c:forEach> 
        </c:if>
    </table>
    <div><%@include file="../../common/webfenye.jsp" %></div>
</form:form>
</body>
</html>