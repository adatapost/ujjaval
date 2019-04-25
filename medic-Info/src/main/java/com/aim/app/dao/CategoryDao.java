package com.aim.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;



import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.aim.app.model.Category;

@Component
public class CategoryDao implements Dao<Category> {

	private JdbcTemplate jdbc;

	public CategoryDao(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public boolean add(Category obj) {
		return jdbc.update("insert into category values (null,?)",
	            obj.getName()) == 1;
	}

	@Override
	public boolean update(Category obj) {
		return jdbc.update("update category set name=? where id=?",
	            obj.getName(), obj.getId()) == 1;
	}

	@Override
	public boolean delete(Category obj) {
		return jdbc.update("delete from category where id=?",
	            obj.getId()) == 1;
	}
	
	
	private RowMapper<Category> rowMapper = new RowMapper<Category>() {
		@Override
		public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
			Category t = new Category();
			t.setId(rs.getInt(1));
			t.setName(rs.getString(2));
			return t;
		}
	};
	
	@Override
	public Category get(Category obj) {
		List<Category> list=jdbc.query("select * from category where id=?", 
						rowMapper, obj.getId());
		if(list.isEmpty()) return null;
		return list.get(0);
	}

	@Override
	public List<Category> gets() {
		return jdbc.query("select * from category",rowMapper);
	}

	@Override
	public List<Category> gets(int id) {
		throw new RuntimeException("Not Implemented");
	}

}
