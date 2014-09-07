package com.mx.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mx.service.ExecuteService;

@RestController

public class ExecuteController {
	String path = "com.mx.entity.";
	
	@Autowired
	private ExecuteService executeService;
	
	@RequestMapping(name="/execute/selectHql", params="action=select")
	public @ResponseBody List<?> selectHql(@RequestParam String sql, @RequestParam String value){
		List<?> list = executeService.selectHql(sql, value);
		return list;
	}

	@RequestMapping(name="/execute/save", params="action=insert")
	public @ResponseBody String save(@RequestParam String entity, @RequestParam String action, HttpServletRequest request){
		
		Object o = request.getAttribute("entityObject");
		executeService.save(path + entity, o);
		return "{result: 1}";
	}
}
