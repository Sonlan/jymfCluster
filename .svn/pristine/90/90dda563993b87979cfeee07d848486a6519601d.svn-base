<%@page import="ch.qos.logback.core.Context"%>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="org.jymf.utils.Constants" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta name="renderer" content="webkit">
<link href="${ctx}/static/css/jquery.Jcrop.min.css" type="text/css" rel="stylesheet"/> 
<link href="${ctx}/static/css/zhezhao.css" type="text/css" rel="stylesheet"/> 
<script type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/static/jquery-validation/1.11.1/additional.js"></script>
<script type="text/javascript" src="${ctx}/static/jquery/jquery.Jcrop.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/common.js"></script>
<title>企业添加 </title>
<script type="text/javascript">
$(document).ready(function(){
    $("#admin-company").addClass("active");
    /** 表单验证    */
    $("#addForm").validate({
        rules: {
        	name: {
                required:true
            },
            capital: {
            	digits:true
            },
            auditValid:{
            	digits:true
            },
            scope: {
                byteMaxLength:340
            },
            url: {
            	url:true
            },
            tel : {
            	isPhone : true
            },
        }
    });
    
   
});



function changePicUrlOld(type){ 
	  var version = window.navigator.userAgent;
	 if(version.substr(version.indexOf("MSIE") + 5, 1) >= 9){
		$("#imgFile"+type).select();
		$("#imgFile"+type).blur();
		//获取如片路径
		var file = document.selection.createRange().text;
		document.selection.empty();
		$("#img"+type).css("filter","progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src='"+file+"?version="+Math.random()+"')");
	 }
	 
	 else{
		var file = $("#imgFile"+type)[0].files[0];
		var fileval = $("#imgFile"+type);
		var maxSize = 500*1024;
	    if(file.size >maxSize){
	    	fileval.after(fileval.clone().val(""));   
	    	fileval.remove();  
	        alert("图片不能超过500K，请重新选择！");
	        return;
	    }
		var reader = new FileReader();
		reader.onload = function(evt){
			$("#img"+type).attr("src",evt.target.result);
		};
		$("#imgName" + type).attr("value", file.name);
		reader.readAsDataURL(file);
	}   
}   

// 删除图片
function delPicOld(type){
  var reader = new FileReader();
  $("#img"+type).attr("src", "");
  reader.readAsDataURL(null);
}


function changeMode(val){
	if(val==10){
		$("#xkz").show();
	}else{
		$("#xkz").hide();
	}
}


</script>

