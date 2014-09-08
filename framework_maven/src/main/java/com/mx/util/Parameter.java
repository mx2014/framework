package com.mx.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

@Repository
public class Parameter {

	private Enumeration<String> params;
	private HttpServletRequest request;
//	private List<String> sortlist = new ArrayList<String>();
	
	public void setRequest(HttpServletRequest request){
		this.request = request;
		this.params = request.getParameterNames();
	}
	public Enumeration<String> getParams() {
		return params;
	}
	public void setParams(Enumeration<String> params) {
		this.params = params;
	}
	
	public String sort(){
		String url = "";
		List<String> sortlist = new ArrayList<String>();
		
		while(params.hasMoreElements()){
			String param = params.nextElement();
			if (!param.equals("key")) {
				sortlist.add(param);
			}
		}
		Collections.sort(sortlist);
		
		for (String key : sortlist) {
			url += key +"=" + request.getParameter(key) + "&";
		}
		if (!url.equals("")) {
			url = url.substring(0, url.length() - 1);
		}
//		System.out.println("------:"+url);
		return url;
	}
	
}
