package com.adatapost.model;

import java.util.Date;

public class UserAccount {
	private Integer userId;
	private String userEmail,
	               userPass,
	               userRole;
	private boolean deleted;
	private java.util.Date dateCreated;
	public UserAccount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserAccount(Integer userId, String userEmail, String userPass, String userRole, boolean deleted,
			Date dateCreated) {
		super();
		this.userId = userId;
		this.userEmail = userEmail;
		this.userPass = userPass;
		this.userRole = userRole;
		this.deleted = deleted;
		this.dateCreated = dateCreated;
	}
	public UserAccount(Integer userId) {
		super();
		this.userId = userId;
	}
	public UserAccount(String userEmail) {
		super();
		this.userEmail = userEmail;
	}
	public UserAccount(String userEmail, String userPass) {
		super();
		this.userEmail = userEmail;
		this.userPass = userPass;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public java.util.Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(java.util.Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAccount other = (UserAccount) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UserAccount [userId=" + userId + ", userEmail=" + userEmail + ", userRole=" + userRole + ", deleted="
				+ deleted + ", dateCreated=" + dateCreated + "]";
	}
}
