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
    
    for(var i=0;i<document.getElementById("saleMethod").options.length;i++)
    {
        if(document.getElementById("saleMethod").options[i].value == $("#saleMethod").attr("value"))
        {
            document.getElementById("saleMethod").options[i].selected=true;
            break;
        }
    }
    /** 表单验证    */
    $("#addform").validate({
    	rules: {
    		startID: {
        		required:true,
            	digits:true,
            	remote:{
            		url:"${ctx}/company/trace/checkStart",
            		data:{
            			endID:function(){return $("input[name='endID']").val();},
            			startID:function(){return $("input[name='startID']").val();},
            			id:function(){return $("input[name='id']").val();}
            		}
            	}
            },
            endID: {
            	required:true,
            	digits:true,
            	remote:{
            		url:"${ctx}/company/trace/checktID",
            		data:{
            			endID:function(){return $("input[name='endID']").val();},
            			startID:function(){return $("input[name='startID']").val();},
            			id:function(){return $("input[name='id']").val();}
            		}
            	}
            },
        	proName: {
                required:true
            },
            proBatchId: {
                required:true
            },
            materialBatchId: {
                required:true
            },
            materialDosage: {
                number:true,
                maxlength:20
            },
            inPacTime: {
            	dateISO:true
            },
            outPacTime: {
            	dateISO:true
            },
            proInTime: {
                dateISO:true
            },
            proInNum: {
            	digits:true,
            	maxlength:20
            },
            proYield: {
                number:true,
                max:100
            },
            deliveryTime: {
                dateISO:true
            },
            buyUrl:{
            	url:true
            }
        },
   	 messages:{
   		startID: {
        	remote:"追溯凭证错误"
        },
        endID: {
        	remote:"追溯凭证错误"
        }
    }
    });
    $("body").on("click",".delMaterialBId",function(){
		var id = $(this).attr("value");
		$("tr[id='"+id+"']").remove();
		  var cur = "";
		  var len=$("#gx tr").length;
		  var i =0;
		  $('#gx tr').each(function(){
			  	if($(this).attr("id")!=""){
			  		i++;
			  		if(len!=i)
			  			cur+=$(this).attr("id")+",";
			  		else cur+=$(this).attr("id");
			  	}
			});
		  $("#materialBatchId").val(cur);
	});
    showMaterialBId();
    //点击原料批号跳转
    $("body").on("click",".toDetail",function(){
    	var CLId=$("input[name='id']").val();  //当前CoedycepsLogistics ID
    	$(location).attr('href', '${ctx}/company/cultivation/update/'+$(this).attr('value')+'_'+CLId);
    });  
});

//显示遮罩层
function showdiv() { 
	parent.$("#bg1").fadeToggle("1000");
	parent.$("#show1").slideToggle("1000");
	parent.document.getElementById("ifm_ID1").src="${ctx}/company/trace/selectRelationCordyceps";
}
function addtr(ids) {
	if(add_row_new(ids)>-1){
    	hidediv1();	
	}
}
</script>
<title>虫草信息添加 </title>

