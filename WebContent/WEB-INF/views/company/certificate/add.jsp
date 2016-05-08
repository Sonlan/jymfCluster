<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${ctx}/static/jquery-validation/1.11.1/additional.js"></script>

<title>上传证书</title>
<script>

    $(document).ready(function(){
    	$("#company-certificate").addClass("active");
        $("#addform").validate({
            rules: {
                name:{
                    required:true,
                }
            } 
        });
    });
  //文件预览
    function changePicUrl(type){ 
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
    function delPic(type){
        var reader = new FileReader();
        $("#img"+type).attr("src", "");
        reader.readAsDataURL(null);
    }
    
    function checkSubmit(){
    	var img1 = $("#imgFile1").val();
    	var img2 = $("#imgFile2").val();
    	
    	if(img1=="" && img2==""){
	    	alert("请选择检验检疫证");
    	}else{
    		$("#addform").submit();
    	}
    }
</script>
</head>
<body>
<div style="margin:20px 300px auto 300px;">
<form:form id="addform" modelAttribute="certificate" action="${ctx}/company/certificate/add" method="post" enctype ="multipart/form-data" >
	<div class="head_describe">上传证书</div>
    <table border="0px" cellpadding="0" cellspacing="0" >
        <tr>
            <td style="padding:8px;">名&nbsp;&nbsp;称
                <input name="name" type="text" class="text"/>
                <span class="must">*</span>
            </td>
        </tr>
        <tr>
        	<td style="padding:8px;color: red;" colspan="2">请选择500K以内、JPEG、JPG类型的图片</td>
        </tr>
        <tr>
        	<td style="padding:8px;">检验检疫证
        		<span class="file-box">
        		 <input type='button' class='btn' value='' />
                 <input id="imgFile1" name="imgFile1" type="file" class="file" onchange="changePicUrl(1)"/>
                 <input name="del" type="button" class="del" value="" onclick="delPic(1)"/>
        		</span>
        	</td>
            <td style="padding:8px;">进口报关单
            	<span class="file-box">
            	 <input type='button' class='btn' value='' />
                 <input id="imgFile2" name="imgFile2" type="file" class="file" onchange="changePicUrl(2)"/>
                 <input name="del" type="button" class="del" value="" onclick="delPic(2)"/>
            	</span>
            </td>
        </tr>
        <tr>
        	<td>
        		<img style="margin-top:25px;" id="img1" name="imgs" width="200px" src="" />
        	</td>
        	<td>
        		<img style="margin-top:25px;" id="img2" name="imgs" width="200px" src="" />
        	</td>
        </tr>
    </table>
    
    <div style="margin: 30px 100px;">
        <table>
            <tr>
                <td><input type="button" class="ok_button" onclick="checkSubmit()"/></td>
                <td>
                    <input class="cancer_btn" type="button" onclick="javascript:window.location.href='javascript:history.go(-1)'"/>
                </td>
            </tr>    
        </table>
    </div>
    <input type="hidden" name="token" value="${token}" />
</form:form>
</div>
</body>
</html>