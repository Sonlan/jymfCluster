<%@page import="ch.qos.logback.core.Context"%>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="org.jymf.utils.Constants" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${ctx}/static/css/zhezhao.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="${ctx}/static/js/GridEdit.js"></script>
<script type="text/javascript" src="${ctx}/static/js/tab.js"></script>
<script type="text/javascript" src="${ctx}/static/jquery-validation/1.11.1/additional.js"></script>
<script type="text/javascript" src="${ctx}/static/js/common.js"></script>
<script type="text/javascript">
$(document).ready(function(){ 
	$("#admin-company").addClass("active");
    
    /** 表单验证    */
    $("#addform").validate({
        rules: {
            name: {
                required:true
            },
            description: {
            	byteMaxLength:1300
            },
            url: {
                url:true
            },
            queryDes:{
                byteMaxLength:160
            }
        }
});
});

</script>
<title>商户添加 </title>

</head>
<body>
<div class="main_div" style="margin-left:200px;">
<form:form id="addform" modelAttribute="product" action="${ctx}/admin/product/add" method="post" 
           enctype ="multipart/form-data">
    <div class="add_top">
        <div class="head_describe">商户信息</div>
        <table border="0" cellpadding="0" cellspacing="0" >
            <tr>
                <td width="100px">商&nbsp;户&nbsp;名&nbsp;称&nbsp;</td>
                <td width="465px">
                    <input type="text" class="text" name="name" maxlength="80"/>
                    <span class="must">*</span> 
                </td>
                <td>商&nbsp;户&nbsp;地&nbsp;址</td>
                <td width="274px">
                    <input type="text" maxlength="80" class="text" name="origin"/>
                </td>
            </tr>
            <tr>
                <td>商&nbsp;户&nbsp;主&nbsp;页&nbsp;</td>
                <td colspan="3">
                    <input type="text" class="text" name="url" maxlength="100"/>
                </td>
            </tr>
            <tr>
                <td>商&nbsp;户&nbsp;描&nbsp;述&nbsp;&nbsp;</td>
                <td colspan="3"><textarea class="product_textare" name="description"></textarea></td>
            </tr>
            <tr>
                <td>查询结果描述</td>
                <td colspan="3">&nbsp;&nbsp;支持80个字，前缀：您所查询的是</td>
            </tr>
            <tr >
                <td></td>
                <td colspan="3"><textarea class="product_textare" name="queryDes"></textarea></td>
            </tr>
        </table>
    </div>
    
    <div class="add_center">
        <div class="head_describe">商户描述图片</div>
        <table>
            <tr>
               <c:forEach var="i" begin="1" end="3" step="1"> 
               <td class="jymf_td row_img" rowspan="1" colspan="1">
                    <span class="file-box">
                      <input type='button' class='btn' value='' />
                        <input id="imgFile${i}" name="imgFiles" type="button" class="file" onclick="changePicUrl(${i})"/>
                        <input name="del" type="button" class="del" value="" onclick="delPic(${i})"/>
                   		<input id="imgFile${i}Name" name="productImgs[${i}].fileName" type="hidden"/>
                    </span>
                </td>
                </c:forEach> 
            </tr>
            <tr>
               <c:forEach var="i" begin="1" end="3" step="1"> 
                   <td>
                       <img style="margin-top:25px;" id="img${i}" 
                            name="imgs" 
                            width="<%=Constants.PHOTO_IMG_WIDTH %>px" 
                            src=""
                        />
                   </td>
               </c:forEach> 
            </tr>
        </table>
    </div>
    
    <div class="add_btn">
        <table>
            <tr>
                <td><input class="ok_button" type="submit" value=" "/></td>
                <td>
                    <input class="cancer_btn" type="button" 
                           onclick="javascript:window.location.href='${ctx}/admin/product/main'"/>
                </td>
            </tr>    
        </table>
    </div>
    <input type="hidden" name="token" value="${token}" />
    </form:form>
</div>

	<!-- 遮罩层 -->
	<div id="bg" class="bg" style="display: none"></div>
	<%--图片剪裁遮罩层 --%>
	<div id="showPic" class="showPic" style="display: none;">
		<iframe src="${ctx}/common/pic" id="pic_iframe" style="width:100%;height:95%;border:0px;" scrolling="no"></iframe>   			
	</div>
</body>
</html>