</head>
<body>
<div class="main_div" style="margin-left:200px;">
<form:form id="addform" modelAttribute="cordycepsLogistic" action="${ctx}/company/trace/add" method="post" 
           enctype ="multipart/form-data">
     <input type="hidden" name="id" value="0"/>  
     <input type="hidden" name="materialBatchId" id="materialBatchId" value="${cordycepsLogistic.materialBatchId}"/>     
     <div class="add_top">
        <div class="head_describe">产品基本信息</div>
        <table border="0" cellpadding="0" cellspacing="0" >
        	<tr>
                <td width="100px">开&nbsp;始&nbsp;标&nbsp;签</td>
                <td width="465px">
	                <input type="text" maxlength="16" class="text" name="startID" value="${cordycepsLogistic.startID}"/>
	                <span class="must">*</span>
                </td>
                <td>结&nbsp;束&nbsp;标&nbsp;签</td>
                <td width="274px">        
                	<input type="text" maxlength="16" class="text" name="endID" value="${cordycepsLogistic.endID}"/>
                	<span class="must">*</span>
                </td>
            </tr>
            <tr>
                <td width="100px">产&nbsp;品&nbsp;名&nbsp;称&nbsp;</td>
                <td width="465px">
                    <input type="text" class="text" name="proName" maxlength="40" value="${cordycepsLogistic.proName}"/>
                    <span class="must">*</span> 
                </td>
                <td>产&nbsp;品&nbsp;批&nbsp;号</td>
                <td width="274px">        
                	<input type="text" maxlength="20" class="text" name="proBatchId" value="${cordycepsLogistic.proBatchId}"/>
                	<span class="must">*</span>
                </td>
            </tr>
            <tr>
                <td width="100px">销&nbsp;售&nbsp;方&nbsp;式</td>
                <td width="465px">
                    <select id="saleMethod" name="saleMethod" style="width:251px;height:30px;margin-left: 10px;line-height: 27px;font-size: 13px;">
                    	<option value="经销商">经销商</option> 
                    	<option value="终端门店">终端门店</option> 
                    	<option value="电子商务">电子商务</option> 
                    	<option value="其他">其他</option> 
                    </select> 
                </td>
                <td>原&nbsp;料&nbsp;用&nbsp;量(Kg)</td>
                <td>
                    <input type="text" maxlength="20" class="text" name="materialDosage" value="${cordycepsLogistic.materialDosage}"/>
                    
                </td>
            </tr>
            <tr>
                <td width="100px">内&nbsp;包&nbsp;负&nbsp;责&nbsp;人</td>
                <td width="465px">
                    <input type="text" class="text" name="inPacHead" maxlength="10" value="${cordycepsLogistic.inPacHead}"/>
                     
                </td>
                <td>内&nbsp;包&nbsp;时&nbsp;间</td>
                <td width="274px">
	                <input name="inPacTime" type="text" class="Wdate" 
	                onclick="WdatePicker({readOnly:'true'})" value="${cordycepsLogistic.inPacTime}"
	                maxlength="40"/>
	                
                </td>
            </tr>
            <tr>
                <td width="100px">内&nbsp;包&nbsp;使&nbsp;用&nbsp;仪&nbsp;器</td>
                <td width="465px">
                    <input type="text" class="text" name="inPacInstrument" maxlength="40" value="${cordycepsLogistic.inPacInstrument}"/>
                     
                </td>
                <td>外&nbsp;包&nbsp;负&nbsp;责&nbsp;人</td>
                <td width="274px">        
                	<input type="text" maxlength="10" class="text" name="outPacHead" value="${cordycepsLogistic.outPacHead}"/>
                	
                </td>
            </tr>
            <tr>
                <td width="100px">外&nbsp;包&nbsp;时&nbsp;间</td>
                <td width="465px">
	                <input name="outPacTime" type="text" class="Wdate" 
	                onclick="WdatePicker({readOnly:'true'})" value="${cordycepsLogistic.outPacTime}"
	                maxlength="40"/>
	                
                </td>
                <td>成&nbsp;品&nbsp;批&nbsp;号</td>
                <td width="274px">        
                	<input type="text" maxlength="20" class="text" name="resBatchId" value="${cordycepsLogistic.resBatchId}"/>
                	
                </td>
            </tr>
            <tr>
                <td width="100px">成&nbsp;品&nbsp;检&nbsp;验&nbsp;合&nbsp;格&nbsp;编&nbsp;号</td>
                <td width="465px">
                    <input type="text" class="text" name="proQualId" maxlength="20" value="${cordycepsLogistic.proQualId}"/>
                     
                </td>
                <td>成&nbsp;品&nbsp;入&nbsp;库&nbsp;时&nbsp;间</td>
                <td width="274px"> 
	                <input name="proInTime" type="text" class="Wdate" 
	                onclick="WdatePicker({readOnly:'true'})" value="${cordycepsLogistic.proInTime}"
	                maxlength="40"/>
	                
                </td>
            </tr>
            <tr>
                <td width="100px">成&nbsp;品&nbsp;入&nbsp;库&nbsp;数(盒)</td>
                <td width="465px">
                    <input type="text" class="text" name="proInNum" maxlength="20" value="${cordycepsLogistic.proInNum}"/>
                     
                </td>
                <td>成&nbsp;品&nbsp;收&nbsp;率(%)</td>
                <td width="274px">        
                	<input type="text" maxlength="40" class="text" name="proYield" value="${cordycepsLogistic.proYield}"/>
                	
                </td>
            </tr>
             <tr>
                <td width="100px">发&nbsp;货&nbsp;时&nbsp;间</td>
                <td width="465px"> 
	                <input name="deliveryTime" type="text" class="Wdate" 
	                onclick="WdatePicker({readOnly:'true'})" value="${cordycepsLogistic.deliveryTime}"
	                maxlength="40"/>
	                
                </td>
                <td>发&nbsp;货&nbsp;地&nbsp;点</td>
                <td width="274px">        
                	<input type="text" maxlength="40" class="text" name="deliveryAddr" value="${cordycepsLogistic.deliveryAddr}"/>
                	
                </td>
            </tr>
            <tr>
            	<td width="100px">购&nbsp;买&nbsp;链&nbsp;接</td>
                <td width="465px">        
                	<input type="text" maxlength="40" class="text" name="buyUrl" value="${cordycepsLogistic.buyUrl}"/>
                	
                </td>
            </tr>
 
        </table>
        <div>
        <table border="0" width="100%">
            <tr>
                <td>
                    <h3>原料批次
                        <input class="center_btn cx_btn" type="button" value="添加" onclick="showdiv();"/>  
                    </h3>
                    <table width="68%" class="jymf_table_para">
                        <thead style="text-align:center">
                           <tr>
                               <td width="100">原料批次</td> 
                               <td width="32">操作</td>
                           </tr>
                        </thead>
                        <tbody id="gx"></tbody>
                    </table>
                </td>
             </tr>
            </table>
        </div>
    </div>
    
    <div class="add_btn">
        <table style="margin-top:30px;">
            <tr>
                <td><input class="ok_button" type="submit" value=" "/></td>
                <td>
                    <input class="cancer_btn" type="button" 
                           onclick="javascript:window.location.href='${ctx}/company/trace/main/'"/>
                </td>
            </tr>    
        </table>
    </div>
    <input type="hidden" name="token" value="${token}" />
    </form:form>
</div>

</body>
</html>