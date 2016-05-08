<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta name="renderer" content="webkit"> 
<script>
$(document).ready(function(){
    $("#company-equipment").addClass("active");
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
<form:form id="fenye" name="fenye" modelAttribute="equipment" action="${ctx}/company/equipment/query" method="post">
    <div id="content_top">
        <table class="jymf_table">
            <tr class="table_1_tr">
                <td>设备号&nbsp;&nbsp;&nbsp;
                    <input name="id" type="text" class="texts" value="${equipment.id}"/>
                </td>
                <td><input class="center_btn cx_btn" type="submit" value="查 询"/></td>
            </tr>
        </table>
    </div> 
    <table id="table_1" class="jymf_table table_border">
        <tr id="table_1_titlebar">
            <td class="jymf_td row_1" rowspan="1" colspan="1">设备号</td>
            <c:if test="${equipment.workMode eq 2 }">
	            <td class="jymf_td row_3" rowspan="1" colspan="1">商户名称</td>
	            <td class="jymf_td row_1" rowspan="1" colspan="1">商户ID</td>
            </c:if>
            <td class="jymf_td row_3" rowspan="1" colspan="1">领用信息</td>
            <td class="jymf_td row_5" rowspan="1" colspan="1">操作</td>
        </tr>
        <c:forEach var="equip" items="${pageView.records}">
        <tr class="jymf_tr table_1_tr">
            <td>${equip.id}</td>
            <c:if test="${equipment.workMode eq 2 }">
	            <td>${equip.productName}</td>
	            <td>${equip.productId}</td>
            </c:if>
            <td class="row_3">
                <div class="td_name_1">
                    <a title="${equip.devInfo}">${equip.devInfo}</a>
                </div>
            </td>
            <td align="center">
                <div class="small_btn">
                    <a class="linke" href="${ctx}/company/equipment/update/${equip.id}" id="editLink-${equip.id}">修改</a>
                </div>
            </td>
        </tr>
        </c:forEach>
        <c:if test="${pageView.pageNum < pageView.pageSize }">
            <c:forEach begin="${pageView.pageNum}" end="${pageView.pageSize -1 }" step="1"> 
                <tr class="jymf_tr table_1_tr">
                    <td>&nbsp;</td>
                    <c:if test="${equipment.workMode eq 2 }">
	                    <td></td>
	                    <td></td>
                    </c:if>
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