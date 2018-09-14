package com.adatapost.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {
    public AdminFilter() {
    }
	public void destroy() {
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		  //Create wrapper objects
		   HttpServletRequest req = (HttpServletRequest) request;
		   HttpServletResponse res = (HttpServletResponse) response;
		   
		   //Obtain the Session object
		   HttpSession session = req.getSession();
		   if(session.getAttribute("isAdmin") == null) {
			   req.getRequestDispatcher("/login").forward(req, res);
			   return;
		   }
		   chain.doFilter(req, res);
		
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}
}
