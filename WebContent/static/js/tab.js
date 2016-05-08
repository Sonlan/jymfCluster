
//隐藏遮罩层
function hidediv() {
document.getElementById("bg").style.display ='none';
document.getElementById("show").style.display ='none';
}
//添加原料批次，由于size更大，另写了一个遮罩层
function hidediv1() {
	document.getElementById("bg1").style.display ='none';
	document.getElementById("show1").style.display ='none';
}
//定义变量
var name="";
var value="";
//定义数组变量
var param=[];

//获取遮罩层input的值
function functionName(){
name=document.getElementById("ifm_ID").contentWindow.document.getElementById("name").value;
value=document.getElementById("ifm_ID").contentWindow.document.getElementById("value").value;

//把获取到input的值保存起来
var data=[];
data.push(name);
data.push(value);
	
//保存data数组
param.push(data);
}
function add_row(value){
	  var paramss = $("#materialBatchId").val().split(",");
	  for(i=0;i<paramss.length;i++){
		  if(paramss[i]==value) {alert("不可重复添加");return -1;}
	  }
	  var html = "<tr id='"+value+"'><td><a class='toDetail' href='javascript:void(0);' value='"+value+"'>"+value+"</a>"+
	  			"</td>"+
	  			"<td><a href='javascript:void(0)' class='delMaterialBId' value='"+value+"'>删除</a></td>"+
	  			"</tr>";
	  $("#gx").append(html);
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
	  return 1 ;
	  
  }
function add_row_new(value){
	  var paramss = $("#materialBatchId").val().split(",");
	  for(i=0;i<paramss.length;i++){
		  if(paramss[i]==value) {alert("不可重复添加");return -1;}
	  }
	  var html = "<tr id='"+value+"'><td>"+value+
	  			"</td>"+
	  			"<td><a href='javascript:void(0)' class='delMaterialBId' value='"+value+"'>删除</a></td>"+
	  			"</tr>";
	  $("#gx").append(html);
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
	  return 1 ;
	  
}
//添加tr
function add_tr(ids) {
	//获取table的行数
	var rowcount = document.getElementById(ids).rows.length;
	var cnt = 1;
	//循环table的每一行的第一列值
	for(var i=0;i<rowcount;i++){
		var trtd = document.getElementById(ids).rows[i].cells[0].innerText;
		//判断文本框的值是否重复
		if(trtd==name){
			cnt=-1;
			alert('产品名称重复,请重新输入!');
		} 	
	}
	if(cnt!=-1){
		//追加的td
		$str = '';
	    /*$str += "<tr align='left'>";*/
		$str += "<tr align='left' class='jymf_tr table_1_tr'>";
	    $str += "<td><input type='hidden' name='productPara." + ids + "[" + rowcount + "].name' value='" +  name + "' />"  + name + "</td>";
        $str += "<td><input type='hidden' name='productPara." + ids + "[" + rowcount + "].value' value='" + value + "' />"  +  value + "</td>";
        
	    $str += "<td onClick='getDel(this)'><a href='javascript:;'>删除</a></td>";
	    $str += "</tr>";
		$("#" + ids).append($str);
	}
		
}


//删除tr
function getDel(k){
   var showMsg="确定要删除该记录?";
   if(!confirm(showMsg))
		return ;
   $(k).parent().remove();    
}


