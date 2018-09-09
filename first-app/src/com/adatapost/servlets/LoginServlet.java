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
		request.getRequestDispatcher("/admin/my-login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		UserAccount model = new UserAccount();
		HttpSession session = request.getSession();
		String url = "/admin/my-login.jsp";
		
		model.setUserEmail(U.getString(request, "userEmail"));
		model.setUserPass(U.getString(request, "userPass"));

		String cmd = U.getString(request, "cmd");
		String message = "";
		session.removeAttribute("isLogged");
		session.removeAttribute("isAdmin");
		session.removeAttribute("isGuest");
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
					session.setAttribute("isGuest", 1);
					
					url = "/admin/my-state.jsp";
				}
			}
		}

		request.setAttribute("message", message);
		request.getRequestDispatcher(url).forward(request, response);
	}
}
