package com.adatapost.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adatapost.U;
import com.adatapost.model.Country;
import com.adatapost.model.CountryDao;
import com.adatapost.model.State;
import com.adatapost.model.StateDao;

/**
 * Servlet implementation class StateServlet
 */
@WebServlet("/admin/state")
public class StateServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("countries", CountryDao.gets());
		request.setAttribute("states", StateDao.gets(0));
		request.getRequestDispatcher("/admin/my-state.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		State state = new State();
		state.setCountryId(U.getInt(request, "countryId"));
		state.setStateId(U.getInt(request, "stateId"));
		state.setStateName(U.getString(request, "stateName"));
		String cmd = U.getString(request, "cmd");
		String message = "";
		System.out.println(state);

		if ("Add".equals(cmd)) {
			message = StateDao.add(state) ? "Added" : "Failed";
		}

		request.setAttribute("message", message);
		request.setAttribute("model", state);

		request.setAttribute("countries", CountryDao.gets());
		request.setAttribute("states", StateDao.gets(state.getCountryId()));
		request.getRequestDispatcher("/admin/my-state.jsp").forward(request, response);
	}
}
