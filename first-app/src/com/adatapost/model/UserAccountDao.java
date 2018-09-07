package com.adatapost.model;

import com.adatapost.Db;

public class UserAccountDao {
	public static boolean add(UserAccount model) {
		String sql = "insert into user_account (user_email, user_pass,user_role, date_created, deleted) values(?,?,?,?,?)";
		try (Db x = new Db(sql)) {
		    x.getPs().setString(1, model.getUserEmail());
		    x.getPs().setString(2,model.getUserPass());
		    x.getPs().setString(3, model.getUserRole());
		    x.getPs().setDate(4, new java.sql.Date ( System.currentTimeMillis()));
		    x.getPs().setInt(5, 0);
			return x.execute();
		} catch (Exception ex) {
			System.out.println("Error in add : " + ex);
		}
		return false;
	}
	
	public static boolean changePassword(UserAccount model, String newPass) {
		String sql = "update  user_account set user_pass= ? where user_email=? and user_pass = ?";
		try (Db x = new Db(sql)) {
			x.getPs().setString(1,newPass);
		    x.getPs().setString(2, model.getUserEmail());
		    x.getPs().setString(3,model.getUserPass());
			return x.execute();
		} catch (Exception ex) {
			System.out.println("Error in changePassword : " + ex);
		}
		return false;
	}
	public static UserAccount login( UserAccount model ) {
		String sql = "select * from user_account where user_email=? and user_pass=?";
		try (Db x = new Db(sql)) {
			x.getPs().setString(1, model.getUserEmail());
			x.getPs().setString(2, model.getUserPass());
			
			Object[] ar = x.row();
			if (ar != null) {
				UserAccount temp = new UserAccount();
				temp.setUserId((Integer) ar[0]);
				temp.setUserEmail((String) ar[1]);
				temp.setUserPass((String) ar[2]);
				temp.setUserRole((String) ar[3]);
				temp.setDateCreated((java.sql.Date) ar[4]);
				temp.setDeleted(Boolean.parseBoolean(ar[5].toString()));
				return temp;
			}
		} catch (Exception ex) {
			System.out.println("Error in get : " + ex);
		}
		return null;
	}
	
	public static UserAccount get( UserAccount model ) {
		String sql = "select * from user_account where user_id=?";
		try (Db x = new Db(sql)) {
			x.getPs().setInt(1, model.getUserId());
			Object[] ar = x.row();
			if (ar != null) {
				UserAccount temp = new UserAccount();
				temp.setUserId((Integer) ar[0]);
				temp.setUserEmail((String) ar[1]);
				temp.setUserPass((String) ar[2]);
				temp.setUserRole((String) ar[3]);
				temp.setDateCreated((java.sql.Date) ar[4]);
				temp.setDeleted(Boolean.parseBoolean(ar[5].toString()));
				return temp;
			}
		} catch (Exception ex) {
			System.out.println("Error in get : " + ex);
		}
		return null;
	}
	
}
