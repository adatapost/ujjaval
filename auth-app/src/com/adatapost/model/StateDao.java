package com.adatapost.model;

import java.util.ArrayList;
import java.util.List;

import com.adatapost.Db;

public class StateDao {
	public static boolean add(State state) {
		String sql = "insert into state (state_name, country_id) values(?,?)";
		try (Db x = new Db(sql)) {
			x.getPs().setString(1, state.getStateName());
			x.getPs().setInt(2, state.getCountryId());
			return x.execute();
		} catch (Exception ex) {
			System.out.println("Error in add : " + ex);
		}
		return false;
	}

	public static boolean delete(State state) {
		String sql = "delete from state where state_id=?";
		try (Db x = new Db(sql)) {
			x.getPs().setInt(1, state.getStateId());
			return x.execute();
		} catch (Exception ex) {
			System.out.println("Error in delete : " + ex);
		}
		return false;
	}

	public static boolean update(State state) {
		String sql = "update state set state_name=?, country_id=? where state_id=?";
		try (Db x = new Db(sql)) {
			x.getPs().setString(1, state.getStateName());
			x.getPs().setInt(2, state.getCountryId());
			x.getPs().setInt(3, state.getStateId());
			return x.execute();
		} catch (Exception ex) {
			System.out.println("Error in update : " + ex);
		}
		return false;
	}

	public static State get(State state) {
		String sql = "select * from view_state where state_id=?";
		try (Db x = new Db(sql)) {
			x.getPs().setInt(1, state.getStateId());
			Object[] ar = x.row();
			if (ar != null) {
				State temp = new State();
				temp.setStateId((Integer) ar[0]);
				temp.setStateName((String) ar[1]);
				temp.setCountryId((Integer) ar[2]);
				temp.setCountryName((String) ar[3]);
				return temp;
			}
		} catch (Exception ex) {
			System.out.println("Error in get : " + ex);
		}
		return null;
	}

	public static List<State> gets(int countryId) {
		List<State> list = new ArrayList<>();
		String sql = "select * from view_state where country_id=?";
		try (Db x = new Db(sql)) {
			x.getPs().setInt(1, countryId);
			for (Object[] ar : x.rows()) {
				State temp = new State();
				temp.setStateId((Integer) ar[0]);
				temp.setStateName((String) ar[1]);
				temp.setCountryId((Integer) ar[2]);
				temp.setCountryName((String) ar[3]);
				list.add(temp);
			}
		} catch (Exception ex) {
			System.out.println("Error in gets : " + ex);
		}
		return list;
	}
}
