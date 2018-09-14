package com.adatapost;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.coyote.http11.filters.BufferedInputFilter;

public class U {
	public static byte[] getBytes( InputStream in) {
		try(BufferedInputStream bs = new BufferedInputStream(in)) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte []array = new byte[8014];
			int numRead = 0;
			while( (numRead = bs.read(array,0, array.length))>0) {
				out.write(array,0,numRead);
			}
			out.toByteArray();
		}catch(Exception ex) {
			System.out.println(ex);
		}
		return null;
	}
	
	public static ByteArrayInputStream getByteStream( InputStream in) {
		try(BufferedInputStream bs = new BufferedInputStream(in)) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte []array = new byte[8014];
			int numRead = 0;
			while( (numRead = bs.read(array,0, array.length))>0) {
				out.write(array,0,numRead);
			}
			return new  ByteArrayInputStream(out.toByteArray());
		}catch(Exception ex) {
			System.out.println(ex);
		}
		return null;
	}
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
