<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="org.jymf.utils.Constants" %>

<!DOCTYPE html>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta name="renderer" content="webkit">
<link href="${ctx}/static/css/jquery.Jcrop.min.css" type="text/css" rel="stylesheet"/> 
<link href="${ctx}/static/css/zhezhao.css" type="text/css" rel="stylesheet"/> 
<link href="${ctx}/static/css/company.css" type="text/css" rel="stylesheet"/> 
<script type="text/javascript" src="${ctx}/static/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx}/static/jquery/jquery.Jcrop.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/common.js"></script>
<title></title>
<script type="text/javascript">
var rate=1;
var selectArea;
$(document).ready(function(){
	var pointer_value="${pointer}";
	if(pointer_value==3){
		selectArea=[0,0,300,300];
	}else{
		selectArea=[70,70,200,200];
	}
	setTimeout(function(){cut_pic(pointer_value)},500);
	
	$("#certain_save").click(function(){
		if(pointer_value==3){
			certain_save()	
		}else{
			alert("请先选择并剪切图片然后保存！");
		}
		});
	$("#cancel_save").click(function(){parent.hidePicdiv()});
	
	var pic_div_height=parseInt($("#pic_div").height());
	var pic_div_width=parseInt($("#pic_div").width());
	
	var pic_height=parseInt("${height}");
	var pic_width=parseInt("${width}");
	
	if(pic_height>pic_div_height && pic_width>pic_div_width){
		if(pic_height>=pic_width){
			rate=pic_div_height/pic_height;
			$("#pic_id").css({
				height:"100%",
			});
		}else{
			rate=pic_div_width/pic_width;
			$("#pic_id").css({
				width:"100%",
			});
		}
	}else if(pic_height>pic_div_height && pic_width<pic_div_width){
		rate=pic_div_height/pic_height;
		$("#pic_id").css({
			height:"100%",
		});
	}else if(pic_height<pic_div_height && pic_width>pic_div_width){
		rate=pic_div_width/pic_width;
		$("#pic_id").css({
			width:"100%",
		});
	}
	
});

function cut_pic(pointer_value){
	//放在jQuery(window).load(...)内调用，否则Jcrop无法正确初始化
	
	$("#pic_id").Jcrop({
		aspectRatio:1,
		allowSelect:false,
		onChange:showCoords,
		onSelect:showCoords,
		aspectRatio: 1,
		setSelect: selectArea ,
		minSize:[30,30]
	});	
	
	//响应自onChange,onSelect事件
	function showCoords(obj){
		$("#x").val(obj.x /rate);
		$("#y").val(obj.y /rate);
		$("#w").val(obj.w /rate);
		$("#h").val(obj.h /rate);
	}
	$("#crop_submit").click(function(){
		if(pointer_value==2 && $("#x").val()>=0){
			upload_pic();
		}else{
			alert("要先在图片上划一个选区再单击确认剪裁的按钮！");	
		}
	});
}


//文件预览
function changePicUrl(obj){
	var pic_size;
	if (obj.value=="") return ;  
	   var pic_size=-1;  
	   try {  
	       //对于IE判断要上传的文件的大小  
	       var fso = new ActiveXObject("Scripting.FileSystemObject");  
	       pic_size=parseInt(fso.getFile(obj.value).size);  
	   } catch (e){  
	       try{  
	           //对于非IE获得要上传文件的大小  
	            pic_size=parseInt(obj.files[0].size);  
	       }catch (e) {  
	       	pic_size=-1;  
	       }  
	   }
/* 	if(pic_size > 5 * 1024 *1024){
		alert("您的图片已经超过5M，请重新选择图片！"); */
	if(pic_size > 500 *1024){
        alert("您的图片已经超过500K，请重新选择图片！"); 
	}else{
		$("#upload_pic").submit();
	}
}  

//确定剪裁
function upload_pic(){
	$("#upload_pic_size").submit();
}
//确定保存
function certain_save(){
	var pic_url=document.getElementById("ask_pic").value;
	var pic_order=document.getElementById("pic_order").value;
	
	parent.$("#imgFile"+pic_order+"Name").val($("#pic_name").val());
	
	parent.$("#img"+pic_order).attr("src",pic_url);
	parent.$("#bg").hide();
	parent.$("#showPic").hide();
}

function delPic(){
	$('.jcrop-holder').remove();
	$("#pointer").show();
}

</script>

<style type="text/css">
	.note{
		font : 15px 'Microsoft Yahei';
		color : red;
	}
</style>

</head>
<body >
	<div style="padding:0px; margin:0px;height:30px;">	
		<span style="font:15px 'Microsoft Yahei';color:grey; " >上传图片</span>
		<a href="javascript:parent.hidePicdiv();" style="float:right"><img style="border:0px;" src="${ctx}/static/images/x.png" /></a>
	</div>
	
	<div align="center" style="padding:15px; background:#EFEFEF;height:450px;">
	 <%--图片框 --%>
	 <div id="pic_div" style="width:240px;height:240px;border:1px solid;color:grey;box-shadow: 1px 1px 1px #888888;-mozbox-shadow: 1px 1px 1px #888888" >
     	<c:if test="${pic_url ne null}">
     		<img id="pic_id" src="${ctx}/common/getPic/${pic_url}" />	
     		<img id="pointer" style="display: none;" src="${ctx}/static/images/upload_pic_window.png" />
     	</c:if>
     	<c:if test="${pic_url eq null}">
     		<img src="${ctx}/static/images/upload_pic_window.png" />	
     	</c:if>
     			
     	
     </div> 
     <%--文字框 --%>
     <div align="center" style="margin:20px 0px 0px 0px ">
     	<span class="note"> 请选择500K以内、JPEG、JPG、PNG类型的图片</span>
     </div>
     
     <div align="center" style="margin-top: 30px;">
     	<form:form id="upload_pic"  action="${ctx}/common/upload" method="post" enctype ="multipart/form-data">
	        <%--图片说明 --%>
	        <input type="hidden" id="pic_order" name="pic_order" value="${pic_order}">
	        <input type="hidden" id="pic_desc" name="pic_desc" value="company_info">
			<input type="button" value="选择图片" style="border:0px;width:80px;height:30px;cursor: pointer; color: white;background-image: url('${ctx}/static/images/pic_btn.png');">
			<input type="file" accept="image/jpeg,image/x-png" name="pic_file" id="pic_file"  style="height:27px;filter:alpha(opacity:0);opacity: 0;width:80px;position:absolute ;left:120px;" onchange="changePicUrl(this)">
			<input id="ask_pic" type="hidden" value="${ctx}/common/getPic/${pic_url}" /> 
		    <input class="pic_btn" type="button" id="crop_submit" value="剪    裁" /> 
		    <input class="pic_btn" type="button" id="certain_save" value="保    存" /> 
		    <input class="pic_btn" type="button" id="cancel_save" style="background : url('${ctx}/static/images/pic_btn_grey.png');color:grey;"  value="取    消" /><br><br>
		</form:form>
     	<form:form id="upload_pic_size" method="post" action="${ctx}/common/uploadPicSize" >
	     	<%-- 图片剪裁尺寸 --%>
		  	<input type="hidden" id="x" name="x" />
			<input type="hidden" id="y" name="y" />
			<input type="hidden" id="w" name="w" />
			<input type="hidden" id="h" name="h" />
			<input type="hidden" id="pic_name" name="old_pic" value="${pic_url}"/>
     	</form:form>
	    
	    <div style="margin-top:10px;">
	    	 <span style="font: 15px 'Microsoft Yahei';color: red;">${note}</span>
	    </div>
	   
     </div>
	
	</div>
</body>
</html>