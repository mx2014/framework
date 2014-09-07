package com.mx.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mx.entity.Test;
import com.mx.service.TestService;
import com.mx.util.Factory;

//import com.entity.User;

@RestController
public class TestController{

	@Autowired
	private TestService testService;
	@Autowired
	private Factory factory;
	
	@RequestMapping(value="/test2.do", method=RequestMethod.GET)
	public String getList(@RequestParam String userName, @RequestParam int pwd, @RequestParam int phone){
		return "{\"id\" : 'login1'}";
	}
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String test(@RequestParam String name, @RequestParam int age){
		return "{\"id\" : 1}";
	}
	
	@RequestMapping(value="/test1", method=RequestMethod.GET)
	public String test(Test user, HttpServletRequest request){
		String url = factory.getUrlBySort(request);
		String md5 = factory.getMD5(url);
		System.out.println("----"+md5);
		testService.save(user);
		return "{\"id\" : 1111}";
	}
}
