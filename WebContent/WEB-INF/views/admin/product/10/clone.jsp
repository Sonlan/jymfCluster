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
<link href="${ctx}/static/tree/css/zTreeStyle/zTreeStyle.css" type="text/css" rel="stylesheet"/>
<link href="${ctx}/static/tree/css/tree.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="${ctx}/static/js/GridEdit.js"></script>
<script type="text/javascript" src="${ctx}/static/js/tab.js"></script>
<script type="text/javascript" src="${ctx}/static/jquery-validation/1.11.1/additional.js"></script>
<script type="text/javascript" src="${ctx}/static/js/common.js"></script>
<script type="text/javascript" src="${ctx}/static/tree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}/static/tree/js/jymf-tree.js"></script>
<script type="text/javascript">

//开启tree
var zNodes =eval('${productType}');
$(document).ready(function(){
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
});
	
$(document).ready(function(){
	$("#admin-company").addClass("active");
	
    /** 表单验证    */
    $("#editform").validate({
        rules: {
            name: {
                required:true
            },
            description: {
                byteMaxLength:1300
            },
            queryDes:{
                byteMaxLength:160
            }
        }
    });
});

}
 
</script>

<title>产品类型修改 </title>
</head>
<body>
<div class="main_div" style="margin-left:200px;">
<form:form id="editform" modelAttribute="product" action="${ctx}/admin/product/cloneAdd" method="post" 
           enctype ="multipart/form-data">
    <input type="hidden" name="id" value="${product.id}"/>
    <div class="add_top">
        <div class="head_describe">商品基本信息</div>
        <input id="companyName" type="hidden" name="productPara.gx[9].value" value="${companybase.name}"/>
        <table border="0" cellpadding="0" cellspacing="0" >
            <tr>
                <td width="100px">商品名称</td>
                <td width="465px">
                    <input type="text" class="text" name="name" maxlength="80" value="${product.name}"/>
                    <span class="must">*</span> 
                </td>
                <td>原产国家</td>
                <td width="274px">
                    <input type="text" maxlength="80" class="text" name="origin" value="${product.origin}"/>
                </td>
            </tr>
            <tr>
                <td>商品类型</td>
                <td colspan="3" align="left">
                    <input id="type" type="text" readonly value="${productTypeName}" class="text" name="productPara.gx[0].value"/>
                    <input id="typeValue" type="hidden" name="type" value="${product.type}"/>
                    <input id="menuBtn"  class="center_btn" type="button" onclick="showMenu(); return false;" value="选择"/>
                    <div id="menuContent" class="menuContent" style="display:none; position: absolute; z-index: 999999999999">
                        <ul id="treeDemo" class="ztree" style="margin-top:0; width:260px;"></ul>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="100px">容&nbsp;&nbsp;&nbsp;量&nbsp;</td>
                <td width="465px">
                    <input type="text" class="text" name="productPara.gx[1].value" value="${product.productPara.gx[1].value}"/>
                </td>
                <td>葡萄品种</td>
                <td width="274px">
                    <input type="text" class="text" name="productPara.gx[2].value" value="${product.productPara.gx[2].value}"/>
                </td>
            </tr>
            <tr>
                <td width="100px">酒精含量</td>
                <td width="465px">
                    <input type="text" class="text" name="productPara.gx[3].value" value="${product.productPara.gx[3].value}"/>
                </td>
                <td>年&nbsp;&nbsp;&nbsp;&nbsp;份</td>
                <td width="274px">
                    <input type="text" class="text" name="productPara.gx[4].value" value="${product.productPara.gx[4].value}"/>
                </td>
            </tr>
            <tr>
                <td width="100px">色&nbsp;&nbsp;&nbsp;泽</td>
                <td width="465px">
                    <input type="text" class="text" name="productPara.gx[5].value" value="${product.productPara.gx[5].value}"/>
                </td>
                <td>醒酒时间</td>
                <td width="274px">
                    <input type="text" class="text" name="productPara.gx[6].value" value="${product.productPara.gx[6].value}"/>
                </td>
            </tr>
            <tr>
                <td width="100px">品尝温度</td>
                <td width="465px">
                    <input type="text" class="text" name="productPara.gx[7].value" value="${product.productPara.gx[7].value}"/>
                </td>
                <td>进口商</td>
                <td width="274px">
                    <input type="text" class="text" name="productPara.gx[8].value" value="${product.productPara.gx[8].value}"/>
                </td>
            </tr>
            <tr>
                <td>商品特点</td>
                <td colspan="3"><textarea class="product_textare"  name="description">${product.description}</textarea></td>
            </tr>
            <tr>
                <td>查询结果描述</td>
                <td colspan="3">&nbsp;&nbsp;支持80个字，前缀：您所查询的是</td>
            </tr>
            <tr>
                <td></td>
                <td colspan="3"><textarea class="product_textare" name="queryDes">${product.queryDes}</textarea></td>
            </tr>
        </table>
    </div>
    
    <div class="add_center">
        <div class="head_describe">商品概述图片</div>
        <table>
            <tr>
               <td class="jymf_td" rowspan="1" colspan="1">
                    <span class="file-box">
                        <input type='button' class='btn' value='' />
                        <input id="imgFile0" name="summFiles" type="button" class="file" onclick="changePicUrl(0)"/>
                        <input name="dels" type="button" class="del" value="" onclick="delPic(0)"/>
                        <input type="hidden" id="imgFile0Name"  
                               name="productImgs[0].fileName" value="${product.productImgs[0].fileName}"/> 
                    </span>
                </td>
            </tr>
            <tr>
	            <td>
	                <img style="margin-top:25px;" id="img0" 
	                     name="imgs" 
	                     width="<%=Constants.PHOTO_IMG_WIDTH %>px" 
	                     src="${product.productImgs[0].fileName}"
	                 />
	            </td>
            </tr>
        </table>
    </div>
    
    <div class="add_center">
        <div class="head_describe">商品描述图片</div>
        <table>
            <tr>
               <c:forEach var="imgf" items="${product.productImgs}">
               <c:if test="${imgf.no > '0'}">
               <td class="jymf_td row_img" rowspan="1" colspan="1">
                    <span class="file-box">
                        <input type='button' class='btn' value='' />
                        <input id="imgFile${imgf.no}" 
                               name="imgFiles" 
                               type="button" 
                               accept="image/jpeg" 
                               class="file" 
                               onclick="changePicUrl(${imgf.no})"
                        />
                        <input name="dels" 
                               type="button" 
                               class="del" 
                               value="" 
                               onclick="delPic(${imgf.no})"
                        />
                        <input type="hidden" id="imgFile${imgf.no}Name"  
                               name="productImgs[${imgf.no}].fileName" value="${imgf.fileName}"/> 
                    </span>
                </td>
                </c:if>
                </c:forEach> 
            </tr>
            <tr>
               <c:forEach var="imgf" items="${product.productImgs}">
                   <c:if test="${imgf.no > '0'}">
                   <td>
                       <img style="margin-top:25px;" id="img${imgf.no}" 
                            name="imgs" 
                            width="<%=Constants.PHOTO_IMG_WIDTH %>px" 
                            src="${imgf.fileName}"
                        />
                   </td>
                   </c:if>
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

<%--图片剪切层 --%>
<div id="showPic" class="showPic" style="display: none;">
	<iframe src="${ctx}/common/pic" id="pic_iframe" style="width:100%;height:95%;border:0px;" scrolling="no"></iframe>   			
</div>
<div class='phoneBack'><div id='phonePic'></div></div>
</body>
</html>