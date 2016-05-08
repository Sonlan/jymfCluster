
function isIE(){ //ie? 
if (window.navigator.userAgent.toLowerCase().indexOf("msie")>=1){ 
	return true; 
	}else{ 
	return false;
	}
} 
if(!isIE()){ //firefox innerText define 
	HTMLElement.prototype.__defineGetter__( "innerText", 
	function(){ 
		var anyString = ""; 
		var childS = this.childNodes; 
		for(var i=0; i<childS.length; i++) { 
		if(childS[i].nodeType==1) 
		anyString += childS[i].tagName=="BR" ? '\n' : childS[i].textContent; 
		else if(childS[i].nodeType==3) 
		anyString += childS[i].nodeValue; 
		} 
		return anyString; 
	} 
	); 
	HTMLElement.prototype.__defineSetter__( "innerText", 
		function(sText){ 
			this.textContent=sText; 
		} 
	); 
} 

function showPhone(id){
	
	 var title="";
	 if(id=="gx"){
		 title="规格参数";
	 }else if(id=="bz"){
		 title="工艺流程";
	 }else if(id=="yl"){
		 title="其他信息";
	 }
	 
	 $("#phonePic").empty();
	 $("#cancel").remove();
	 
	 var phoneHtml = "<table class='phoneTable'>"+
	 				 "<tr class='trFirst' align='center'><td colspan='2' class='tdFirst'>"+
	 				 "<span style='float: left;left:100px;font-size: 12px;'>&nbsp;&nbsp;&lt;"+
	 				 "</span>"+title+"</td></tr>";
	var trs = $("#"+id+" tr");
	var trCnt = trs.length;
	for(var i=0;i<trs.length;i++){
		phoneHtml += "<tr class='trContent'>";
		phoneHtml += "<td class='tdContent td1'>"+trs[i].cells[0].innerText+"</td>"; 
		phoneHtml += "<td class='tdContent td2'>"+trs[i].cells[1].innerText+"</td>"; 
		phoneHtml += "</tr>";
	}
	phoneHtml += "</table>";
	$("#phonePic").append(phoneHtml);
	var cancelHtml = "<div id='cancel' class='cancel' title='关闭' onclick='hidePhone()'>"+
				 "<img src='static/images/x.png' style='width:100%'></div>";
	$(".phoneBack").append(cancelHtml);
	
	$(".phoneBack").animate({
		  right:'150px'
		 }, 1000); 
	setInterval("lightHide()",2000);
	
 }
 
  
 function lightHide(){
	 $("#cancel").animate({
		  opacity: 1
		 }, 1000).animate({
		  opacity: 0
		 },1000);
 }
 
 function hidePhone(){
	 $(".phoneBack").animate({
		  right:'-350px'
		 }, 1000);
 }
 
 
 