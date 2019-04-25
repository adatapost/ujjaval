package com.aim.app.model;

import java.sql.Date;

public class PurchaseDetail {
	
	private int detId;
	private double qty;
	private double rate;
	private String batchNo;
	private Date expireDate;
	private Purchase purchase;
	private Medicine medicine;
	private User user;
	/*	 
 	*   foreign key(purchase_id) references purchase(id),
		foreign key(medicine_id) references medicine(id),
		foreign key(shop_admin_id) references user(user_id)*/
	/**
	 * 
	 */
	public PurchaseDetail() {
		super();
		purchase = new Purchase();
		medicine = new Medicine();
		user = new User();
	}
	/**
	 * @param detId
	 * @param qty
	 * @param rate
	 * @param batchNo
	 * @param exprieDate
	 * @param purchase
	 * @param medicine
	 * @param user
	 */
	public PurchaseDetail(int detId, double qty, double rate, String batchNo, Date expireDate, Purchase purchase,
			Medicine medicine, User user) {
		super();
		this.detId = detId;
		this.qty = qty;
		this.rate = rate;
		this.batchNo = batchNo;
		this.expireDate = expireDate;
		this.purchase = purchase;
		this.medicine = medicine;
		this.user = user;
	}
	/**
	 * @param detId
	 */
	public PurchaseDetail(int detId) {
		super();
		this.detId = detId;
	}
	public int getDetId() {
		return detId;
	}
	public void setDetId(int detId) {
		this.detId = detId;
	}
	public double getQty() {
		return qty;
	}
	public void setQty(double qty) {
		this.qty = qty;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public Purchase getPurchase() {
		return purchase;
	}
	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
	public Medicine getMedicine() {
		return medicine;
	}
	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "PurchaseDetail [detId=" + detId + ", qty=" + qty + ", rate=" + rate + ", batchNo=" + batchNo
				+ ", exprieDate=" + expireDate + ", purchase=" + purchase + ", medicine=" + medicine + ", user=" + user
				+ "]";
	}
		
}
