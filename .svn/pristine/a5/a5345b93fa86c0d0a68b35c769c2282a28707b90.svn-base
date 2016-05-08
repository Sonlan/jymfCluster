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
    	$("#admin-company").addClass("active");
        /** 新增按钮点击 */
        $("#addBtn").click(function() {
            $(location).attr('href', '${ctx}/admin/product/add');
        });
        
        /** 返回按钮点击 */
        $("#backBtn").click(function() {
            $(location).attr('href', '${ctx}/admin/company/main');
        });
        
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
<form:form id="fenye" name="fenye" action="${ctx}/admin/product/query" method="post">
    <div id="content_top">
        <table class="jymf_table" >
            <tr class="table_1_tr">
                <td>商户名称&nbsp;&nbsp;&nbsp;
                    <input name="name" type="text" class="texts" value="${product.name}"/>
                </td>
                <td><div style="margin-left: 50px;">&nbsp;</div></td>
                <td>商户ID&nbsp;&nbsp;&nbsp;
                    <input name="id" type="text" class="texts" value="${product.id}"/>
                </td>
                <td><input class="center_btn cx_btn" type="submit" value="查 询"/></td>
                <td><input class="center_btn cx_btn" id ="addBtn" type="button" value="新 增"/></td>
                <td><input class="center_btn cx_btn" id ="backBtn" type="button" value="返 回" /></td>
            </tr>
        </table>
    </div> 
    
    <table id="table_1" class="jymf_table table_border">
        <tr id="table_1_titlebar">
            <td class="jymf_td row_6" rowspan="1" colspan="1">名称</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">ID</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">地址</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">描述</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">激活数量</td>
            <td class="jymf_td row_6" rowspan="1" colspan="1">创建时间</td>
            <td class="jymf_td row_6" rowspan="1" colspan="1">最终更新时间</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">状态</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">操作</td>
        </tr>
        <c:forEach var="product" items="${pageView.records}">
        <tr class="jymf_tr table_1_tr">
            <td>${product.name}</td>
            <td>${product.id}</td>
            <td>
                <div class="td_name_1">
                    <a title="${product.origin}">${product.origin}</a>
                </div>
            </td>
            <td>
                <div class="td_name_1">
                    <a title="${product.description}">${product.description}</a>
                </div>
            </td>
            <td>${product.labelCnt}</td>
            <td><fmt:formatDate value="${product.createDate}" type="both" pattern="yyyy/MM/dd"/></td>
            <td><fmt:formatDate value="${product.updateDate}" type="both" pattern="yyyy/MM/dd"/></td>
            <td>
                <c:if test="${product.status ne '1'}">未审核</c:if>
                <c:if test="${product.status eq '1'}">审核通过</c:if>
            </td>
            <td  align="center">
                <c:if test="${product.status ne '1'}">
                    <div class="small_hong_btn">
                        <a class="linke" href="${ctx}/admin/product/update/${product.id}" id="editLink-${product.id}">审核</a>
                    </div>
                </c:if>
                <c:if test="${product.status eq '1'}">
                    <div class="small_btn">
                        <a class="linke" href="${ctx}/admin/product/update/${product.id}" id="editLink-${product.id}">修改</a>
                    </div>
                </c:if>
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
    <div><%@include file="../../../common/webfenye.jsp" %></div>
</form:form>
</body>
</html>