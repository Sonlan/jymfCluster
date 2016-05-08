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
<script type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#company-cultivation").addClass("active");
    //时间拆分显示
    timeSplit($("#bacterialTime").val());
    /** 表单验证    */
    $("#editform").validate({
        rules: {
        	materialBatchId: {
                required:true,
               // idFormat:true,
                maxlength:20,
                remote:"${ctx}/company/cultivation/checkId"
            },
            wheatInventory: {
                number:true,
                maxlength:20
            },
            vacTime: {
                dateISO:true
            },
            bacterialTime: {
                dateISO:true
            },
            childInTime: {
                dateISO:true
            },
            childInNum: {
                number:true,
                maxlength:20
            },
            childYield: {
                number:true,
                max:100
            },
            conPowderInNum: {
                number:true,
                maxlength:20
            },
            conPowderYield: {
                number:true,
                max:100
            },
            myceliumInNum: {
                number:true,
                maxlength:20
            },
            mycoplasmaInNum: {
                number:true,
                maxlength:20
            }
        } ,
        messages:{
        	materialBatchId: {
            	remote:"用户ID重复"
            },
        }
    });
});
//时间拼接
function timeConcat(){
	$("#bacterialTime").val($("#startTime").val()+"~"+$("#overTime").val());
}
//时间拆分显示
function timeSplit(value){
	$("#startTime").val(value.split("~")[0]);
	$("#overTime").val(value.split("~")[1]);
}
//显示遮罩层
function showdiv(ids) { 
    document.getElementById("bg").style.display ="block";
    document.getElementById("show").style.display ="block";
    document.getElementById("ifm_ID").src="${ctx}/company/product/save?id="+ids;
}
</script>
<title>虫草信息添加 </title>

