package com.aim.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.aim.app.model.U;
import com.aim.app.model.User;

@Component
public class UserDao implements Dao<User> {

	private JdbcTemplate jdbc;

	public UserDao(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

//	user_id(pk)	email	pass	role_id	active
// user_id	last_name	first_name	address	city	gender	phone	photo	lat_cor	long_cor
	@Override
	public boolean add(User obj) {
		String sql = "insert into user values (null,?,?,?,?)";
		obj.setPass(U.toEncrypt(obj.getPass()));
		
		jdbc.update(sql, obj.getEmail(), obj.getPass(), obj.getRole().getId(), obj.isActive());
		sql = "select user_id from user where email=?";
		int id = jdbc.query(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt(1);
			}
		}, obj.getEmail()).get(0);
		sql = "insert into user_profile values(?,?,?,?,?,?,?,?,?,?)";
		return jdbc.update(sql, id, obj.getLastName(), 
				obj.getFirstName(), 
				obj.getAddress(), obj.getCity(),
				obj.getGender(), obj.getPhone(), obj.getPhoto(), obj.getLatCor(), obj.getLongCor()) == 1;
	}

	@Override
	public boolean update(User obj) {

		String sql = "select user_id from user where email=?";
		int id = jdbc.query(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt(1);
			}
		}, obj.getEmail()).get(0);
		sql = "update user_profile set last_name=?,first_name=?,address=?,city=?,gender=?,phone=?,photo=?,lat_cor=?,long_cor=? where user_id=?";
		return jdbc.update(sql, obj.getLastName(), obj.getFirstName(), obj.getAddress(), obj.getCity(), obj.getGender(),
				obj.getPhone(), obj.getPhoto(), obj.getLatCor(), obj.getLongCor(), id) == 1;
	}

	@Override
	public boolean delete(User obj) {
		return jdbc.update("update user set active=? where email=?", false, obj.getEmail()) == 1;
	}
	
	public boolean login(User obj) {
		User search = this.get(obj);
		if(search == null) {
			return false;
		}
		
		boolean result = U.comparePassword(obj.getPass(), search.getPass());
		if(result)
			obj = search;
		return result;
	}

	public boolean changePassword(User obj, String newPassword) {
		User search = this.get(obj);
		if(search == null) {
			return false;
		}
		
		boolean result = U.comparePassword(obj.getPass(), search.getPass());
		if(result){
			return jdbc.update("update user set pass=? where email=?", obj.getEmail(), U.toEncrypt(newPassword)) == 1;
		}
		return result;
	}
//	user_id last_name first_name address city gender phone 
//	photo lat_cor long_cor email pass role_id active name

	private RowMapper<User> rowMapper = new RowMapper<User>() {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User t = new User();
			t.setId(rs.getInt(1));
			t.setLastName(rs.getString(2));
			t.setFirstName(rs.getString(3));
			t.setAddress(rs.getString(4));
			t.setCity(rs.getString(5));
			t.setGender(rs.getString(6));
			t.setPhone(rs.getString(7));
			t.setPhoto(rs.getBytes(8));
			t.setLatCor(rs.getString(9));
			t.setLongCor(rs.getString(10));
			t.setEmail(rs.getString(11));
			t.setPass(rs.getString(12));
			t.getRole().setId(rs.getInt(13));
			t.setActive(rs.getBoolean(14));
			t.getRole().setName(rs.getString(15));
			return t;
		}
	};

	@Override
	public User get(User obj) {
		List<User> list = jdbc.query("select * from view_user where user_id=? or email=?", rowMapper, obj.getId(),
				obj.getEmail());
		if (list.isEmpty())
			return null;
		return list.get(0);
	}

	@Override
	public List<User> gets() {
		return jdbc.query("select * from view_user", rowMapper);
	}

	@Override
	public List<User> gets(int id) {
		List<User> list1 = jdbc.query("select * from view_user where user_id=?", rowMapper, id);
		if (list1.isEmpty())
			return null;
		return (List<User>) list1.get(0);
	}


	public boolean isExist(int id) {
		return get(new User(id)) != null;
	}

	public boolean isExist(String email) {
		return get(new User(email)) != null;
	}
}
