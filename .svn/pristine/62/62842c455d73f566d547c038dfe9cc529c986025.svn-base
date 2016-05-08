<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link type="text/css" href="${ctx}/static/css/bottom.css" rel="stylesheet" />
<link href="${ctx}/static/css/company.css" type="text/css" rel="stylesheet"/>
<link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet"/>
<script src="${ctx}/static/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/messages_cn.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#admin-company").addClass("active");
    /** 表单验证    */
    $("#myform").validate({
        rules: {
        	typeName: {
        		required:true,
        		remote : "${ctx}/admin/productType/checkProductTypeRepeat"
            }
        },
        messages : {
        	typeName : {
				remote : "名称重复!"
			}
		} 
    });
});
 
 function doSubmit(){
	 $("#myform").submit();
 }

</script>
</head>
<body>
<form:form id="myform" action="${ctx}/admin/productType/add" modelAttribute="productType" method="post">
    <div style="text-align: center;">
    
    	<div  style="margin: 50px 50px 50px 430px;font-size: 15px;" align="left">
    		${company.name} &nbsp;产品类型新增：
    	</div>
        <div style="margin: 50px 50px 50px 50px;">&nbsp;名称&nbsp;&nbsp;
        	<input style="width:350px;height: 20px" type="text" id="typeName" name="typeName" value=""/>
        	<span class="must">*</span> 
        </div>

        <input class="center_btn" type="button" value="取消" onclick="history.back();"/>
        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
        <input class="center_btn" type="submit" value="确定" onclick="doSubmit();"/>
    </div>
</form:form>
</body>
</html>