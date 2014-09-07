package com.mx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.dao.ExecuteDao;
import com.mx.util.SqlUtil;

@Service
public class ExecuteService {
	
	@Autowired
	private SqlUtil sqlUtil;
	@Autowired
	private ExecuteDao executeDao;
	
	public List<?> selectHql(String key, String where){
		String sql = sqlUtil.getSentence(key, where);
		
		return executeDao.selectHql(sql);
	}
	@Transactional
	public int save(String entity, Object o){
		return executeDao.save(entity, o);
	}
}
