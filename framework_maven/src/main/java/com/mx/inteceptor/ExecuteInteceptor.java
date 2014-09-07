package com.mx.inteceptor;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mx.util.Configure;

public class ExecuteInteceptor implements HandlerInterceptor {

	@Autowired
	private Configure configure;
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public void afterCompletion(HttpServletRequest request,
		HttpServletResponse response, Object arg2, Exception arg3)
		throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
		Object arg2, ModelAndView arg3) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
		Object handler) throws Exception {
		String action = request.getParameter("action");
		if (action.equals(configure.getActionList().get(0))) {
			Object o = installParams(request);
			request.setAttribute("entityObject", o);
//			HandlerMethod handlerMethod = (HandlerMethod) handler;
//			for (MethodParameter methodParameter : handlerMethod.getMethodParameters()) {  
//		        String str = methodParameter.getParameterName();
//		        System.out.println("==srt:"+str);
//		        //怎么获取参数值？获取username 和password  
//		    }
//			System.out.println("===="+handlerMethod.getMethod().getParameterTypes());
			
		}
		
		return true;
	}
	
	private Object installParams(HttpServletRequest request) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException, JsonParseException, JsonMappingException, IOException{
		String context = "";
		String entity = request.getParameter("entity");
		Class<?> object = Class.forName(configure.getEntityPath() + "." + entity);
		Field[] f = object.getDeclaredFields();
		
		for (Field field : f) {
			String name = field.getName();
			String value = request.getParameter(name);
			if (value != null) {
				context += "\"" + name + "\"" + ":" + "\"" + value + "\",";
			}
		}
		context = context.substring(0, context.length()-1);
		String json = "{" + context + "}";
		return objectMapper.readValue(json, object);
		
	}
}
