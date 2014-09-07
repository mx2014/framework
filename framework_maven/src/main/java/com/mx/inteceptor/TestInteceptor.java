package com.mx.inteceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TestInteceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
		HttpServletResponse arg1, Object arg2, Exception arg3)
		throws Exception {
		// TODO Auto-generated method stub
		System.out.println(6666666);
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
		Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(555555);
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
		Object handler) throws Exception {
		// TODO Auto-generated method stub
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		System.out.println(233344);
		for (MethodParameter methodParameter : handlerMethod.getMethodParameters()) {  
	        String str = methodParameter.getParameterName();
	        System.out.println("==srt:"+str);
	        //怎么获取参数值？获取username 和password  
	    }
		return true;
	}

}
