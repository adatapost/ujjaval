package com.adatapost.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adatapost.U;
import com.adatapost.model.City;
import com.adatapost.model.CityDao;
import com.adatapost.model.CountryDao;
import com.adatapost.model.State;
import com.adatapost.model.StateDao;

/**
 * Servlet implementation class CityServlet
 */
@WebServlet("/admin/city")
public class CityServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("countries", CountryDao.gets());
		request.setAttribute("states", StateDao.gets(0));
		request.setAttribute("cities", CityDao.gets(0));
		request.getRequestDispatcher("/admin/my-city.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		City city = new City();

		city.setCountryId(U.getInt(request, "countryId"));
		city.setStateId(U.getInt(request, "stateId"));
		city.setCityId(U.getInt(request, "cityId"));
		city.setCityName(U.getString(request, "cityName"));
		String cmd = U.getString(request, "cmd");
		String message = "";

		if ("Add".equals(cmd)) {
			message = CityDao.add(city) ? "Added" : "Failed";
		}

		request.setAttribute("message", message);
		request.setAttribute("model", city);

		request.setAttribute("countries", CountryDao.gets());
		request.setAttribute("states", StateDao.gets(city.getCountryId()));
		request.setAttribute("cities", CityDao.gets(city.getStateId()));
		request.getRequestDispatcher("/admin/my-city.jsp").forward(request, response);
	}
}
