package com.aim.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.aim.app.model.PurchaseDetail;
import com.aim.app.util.Util;
@Component
public class PurchaseDetailDao implements Dao<PurchaseDetail> {
	
	private JdbcTemplate jdbc;
	
	public PurchaseDetailDao(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	@Override
	public boolean add(PurchaseDetail obj) {
		return jdbc.update("insert into purchase_detail values(null,?,?,?,?,?,?,?)",
				obj.getPurchase().getId(),obj.getMedicine().getId(),
				obj.getQty(), obj.getRate(),Util.utilToSql(obj.getExpireDate()),obj.getBatchNo(),
				obj.getUser().getId()) == 1;
	}

	@Override
	public boolean update(PurchaseDetail obj) {
		return jdbc.update("update purchase_detail set purchase_id=?,medicine_id=?,qty=?,rate=?,expire_date=?,batch_no=?,shop_admin_id=? where det_id=?",
				obj.getPurchase().getId(),obj.getMedicine().getId(),obj.getQty(),obj.getRate(),obj.getExpireDate(),obj.getBatchNo(),obj.getUser().getId(),obj.getDetId())==1;
	}

	@Override
	public boolean delete(PurchaseDetail obj) {
		return jdbc.update("delete from purchase_detail where det_id=?",
	            obj.getDetId()) == 1;
	}
	
	private RowMapper<PurchaseDetail> rowMapper = new RowMapper<PurchaseDetail>() {
			@Override
			public PurchaseDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
				PurchaseDetail t = new PurchaseDetail();
				t.setDetId(rs.getInt(1));
				t.getPurchase().setId(rs.getInt(2));
				t.getMedicine().setId(rs.getInt(3));
				t.setQty(rs.getDouble(4));
				t.setRate(rs.getDouble(5));
				t.setExpireDate(rs.getDate(6));
				t.setBatchNo(rs.getString(7));
				t.getUser().setId(rs.getInt(8));
				t.getMedicine().setName(rs.getString(9));
				return t;
			}
		};

	@Override
	public PurchaseDetail get(PurchaseDetail obj) {
		List<PurchaseDetail> list=
				jdbc.query("select * from view_purchase_detail where det_id=?", 
						rowMapper, obj.getDetId());
		if(list.isEmpty()) return null;
		return list.get(0);
	}

	@Override
	public List<PurchaseDetail> gets() {
		return jdbc.query("select * from view_purchase_detail",rowMapper);
	}

	@Override
	public List<PurchaseDetail> gets(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