</head>
<body>
<div class="main_div" style="margin-left:200px;">
<form:form id="editform" modelAttribute="cordyceps" action="${ctx}/company/cultivation/update" method="post" 
           enctype ="multipart/form-data">
    <input type="hidden" name="materialBatchId" value="${cordyceps.materialBatchId}"/>
    <div class="add_top">
        <div class="head_describe">产品基本信息</div>
        <table border="0" cellpadding="0" cellspacing="0" >
            <tr>
                <td>前&nbsp;处&nbsp;理&nbsp;负&nbsp;责&nbsp;人</td>
                <td width="274px">        
                	<input type="text" maxlength="10" class="text" name="formerLeader" value="${cordyceps.formerLeader}"/>
                </td>
                <td width="100px">菌&nbsp;质&nbsp;入&nbsp;库&nbsp;数&nbsp;量(Kg)</td>
                <td width="465px">
                    <input type="text" class="text" name="mycoplasmaInNum" maxlength="20" value="${cordyceps.mycoplasmaInNum}"/>
 
                </td>
            </tr>
            <tr>
                <td>原&nbsp;料&nbsp;小&nbsp;麦&nbsp;批&nbsp;号&nbsp;</td>
                <td>
                    <input type="text" class="text" name="wheatBatchId" maxlength="20" value="${cordyceps.wheatBatchId}"/>
                    
                </td>
                <td>小&nbsp;麦&nbsp;投&nbsp;料&nbsp;量</td>
                <td>
                    <input type="text" maxlength="20" class="text" name="wheatInventory" value="${cordyceps.wheatInventory}"/>
                    
                </td>
            </tr>
            <tr>
                <td width="100px">小&nbsp;麦&nbsp;检&nbsp;验&nbsp;合&nbsp;格&nbsp;编&nbsp;号&nbsp;</td>
                <td width="465px">
                    <input type="text" class="text" name="wheatQualifiedId" maxlength="20" value="${cordyceps.wheatQualifiedId}"/>
 
                </td>
                <td>菌&nbsp;种&nbsp;批&nbsp;号</td>
                <td width="274px">        
                	<input type="text" maxlength="20" class="text" name="bacterialBatchId" value="${cordyceps.bacterialBatchId}"/>
                	
                </td>
            </tr>
            <tr>
                <td width="100px">菌&nbsp;种&nbsp;检&nbsp;验&nbsp;合&nbsp;格&nbsp;批&nbsp;号</td>
                <td width="465px">
                    <input type="text" class="text" name="bacterialQualId" maxlength="20" value="${cordyceps.bacterialQualId}"/>
 
                </td>
                <td>菌&nbsp;种&nbsp;生&nbsp;产&nbsp;序&nbsp;列&nbsp;号</td>
                <td width="274px">        
                	<input type="text" maxlength="20" class="text" name="bacterialSeqNum" value="${cordyceps.bacterialSeqNum}"/>
                	
                </td>
            </tr>
            <tr>
                <td width="100px">培&nbsp;育&nbsp;车&nbsp;间&nbsp;负&nbsp;责&nbsp;人</td>
                <td width="465px">
                    <input type="text" class="text" name="cultivateLeader" maxlength="10" value="${cordyceps.cultivateLeader}"/>
 
                </td>
                <td>接&nbsp;种&nbsp;时&nbsp;间</td>
                <td width="274px"> 
	                <input name="vacTime" type="text" class="Wdate" 
	                onclick="WdatePicker({readOnly:'true'})" value="${cordyceps.vacTime}"
	                maxlength="40"/>
	                
                </td>
            </tr>
            <tr>
                <td width="100px">培&nbsp;养&nbsp;房</td>
                <td width="465px">
                    <input type="text" class="text" name="bacterialRoom" maxlength="20" value="${cordyceps.bacterialRoom}"/>
 
                </td>
                <td>培&nbsp;育&nbsp;时&nbsp;间</td>
                <td width="274px"> 
                	<input type="hidden"  class="text" id= "bacterialTime" name="bacterialTime" value="${cordyceps.bacterialTime}"/> 
	                <input id="startTime" type="text" class="Wdate" style="float:left; width:110px;"
	                onFocus="var overTime=$dp.$('overTime');WdatePicker({onpicked:timeConcat(),readOnly:'true',maxDate:'#F{$dp.$D(\'overTime\')}'})"
	                />
	                <span style="float:left;margin:10px 7px;">~</span>
	                <input id="overTime" type="text" class="Wdate" style="float:left; width:110px;"
	                onFocus="WdatePicker({onpicked:timeConcat(),readOnly:'true',minDate:'#F{$dp.$D(\'startTime\')}'})"
	                />
	                
                </td>
            </tr>
            <tr>
                <td width="100px">后&nbsp;处&nbsp;理&nbsp;负&nbsp;责&nbsp;人</td>
                <td width="465px">
                    <input type="text" class="text" name="postProcLeader" maxlength="10" value="${cordyceps.postProcLeader}"/>
 
                </td>
                <td>子&nbsp;实&nbsp;体&nbsp;检&nbsp;验&nbsp;合&nbsp;格&nbsp;编&nbsp;号</td>
                <td width="274px">        
                	<input type="text" maxlength="20" class="text" name="childQualId" value="${cordyceps.childQualId}"/>
                	
                </td>
            </tr>
             <tr>
                <td width="100px">子&nbsp;实&nbsp;体&nbsp;入&nbsp;库&nbsp;时&nbsp;间</td>
                <td width="465px"> 
	                <input name="childInTime" type="text" class="Wdate" 
	                onclick="WdatePicker({readOnly:'true'})" value="${cordyceps.childInTime}"
	                maxlength="40"/>
	                
                </td>
                <td>子&nbsp;实&nbsp;体&nbsp;入&nbsp;库&nbsp;数&nbsp;量(Kg)</td>
                <td width="274px">        
                	<input type="text" maxlength="20" class="text" name="childInNum" value="${cordyceps.childInNum}"/>
                	
                </td>
            </tr>
             <tr>
                <td width="100px">子&nbsp;实&nbsp;体&nbsp;收&nbsp;率(%)</td>
                <td width="465px">
                    <input type="text" class="text" name="childYield" maxlength="40" value="${cordyceps.childYield}"/>
 
                </td>
                <td>孢&nbsp;子&nbsp;粉&nbsp;入&nbsp;库&nbsp;数&nbsp;量(Kg)</td>
                <td width="274px">        
                	<input type="text" maxlength="20" class="text" name="conPowderInNum" value="${cordyceps.conPowderInNum}"/>
                	
                </td>
            </tr>
             <tr>
                <td width="100px">孢&nbsp;子&nbsp;粉&nbsp;收&nbsp;率(%)</td>
                <td width="465px">
                    <input type="text" class="text" name="conPowderYield" maxlength="40" value="${cordyceps.conPowderYield}"/>
 
                </td>
                <td>菌&nbsp;丝&nbsp;体&nbsp;入&nbsp;库&nbsp;数&nbsp;量(Kg)</td>
                <td width="274px">        
                	<input type="text" maxlength="20" class="text" name="myceliumInNum" value="${cordyceps.myceliumInNum}"/>
                	
                </td>
            </tr>
        </table>
    </div>
    
    <div class="add_btn">
        <table style="margin-top:30px;">
            <tr>
                <td><input class="ok_button" type="submit" value=" "/></td>
                <td>
                    <input class="cancer_btn" type="button" 
                           onclick="javascript:window.location.href='${ctx}/company/cultivation/main/'"/>
                </td>
            </tr>    
        </table>
    </div>
    <input type="hidden" name="token" value="${token}" />
    </form:form>
</div>
</body>
</html>