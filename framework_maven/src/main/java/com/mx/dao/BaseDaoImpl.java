package com.mx.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {

	@Override
	public List<Object> query(String queryString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(final Object entity) {
		getHibernateTemplate().execute(
		new HibernateCallback<Object>() {
			public Object doInHibernate(org.hibernate.Session session)
					throws org.hibernate.HibernateException {
				session.save(entity);
				return null;
			}
		});

	}

	@Override
	public void update(final Object entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(final Object entity) {
		// TODO Auto-generated method stub

	}
	
	@Resource  
    public void setSuperSessionFactory(SessionFactory sessionFactory) {  
        super.setSessionFactory(sessionFactory);  
    }

}
