<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        $(location).attr('href', '${ctx}/admin/equipment/add');
    });
    
    $("#admin-equipment").addClass("active");
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
<form:form id="fenye" name="fenye" modelAttribute="equipment" action="${ctx}/admin/equipment/query" method="post">
    <div id="content_top">
        <table class="jymf_table">
            <tr class="table_1_tr">
                <td>企业名称&nbsp;&nbsp;&nbsp;
                    <input name="name" type="text" class="texts" value="${equipment.name}"/>
                </td>
                <td><div style="margin-left: 50px;">&nbsp;</div></td>
                <td>设备号&nbsp;&nbsp;&nbsp;
                    <input name="id" type="text" class="texts" value="${equipment.id}"/>
                </td>
                <td><input class="center_btn cx_btn" type="submit" value="查 询"/></td>
                <td><input class="center_btn" id ="addBtn" type="button" value="新 增"/></td>
            </tr>
        </table>
    </div> 
    <table id="table_1" class="jymf_table table_border">
        <tr id="table_1_titlebar">
            <td class="jymf_td row_1" rowspan="1" colspan="1">设备号</td>
            <td class="jymf_td row_3" rowspan="1" colspan="1">所属企业</td>
            <td class="jymf_td row_2" rowspan="1" colspan="1">企业ID</td>
            <td class="jymf_td row_8" rowspan="1" colspan="1">商户名称</td>
            <td class="jymf_td row_4" rowspan="1" colspan="1">商户ID</td>
            <td class="jymf_td row_7" rowspan="1" colspan="1">追溯模式</td>
            <td class="jymf_td row_4" rowspan="1" colspan="1">当前状态</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">操作</td>
        </tr>
        <c:forEach var="equip" items="${pageView.records}">
        <tr class="jymf_tr table_1_tr">
            <td>${equip.id}</td>
            <td class="row_3">
           		<div class="td_name_1">
                    <a title="${equip.name}">${equip.name}</a>
                </div>
            </td>
            <td>${equip.companyId}</td>
            <td>${equip.productName}</td>
            <td>${equip.productId}</td>
            <td>
               	<c:set var ="mode" value="${equip.workMode}"/>
				${workModelMap[fn:trim(mode)]}
            </td>
            <td>
                <c:if test="${equip.devStatus eq '0'}">挂起</c:if>
                <c:if test="${equip.devStatus eq '1'}">启用</c:if>
                <c:if test="${equip.devStatus eq '2'}">作废</c:if>
            </td>
            <td class="row_5" align="center">
                <div class="small_btn">
                    <a class="linke" href="${ctx}/admin/equipment/update/${equip.id}" id="editLink-${equip.id}">修改</a>
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
                </tr> 
            </c:forEach> 
        </c:if>
    </table>
    <div><%@include file="../../common/webfenye.jsp" %></div>
</form:form>
</body>
</html>