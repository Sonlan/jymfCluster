<%@page import="ch.qos.logback.core.Context"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="org.jymf.utils.Constants" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit"> 
<link type="text/css" href="${ctx}/static/css/bottom.css" rel="stylesheet" />
<link href="${ctx}/static/css/company.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="${ctx}/static/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/static/js/popup_layer.js"></script>
<style type="text/css">

</style>
<script type="text/javascript">
$(document).ready(function(){
	$("#addBtn").click(function() {
        $(location).attr('href', '${ctx}/company/trace/toAdd');
    });
    $("#company-cultivation").addClass("active");
    /** 表单验证    */
    $("#fenye").validate({
        rules: {
        	startID: {
            	digits:true
            },
            endID: {
            	digits:true
            }
        }
    });
}); 
</script>
</head>
<body>
<form:form id="fenye" name="fenye" modelAttribute="cordycepsLogistic" action="${ctx}/company/trace/main" method="post">
    <div id="content_top">
        <table class="jymf_table">
            <tr class="table_1_tr">
                <td>产品名称&nbsp;&nbsp;&nbsp;
                    <input name="proName" type="text" class="texts"/>
                </td>
                <td>产品批号&nbsp;&nbsp;&nbsp;
                    <input name="proBatchId" type="text" class="texts" />
                </td>
                <td>追溯标签&nbsp;&nbsp;&nbsp;
                    <input name="startID" type="text" class="texts"/>
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
        	<td class="jymf_td row_1" rowspan="1" colspan="1">开始标签</td>
            <td class="jymf_td row_3" rowspan="1" colspan="1">结束标签</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">产品名称</td>
            <td class="jymf_td row_3" rowspan="1" colspan="1">产品批号</td>
            <td class="jymf_td row_5" rowspan="1" colspan="1">原料用量</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">内包时间</td>
            <td class="jymf_td row_3" rowspan="1" colspan="1">内包使用仪器</td>
            <td class="jymf_td row_5" rowspan="1" colspan="1">成品批号</td>
            <c:if test="${companyuser.authority eq '1'}">
            	<td class="jymf_td row_5" rowspan="1" colspan="1">操作</td>
            </c:if>
        </tr>
        <c:forEach var="cordycepsLogistic" items="${pageView.records}">
        <tr class="jymf_tr table_1_tr">
        	<td>${cordycepsLogistic.startID}</td>
            <td>${cordycepsLogistic.endID}</td>
            <td>${cordycepsLogistic.proName}</td>
            <td>${cordycepsLogistic.proBatchId}</td>
            <td>${cordycepsLogistic.materialDosage}</td>
            <td>${cordycepsLogistic.inPacTime}</td>
            <td>${cordycepsLogistic.inPacInstrument}</td>
            <td>${cordycepsLogistic.resBatchId}</td>
            <c:if test="${companyuser.authority eq '1'}">
	            <td align="center">
	                <div class="small_btn">
	                    <a class="linke" href="${ctx}/company/trace/update/${cordycepsLogistic.id}" id="editLink-${cordycepsLogistic.id}">修改</a>
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