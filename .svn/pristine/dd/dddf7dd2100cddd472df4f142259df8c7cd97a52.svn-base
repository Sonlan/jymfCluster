<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<link href="${ctx}/static/css/company.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
<script>
$().ready(function() {
    $("#agent-company").addClass("active");
});

$(document).ready(function(){
	      
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
    <form:form id="fenye" name="fenye" modelAttribute="company" action="${ctx}/agent/main" method="post">
        <div id="content_top">
		    <table class="jymf_table" >
		        <tr class="table_1_tr">
			        <td>企业名称&nbsp;&nbsp;&nbsp;
			            <input name="name" type="text" class="texts" value="${company.name}"/>
			        </td>
			        <td><div style="margin-left: 50px;">&nbsp;</div></td>
				    <td>企业ID&nbsp;&nbsp;&nbsp;
				        <input name="id" type="text" class="texts" value="${company.id}"/>
				    </td>
				    <td>开始日期&nbsp;&nbsp;&nbsp;
		                <input name="startDate" type="text" class="Wdate" onclick="WdatePicker()" 
		                       value="${company.startDate}" style="width:190px;height:25px;"/>
	                <td>     
	                <td>结束日期&nbsp;&nbsp;&nbsp;
	                    <input name="endDate" type="text" class="Wdate" onclick="WdatePicker()" 
	                           value="${company.endDate}" style="width:190px;height:25px;"/>
	                <td> 
				    <td><input class="center_btn cx_btn" type="submit" value="查 询"/></td>
			    </tr>
			</table>
		</div> 
		
		<table id="table_1" class="jymf_table table_border">
			<tr id="table_1_titlebar">
				<td class="jymf_td row_3" rowspan="1" colspan="1">企业名称</td>
				<td class="jymf_td row_1" rowspan="1" colspan="1">企业代码</td>
                <td class="jymf_td row_1" rowspan="1" colspan="1">包数量</td>
				<td class="jymf_td row_1" rowspan="1" colspan="1">标签使用量</td>
				<td class="jymf_td row_1" rowspan="1" colspan="1">当前状态</td>
				<td class="jymf_td row_1" rowspan="1" colspan="1">工作模式</td>
				<td class="jymf_td row_1" rowspan="1" colspan="1">产品详细</td>
                <td class="jymf_td row_1" rowspan="1" colspan="1">公司详细</td>
			</tr>
            <c:forEach var="company" items="${pageView.records}">
            <tr class="jymf_tr table_1_tr">
                <td class="jymf_td row_3">
                    <div class="td_name"><a title="${company.name}">${company.name}</a></div>
                </td>
                <td>${company.id}</td>
                <td>${company.packageCnt}</td>
                <td>${company.activeCnt}</td>
                <td>
             		<c:if test="${company.status eq '0'}">正常</c:if>
                    <c:if test="${company.status eq '1'}">挂起</c:if>
                </td>
				<td>
					<c:set var ="mode" value="${company.workMode}"/>
					${workModelMap[fn:trim(mode)]}
                </td>
                <td class="row_5"  align="center">
                    <c:if test="${company.workMode ne '1'}">
	                    <div class="small_btn">
	                        <a class="linke" href="${ctx}/agent/product/main/${company.id}" 
	                           id="editLink-${company.id}">产品详细</a>
	                    </div>
                    </c:if>
                </td>
                <td class="row_5"  align="center">
                    <div class="small_btn">
                        <a class="linke" href="${ctx}/agent/company/view/${company.id}" id="editLink-${company.id}">查看</a>
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
		<div><%@include file="../common/webfenye.jsp" %></div>
	</form:form>
</body>
</html>