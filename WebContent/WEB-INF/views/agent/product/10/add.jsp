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

//tree基本配置
var zNodes =eval('${productType}');

$(document).ready(function(){
    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
});
	
$(document).ready(function(){
	var typeJson = '${productType}';
	$("#agent-company").addClass("active");
	$("#phonePic").niceScroll({cursorborder:"#00F",cursorcolor:"#CAC9CE",boxzoom:false,cursorwidth: "3px"});
    /** 表单验证    */
    $("#addform").validate({
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

</script>
<title>产品类型添加 </title>

</head>
<body>
<div class="main_div" style="margin-left:200px;">
<form:form id="addform" modelAttribute="product" action="${ctx}/agent/product/add" method="post" 
           enctype ="multipart/form-data">
    <div class="add_top">
        <div class="head_describe">商品基本信息</div>
        <input id="companyName" type="hidden" name="productPara.gx[9].value" value="${companybase.name}"/>
        <table border="0" cellpadding="0" cellspacing="0" >
            <tr>
                <td width="100px">商品名称</td>
                <td width="465px">
                    <input type="text" class="text" name="name" maxlength="80"/>
                    <span class="must">*</span> 
                </td>
                <td>原产国家</td>
                <td width="274px">
                	<input type="text" maxlength="80" class="text" name="origin"/>
                </td>
            </tr>
            <tr id="tree_tr" >
                <td>商品类型</td>
                <td colspan="3" align="left">
                    <input id="type" type="text" readonly value="" class="text" name="productPara.gx[0].value"/>
                    <input id="typeValue" type="hidden" name="type" value=""/>
                    <input id="menuBtn"  class="center_btn" type="button" onclick="showMenu(); return false;" value="选择"/>
                    <div id="menuContent" class="menuContent" style="display:none; position: absolute; z-index: 999999999999">
                        <ul id="treeDemo" class="ztree" style="margin-top:0; width:260px;"></ul>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="100px">容&nbsp;&nbsp;&nbsp;量&nbsp;</td>
                <td width="465px">
                    <input type="text" class="text" name="productPara.gx[1].value"/>
                </td>
                <td>葡萄品种</td>
                <td width="274px">
                    <input type="text" class="text" name="productPara.gx[2].value"/>
                </td>
            </tr>
            <tr>
                <td width="100px">酒精含量</td>
                <td width="465px">
                    <input type="text" class="text" name="productPara.gx[3].value"/>
                </td>
                <td>年&nbsp;&nbsp;&nbsp;&nbsp;份</td>
                <td width="274px">
                    <input type="text" class="text" name="productPara.gx[4].value"/>
                </td>
            </tr>
            <tr>
                <td width="100px">色&nbsp;&nbsp;&nbsp;泽</td>
                <td width="465px">
                    <input type="text" class="text" name="productPara.gx[5].value"/>
                </td>
                <td>醒酒时间</td>
                <td width="274px">
                    <input type="text" class="text" name="productPara.gx[6].value"/>
                </td>
            </tr>
            <tr>
                <td width="100px">品尝温度</td>
                <td width="465px">
                    <input type="text" class="text" name="productPara.gx[7].value"/>
                </td>
                <td>进口商</td>
                <td width="274px">
                    <input type="text" class="text" name="productPara.gx[8].value"/>
                </td>
            </tr>
            <tr>
                <td>商品特点</td>
                <td colspan="3"><textarea class="product_textare"  name="description"></textarea></td>
            </tr>
            <tr >
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
        <div class="head_describe">商品概述图片</div>
        <table>
            <tr>
               <td class="jymf_td row_3" rowspan="1" colspan="1">
                   <span class="file-box">
                       <input type='button' class='btn' value='' />
                       <input id="imgFile0" name="summFiles" type="button" class="file" onclick="changePicUrl(0)"/>
                       <input name="del" type="button" class="del" value="" onclick="delPic(0)"/>
                   	   <input id="imgFile0Name" name="productImgs[0].fileName" type="hidden"/>
                   </span>
                </td>
            </tr>
            <tr>
	            <td>
	                <img style="margin-top:25px;" id="img0" 
	                     name="imgs" 
	                     width="<%=Constants.PHOTO_IMG_WIDTH %>px" 
	                     src=""
	                 />
	            </td>
            </tr>
        </table>
    </div>
    
    <div class="add_center">
        <div class="head_describe">商品描述图片</div>
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
                           onclick="history.back()"/>
                </td>
            </tr>    
        </table>
    </div>
    <input type="hidden" name="token" value="${token}" />
    </form:form>
</div>

<%--图片剪裁遮罩层 --%>
<div id="showPic" class="showPic" style="display: none;">
    <iframe src="${ctx}/common/pic" id="pic_iframe" style="width:100%;height:95%;border:0px;" scrolling="no"></iframe>              
</div>
</body>
</html>