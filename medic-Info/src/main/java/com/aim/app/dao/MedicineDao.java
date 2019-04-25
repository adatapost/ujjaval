package com.aim.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.aim.app.model.Medicine;
@Component
public class MedicineDao implements Dao<Medicine> {

	private JdbcTemplate jdbc;

	public MedicineDao(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public boolean add(Medicine obj) {
		return jdbc.update("insert into medicine (id,name,company_id,category_id,available,is_prescribed,unit,reorder_level,shop_admin_id)values (null,?,?,?,?,?,?,?,?)", 
				obj.getName(),
				obj.getCompany().getId(), 
				obj.getCategory().getId(), 
				obj.isAvailable(), 
				obj.isPrescribed(),
				obj.getUnit(), 
				obj.getReorderLevel(), obj.getUser().getId()) == 1;
	}

	@Override
	public boolean update(Medicine obj) {
		return jdbc.update(
				"update medicine set name=?, company_id=?, category_id=?, available=?,is_prescribed=?, unit=?, reorder_level=?, shop_admin_id=? where id=?",
				obj.getName(), obj.getCompany().getId(), obj.getCategory().getId(), obj.isAvailable(),
				obj.isPrescribed(), obj.getUnit(), obj.getReorderLevel(), obj.getUser().getId(), obj.getId()) == 1;
	}

	@Override
	public boolean delete(Medicine obj) {
		return jdbc.update("delete from medicine where id=?", obj.getId()) == 1;
	}
	
	private RowMapper<Medicine> rowMapper = new RowMapper<Medicine>() {
		@Override
		public Medicine mapRow(ResultSet rs, int rowNum) throws SQLException {
			Medicine t = new Medicine();
			t.setId(rs.getInt(1));
			t.setName(rs.getString(2));
			t.getCompany().setId(rs.getInt(3));
			t.getCategory().setId(rs.getInt(4));
			t.setAvailable(rs.getBoolean(5));
			t.setPrescribed(rs.getBoolean(6));
			t.setUnit(rs.getString(7));
			t.setReorderLevel(rs.getDouble(8));
			t.getUser().setId(rs.getInt(9));
			t.getCompany().setName(rs.getString(10));
			t.getCompany().setAddress(rs.getString(11));
			t.getCompany().setCity(rs.getString(12));
			t.getCompany().setWebsite(rs.getString(13));
			t.getCompany().setPhone(rs.getString(14));
			t.getCategory().setName(rs.getString(15));
			return t;
		}
	};

	@Override
	public Medicine get(Medicine obj) {
		List<Medicine> list = jdbc.query("select * from view_medicine where id=?", rowMapper, obj.getId());
		if (list.isEmpty())
			return null;
		return list.get(0);
	}

	@Override
	public List<Medicine> gets() {
		return jdbc.query("select * from view_medicine",rowMapper);
	}
	
	public Medicine search(Medicine obj) {
		List<Medicine> list = jdbc.query("select * from view_medicine where name LIKE ?", rowMapper,"%"+obj.getName()+"%");
		if (list.isEmpty()) { System.out.println("null");
			return null;}
		System.out.println(list.get(0));
		return list.get(0);
	}

	@Override
	public List<Medicine> gets(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
