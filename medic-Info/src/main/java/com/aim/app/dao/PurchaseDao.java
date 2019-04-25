package com.aim.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.aim.app.model.Purchase;
import com.aim.app.util.Util;
@Component
public class PurchaseDao implements Dao<Purchase> {
	
	private JdbcTemplate jdbc;
	
	public PurchaseDao(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
//id	pur_date	supplier_id	shop_admin_id
	@Override
	public boolean add(Purchase obj) {
		return jdbc.update("insert into purchase values (null,?,?,?)",obj.getPurDate(), 
				obj.getSupplier().getId(),obj.getUser().getId())==1;
	}
	
	@Override
	public boolean update(Purchase obj) {
	return jdbc.update("update purchase set pur_date=? , supplier_id=?,shop_admin_id=? where id=?",
					obj.getPurDate(),
					obj.getSupplier().getId(),obj.getUser().getId(), obj.getId())==1;
	}

	@Override
	public boolean delete(Purchase obj) {
		return jdbc.update("delete from purchase where id=?",
	            obj.getId()) == 1;
	}
	
	private RowMapper<Purchase> rowMapper = new RowMapper<Purchase>() {
		@Override
		public Purchase mapRow(ResultSet rs, int rowNum) throws SQLException {
			Purchase t = new Purchase();
			t.setId(rs.getInt(1));
			t.setPurDate(rs.getDate(2));
			t.getSupplier().setId(rs.getInt(3));
			t.getUser().setId(rs.getInt(4));
			t.getSupplier().setName(rs.getString(5));
			t.getSupplier().setAddress(rs.getString(6));
			t.getSupplier().setCity(rs.getString(7));
			t.getSupplier().setPhone(rs.getString(8));
			return t;
		}
	};
	
	@Override
	public Purchase get(Purchase obj) {
		List<Purchase> list=
				jdbc.query("select * from view_purchase where id=?", 
						rowMapper, obj.getId());
		if(list.isEmpty()) return null;
		return list.get(0);
	}

	@Override
	public List<Purchase> gets() {
		return jdbc.query("select * from view_purchase",rowMapper);
	}

	@Override
	public List<Purchase> gets(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
