package com.adatapost.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.adatapost.U;
import com.adatapost.model.UserAccount;
import com.adatapost.model.UserAccountDao;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate(); // Re-create the current session
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		UserAccount model = new UserAccount();
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(50000);
		 
		String url = "/login.jsp";
		
		model.setUserEmail(U.getString(request, "userEmail"));
		model.setUserPass(U.getString(request, "userPass"));

		String cmd = U.getString(request, "cmd");
		String message = "";
		session.removeAttribute("isLogged");
		session.removeAttribute("isAdmin");
		session.removeAttribute("isCustomer");
		if ("Login".equals(cmd)) {
			model = UserAccountDao.login(model);
			message = model == null ? "Invalid username or password" : "You're authenticated successfully!!!";
			if(model!=null) {
				session.setAttribute("isLogged", "Yes");
				if("Admin".equals(model.getUserRole())) {
					session.setAttribute("isAdmin", 1);
					url = "/admin/my-country.jsp";
				}
				if("Guest".equals(model.getUserRole())) {
					session.setAttribute("isCustomer", 1);
					url = "/customer/index.jsp";
				}
			}
		}

		request.setAttribute("message", message);
		request.getRequestDispatcher(url).forward(request, response);
	}
}
