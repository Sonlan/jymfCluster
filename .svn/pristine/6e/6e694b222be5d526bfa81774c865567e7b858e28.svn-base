<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="top">
	<div class="top_left"></div>
</div>

<div class="top_one">
	<div class="top_div">
		<a id="admin-welcome" href="${ctx}/admin/main/">首页</a>
		<a id="admin-company" href="${ctx}/admin/company/main/">企业信息</a>
		<a id="admin-monitor" href="${ctx}/admin/monitor/main/">监管部门</a> 
		<a id="admin-equipment" href="${ctx}/admin/equipment/main/">设备管理</a>
		<a id="admin-agent" href="${ctx}/admin/agent/main/">代理商</a>
		<a id="admin-label" href="${ctx}/admin/labelIndex/main/">标签卷</a>
		<a id="admin-logs" href="${ctx}/admin/logs/main/">日志</a>
		<c:if test="${ sessionScope.adminuser.role == 0 }">
			<a id="admin-roles" href="${ctx}/admin/roles/main/">用户管理</a>
		</c:if>
		<c:if test="${ sessionScope.adminuser.role > 0 }">
            <a id="admin-roles" href="${ctx}/admin/roles/updpwd/">修改密码</a>
        </c:if>
	</div>
</div>

