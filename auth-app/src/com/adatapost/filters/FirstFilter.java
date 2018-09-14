package com.adatapost.filters;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class FirstFilter
 */
@WebFilter("/download/*")
public class FirstFilter implements Filter {
	public void doFilter(ServletRequest request, 
			ServletResponse response, FilterChain chain) throws IOException, ServletException {
		  //Create wrapper objects
		   HttpServletRequest req = (HttpServletRequest) request;
		   HttpServletResponse res = (HttpServletResponse) response;
		   
		   String requestUri = req.getRequestURI();
		   String []parts = requestUri.split("/");
		   for(String p: parts) {
			   System.out.println(p);
		   }
		   if( parts.length != 4) {
			   res.getWriter().print("Invalid resource!!!");
			   return;
		   }  
		   String resource = parts[3];
		   
		   HashMap<String, String> map = new HashMap<>();
		   map.put("googoosh", "one.mp4");
		   map.put("reena", "two.png");
		   map.put("seminar", "three.pdf");
		   
		   if(!map.containsKey(resource)) {
			   res.getWriter().print("Given resource is not found!");
			   return;
		   }
		   
		  //Convert virtual or relative path to absolute path
		   String relativePath = "/files/" + map.get(resource);
		   String absPath = req.getServletContext().getRealPath(relativePath);
		   File file= new File(absPath);
		   
		   //Set Response Headers/Instructions
		   res.addHeader("content-type", "application/octate-stream");
		   res.addHeader("content-length", String.valueOf(file.length()));
		   res.addHeader("content-disposition", "attachment; filename=" + map.get(resource));

		   try(BufferedInputStream in = 
				   new BufferedInputStream(
						   new FileInputStream(absPath))) {
			   byte []bytes = new byte[8012];
			   int numRead = 0;
			   while( (numRead = in.read(bytes,0,bytes.length))>0 ) {
				   res.getOutputStream().write(bytes, 0, numRead );
			   }
			   res.getOutputStream().flush();
		   }catch(Exception ex) {
			  System.out.println(ex);   
		   }
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}
	public FirstFilter() {
	}
	public void destroy() {
	}
}
