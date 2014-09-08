package com.mx.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ExecuteDao extends BaseDaoImpl {
	
	public List<Object[]> selectSql(final String sql, String where){
		HibernateTemplate tmpl = getHibernateTemplate();  
		return tmpl.execute(new HibernateCallback<List<Object[]>>() {  
		    @SuppressWarnings("unchecked")  
		    @Override  
		    public List<Object[]> doInHibernate(Session session)  throws HibernateException {  
		        SQLQuery query = session.createSQLQuery(sql);
		        List<Object[]> results = query.list();
		        return results;  
		    }  
		}); 
	}
	public List<?> selectHql(final String sql){
		List<?> list = getHibernateTemplate().find(sql);
		return list;
	}
	
	public Object save(final String entity, final Object o){
		return getHibernateTemplate().execute(
			new HibernateCallback<Object>() {
				public Object doInHibernate(org.hibernate.Session session)
						throws org.hibernate.HibernateException {
					return session.save(entity, o);
				}
			}
		);
	}
}
