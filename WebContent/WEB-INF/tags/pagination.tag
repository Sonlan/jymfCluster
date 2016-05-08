<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="org.springframework.data.domain.Page" required="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
int current =  page.getNumber() + 1;
request.setAttribute("current", current);
%>

<div class="pagination">
	<ul>
		 <% if (page.hasPreviousPage()){%>
               	<li><a href="?page=1&sortType=${sortType}&${searchParams}">首页</a></li>
                <li><a href="?page=${current-1}&sortType=${sortType}&${searchParams}">上一页</a></li>
         <%}else{%>
                <li class="disabled"><a href="#">首页</a></li>
                <li class="disabled"><a href="#">上一页</a></li>
         <%} %>
   
	  	 <% if (page.hasNextPage()){%>
               	<li><a href="?page=${current+1}&sortType=${sortType}&${searchParams}">下一页</a></li>
                <li><a href="?page=${page.totalPages}&sortType=${sortType}&${searchParams}">尾页</a></li>
         <%}else{%>
                <li class="disabled"><a href="#">尾页</a></li>
                <li class="disabled"><a href="#">下一页</a></li>
         <%} %>

	</ul>
</div>

