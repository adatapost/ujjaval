package com.adatapost;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

public class U {
	public static int getInt(HttpServletRequest request, String key) {
		return toInt( request.getParameter(key));
	}
	public static String getString(HttpServletRequest request, String key) {
		return request.getParameter(key);
	}
	
	public static int toInt( String value ) {
		try {
			return Integer.parseInt(value);
		} catch(Exception ex) {
			
		}
		return 0;
	}
	public static java.util.Date toDate(String value, String pattern) {
		try {
			return  new SimpleDateFormat(pattern).parse(value);
		} catch(Exception ex) {
			
		}
		return  null;
	}
	
	public static java.sql.Date utilToSql( java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}
}
