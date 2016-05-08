<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>金源茂丰>>企业选择</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="${ctx}/static/css/bottom.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/css/top.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/css/company.css" type="text/css" rel="stylesheet"/>
<link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet"/>
<script src="${ctx}/static/js/jymf.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/messages_cn.js" type="text/javascript"></script>
<script>
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

function setProductview(productId,name){
	//给父窗体的动态文本框赋值
    parent.setProduct(productId,name);
    parent.hidediv();
}
//关闭弹出层
function hidebtn(){
    //关闭窗体
    parent.hidediv();
}
</script>
</head>

<body>
<form:form id="fenye" name="fenye" modelAttribute="product" action="${ctx}/company/product/selectProduct" method="post">
     <div id="content_top">
        <table class="jymf_table">
            <tr class="table_1_tr">
                <td>商户名称&nbsp;<input name="name" type="text"/></td>
                <td>商户ID&nbsp;<input name="id" type="text"/></td>
                <td><input class="center_btn cx_btn" type="submit" value="查 询"/></td>
                <td><input class="center_btn cx_btn" type="button" value="返 回" onclick="hidebtn()"/></td>
            </tr>
        </table>
    </div> 
    
    <table id="table_1" class="jymf_table table_border">
        <tr id="table_1_titlebar">
            <td class="jymf_td row_3" rowspan="1" colspan="1">商户名称</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">商户代码</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">操作</td>
        </tr>
        <c:forEach var="product" items="${pageView.records}">
        <tr class="jymf_tr table_1_tr">
            <td class="jymf_td row_3" >
           		<div class="td_name_1">
                    <a title="${product.name}">${product.name}</a>
                </div>
            </td>
            <td>${product.id}</td>
            <td>
                <a href="javascript:setProductview('${product.id}','${product.name}');">选择</a>
            </td>
        </tr>
        </c:forEach>
        <c:if test="${pageView.pageNum < pageView.pageSize }">
        <c:forEach begin="${pageView.pageNum}" end="${pageView.pageSize -1 }" step="1"> 
        <tr class="jymf_tr table_1_tr">
            <td>&nbsp;</td>
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