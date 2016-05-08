<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="top">
	<c:if test="${companyMonitors.proType == 1}">
  		<div class="top_ynqp_left"></div>
  	</c:if>
  	<c:if test="${companyMonitors.proType ne 1}">
  		<div class="top_left"></div>
  	</c:if>
</div>

<div class="top_one">
	<div class="top_div">
		<a id="monitor-welcome" href="${ctx}/monitor/main/">首页</a>
		<c:if test="${companyMonitors.monitorMode == 3}">
  		    <a id="monitor-carinfo" href="${ctx}/monitor/carinfo/">车辆信息查询</a>
		</c:if>
		<a id="monitor-editPassWord" href="${ctx}/monitor/editPassWord/">修改密码</a>
	</div>
</div>

