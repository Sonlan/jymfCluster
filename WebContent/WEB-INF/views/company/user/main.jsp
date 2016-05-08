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
            $(location).attr('href', '${ctx}/company/user/add');
        });
        $("#company-user").addClass("active");
    });
    
</script>
</head>

<body>
<form:form id="fenye" name="fenye" action="${ctx}/company/user/query" method="post">
    <div id="content_top">
        <table class="jymf_table" >
            <tr class="table_1_tr">
                <td>用户ID&nbsp;&nbsp;&nbsp;
                    <input name="searchId" class="texts" type="text" value="${user.searchId}"/>
                </td>
                <td><input class="center_btn cx_btn" type="submit" value="查 询"/></td>
                <td><input class="center_btn" id ="addBtn" type="button" value="新 增"/></td>
            </tr>
        </table>
    </div> 
    
    <table id="table_1" class="jymf_table table_border">
        <tr id="table_1_titlebar">
            <td class="jymf_td row_3" rowspan="1" colspan="1">用户ID</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">组织代码</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">创建日期</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">权限</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">状态</td>
            <td class="jymf_td row_5" rowspan="1" colspan="1">密码</td>
            <td class="jymf_td row_5" rowspan="1" colspan="1">操作</td>
        </tr>
        <c:forEach var="user" items="${pageView.records}">
        <tr class="jymf_tr table_1_tr">
            <td>${user.id}</td>
            <td>${user.companyId}</td>
            <td><fmt:formatDate value="${user.createDate}" type="both" pattern="yyyy/MM/dd"/> </td>
            <td>
                <c:if test="${user.authority eq '0'}">浏览</c:if>
                <c:if test="${user.authority eq '1'}">管理</c:if>
            </td>  
            <td>
                <c:if test="${user.status eq '0'}">启用</c:if>
                <c:if test="${user.status eq '1'}">禁用 </c:if>
            </td>
            <td align="center">
                <div class="small_btn">
                    <a class="linke" href="${ctx}/company/user/initPwd/${user.id}" id="editLinkPwd-${user.id}">初始设置</a>
                </div>
            </td>
            <td align="center">
                <div class="small_btn">
                    <a class="linke" href="${ctx}/company/user/update/${user.id}" id="editLink-${user.id}">修改</a>
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