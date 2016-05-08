<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">

<script type="text/javascript">
$(document).ready(function(){ 
	$("#admin-company").addClass("active");
});
</script>
</head>

<body>
    <form:form id="fenye" name="fenye" modelAttribute="company" action="${ctx}/admin/productType/main" method="post">
        <div id="content_top">
		    <table class="jymf_table" >
		        <tr class="table_1_tr" align="left" style="font-size:14px;font-weight:  bold;">
			        <td >
			        	${company.name}&nbsp;
			        	<a href="${ctx}/admin/productType/child/0">产品类型</a>
			        	&nbsp;&nbsp;&nbsp;&gt;&gt;&nbsp;&nbsp;&nbsp;
			        	<c:if test="${currentType ne null && currentType ne ''}">	
			        		${currentType}
			        	</c:if>
			        	<c:if test="${currentType eq null || currentType eq ''}">	
			        		产品总类
			        	</c:if>
			        		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			        </td>
				    <td>
				    	<input class="center_btn" id ="addBtn" type="button" value="新 增" onclick="location.href='${ctx}/admin/productType/add'"/>
						<input class="center_btn" type="button" value="返 回" onclick="location.href='${ctx}/admin/productType/child/0'"/>
					</td>
			    </tr>
			</table>
		</div> 
		
		<table id="table_1" class="jymf_table table_border">
			<tr id="table_1_titlebar">
				<td class="jymf_td row_17" >产品类型ID</td>
				<td class="jymf_td row_3" >产品类型名称</td>
				<td class="jymf_td row_3" >子类型个数</td>
				<td class="jymf_td row_3" >修改</td>
				<td class="jymf_td row_3" >管理子类</td>
				
			</tr>
           <c:forEach var="list" items="${pageView.records}">
           		<tr class="jymf_tr table_1_tr">
           			<td>${list.id}</td>
           			<td>${list.typeName}</td>
           			<td>
           				<c:if test="${list.childTypeCount>0}">
           					<a href="${ctx}/admin/productType/child/${list.id}">
           						共 <span style="font-weight: bold;">${list.childTypeCount}</span> 个子类
           					</a>
           				</c:if>
           				<c:if test="${list.childTypeCount<=0}">
           					共 <span style="font-weight: bold;"> ${list.childTypeCount}</span> 个子类
           				</c:if>
           				
           			</td>
           			<td align="center">
           				<div class="small_btn" >
	                        <a class="linke" href="${ctx}/admin/productType/edit/${list.id}"  
	                           id="editLink-${list.id}">修 改</a>
                    	</div>
           			</td>
           			<td align="center">
           				<div class="small_btn" >
	                        <a class="linke" href="${ctx}/admin/productType/child/${list.id}" 
	                           id="addChild-${list.id}" >管理子类</a>
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
					</tr> 
				</c:forEach> 
			</c:if>
		</table>
		<input id="companyId" type="hidden" value="${company.id}"/>		
		<div><%@include file="../../common/webfenye.jsp" %></div>
	</form:form>
</body>

	

</html>