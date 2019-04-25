package com.aim.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.aim.app.model.Supplier;
@Component
public class SupplierDao implements Dao<Supplier> {

	private JdbcTemplate jdbc;

	public SupplierDao(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	@Override
	public boolean add(Supplier obj) {
		return jdbc.update("insert into supplier values (null,?,?,?,?,?)",
	            obj.getName(),obj.getAddress(), obj.getCity(),obj.getPhone(),obj.getUser().getId()) == 1;
	}

	@Override
	public boolean update(Supplier obj) {
//	id	name	address	city	phone	shop_admin_id
		return jdbc.update("update supplier set name=?, address=?, city=?, phone=?,shop_admin_id=? where id=?",
	            obj.getName(),obj.getAddress(), obj.getCity(), obj.getPhone(),obj.getUser().getId(),obj.getId()) == 1;
	}

	@Override
	public boolean delete(Supplier obj) {
		return jdbc.update("delete from supplier where id=?",
	            obj.getId()) == 1;
	}
	
	private RowMapper<Supplier> rowMapper = new RowMapper<Supplier>() {
		@Override
		public Supplier mapRow(ResultSet rs, int rowNum) throws SQLException {
			Supplier t = new Supplier();
			t.setId(rs.getInt(1));
			t.setName(rs.getString(2));
			t.setAddress(rs.getString(3));
			t.setCity(rs.getString(4));
			t.setPhone(rs.getString(5));
			t.getUser().setId(rs.getInt(6));
			return t;
		}
	};
	
	@Override
	public Supplier get(Supplier obj) {
		List<Supplier> list=
				jdbc.query("select * from supplier where id=?", 
						rowMapper, obj.getId());
		if(list.isEmpty()) return null;
		return list.get(0);
	}

	@Override
	public List<Supplier> gets() {
		return jdbc.query("select * from supplier",rowMapper);
	}

	@Override
	public List<Supplier> gets(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
