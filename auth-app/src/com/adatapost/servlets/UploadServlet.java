package com.adatapost.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig()
public class UploadServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/upload.jsp")
		.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		Part file = request.getPart("file");
		String message = "Cannot upload file";
		
		if(file!=null  && file.getSize() !=0 ) {
		  System.out.println(file.getHeader("content-disposition"));
		  String []array = file.getHeader("content-disposition")
				  .split("filename=");
		  String fileName = array[1].replaceAll("\"", "");
		  System.out.println(fileName);
		   String absPath = request.getServletContext()
			   	.getRealPath("/data/" + fileName);
		   file.write(absPath);
		   message = "File uploaded successfully!";
		}
		
		request.setAttribute("message", message);
		request.getRequestDispatcher("/upload.jsp")
		.forward(request, response);
	}
}
