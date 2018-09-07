package com.adatapost;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Db implements AutoCloseable {

	// Database specific constants
	private static final String URL = "jdbc:mysql://localhost/sample_db";
	private static final String USER = "root";
	private static final String PASS = "";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	
	static {
		try {
			Class.forName(DRIVER);
			
			
		} catch (Exception ex) {
			System.out.println("Error while loading JDBC Driver!!!" + ex);
		}
	}

	// JDBC Resource instances
	private Connection cn = null;
	private PreparedStatement ps = null;

	// Constructor
	public Db(String sql) throws Exception {
		cn = DriverManager.getConnection(URL, USER, PASS);
		ps = cn.prepareStatement(sql);
	}

	// Returns the statement object
	public PreparedStatement getPs() {
		return ps;
	}

	// Execute the non-Queries (Other that SELECT query)
	public boolean execute() throws Exception {
		return ps.executeUpdate() > 0;
	}

	// Execute the SELECT (Query) which returns single row.
	public Object[] row() throws Exception {
		Object[] ar = null;
		try (ResultSet rs = ps.executeQuery()) {
			int count = rs.getMetaData().getColumnCount();
			if (rs.next()) {
				ar = new Object[count];
				for (int i = 0; i < count; i++) {
					ar[i] = rs.getObject(i + 1);
				}
			}

		} catch (Exception ex) {
			throw new Exception("Error while executing 'row' method", ex);
		}
		return ar;
	}

	// Execute the SELECT (Query) which returns multiple rows.
	public List<Object[]> rows() throws Exception {
		List<Object[]> list = new ArrayList<>();
		Object[] ar = null;
		try (ResultSet rs = ps.executeQuery()) {
			int count = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				ar = new Object[count];
				for (int i = 0; i < count; i++) {
					ar[i] = rs.getObject(i + 1);
				}
				list.add(ar);
			}

		} catch (Exception ex) {
			throw new Exception("Error while executing 'rows' method", ex);
		}
		return list;
	}

	// Dispose the JDBC Resources
	@Override
	public void close() throws Exception {
		if (ps != null)
			ps.close();
		if (cn != null)
			cn.close();
	}
}
