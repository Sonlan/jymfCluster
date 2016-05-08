<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<link href="${ctx}/static/css/company.css" type="text/css" rel="stylesheet"/>
<script>
$(document).ready(function(){
    /** 新增按钮点击 */
	$("#addBtn").click(function() {
		$(location).attr('href', '${ctx}/admin/company/add');
	});
	      
	$("#admin-company").addClass("active");
	
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
    <form:form id="fenye" name="fenye" modelAttribute="relation" action="${ctx}/admin/relation/query" method="post">
        <div id="content_top">
		    <table class="jymf_table" >
		        <tr class="table_1_tr">
			        <td>企业名称&nbsp;&nbsp;&nbsp;
			            <input name="name" type="text" class="texts" value="${relation.name}"/>
			        </td>
			        <td><div style="margin-left: 50px;">&nbsp;</div></td>
				    <td><input class="center_btn cx_btn" type="submit" value="查 询"/></td>
			    </tr>
			</table>
		</div> 
		
		<table id="table_1" class="jymf_table table_border">
			<tr id="table_1_titlebar">
				<td class="jymf_td row_3" rowspan="1" colspan="1">企业名称</td>
				<td class="jymf_td row_1" rowspan="1" colspan="1">组织代码</td>
				<td class="jymf_td row_1" rowspan="1" colspan="1">当前状态</td>
				<td class="jymf_td row_1" rowspan="1" colspan="1">操作</td>
			</tr>
            <c:forEach var="relation" items="${pageView.records}">
            <tr class="jymf_tr table_1_tr">
                <td class="jymf_td row_3">
                    <div class="td_name"><a title="${relation.name}">${relation.name}</a></div>
                </td>
                <td>${relation.monitorId}</td>
                <td>
             		<c:if test="${relation.status eq '0'}">正常</c:if>
                    <c:if test="${relation.status eq '1'}">挂起</c:if>
                </td>
			
                <td class="row_5">
                	<c:if test="${relation.status eq '0'}"> 
                    	<div class="small_btn">
                    		<a class="linke" href="${ctx}/admin/relation/updateStatus1/?id=${relation.id}" id="editLink-${relation.id}">挂起</a>
                    	 </div>
                    </c:if>
                    <c:if test="${relation.status eq '1'}">
                    	<div class="small_hong__btn">
                    		<a class="linke" href="${ctx}/admin/relation/updateStatus0/?id=${relation.id}" id="editLink-${relation.id}">启用</a>
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
		              
					</tr> 
				</c:forEach> 
			</c:if>
		</table>
		<div><%@include file="../../common/webfenye.jsp" %></div>
	</form:form>
</body>
</html>