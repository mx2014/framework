package com.mx.inteceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mx.entity.Result;
import com.mx.util.Configure;
import com.mx.util.Factory;

public class ExecuteInteceptor implements HandlerInterceptor {

	@Autowired
	private Configure configure;
	@Autowired
	private Factory factory;
	@Autowired
	private Result result;
	
	@Override
	public void afterCompletion(HttpServletRequest request,
		HttpServletResponse response, Object arg2, Exception arg3)
		throws Exception {
		System.out.println("==========");
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
			Object o = factory.getEntity(request);
			request.setAttribute("entityObject", o);
			/**
//			HandlerMethod handlerMethod = (HandlerMethod) handler;
//			for (MethodParameter methodParameter : handlerMethod.getMethodParameters()) {  
//		        String str = methodParameter.getParameterName();
//		        System.out.println("==srt:"+str);
//		        //怎么获取参数值？获取username 和password  
//		    }
//			System.out.println("===="+handlerMethod.getMethod().getParameterTypes());
			**/
		}
		return true;
	}
}
