<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<script type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
<script>
    $().ready(function() {
        $("#company-salescnt").addClass("active");
               
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
<form:form id="fenye" name="fenye" action="${ctx}/company/count/salesCntQuery" method="post">
    <div id="content_top">
        <table class="jymf_table" >
            <tr class="table_1_tr">
                <td>产品名称&nbsp;&nbsp;&nbsp;
                    <input name="name" type="text" class="texts" value="${product.name}"/>
                </td>
                <td><div style="margin-left: 20px;">&nbsp;</div></td>
                <td>开始日期&nbsp;&nbsp;&nbsp;
                    <input name="startDate" type="text" class="Wdate" 
                    onclick="WdatePicker()" value="${product.startDate}" 
                    style="width:190px;height:25px;"/>
                <td>     
                <td>结束日期&nbsp;&nbsp;&nbsp;
                    <input name="endDate" type="text" class="Wdate" 
                    onclick="WdatePicker()" value="${product.endDate}"
                     style="width:190px;height:25px;"/>
                <td>   
                <td><input class="center_btn cx_btn" type="submit" value="查 询"/></td>
            </tr>
        </table>
    </div> 
    
    <table id="table_1" class="jymf_table table_border">
        <tr id="table_1_titlebar">
            <td class="jymf_td row_1" rowspan="1" colspan="1">产品ID</td>
            <td class="jymf_td row_6" rowspan="1" colspan="1">产品名称</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">生产商</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">车型</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">销售数量</td>
            <td class="jymf_td row_1" rowspan="1" colspan="1">查询数量</td>
        </tr>
        <c:forEach var="product" items="${pageView.records}">
        <tr class="jymf_tr table_1_tr">
            <td>${product.id}</td>
            <td>${product.name} ${product.numberId}</td>
            <td><a title="${product.producer}">${product.producer}</a></td>
            <td><a title="${product.cartype}">${product.cartype}</a></td>
            <c:if test="${product.labelCnt ne '0'}">
                <td>
                    <a style="text-decoration:underline;" 
                       href="${ctx}/company/product/queryopt/${product.id}" 
                       id="editLink-${product.companyId}">&nbsp;&nbsp;${product.labelCnt}&nbsp;&nbsp;</a>
                </td>
            </c:if>
            <c:if test="${product.labelCnt eq '0'}">
                <td><a title="${product.labelCnt}">${product.labelCnt}</a></td>
            </c:if>
            <c:if test="${product.consNum eq ''}">
                <td>${product.consNum}</td>
            </c:if>
            <c:if test="${product.consNum ne ''}">
                <td>0</td>
            </c:if>
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
                </tr> 
            </c:forEach> 
        </c:if>
    </table>
    <div><%@include file="../../../common/webfenye.jsp" %></div>
</form:form>
</body>
</html>