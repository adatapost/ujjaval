package com.aim.app;

import javax.servlet.http.HttpSession;

import com.aim.app.model.U;
import com.aim.app.model.User;

public class SessionWrapper {
	public static boolean isLogged(HttpSession session) {
		return U.toInt(session.getAttribute("id"))!=0;
	}
	public static String role(HttpSession session) {
		return session.getAttribute("role") == null ? "" : session.getAttribute("role").toString();
	}
	public static User user(HttpSession session) {
		return (User)session.getAttribute("user");
	}

}
