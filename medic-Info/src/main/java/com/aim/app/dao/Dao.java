package com.aim.app.dao;

import java.util.List;

public interface Dao<T> {
	
	public boolean add(T obj);
	
	public boolean update(T obj);
	
	public boolean delete(T obj);
	
	public T get(T obj);
	
	public List<T> gets();

	public List<T> gets(int id);
}
