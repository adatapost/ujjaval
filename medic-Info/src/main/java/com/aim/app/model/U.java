package com.aim.app.model;

import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class U {
	public static String getString(String key, HttpServletRequest request) {
		String str = request.getParameter(key);
		if (str != null)
			return str;
		return "";
	}

	public static int getInt(String key, HttpServletRequest request) {
		String str = request.getParameter(key);
		try {
			if (str != null)
				return Integer.parseInt(str);
		} catch (Exception ex) {
		}
		return 0;
	}

	public static double getDouble(String key, HttpServletRequest request) {
		String str = request.getParameter(key);
		try {
			if (str != null)
				return Double.parseDouble(str);
		} catch (Exception ex) {
		}
		return 0;
	}

	public static double toDbl(Object key) {
		try {
			if (key != null)
				return Double.parseDouble(key.toString());
		} catch (Exception ex) {
		}
		return 0;
	}

	public static Integer toInt(Object key) {
		try {
			if (key != null)
				return Integer.parseInt(key.toString());
		} catch (Exception ex) {
		}
		return 0;
	}

	public static Date toDate(String dateStr, String pattern) {
		try {
			return new SimpleDateFormat(pattern).parse(dateStr);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return null;
	}

	public static Date toDateFromISO(String dateStr) {
		return U.toDate(dateStr, "yyyy/MM/dd");
	}

	public static Date now() {
		return new Date();

	}

	public static Date toDateFromInd(String dateStr) {
		return U.toDate(dateStr, "dd/MM/yyyy");
	}

	public static String dateToStr(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}

	public static String datetoISO(Date date) {
		return dateToStr(date, "yyyy/MM/dd");
	}

	public static String datetoInd(Date date) {
		return dateToStr(date, "dd/MM/yyyy");
	}

	public static String toEncrypt(String password) {
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		String hash = bc.encode(password);
		return hash;
	}
	
	public static boolean comparePassword(String rawPassword,String encodedPassword) {
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		return bc.matches(rawPassword, encodedPassword);
	}
}
