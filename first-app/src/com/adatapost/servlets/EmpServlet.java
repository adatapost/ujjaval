package com.adatapost.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adatapost.U;
import com.adatapost.model.Emp;
import com.adatapost.model.EmpDao;

@WebServlet("/emp")
public class EmpServlet extends HttpServlet {
	
	// List the resource
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("emps", EmpDao.gets());
		request.setAttribute("emp", new Emp());
		request.getRequestDispatcher("/emp/index.jsp")
		       .forward(request, response);
	}
 
	//Update the resource as well as list
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Receive the client data
		Emp emp = new Emp();
		emp.setId( U.toInt(request.getParameter("id")) );
		emp.setEmpName(request.getParameter("empName"));
		emp.setEmpSalary(U.toInt(request.getParameter("empSalary")));
		emp.setJoinDate( U.toDate(request.getParameter("joinDate"), "yyyy-MM-dd"));
		String cmd = request.getParameter("cmd");
		String message = "";
		
		/* Actions */
		if("Add".equals(cmd)) {
			message = EmpDao.add(emp) ? "Added" : "Can't Add";
		}
		if("Update".equals(cmd)) {
			message = EmpDao.update(emp) ? "Updated" : "Can't Update";
		}
		if("Delete".equals(cmd)) {
			message = EmpDao.delete(emp) ? "Deleted" : "Can't Delete";
		}
		if("ById".equals(cmd)) {
			emp = EmpDao.get(emp);
			if(emp==null) message = "Not found";
		}
		if("Cancel".equals(cmd)) {
			emp = null;
		}
				
		request.setAttribute("emps", EmpDao.gets());
		request.setAttribute("emp", emp);
		request.setAttribute("message", message);
		request.getRequestDispatcher("/emp/index.jsp")
		       .forward(request, response);
	}
}
