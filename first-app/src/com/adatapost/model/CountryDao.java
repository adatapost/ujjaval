package com.adatapost.model;

import java.util.ArrayList;
import java.util.List;

import com.adatapost.Db;
import com.adatapost.U;

public class CountryDao {
	public static boolean add(Country country) {
		String sql = "insert into country (country_name) values(?)";
		try (Db x = new Db(sql)) {
			x.getPs().setString(1, country.getCountryName());
			return x.execute();
		} catch (Exception ex) {
			System.out.println("Error in add : " + ex);
		}
		return false;
	}

	public static boolean delete(Country country) {
		String sql = "delete from country where country_id=?";
		try (Db x = new Db(sql)) {
			x.getPs().setInt(1, country.getCountryId());
			return x.execute();
		} catch (Exception ex) {
			System.out.println("Error in delete : " + ex);
		}
		return false;
	}

	public static boolean update(Country country) {
		String sql = "update country set country_name=? where country_id=?";
		try (Db x = new Db(sql)) {
			x.getPs().setString(1, country.getCountryName());
			x.getPs().setInt(2, country.getCountryId());
			return x.execute();
		} catch (Exception ex) {
			System.out.println("Error in update : " + ex);
		}
		return false;
	}

	public static Country get(Country country) {
		String sql = "select * from country where country_id=?";
		try (Db x = new Db(sql)) {
			x.getPs().setInt(1, country.getCountryId());
			Object[] ar = x.row();
			if (ar != null) {
				Country temp = new Country();
				temp.setCountryId((Integer) ar[0]);
				temp.setCountryName((String) ar[1]);
				return temp;
			}
		} catch (Exception ex) {
			System.out.println("Error in get : " + ex);
		}
		return null;
	}

	public static List<Country> gets() {
		List<Country> list = new ArrayList<>();
		String sql = "select * from country order by country_name";
		try (Db x = new Db(sql)) {
			for (Object[] ar : x.rows()) {
				Country temp = new Country();
				temp.setCountryId((Integer) ar[0]);
				temp.setCountryName((String) ar[1]);
				list.add(temp);
			}
		} catch (Exception ex) {
			System.out.println("Error in gets : " + ex);
		}
		return list;
	}
}
