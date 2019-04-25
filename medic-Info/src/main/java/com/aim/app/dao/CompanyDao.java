package com.aim.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.aim.app.model.Category;
import com.aim.app.model.Company;
@Component
public class CompanyDao implements Dao<Company> {

	private JdbcTemplate jdbc;

	public CompanyDao(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public boolean add(Company obj) {
		return jdbc.update("insert into company values (null,?,?,?,?,?)", obj.getName(), obj.getAddress(), obj.getCity(),
				obj.getWebsite(), obj.getPhone()) == 1;
	}

	@Override
	public boolean update(Company obj) {
		return jdbc.update("update company set name=?, address=?, city=?, website=?, phone=? where id=?", obj.getName(),
				obj.getAddress(), obj.getCity(), obj.getWebsite(), obj.getPhone(), obj.getId()) == 1;
	}

	@Override
	public boolean delete(Company obj) {
		return jdbc.update("delete from company where id=?",
	            obj.getId()) == 1;
	}
	private RowMapper<Company> rowMapper = new RowMapper<Company>() {
		@Override
		public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
			Company t = new Company();
			t.setId(rs.getInt(1));
			t.setName(rs.getString(2));
			t.setAddress(rs.getString(3));
			t.setCity(rs.getString(4));
			t.setWebsite(rs.getString(5));
			t.setPhone(rs.getString(6));
			return t;
		}
	};
	@Override
	public Company get(Company obj) {
		List<Company> list=jdbc.query("select * from company where id=?", 
				rowMapper, obj.getId());
			if(list.isEmpty()) return null;
			return list.get(0);
	}

	@Override
	public List<Company> gets() {
		return jdbc.query("select * from company",rowMapper);
	}

	@Override
	public List<Company> gets(int id) {
		throw new RuntimeException("Not Implemented");
	}

}
