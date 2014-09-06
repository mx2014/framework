package com.mx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.dao.TestDaoImpl;
import com.mx.entity.Test;

@Service
public class TestService {
	@Autowired
	TestDaoImpl testDao;
	
	@Transactional
	public void save(Test t) {
		testDao.saveImpl(t);
	}
}
