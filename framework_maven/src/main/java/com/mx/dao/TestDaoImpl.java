package com.mx.dao;

import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.mx.entity.Test;

@Repository
public class TestDaoImpl extends BaseDaoImpl {
	
	public void saveImpl(final Test t){
		getHibernateTemplate().execute(
			new HibernateCallback<Object>() {
				public Object doInHibernate(org.hibernate.Session session)
						throws org.hibernate.HibernateException {
					session.save(t);
					return null;
				}
			}
		);
	}
}
