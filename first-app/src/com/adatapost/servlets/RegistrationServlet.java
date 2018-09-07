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

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/admin/my-register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserAccount model = new UserAccount();
		String userPass1 = U.getString(request, "userPass1");
		model.setUserEmail(U.getString(request, "userEmail"));
		model.setUserPass(U.getString(request, "userPass"));
		model.setUserRole("Guest");
		model.setDeleted(false);
		String cmd = U.getString(request, "cmd");
		String message = "";

		if ("Add".equals(cmd) && userPass1.equals(model.getUserPass())) {
			message = UserAccountDao.add(model) ? "User account created successfully"
					: "Please verify your email. It is taken by someone else";
		} else {
			message = "User password and confirm are not match!!!";
		}

		request.setAttribute("message", message);
		request.setAttribute("model", model);
		request.getRequestDispatcher("/admin/my-register.jsp").forward(request, response);
	}
}
