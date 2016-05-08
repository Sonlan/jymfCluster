<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta name="renderer" content="webkit"> 
<script type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
<script>
$(document).ready(function(){
	$("#admin-logs").addClass("active");
});
</script>
</head>

<body>
    <form:form id="fenye" name="fenye" modelAttribute="logs" action="${ctx}/admin/logs/query" method="post">
        <div id="content_top">
		    <table class="jymf_table" >
		        <tr class="table_1_tr">
		            <td>用户ID&nbsp;
                        <input name="userId" class="texts" type="text" value="${logs.userId}"/>
                    </td>
			        <td>事件&nbsp;
			            <input name="event" class="texts" type="text" value="${logs.event}"/>
			        </td>
			        <td>开始日期&nbsp;
                        <input name="startDate" type="text" 
                        class="Wdate" onclick="WdatePicker()" 
                        value="${logs.startDate}" style="width:190px;height: 25px;"/>
                    <td>     
                    <td>结束日期&nbsp;
                        <input name="endDate" type="text" 
                        class="Wdate" onclick="WdatePicker()" 
                        value="${logs.endDate}" style="width:190px;height: 25px;"/>
                    <td>   
				    <td><input class="center_btn cx_btn" type="submit" value="查 询"/></td>
			    </tr>
			</table>
		</div> 
		
		<table id="table_1" class="jymf_table table_border">
			<tr id="table_1_titlebar">
				<td class="jymf_td row_1" rowspan="1" colspan="1">记录ID</td>
				<td class="jymf_td row_1" rowspan="1" colspan="1">用户ID</td>
				<td class="jymf_td row_1" rowspan="1" colspan="1">操作事件</td>
				<td class="jymf_td row_1" rowspan="1" colspan="1">描述信息</td>
				<td class="jymf_td row_1" rowspan="1" colspan="1">公司名称</td>
				<td class="jymf_td row_1" rowspan="1" colspan="1">时间</td>
			</tr>
            <c:forEach var="logs" items="${pageView.records}">
            <tr class="jymf_tr table_1_tr">
                <td>${logs.id}</td>
                <td>${logs.userId}</td>
                <td>${logs.event}</td>
				<td class="jymf_td row_3">
					<div class="td_name"><a title="${logs.description}">${logs.description}</a></div>
				</td>
				<td>${logs.companyName}</td>
                <td><fmt:formatDate value="${logs.createTime}" type="both" pattern="yyyy/MM/dd"/></td>
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
					</tr> 
				</c:forEach> 
			</c:if>
		</table>
		<div><%@include file="../../common/webfenye.jsp" %></div>
	</form:form>
</body>
</html>