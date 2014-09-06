package com.mx.dao;

import java.util.List;


public interface BaseDao {
	public List<Object> query(final String queryString);
	public void save(final Object entity);
	public void update(final Object entity);
	public void delete(final Object entity);
}
