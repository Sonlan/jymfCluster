/**
 * 检索结果列表中行逐行变换颜色中使用
 */
/*
$(function(){
	$(".jymf_table tr:even").addClass('cc');
}); */

/**
 * 分页中使用
 * @param pageNow
 */
function pageNow(pageNow){
	//对表数据进行序列化
    var fy=$("#fenye").serialize(); 
    //获取表单action的属性值
	var f=$("#fenye").attr("action");
	window.location.href=f+"?pageNow="+pageNow+"&"+fy;
}
