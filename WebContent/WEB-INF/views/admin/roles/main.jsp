<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<link href="${ctx}/static/css/company.css" type="text/css" rel="stylesheet"/>
<script>
$(document).ready(function(){
    /** 新增按钮点击 */
	$("#addBtn").click(function() {
		$(location).attr('href', '${ctx}/admin/roles/add');
	});
	      
	$("#admin-roles").addClass("active");
	
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
    <form:form id="fenye" name="fenye" modelAttribute="admin" action="${ctx}/admin/roles/main" method="post">
        <div id="content_top">
		    <table class="jymf_table" >
		        <tr class="table_1_tr">
			        <td>用户姓名&nbsp;&nbsp;&nbsp;
			            <input name="name" type="text" class="texts" value="${admin.name}"/>
			        </td>
				    <td><input class="center_btn cx_btn" type="submit" value="查 询"/></td>
				    <td><input class="center_btn" id ="addBtn" type="button" value="新 增"/></td>
			    </tr>
			</table>
		</div> 
		
		<table id="table_1" class="jymf_table table_border">
			<tr id="table_1_titlebar">
				<td class="jymf_td row_5" >用户ID</td>
				<td class="jymf_td row_4" >用户帐号</td>
				<td class="jymf_td row_3" >姓名</td>
				<td class="jymf_td row_3" >电话</td>
				<td class="jymf_td row_3" >邮箱</td>
				<td class="jymf_td row_3" >角色</td>
				<td class="jymf_td row_3" >状态</td>
				<td class="jymf_td row_5" >修改</td>
				<td class="jymf_td row_5" >密码初始化</td>
			</tr>
            <c:forEach var="user" items="${pageView.records}">
            <tr class="jymf_tr table_1_tr">
                <td>${user.id}</td>
                <td>${user.account}</td>
                <td>${user.name}</td>
				<td>${user.telephone}</td>
                <td>${user.mail}</td>
                <td>
                	<c:if test="${user.role==0}">超级管理员</c:if>
                	<c:if test="${user.role==1}">高级管理员</c:if>
                	<c:if test="${user.role==2}">普通管理员</c:if>
                </td>
                <td>
                	<c:if test="${user.status==0}">正常</c:if>
                	<c:if test="${user.status==1}">禁用</c:if>
                </td>
                <td>
                    <div class="small_btn">
                        <a class="linke" href="${ctx}/admin/roles/edit/${user.id}" id="editLink-">修改资料</a>
                    </div>
                </td>
                <td>
                    <div class="small_btn">
                        <a class="linke" href="${ctx}/admin/roles/initPwd/${user.id}" id="editLink-">初始化</a>
                    </div>
                </td>
            </tr>
            </c:forEach>
            <c:if test="${pageView.pageNum < pageView.pageSize}">
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
		<div><%@include file="../../common/webfenye.jsp" %></div>
	</form:form>
</body>
</html>