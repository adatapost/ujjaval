package com.adatapost.model;

import java.util.ArrayList;
import java.util.List;

import com.adatapost.Db;

public class CityDao {
	public static boolean add(City city) {
		String sql = "insert into city (city_name, state_id) values(?,?)";
		try (Db x = new Db(sql)) {
			x.getPs().setString(1, city.getCityName());
			x.getPs().setInt(2, city.getStateId());
			return x.execute();
		} catch (Exception ex) {
			System.out.println("Error in add : " + ex);
		}
		return false;
	}

	public static boolean delete(City city) {
		String sql = "delete from city where city_id=?";
		try (Db x = new Db(sql)) {
			x.getPs().setInt(1, city.getCityId());
			return x.execute();
		} catch (Exception ex) {
			System.out.println("Error in delete : " + ex);
		}
		return false;
	}

	public static boolean update(City city) {
		String sql = "update city set city_name=?, state_id=? where city_id=?";
		try (Db x = new Db(sql)) {
			x.getPs().setString(1, city.getCityName());
			x.getPs().setInt(2, city.getStateId());
			x.getPs().setInt(3, city.getCityId());
			return x.execute();
		} catch (Exception ex) {
			System.out.println("Error in update : " + ex);
		}
		return false;
	}

	public static City get(City city) {
		String sql = "select * from view_city where city_id=?";
		try (Db x = new Db(sql)) {
			x.getPs().setInt(1, city.getCityId());
			Object[] ar = x.row();
			if (ar != null) {
				City temp = new City();
				temp.setCityId((Integer) ar[0]);
				temp.setCityName((String) ar[1]);
				temp.setStateId((Integer) ar[2]);
				temp.setStateName((String) ar[3]);
				temp.setCountryId((Integer) ar[4]);
				temp.setCountryName((String) ar[5]);
				return temp;
			}
		} catch (Exception ex) {
			System.out.println("Error in get : " + ex);
		}
		return null;
	}

	public static List<City> gets(int stateId) {
		List<City> list = new ArrayList<>();
		String sql = "select * from view_city where state_id=?";
		try (Db x = new Db(sql)) {
			x.getPs().setInt(1, stateId);
			for (Object[] ar : x.rows()) {
				City temp = new City();
				temp.setCityId((Integer) ar[0]);
				temp.setCityName((String) ar[1]);
				temp.setStateId((Integer) ar[2]);
				temp.setStateName((String) ar[3]);
				temp.setCountryId((Integer) ar[4]);
				temp.setCountryName((String) ar[5]);
				list.add(temp);
			}
		} catch (Exception ex) {
			System.out.println("Error in gets : " + ex);
		}
		return list;
	}
}
