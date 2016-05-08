package org.jymf.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecuritypFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, 
			             ServletResponse response, 
			             FilterChain chain) throws IOException,
			ServletException {

		//以下目录不需要判断
		// 1、/admin/login 
		// 2、/company/login
		
		if (!(req instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {  
	        throw new ServletException("OncePerRequestFilter just supports HTTP requests");  
	    }  
	    HttpServletRequest httpRequest = (HttpServletRequest) req;  
	    HttpServletResponse httpResponse = (HttpServletResponse) response;  
	    HttpSession session = httpRequest.getSession(true);  
	  
	    String url = httpRequest.getRequestURI();  
	    String ctx_path = httpRequest.getContextPath(); 
	    
	    if (url.substring(ctx_path.length()).startsWith("/admin/login") ||
	    	url.substring(ctx_path.length()).startsWith("/company/login")||
	    	url.substring(ctx_path.length()).startsWith("/agent/login")||
	    	url.substring(ctx_path.length()).startsWith("/monitor/login")){
	    	chain.doFilter(httpRequest, response);
	    	return;
	    } 
	    	  
	    if(url.substring(ctx_path.length()).contains("product/save") ||
	    		url.substring(ctx_path.length()).contains("dialog")){
	    	chain.doFilter(httpRequest, response);
	    	return;
	    }
	    
	    if(url.substring(ctx_path.length()).startsWith("/admin/")){
	    	Object object = session.getAttribute(Constants.SESSION_ADMIN);  
	  	    if (object == null) {  
	  	        httpResponse.sendRedirect(ctx_path+"/admin/login");  
	  	        return;  
	  	    }  
	    }
	  
	    if(url.substring(ctx_path.length()).startsWith("/company/")){
	    	Object object = session.getAttribute(Constants.SESSION_COMPANY_USER);  
	  	    if (object == null) {  
	  	        httpResponse.sendRedirect(ctx_path+"/company/login");  
	  	        return;  
	  	    }  
	    }
	    
	    if(url.substring(ctx_path.length()).startsWith("/agent/")){
	    	Object object = session.getAttribute(Constants.SESSION_AGENT);  
	  	    if (object == null) {  
	  	        httpResponse.sendRedirect(ctx_path+"/agent/login");  
	  	        return;  
	  	    }  
	    }
	    
	    if(url.substring(ctx_path.length()).startsWith("/monitor/")){
	    	Object object = session.getAttribute("companyMonitors");  
	  	    if (object == null) {  
	  	        httpResponse.sendRedirect(ctx_path+"/monitor/login");  
	  	        return;  
	  	    }  
	    }
	        
		chain.doFilter(httpRequest, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
