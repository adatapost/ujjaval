package com.aim.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.aim.app.model.Customer;
@Component
public class CustomerDao implements Dao<Customer>{
	private JdbcTemplate jdbc;
	
	public CustomerDao(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	} 
	
	@Override
	public boolean add(Customer obj) {
		List<Customer> list=
				jdbc.query("select * from customer where phone=?", 
						rowMapper, obj.getPhone());
		if(list.isEmpty())
		return jdbc.update("insert into customer values(null,?,?,?)",obj.getName(),obj.getAddress(),obj.getPhone()) == 1;
		
		return true;
	}

	@Override
	public boolean update(Customer obj) {
		return jdbc.update("update customer set name=?, address=?, phone=? where id=?",obj.getName(),obj.getAddress(),obj.getPhone(),obj.getId()) == 1;
	}

	@Override
	public boolean delete(Customer obj) {
		return jdbc.update("delete from customer where id=?",
	            obj.getId()) == 1;
	}
	
	private RowMapper<Customer> rowMapper = new RowMapper<Customer>() {
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
	public Customer get(Customer obj) {
		List<Customer> list=
				jdbc.query("select * from customer where id=?", 
						rowMapper, obj.getId());
		if(list.isEmpty()) return null;
		return list.get(0);
	}

	@Override
	public List<Customer> gets() {
		return jdbc.query("select * from customer",rowMapper);
	}

	@Override
	public List<Customer> gets(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
