package com.aim.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.aim.app.model.Role;
@Component
public class RoleDao implements Dao<Role> {

	private JdbcTemplate jdbc;

	public RoleDao(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public boolean add(Role obj) {
		return jdbc.update("insert into role values(null,name)", obj.getName()) == 1;
	}

	@Override
	public boolean update(Role obj) {
		return jdbc.update("update role set name=? where id=?", obj.getName(), obj.getId()) == 1;
	}

	@Override
	public boolean delete(Role obj) {
		return jdbc.update("delete from role where id=?", obj.getId()) == 1;
	}

	public RowMapper<Role> rowMapper = new RowMapper<Role>() {

		@Override
		public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
			Role t = new Role();
			t.setId(rs.getInt(1));
			t.setName(rs.getString(2));
			return t;
		}
	};

	@Override
	public Role get(Role obj) {
		List<Role> list = jdbc.query("select * from role where id=?", rowMapper, obj.getId());
		if (list.isEmpty())
			return null;
		return list.get(0);
	}

	@Override
	public List<Role> gets() {
		return jdbc.query("select * from role",rowMapper);
	}

	@Override
	public List<Role> gets(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
