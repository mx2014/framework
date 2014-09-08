package com.mx.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mx.entity.Result;
import com.mx.service.ExecuteService;
import com.mx.util.Configure;

@RestController

public class ExecuteController {
	@Autowired
	private Result result;
	@Autowired
	private Configure configure;
	@Autowired
	private ExecuteService executeService;
	
	@RequestMapping(name="/user/execute/selectHql", params="action=select")
	public @ResponseBody Result selectHql(@RequestParam String sql, @RequestParam String value){
		List<?> list = executeService.selectHql(sql, value);
		result.setSuccess(true);
		result.setContext(list);
		return result;
	}

	@RequestMapping(name="/user/execute/save", params="action=insert")
	public @ResponseBody Result save(@RequestParam String entity, @RequestParam String action, HttpServletRequest request){
		
		Object obj = executeService.save(configure.getEntityPath() + "." + entity, request.getAttribute("entityObject"));
		if (obj != null) {
			result.setSuccess(true);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", obj);
			result.setContext(map);
		}
		return result;
	}
}
