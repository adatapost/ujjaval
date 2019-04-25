package com.aim.app.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Util {
	
	public static int toInt(String val) {
		try {
			return Integer.parseInt(val);
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}
	
	public static Date utilToSql(java.util.Date date) {
		return new Date(date.getTime());
	}
	public static java.util.Date toDate(String value, String pattern) {
		try {
			return  new SimpleDateFormat(pattern).parse(value);
		} catch(Exception ex) {
			
		}
		return  null;
	}

}