</head>
<body>
<div class="main_div" style="margin-left:200px;">
<form:form id="addForm" modelAttribute="company" action="${ctx}/admin/company/add" 
           method="post" enctype ="multipart/form-data">
    <div class="add_top">
        <div class="head_describe">公司基本信息</div>
        <table border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td>公&nbsp;司&nbsp;名&nbsp;称
                    <input type="text" maxlength="80" class="text" name="name" />
                    <span class="must">*</span>
                </td>
                <td class="jj">
                    <span class="td">公司注册号
                        <input type="text" maxlength="100" class="text" name="licenseNum"/>
                    </span>
                </td>
            </tr>
            <tr>
                <td>法&nbsp;人&nbsp;代&nbsp;表
                    <input type="text" maxlength="40" class="text" name="person"/>
                </td>
                <td>
                    <span class="td">公司注册地
                        <input type="text" maxlength="80" class="text" name="address"/>
                    </span>
                </td>
            </tr>
            <tr>
                <td colspan="2">注&nbsp;册&nbsp;资&nbsp;金
                    <input type="text" class="text" name="capital" maxlength="9" />
                </td>
            </tr>
        </table>
        
        <table border="0" cellpadding="0" cellspacing="0" >
            <tr align="center">
                <td>经&nbsp;营&nbsp;范&nbsp;围</td>
                <td>
                    <textarea class="textare" name="scope"></textarea>
                </td>
            </tr>
        </table>
        <table border="0" cellpadding="0" cellspacing="0" >
            <tr align="center">
                <td>公&nbsp;司&nbsp;描&nbsp;述</td>
                <td>
                    <textarea class="textare" name="depict.depicts"></textarea>
                </td>
            </tr>
        </table>
    </div>
    
    <div class="add_bottom">
    <div class="head_describe">审核信息</div>
        <table border="0px" cellpadding="0" cellspacing="0">
            <tr>
                <td>审&nbsp;核&nbsp;日&nbsp;期
                    <input name="auditTime" type="text" class="Wdate" onclick="WdatePicker()"/>
                </td>
                <td>
                    <span class="td">&nbsp;&nbsp;有&nbsp;效&nbsp;日&nbsp;期
                        <input class="text" type="text" name="auditValid" maxlength="2"/>&nbsp;&nbsp;(年)
                    </span>
                </td>
            </tr>
        </table>
    </div>
    
    <div class="add_center">
        <div class="head_describe">公司其他信息</div>
        <table border="0px" cellpadding="0" cellspacing="0">
            <tr>
                <td>主&nbsp;页&nbsp;地&nbsp;址
                    <input type="text" class="text" name="url" maxlength="200"/>
                </td>
                <td>
                    <span class="td">&nbsp;&nbsp;追&nbsp;溯&nbsp;模&nbsp;式
                        <form:select path="workMode" class="select" items="${workModelMap}" onchange="changeMode(this.value)"/>
                    </span>
                </td>
            </tr>
            <tr>
                <td>举&nbsp;报&nbsp;电&nbsp;话
                    <input type="text" class="text" name="tel" maxlength="16"/>
                </td>
            </tr>
        </table>
        <div class="head_describe">公&nbsp;司&nbsp;图&nbsp;片</div>
        <table>
            <tr>
               <c:forEach var="i" begin="0" end="2" step="1"> 
               <td class="jymf_td row_img" rowspan="1" colspan="1">
                    <span class="file-box">
                        <input type='button' class='btn' value='' />
                        <input id="imgFile${i}" name="imgFiles" class="file" type="button" 
                               type="button" onclick="changePicUrl(${i})"/>
                        <input name="del" type="button" class="del" value="" onclick="delPic(${i})"/>
                    	<input id="imgFile${i}Name" name="comImgs[${i}].fileName" type="hidden"/>
                    	<input id="imgFile${i}Name" name="comImgs[${i}].no" type="hidden"/>
                    </span>
                </td>
                </c:forEach> 
            </tr>
            <tr>
               <c:forEach var="i" begin="0" end="2" step="1"> 
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
	
	<div id="xkz" style="display: none">
        <div class="head_describe">许&nbsp;可&nbsp;证</div>
        <table>
            <tr>
              <td style="padding:8px;">
        		<span class="file-box">
        		 <input type='button' class='btn' value='' />
                 <input id="imgFile4" name="imgFile4" type="file" class="file" onchange="changePicUrlOld(4)"/>
                 <input name="del" type="button" class="del" value="" onclick="delPicOld(4)"/>
        		</span>
        	  </td>
            </tr>
            <tr>
            	<td>
               		<img style="margin-top:25px;" id="img4" name="imgs" width="200px" src="" />
            	</td>
            </tr>
        </table>
    </div>
    
    <div class="add_btn">
        <table>
            <tr>
                <td><input class="ok_button" type="submit" value=""/></td>
                <td>
                    <input class="cancer_btn" type="button" value="" 
                           onclick="javascript:window.location.href='javascript:history.go(-1)'"/>
                </td>
            </tr>    
        </table>
    </div>
    <input type="hidden" name="token" value="${token}" />
    </form:form>
</div>

	<div id="bg" class="bg" style="display: none"></div>
	<div id="showPic" class="showPic" style="display: none;">
		<iframe src="${ctx}/common/pic" id="pic_iframe" style="width:100%;height:95%;border:0px;" scrolling="no"></iframe>   			
 	</div>
</body>
</html>