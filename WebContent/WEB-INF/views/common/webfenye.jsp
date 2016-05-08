 <%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>

.bottom_bot{
margin-top: 15px;
font-size: 12px;
}

.txt_css a{
text-decoration: none;
color:#BA55D3;
}


/*所有DIV样式*/
.txt_css{
float: left;

}

/*右边文字*/
.txt_right{
/*margin-left: 65%;*/
float: right;
}

/*共通字体颜色*/
.txt_color_b{
color: #0000EE;

}

/*选中页码的样式*/
.txt_color_c{
width:35px;
height:20px;
border:0px solid #1C86EE; 
float: left;
text-align: center;
color:black; 
line-height: 20px;
font-weight: bold;
font-size:14px;
}

/*页码样式*/
.yema{
width:50px;
height: 20px;
border:1px solid #00F5FF; 
float: left;
text-align: center;
line-height: 20px;
margin-left: 5px;
}

/*边框样式*/
.disabled{
border:1px solid #00FFFF;
width:65px;
height: 20px;
text-align: center;
line-height: 20px;
margin-left: 3px;
margin-right: 3px;
}

.end_color{
color: #98F5FF;
}

.txt_css a{
 display: inline-block;
 width:50px;
 height: 40px;
}

</style>
<c:if test="${pageView.pageCount gt 1}">
<div class="bottom_bot">
<div class="txt_css">每页【<span class="txt_color_b">${pageView.pageSize}</span>】行</div>
<div class="txt_css">第<span class="txt_color_b">${pageView.pageNow}</span>页/共<span class="txt_color_b">${pageView.pageCount}</span>页&nbsp;&nbsp;</div>
<div class="txt_right">
	<div class="txt_css"><div  class="disabled"><a href="javascript:void(0);" onclick="pageNow('1');"> 首页 </a></div></div>
	<c:if test="${pageView.pageNow eq 1}">
		<div class="txt_css"><div class="disabled"> <div class="end_color">&lt; 上一页 </div></div></div>
	</c:if>
	<c:if test="${pageView.pageNow gt 1}">
		<div class="txt_css"><div class="disabled"><a href="javascript:void(0);" onclick="return pageNow('${pageView.pageNow - 1}')"> &lt; 上一页 </a></div></div>
	</c:if>
	<c:forEach begin="${pageView.pageindex.startindex}"	end="${pageView.pageindex.endindex}" var="key">
		<c:if test="${pageView.pageNow==key}">
			<div class="txt_css"><div class="txt_color_c">${key}</div></div>
		</c:if>
		<c:if test="${pageView.pageNow!=key}">
		<div class="txt_css"><div class="yema"><a href="javascript:void(0);" onclick="pageNow('${key}')">${key}</a></div></div>
		</c:if>
	</c:forEach>
    <c:if test="${pageView.pageNow lt pageView.pageCount}">
	    <div class="txt_css"><div class="disabled"><a href="javascript:void(0);" onclick="pageNow('${pageView.pageNow + 1}')"> 下一页 &gt;</a></div></div>
	</c:if>
	<c:if test="${pageView.pageNow ge pageView.pageCount}">
		<div class="txt_css"><div class="disabled"> <div class="end_color">下一页 &gt;</div> </div></div>
	</c:if>
	<div class="txt_css"><div class="disabled"><a href="javascript:void(0);" onclick="pageNow('${pageView.pageCount}')"> 尾页 </a></div></div>
</div>
</div>
</c:if>