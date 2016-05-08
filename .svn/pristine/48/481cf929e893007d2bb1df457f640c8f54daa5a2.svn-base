<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta name="renderer" content="webkit"> 
<script type="text/javascript">
$(document).ready(function(){
	$("#addBtn").click(function() {
        $(location).attr('href', '${ctx}/company/cultivation/toAdd');
    });
    $("#company-cultivation").addClass("active");
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
<form:form id="fenye" name="fenye" modelAttribute="cordyceps" action="${ctx}/company/cultivation/main" method="post">
    <div id="content_top">
        <table class="jymf_table">
            <tr class="table_1_tr">
                <td>原料批号&nbsp;&nbsp;&nbsp;
                    <input name="materialBatchId" type="text" class="texts" />
                </td>
                <td>前处理负责人&nbsp;&nbsp;&nbsp;
                    <input name="formerLeader" type="text" class="texts"/>
                </td>
                <td>培育车间负责人&nbsp;&nbsp;&nbsp;
                    <input name="cultivateLeader" type="text" class="texts"/>
                </td>
                <td>后处理负责人&nbsp;&nbsp;&nbsp;
                    <input name="postProcLeader" type="text" class="texts"/>
                </td>
                <td><input class="center_btn cx_btn" type="submit" value="查 询"/></td>
                <c:if test="${companyuser.authority eq '1'}">
                	<td><input class="center_btn cx_btn" id="addBtn" value="新增"/></td>
                </c:if>
            </tr>
        </table>
    </div> 
    <table id="table_1" class="jymf_table table_border">
        <tr id="table_1_titlebar">
            <td class="jymf_td row_1" rowspan="1" colspan="1">原料批号</td>
            <td class="jymf_td row_3" rowspan="1" colspan="1">前处理负责人</td>
            <td class="jymf_td row_5" rowspan="1" colspan="1">原料小麦批号</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">小麦合格编号</td>
            <td class="jymf_td row_3" rowspan="1" colspan="1">培育车间负责人</td>
            <td class="jymf_td row_5" rowspan="1" colspan="1">接种时间</td>
            <td class="jymf_td row_3" rowspan="1" colspan="1">后处理负责人</td>
            <td class="jymf_td row_5" rowspan="1" colspan="1">子实体合格编号</td>
            <c:if test="${companyuser.authority eq '1'}">
            	<td class="jymf_td row_5" rowspan="1" colspan="1">操作</td>
            </c:if>
        </tr>
        <c:forEach var="cordyceps" items="${pageView.records}">
        <tr class="jymf_tr table_1_tr">
            <td>${cordyceps.materialBatchId}</td>
            <td>${cordyceps.formerLeader}</td>
            <td>${cordyceps.wheatBatchId}</td>
            <td>${cordyceps.wheatQualifiedId}</td>
            <td>${cordyceps.cultivateLeader}</td>
            <td>${cordyceps.vacTime}</td>
            <td>${cordyceps.postProcLeader}</td>
            <td>${cordyceps.childQualId}</td>
            <c:if test="${companyuser.authority eq '1'}">
	            <td align="center">
	                <div class="small_btn">
	                    <a class="linke" href="${ctx}/company/cultivation/update/${cordyceps.materialBatchId}" id="editLink-${cordyceps.materialBatchId}">修改</a>
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
                    <td></td>
                    <td></td>
                    <td></td>
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