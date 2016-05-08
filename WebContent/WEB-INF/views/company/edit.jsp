<%@page import="ch.qos.logback.core.Context"%>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="org.jymf.utils.Constants" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<title>企业信息修改 </title>
<link href="${ctx}/static/css/zhezhao.css" type="text/css" rel="stylesheet"/> 
<script type="text/javascript" src="${ctx}/static/js/common.js"></script>
<script type="text/javascript">
$().ready(function() {
    $("#company-update").addClass("active");
    
    /** 取消按钮点击 */
    $("#btnCancel").click(function() {
        $(location).attr('href', '${ctx}/company/main');
    });
    /** 表单验证    */
    $("#editform").validate({
        rules: {
            url: {
                url:true
            }
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


</script>
</head>
<body>
<div class="main_div" style="margin-left:200px;">
<form:form id="editform" modelAttribute="company" 
           action="${ctx}/company/update" method="post" enctype ="multipart/form-data">
    <input type="hidden" name="workMode" value="${company.workMode}"/>
    <div class="add_top">
        <div class="head_describe">                
            <c:if test="${company.workMode eq '2'}">商城基本信息</c:if> 
            <c:if test="${company.workMode ne '2'}">企业基本信息</c:if>                  
        </div>
        <table border="0" cellpadding="0" cellspacing="0" >
            <tr>
                <td>企&nbsp;业&nbsp;名&nbsp;称&nbsp;
                    <input type="text" class="text readonly" readonly="readonly"
                           name="name" value="${company.name}"/>
                </td>
                <td>
                    <span class="td">企&nbsp;业&nbsp;编&nbsp;号&nbsp;</span>
                    <input type="text" class="text readonly" readonly="readonly" name="id" value="${company.id}"/>
                </td>
            </tr>
            <tr>
                <td>企&nbsp;业&nbsp;主&nbsp;页&nbsp;                                               
                    <input type="text" maxlength="100" class="text" name="url" value="${company.url}"/>
                </td>
                <c:if test="${company.tel ne null && company.tel ne ''}">
	                <td>
	                    <span class="td">举&nbsp;报&nbsp;电&nbsp;话&nbsp;</span>
	                    <input type="text" class="text readonly" readonly="readonly" name="tel" value="${company.tel}"/>
	                </td>
                </c:if>
            </tr>
        </table>
        
        <table border="0" cellpadding="0" cellspacing="0" >
            <tr align="center">
                <td>企&nbsp;业&nbsp;描&nbsp;述&nbsp;</td>
                <td>
                    <textarea class="textare" name="depict.depicts">${company.depict.depicts}</textarea>
                </td>
            </tr>
        </table>
    </div>
    
    <div class="head_describe">企&nbsp;业&nbsp;图&nbsp;片</div>
    <table>
        <tr>
           <c:set var="no" value="-1"></c:set>
               <c:forEach var="imgf" items="${company.comImgs}" >
               <c:set var="no" value="${no+1}" /> 
               
               <td class="jymf_td row_img" rowspan="1" colspan="1">
                    <span class="file-box">
                        <input type="button" class="btn" value="" />
                        <input id="imgFile${no}" 
                               name="imgFiles" 
                               type="button" 
                               class="file" 
                               onclick="changePicUrl(${no})"
                        />
                        <input name="dels" 
                               type="button" 
                               class="del" 
                               value="" 
                               onclick="javascript:document.getElementById('img'+${no}+'').src ='';$('#imgFile'+${no}+'Name').val('');"
                        />
                        <input type="hidden" id="imgFile${no}Name"  
                               name="comImgs[${no}].fileName" value="${imgf.fileName}"/> 
                        <input type="hidden"  
                               name="comImgs[${no}].no" value="${no}"/> 
                    </span>
                </td>
                </c:forEach> 
        </tr>
        <tr>
       		<c:set var="no_img" value="-1" /> 
            <c:forEach var="imgf" items="${company.comImgs}">
                 <c:set var="no_img" value="${no_img+1}" /> 
                 <td>
                     <img id="img${no_img}" 
                          name="imgs" 
                          width="<%=Constants.PHOTO_IMG_WIDTH %>px" 
                          src="${imgf.fileName}"
                          style="margin-top:25px;"
                      />
                 </td>
            </c:forEach> 
        </tr>
    </table>
    
    <c:if test="${company.workMode==10}">
	<div id="xkz" >
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
               		<img style="margin-top:25px;" id="img4" name="imgs" width="200px"  src="${company.xkzUrl}" />
            	</td>
            </tr>
        </table>
    </div>
	</c:if>
    
    
    
    <div class="add_btn">
        <table>
            <tr>
                <td><input class="ok_button" type="submit" value=" "/></td>
                <td>
                    <input id="btnCancel" class="cancer_btn" type="button" value=""/>
                </td>
            </tr>    
        </table>
    </div>
</form:form>
</div>

	<div id="bg" class="bg" style="display: none"></div>
	<div id="showPic" class="showPic" style="display: none;">
		<iframe src="${ctx}/common/pic" id="pic_iframe" style="width:100%;height:95%;border:0px;" scrolling="no"></iframe>   			
	</div>

</body>
</html>