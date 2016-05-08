<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%String id=request.getParameter("id");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>产品参数录入画面</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link type="text/css" href="${ctx}/static/css/bottom.css" rel="stylesheet" />
<link href="${ctx}/static/css/company.css" type="text/css" rel="stylesheet"/>
<link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet"/>
<script src="${ctx}/static/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/messages_cn.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/additional.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
    /** 表单验证    */
    $("#myform").validate({
        rules: {
        	name: {
        		required:true
            },
            value: {
        		required:true
            }
        }
    });
});

function validate(){
    //调用父窗体的方法
    parent.functionName();
    //添加行
    parent.add_tr('<%=id%>');
    //关闭窗体
    parent.hidediv();
    return true;
}

//关闭弹出层
function hidebtn(){
	//关闭窗体
    parent.hidediv();
}

function check(){
	var name  = $("#name").val();
    var value = $("#value").val();
    if(name!=null && name.trim()!="" && value!=null && value.trim()!="" ){
        validate();
    }
}
</script>
</head>
<body>
<form:form id="myform" name="myform">
    <div style="text-align: center;">
        <table style="margin:50px 0 50px 100px;">
        	<tr>
        	    <td style="width:50px;"></td>
                <td align="left">
                    <span class="must">为了更好的展示效果，名称最好在8个汉字之内</span>
                </td>
        	</tr>
        	<tr >
        		<td style="width:50px;">名称</td>
        		<td>
        			<input style="height: 20px;width:350px;" type="text" id="name" name="name"/>
        			<span class="must">*</span>
        		</td>
        	</tr>
        	<tr style="height:50px;">
        	    <td style="width:50px;"></td>
                <td align="left" style="vertical-align: bottom;">
                    <span class="must">为了更好的展示效果，内容最好在10个汉字之内</span>
                </td>
        	</tr>
        	<tr style="height:70px;">
        		<td class="proParamKey2">内容</td>
        		<td>
        			<textarea style="height: 60px;width:350px;font-size: 12px;"  name="value" id="value"></textarea>
        			<span class="must">*</span>
        		</td>
        	</tr>
        	
        </table>
        <input class="center_btn cx_btn" type="button" value="取消" onclick="hidebtn();"/>
        <input class="center_btn cx_btn" type="submit" value="确定" onclick="check()"/>
    </div>
</form:form>
</body>
</html>