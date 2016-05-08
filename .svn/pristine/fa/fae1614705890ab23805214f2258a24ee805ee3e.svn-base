<%@page import="ch.qos.logback.core.Context"%>
<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${ctx}/static/css/zhezhao.css" type="text/css"
	rel="stylesheet" />
<title>代理商更新页面</title>
<script>
	$(document).ready(function() {
		$("#admin-agent").addClass("active");

		/** 表单验证    */
		$("#updateform").validate({
			rules : {
				name : {
					required : true
				},
				tel : {
					isPhone : true
				},
				account : {
					required : true
				}
			}
		});
	});
</script>

<script>
	//解决火狐浏览器不支持innerText属性的方法
	function isIE() { //ie? 
		if (window.navigator.userAgent.toLowerCase().indexOf("msie") >= 1)
			return true;
		else
			return false;
	}

	if (!isIE()) { //firefox innerText define
		HTMLElement.prototype.__defineGetter__("innerText", function() {
			var anyString = "";
			var childS = this.childNodes;
			for (var i = 0; i < childS.length; i++) {
				if (childS[i].nodeType == 1)
					anyString += childS[i].innerText;
				else if (childS[i].nodeType == 3)
					anyString += childS[i].nodeValue;
			}
			return anyString;
		});
		HTMLElement.prototype.__defineSetter__("innerText", function(sText) {
			this.textContent = sText;
		});
	}

	//显示遮罩层
	function showdiv() {
		document.getElementById("bg").style.display = "block";
		document.getElementById("showCompany").style.display = "block";
		document.getElementById("ifm_ID").src = "${ctx}/admin/dialog/agentQueryUpdate/${agent.id}";
	}
	//隐藏遮罩层
	function hidediv() {
		document.getElementById("bg").style.display = 'none';
		document.getElementById("showCompany").style.display = 'none';
	}

	function addtr(ids, names) {
		//获取table的行数
		var rowcount = document.getElementById("table_1").rows.length;
		var cnt = 1;
		//循环table的每一行的第一列值
		for (var i = 0; i < rowcount; i++) {
			var trtd = document.getElementById("table_1").rows[i].cells[0].innerText;
			//判断文本框的值是否重复
			if (trtd == ids) {
				cnt = -1;
				alert('该企业已选择,请重新选择!');
			}
		}
		if (cnt != -1) {
			$str = '';
			$str += "<tr align='center'>";
			$str += "<td><input type='hidden' id='companyId' class='text' name='companyId' value="+ids+">"
					+ ids + "</td>";
			$str += "<td><input type='hidden' id='companyName' class='text' name='companyName' value="+names+">"
					+ names + "</td>";
			$str += "<td onClick='getDel(this)'><a href='#'>删除</a></td>";
			$str += "</tr>";
			$("#addTr").append($str);
		}
	}

	//删除tr
	function getDel(k) {
		var showMsg = "确定要删除该记录?";
		if (!confirm(showMsg))
			return;
		$(k).parent().remove();
	}
</script>
</head>
<body>
	<div style="margin-left: 200px; margin-top: 50px;">
		<form:form id="updateform" modelAttribute="agent"
			       action="${ctx}/admin/agent/update?monitorIds=${monitor.id}" method="post">
			<table border="0px" cellpadding="0" cellspacing="0">
				<tr>
					<td>部&nbsp;门&nbsp;名&nbsp;称
					    <input class="text" name="name" type="text" value="${agent.name}" maxlength="80"/>
					    <span class="must">*</span>
					</td>
					<td>
					    <span style="margin-left: 150px;">部&nbsp;门&nbsp;编&nbsp;号 
                        	<input class="text readonly" name="id" type="text" value="${agent.id}" readonly="readonly" />
                    	</span>
                    </td>
				</tr>
				<tr>
					<td>联&nbsp;系&nbsp;电&nbsp;话 
					    <input class="text" name="tel" type="text" value="${agent.tel}" maxlength="16"/> 
					    <span class="must">*</span>
					</td>
					<td>
						<span style="margin-left: 150px;">管&nbsp;理&nbsp;用&nbsp;户 
					    <input class="text readonly" type="text" name="account" value="${agent.account}" readonly="readonly" />
					    </span>
					</td>
				</tr>
				<tr>
					<td>部&nbsp;门&nbsp;状&nbsp;态 
					    <form:select path="status" class="select" items="${statusMap}" value="${agent.status}" />
					</td>
					<td>
						<span style="margin-left: 150px;">管&nbsp;理&nbsp;企&nbsp;业&nbsp;&nbsp;
					     <input type="button" class="center_btn" onclick="showdiv();" value="添加企业" />
					    </span>
					</td>
				</tr>
			</table>
			
			<table id="table_1" style="margin-top: 20px;"
				class="jymf_table table_border">
				<thead style="text-align: center">
					<tr>
						<td>企业编号</td>
						<td>企业名称</td>
						<td>用户操作</td>
					</tr>
				</thead>
				<tbody id="addTr">
				</tbody>
			</table>
			<div style="margin-top: 30px; margin-left: 30%">
				<table>
					<tr>
						<td>
						    <input id="addBtn" class="ok_button" type="submit" value=" " />
						</td>
						<td>
						    <input class="cancer_btn" type="button" value=""
						           onclick="javascript:window.location.href='javascript:history.go(-1)'" />
						</td>
					</tr>
				</table>
			</div>
		</form:form>
	</div>

	<!-- 遮罩层 -->
	<div id="bg" class="bg" style="display: none"></div>
	<div id="showCompany" class="showCompany" style="display: none">
		<div class="show_top">
			<input type="button" onclick="hidediv();" value="" />
		</div>
		<iframe id="ifm_ID" width="100%" height="85%" frameborder="no" scrolling="no"></iframe>
	</div>

</body>
</html>