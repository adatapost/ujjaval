package com.adatapost.servlets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.adatapost.Db;
import com.adatapost.U;

@WebServlet("/upload-db")
@MultipartConfig()
public class UploadDbServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/upload.jsp")
		.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		Part file = request.getPart("file");
		String message = "Cannot upload file";
		
		if(file!=null  && file.getSize() !=0 ) {
			InputStream in = file.getInputStream();
			try(Db x = new Db("insert into student values (?,?)")) {
				x.getPs().setString(1, name);
				 // x.getPs().setBinaryStream(2, U.getByteStream(in));
				x.getPs().setBlob(2,in);
				//x.getPs().setBinaryStream(2,in);
				
				x.execute();
				message = "File uploaded successfully!";
			} catch(Exception ex) {
				System.out.println(ex);
			}
		}
		
		request.setAttribute("message", message);
		request.getRequestDispatcher("/upload.jsp")
		.forward(request, response);
	}
}
