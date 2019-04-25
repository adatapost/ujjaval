package com.aim.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.aim.app.model.SaleDetail;
@Component
public class SaleDetailDao implements Dao<SaleDetail> {
	
	private JdbcTemplate jdbc;

	public SaleDetailDao(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public boolean add(SaleDetail obj) {
		return jdbc.update("insert into sale_detail values (null,?,?,?,?,?,?)",
	             obj.getSale().getId(), 
	            obj.getMedicine().getId(), obj.getQty(), obj.getRate(), 
	            obj.getDiscount(),obj.getUser().getId())==1;
	}
	//det_id	sale_id	medicine_id	qty	rate	discount	shop_admin_id
	@Override
	public boolean update(SaleDetail obj) {
		return jdbc.update("update sale_detail set sale_id=?, medicine_id=?,qty=?,rate=?,discount=?,shop_Admin_id=? where det_id=? ",
	             obj.getSale().getId(), 
	            obj.getMedicine().getId(), obj.getQty(), obj.getRate(), 
	            obj.getDiscount(),obj.getUser().getId(),obj.getDetId())==1;
	}

	@Override
	public boolean delete(SaleDetail obj) {
		return jdbc.update("delete from sale where det_id=?",
	            obj.getDetId()) == 1;
	}
//	det_id	sale_id	medicine_id	qty	rate	discount	shop_admin_id	medicine_name
	public RowMapper<SaleDetail> rowMapper = new RowMapper<SaleDetail>() {
		
		@Override
		public SaleDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
			SaleDetail t = new SaleDetail();
			t.setDetId(rs.getInt(1));
			t.getSale().setId(rs.getInt(2));
			t.getMedicine().setId(rs.getInt(3));
			t.setQty(rs.getInt(4));
			t.setRate(rs.getDouble(5));
			t.setDiscount(rs.getDouble(6));
			t.getUser().setId(rs.getInt(7));
			t.getMedicine().setName(rs.getString(8));
			return t;
		}
	};
	
	@Override
	public SaleDetail get(SaleDetail obj) {
		List<SaleDetail> list=
				jdbc.query("select * from view_sale_detail where det_id=?", 
						rowMapper, obj.getDetId());
		if(list.isEmpty()) return null;
		return list.get(0);
	}

	@Override
	public List<SaleDetail> gets() {
		return jdbc.query("select * from view_sale_detail where det_id !=0",rowMapper);
	}

	@Override
	public List<SaleDetail> gets(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
