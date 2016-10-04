package com.rsh.project.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class BaseInterceptor extends HandlerInterceptorAdapter {

    // for thymeleaf knows which controller, very fragile but no better idea
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

		if(modelAndView == null) return;
		if(handler == null) return;

		String controllerName = "";
		String actionName = "";
		 
		if( handler instanceof HandlerMethod ) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			controllerName  = handlerMethod.getBeanType().getSimpleName().replace("Controller", "");
			actionName = handlerMethod.getMethod().getName();
		}
		
		modelAndView.addObject("controllerName", controllerName );
		modelAndView.addObject("actionName", actionName );
	}

}
