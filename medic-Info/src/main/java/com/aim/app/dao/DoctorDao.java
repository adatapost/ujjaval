package com.aim.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.aim.app.model.Category;
import com.aim.app.model.Doctor;
@Component
public class DoctorDao implements Dao<Doctor> {

	private JdbcTemplate jdbc;

	public DoctorDao(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public boolean add(Doctor obj) {
		return jdbc.update("insert into doctor values (null,?,?,?)", obj.getName(), obj.getAddress(),
				obj.getPhone()) == 1;
	}

	@Override
	public boolean update(Doctor obj) {
		return jdbc.update("update doctor set name=?,address=?,phone=?", obj.getName(), obj.getAddress(),
				obj.getPhone()) == 1;
	}

	@Override
	public boolean delete(Doctor obj) {
		return jdbc.update("delete from doctor where id=?", obj.getId()) == 1;
	}

	private RowMapper<Doctor> rowMapper = new RowMapper<Doctor>() {
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

	@Override
	public Doctor get(Doctor obj) {
		List<Doctor> list = 
				jdbc.query("select * from doctor where id=?", rowMapper, obj.getId());
		if (list.isEmpty())
			return null;
		return list.get(0);
	}

	@Override
	public List<Doctor> gets() {
		return jdbc.query("select * from Doctor",rowMapper);
	}

	@Override
	public List<Doctor> gets(int id) {
		throw new RuntimeException("Not Implemented");
	}

}
