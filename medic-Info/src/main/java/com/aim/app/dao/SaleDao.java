package com.aim.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.aim.app.model.Customer;
import com.aim.app.model.Doctor;
import com.aim.app.model.Sale;

@Component
public class SaleDao implements Dao<Sale> {

	private JdbcTemplate jdbc;

	public SaleDao(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	private RowMapper<Doctor> rowMapperDoc = new RowMapper<Doctor>() {
		@Override
		public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
			Doctor t = new Doctor();
			t.setId(rs.getInt(1));
			t.setName(rs.getString(2));
			t.setAddress(rs.getString(3));
			t.setPhone(rs.getString(4));
			return t;
		}
	};
	
	private RowMapper<Customer> rowMapperCust = new RowMapper<Customer>() {
		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer t = new Customer();
			t.setId(rs.getInt(1));
			t.setName(rs.getString(2));
			t.setAddress(rs.getString(3));
			t.setPhone(rs.getString(4));
			return t;
		}
	};
	
	@Override
	public boolean add(Sale obj) {
		return jdbc.update("insert into sale values(null,?,?,?,?)", 
				obj.getSaleDate(), obj.getCustomer().getId(),
				obj.getDoctor().getId(),obj.getUser().getId()) == 1;
	}

	@Override
	public boolean update(Sale obj) {
		return jdbc.update("update sale set sal_date=?, customer_id=?,doctor_id=?, shop_Admin_id=? where id=?",
				obj.getSaleDate(), obj.getCustomer().getId(), obj.getDoctor().getId(), obj.getUser().getId(), obj.getId()) == 1;
	}

	@Override
	public boolean delete(Sale obj) {
		return jdbc.update("delete from sale where id=?",
		            obj.getId()) == 1;
	}
	
	private RowMapper<Sale> rowMapper = new RowMapper<Sale>() {
		@Override
		public Sale mapRow(ResultSet rs, int rowNum) throws SQLException {
			Sale t = new Sale();
			t.setId(rs.getInt(1));
			t.setSaleDate(rs.getDate(2));
			t.getCustomer().setId(rs.getInt(3));
			t.getDoctor().setId(rs.getInt(4));
			t.getUser().setId(rs.getInt(5));
			t.getCustomer().setName(rs.getString(6));
			t.getCustomer().setAddress(rs.getString(7));
			t.getCustomer().setPhone(rs.getString(8));
			t.getDoctor().setName(rs.getString(9));
			t.getDoctor().setAddress(rs.getString(10));
			t.getDoctor().setPhone(rs.getString(11));	
			return t;
		}
	};

	@Override
	public Sale get(Sale obj) {
		List<Sale> list=
				jdbc.query("select * from view_sale where id=?", 
						rowMapper, obj.getId());
		if(list.isEmpty()) return null;
		return list.get(0);
	}

	@Override
	public List<Sale> gets() {
		return jdbc.query("select * from view_sale",rowMapper);
	}

	@Override
	public List<Sale> gets(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
