package com.adatapost.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adatapost.U;
import com.adatapost.model.UserAccount;
import com.adatapost.model.UserAccountDao;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/admin/my-login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		UserAccount model = new UserAccount();
		
		model.setUserEmail(U.getString(request, "userEmail"));
		model.setUserPass(U.getString(request, "userPass"));

		String cmd = U.getString(request, "cmd");
		String message = "";

		if ("Login".equals(cmd)) {
			model = UserAccountDao.login(model);
			message = model == null ? "Invalid username or password" : "You're authenticated successfully!!!";
		}

		request.setAttribute("message", message);
		request.getRequestDispatcher("/admin/my-login.jsp").forward(request, response);
	}
}
