<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="top">
	<div class="top_left"></div>
</div>

<div class="top_one">
	<div class="top_div">
		<a id="monitor-company" href="${ctx}/monitor/company/main/">首页</a>
		<a id="monitor-product" href="${ctx}/monitor/company/product">产品列表</a>
		<c:if test="${companyMonitors.monitorMode == 3}">
			<a id="monitor-salescnt" href="${ctx}/monitor/company/salescnt">销售统计</a>
			<a id="monitor-document" href="${ctx}/monitor/company/document">维修合同</a>
		</c:if>
		<a id="monitor-back" href="${ctx}/monitor/company/back">返回企业</a>
	</div>
</div>

