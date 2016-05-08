//显示选择图片的页面
function changePicUrl(type){
	showDiv();
	document.getElementById("pic_iframe").contentWindow.document.getElementById("pic_order").value=type;
	document.getElementById("pic_iframe").contentWindow.delPic();
}  

// 删除图片
function delPic(type){
	var version = window.navigator.userAgent;
	if(version.substr(version.indexOf("MSIE") + 5, 1) >= 9 || version.substr(version.indexOf("MSIE") + 5, 1) == 1){
		$('#img'+type+'').css("filter","progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src='')");
	}
	document.getElementById('img'+type+'').src =Date();
	$('#imgFile'+type+'Name').val('');	 
		 
}

//打开遮罩
function showDiv(){
	$("#bg").show();
	$("#showPic").show();
}

//关闭图片遮罩
function hidePicdiv() {
	$("#bg").hide();
	$("#showPic").hide();
}


//关闭遮罩
function hidediv() {
	$("#bg").hide();
	$("#show").hide();
}


//-------------操作号段管理部分 履历信息---------------------                		

function formatDate(now) { 
	var year=now.getFullYear(); 
	var month=now.getMonth()+1; 
	var date=now.getDate(); 
	var hour=now.getHours(); 
	var minute=now.getMinutes(); 
	var second=now.getSeconds(); 
	return changeTwo(year)+"-"+changeTwo(month)+"-"+changeTwo(date)+" "+changeTwo(hour)+":"+changeTwo(minute)+":"+changeTwo(second); 
} 

function changeTwo(a){
	if(a<10){
		a = "0"+a;
	}
	return a;
}

function makeTable(data){
	$("#lvli").empty();
	var html = "<tr ><td colspan='4' class='lvli_td'>审核进度</td></tr>";
	html    += "<tr ><td class='lvli_td'>时间</td><td class='lvli_td'>操作人类型</td>"
				+ "<td class='lvli_td'>操作人</td><td class='lvli_td'>进度</td></tr>";
	
	if(data.list.length==0){
		html += "<tr class='lvli_tr' ><td class='lvli_td' colspan='4'>暂无数据</td>";
	}else{
		for(var i=0;i<data.list.length;i++){
			var userType = "";
			
			if(data.list[i].userType==0){
				userType = "企业";
			}else if(data.list[i].userType==1){
				userType = "代理商";
			}else if(data.list[i].userType==2){
				userType = "管理者";
			}
			
			var status = "";
			if(data.list[i].status==0){
				status = "企业提交申请";
			}else if(data.list[i].status==1){
				status = "确认结束";
			}else if(data.list[i].status==2){
				status = "普通管理者确认结束";
			}else if(data.list[i].status==3){
				status = "高级管理者确认结束";
			}else if(data.list[i].status==4){
				status = "制作完成";
			}else if(data.list[i].status==5){
				status = "撤销申请";
			}else if(data.list[i].status==6){
				status = "发货中";
			}else if(data.list[i].status==7){
				status = "标签已接收";
			}
			var logTime = new Date(data.list[i].logTime);
			var userName = data.list[i].userName==null?"":data.list[i].userName;
			
			
			html += "<tr class='lvli_tr'><td class='lvli_td'>"+formatDate(logTime)+"</td><td class='lvli_td'>"+userType+"</td>"
				 + "<td class='lvli_td'>"+userName+"</td><td class='lvli_td'>"+status+"</td></tr>";
		}
	}
	$("#lvli").append(html);
}

function labelNoTypeChange(obj){
	$("#labelTypeMust").remove();
	$(obj).siblings().removeAttr("checked");
	$(obj).siblings().removeAttr("name");
	$(obj).attr("checked","checked");
	$(obj).attr("name","labelNoType");
	if(obj.value==1){
		$("input[name='mail']").parent().append("<span id='labelTypeMust' class='must'>*</span>");
	}else{
		$("#labelTypeMust").remove();
	}
}

function labelTypeChange(obj){
	$(obj).siblings().removeAttr("checked");
	$(obj).siblings().removeAttr("name");
	$(obj).attr("checked","checked");
	$(obj).attr("name","labelType");
}