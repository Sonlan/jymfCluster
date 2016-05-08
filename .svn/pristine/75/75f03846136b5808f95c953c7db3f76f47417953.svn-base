<%@page import="ch.qos.logback.core.Context"%>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="org.jymf.utils.Constants" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="CLId" value="${CLId}"/>
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
</script>
<title>虫草信息添加 </title>

</head>
<body>
<div class="main_div" style="margin-left:200px;">
<form:form id="editform" modelAttribute="cordyceps" action="${ctx}/company/cultivation/update" method="post" 
           enctype ="multipart/form-data">
    <input type="hidden" name="materialBatchId" value="${cordyceps.materialBatchId}"/>
    <div class="add_top">
        <div class="head_describe">原料批次:${cordyceps.materialBatchId}</div>
        <table  class="jymf_table table_border">
            <tr class="jymf_tr table_1_tr">
                <td style="padding:16px 0 3px 0px;">前&nbsp;处&nbsp;理&nbsp;负&nbsp;责&nbsp;人</td>
                <td width="274px">        
                	${cordyceps.formerLeader}
                </td>
                <td width="100px">菌&nbsp;质&nbsp;入&nbsp;库&nbsp;数&nbsp;量(Kg)</td>
                <td width="465px">
                    ${cordyceps.mycoplasmaInNum}
                </td>
            </tr>
            <tr class="jymf_tr table_1_tr">
                <td>原&nbsp;料&nbsp;小&nbsp;麦&nbsp;批&nbsp;号&nbsp;</td>
                <td>
                    ${cordyceps.wheatBatchId}
                </td>
                <td>小&nbsp;麦&nbsp;投&nbsp;料&nbsp;量</td>
                <td>
                    ${cordyceps.wheatInventory}
                </td>
            </tr>
            <tr class="jymf_tr table_1_tr">
                <td width="100px">小&nbsp;麦&nbsp;检&nbsp;验&nbsp;合&nbsp;格&nbsp;编&nbsp;号&nbsp;</td>
                <td width="465px">
                    ${cordyceps.wheatQualifiedId}
                </td>
                <td>菌&nbsp;种&nbsp;批&nbsp;号</td>
                <td width="274px">        
                	${cordyceps.bacterialBatchId}
                	
                </td>
            </tr>
            <tr class="jymf_tr table_1_tr">
                <td width="100px">菌&nbsp;种&nbsp;检&nbsp;验&nbsp;合&nbsp;格&nbsp;批&nbsp;号</td>
                <td width="465px">
                    ${cordyceps.bacterialQualId}
                     
                </td>
                <td>菌&nbsp;种&nbsp;生&nbsp;产&nbsp;序&nbsp;列&nbsp;号</td>
                <td width="274px">        
                	${cordyceps.bacterialSeqNum}
                	
                </td>
            </tr>
            <tr class="jymf_tr table_1_tr">
                <td width="100px">培&nbsp;育&nbsp;车&nbsp;间&nbsp;负&nbsp;责&nbsp;人</td>
                <td width="465px">
                    ${cordyceps.cultivateLeader}
                     
                </td>
                <td>接&nbsp;种&nbsp;时&nbsp;间</td>
                <td width="274px"> 
	                ${cordyceps.vacTime}
                </td>
            </tr>
            <tr class="jymf_tr table_1_tr">
                <td width="100px">培&nbsp;养&nbsp;房</td>
                <td width="465px">
                    ${cordyceps.bacterialRoom}
                </td>
                <td>培&nbsp;育&nbsp;时&nbsp;间</td>
                <td width="274px"> 
                	${cordyceps.bacterialTime}
                </td>
            </tr>
            <tr class="jymf_tr table_1_tr">
                <td width="100px">后&nbsp;处&nbsp;理&nbsp;负&nbsp;责&nbsp;人</td>
                <td width="465px">
                    ${cordyceps.postProcLeader}
                </td>
                <td>子&nbsp;实&nbsp;体&nbsp;检&nbsp;验&nbsp;合&nbsp;格&nbsp;编&nbsp;号</td>
                <td width="274px">        
                	${cordyceps.childQualId}
                </td>
            </tr>
             <tr class="jymf_tr table_1_tr">
                <td width="100px">子&nbsp;实&nbsp;体&nbsp;入&nbsp;库&nbsp;时&nbsp;间</td>
                <td width="465px"> 
	                ${cordyceps.childInTime}
                </td>
                <td>子&nbsp;实&nbsp;体&nbsp;入&nbsp;库&nbsp;数&nbsp;量(Kg)</td>
                <td width="274px">        
                	${cordyceps.childInNum}
                </td>
            </tr>
             <tr class="jymf_tr table_1_tr">
                <td width="100px">子&nbsp;实&nbsp;体&nbsp;收&nbsp;率(%)</td>
                <td width="465px">
                    ${cordyceps.childYield}
                </td>
                <td>孢&nbsp;子&nbsp;粉&nbsp;入&nbsp;库&nbsp;数&nbsp;量(Kg)</td>
                <td width="274px">        
                	${cordyceps.conPowderInNum}
                </td>
            </tr>
             <tr class="jymf_tr table_1_tr">
                <td width="100px">孢&nbsp;子&nbsp;粉&nbsp;收&nbsp;率(%)</td>
                <td width="465px">
                    ${cordyceps.conPowderYield}
                </td>
                <td>菌&nbsp;丝&nbsp;体&nbsp;入&nbsp;库&nbsp;数&nbsp;量(Kg)</td>
                <td width="274px">        
                	${cordyceps.myceliumInNum}
                </td>
            </tr>
        </table>
    </div>
    
    <div class="add_btn">
        <table style="margin-top:30px;">
            <tr>
                <td><input class="ok_button" type="button" value=" "
					onclick="javascript:window.location.href='${ctx}/company/trace/update/${CLId}'"/></td>
            </tr>    
        </table>
    </div>
    <input type="hidden" name="token" value="${token}" />
    </form:form>
</div>
</body>
</html>