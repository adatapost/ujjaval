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

@WebServlet("/admin/country")
public class CountryServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("countries", CountryDao.gets());
		request.getRequestDispatcher("/admin/my-country.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Country country = new Country();
		country.setCountryId(U.getInt(request, "countryId"));
		country.setCountryName(U.getString(request, "countryName"));
		String cmd = U.getString(request, "cmd");
		String message = "";

		if ("Add".equals(cmd)) {
			message = CountryDao.add(country) ? "Added" : "Failed";
		}

		request.setAttribute("message", message);
		request.setAttribute("model", country);
		request.setAttribute("countries", CountryDao.gets());
		request.getRequestDispatcher("/admin/my-country.jsp").forward(request, response);
	}
}
