<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
html,*,body, div, ul,ol, li,h1,h2,h3,h4,h5,h6,p,span{margin:0;padding:0;}




#company-count:hover{

}
</style>

<script>
 $(function(){		   
		$("#navul > li").not(".navhome").hover(function(){
			$(this).addClass("navmoon");
		},function(){
			$(this).removeClass("navmoon");
		});
		
	}); 

	(function($){
	    $.fn.capacityFixed = function(options) {
	        var opts = $.extend({},$.fn.capacityFixed.deflunt,options);
	        var FixedFun = function(element) {
	            var top = opts.top;
	            element.css({
	                "top":top
	            });
	            $(window).scroll(function() {
	                var scrolls = $(this).scrollTop();
	                if (scrolls > top) {

	                    if (window.XMLHttpRequest) {
	                        element.css({
	                            position: "fixed",
	                            top: 0							
	                        });
	                    } else {
	                        element.css({
	                            top: scrolls
	                        });
	                    }
	                }else {
	                    element.css({
	                        position: "absolute",
	                        top: top
	                    });
	                }
	            });
	            element.find(".close-ico").click(function(event){
	                element.remove();
	                event.preventDefault();
	            })
	        };
	        return $(this).each(function() {
	            FixedFun($(this));
	        });
	    };
	    $.fn.capacityFixed.deflunt={
			right : 0,//相对于页面宽度的右边定位
	        top:0
		};
	})(jQuery);


</script>

<div class="top">
	<div class="top_left"></div>
</div>

<div id="tops_bg">
	<div class="tops">
		<!--导航开始-->
		<div class="nav_z">
			<ul id="navul" class="cl">
			    <c:forEach var="menu" items="${companyuser.menus}">
			        <c:if test="${companyuser.authority >= menu.authority}">
				    <li>
					   <a id="${menu.id}" href="${ctx}${menu.link}">${menu.title}</a>
					   <ul>
					       <c:forEach var="submenu" items="${menu.submenu}"> 
                               <li><a id="${submenu.id}" href="${ctx}${submenu.link}">${submenu.title}<br></a></li>
                           </c:forEach>
                       </ul>
				    </li>
				    </c:if>
				</c:forEach>
			</ul>
		</div><!--导航结束-->
		<script  type="text/javascript"> 
		$(".navbg").capacityFixed();
		</script>
	</div>
</div>
