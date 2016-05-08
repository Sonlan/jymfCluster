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
    <form:form id="fenye" name="fenye" modelAttribute="company" action="${ctx}/admin/company/query" method="post">
        <div id="content_top">
		    <table class="jymf_table" >
		        <tr class="table_1_tr">
			        <td>企业名称&nbsp;
			            <input name="name" type="text" style="width:120px;height:25px;" class="texts" value="${company.name}"/>
			        </td>
			        <td>
				    <td>企业ID&nbsp;
				        <input name="id" type="text" style="width:120px;height:25px;" class="texts" value="${company.id}"/>
				    </td>
			        <td>代理商&nbsp;
			            <select name="agentId" style="width:120px;height:32px;">
			            		<option value="0">请选择</option>
			            	<c:forEach items="${agents}" var="agent">
			            		<option value="${agent.id}" <c:if test="${agent.id==company.agentId}">selected="selected"</c:if>>${agent.name}</option>	
			            	</c:forEach>
			            	
			            </select>
			        </td>
				    <td>企业模式&nbsp;
				    	<select name="workMode" style="width:120px;height:32px;">
				    		<option value="-1">请选择</option>
				    		<c:forEach items="${workModelMap}" var ="map">
				    			<option value="${map.key}" <c:if test="${map.key==company.workMode}">selected="selected"</c:if>>${map.value} </option>
				    		</c:forEach>
				    	</select>
				    </td>
				    <td><input class="center_btn cx_btn" type="submit" value="查 询"/></td>
				    <td><input class="center_btn" id ="addBtn" type="button" value="新 增"/></td>
			    </tr>
			</table>
		</div> 
		
		<table id="table_1" class="jymf_table table_border">
			<tr id="table_1_titlebar">
				<td class="jymf_td row_3" >企业名称</td>
				<td class="jymf_td row_4" >企业代码</td>
				<td class="jymf_td row_17" >当前状态</td>
				<td class="jymf_td row_4" >工作模式</td>
				<td class="jymf_td row_4" >代理商</td>
				<td class="jymf_td row_16" >产品维护</td>
				<td class="jymf_td row_5" >统计</td>
				<td class="jymf_td row_5" >操作</td>
			</tr>
            <c:forEach var="company" items="${pageView.records}">
            <tr class="jymf_tr table_1_tr">
                <td >
                	<div >
                    	<a title="${company.name}">${company.name}</a>
                	</div>
                </td>
                <td>${company.id}</td>
                <td>
             		<c:if test="${company.status eq '0'}">正常</c:if>
                    <c:if test="${company.status eq '1'}">挂起</c:if>
                </td>
				<td >
					<c:set var ="mode" value="${company.workMode}"/>
             		${workModelMap[fn:trim(mode)]}
                </td>
				<td >
					${company.agentName}
                </td>
                <td align="center">
                    <div class="small_btn">
                        <a class="linke" href="${ctx}/admin/company/product/${company.id}/${company.workMode}" 
                           id="editLink-${company.id}">产品详细</a>
                    </div>
                </td>
                <td align="center">
                    <div class="small_btn">
                        <a class="linke" href="${ctx}/admin/company/count/${company.id}/${company.workMode}" id="editLink-${company.id}">统计</a>
                    </div>
                </td>
                <td align="center">
                    <div class="small_btn">
                        <a class="linke" href="${ctx}/admin/company/update/${company.id}" id="editLink-${company.id}">修改</a>
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