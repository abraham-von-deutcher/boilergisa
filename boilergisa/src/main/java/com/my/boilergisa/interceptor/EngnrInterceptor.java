package com.my.boilergisa.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@SuppressWarnings("deprecation")
@Component
public class EngnrInterceptor extends HandlerInterceptorAdapter{

/*
	private void saveDestination(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String query = request.getQueryString();
		if(query == null || query.equals("null")) {
			query ="";
		} else {
			query ="?" + query;
		}
		
		if(request.getMethod().equals("GET")) {
			log.info("destination :" + (uri + query));
			request.getSession().setAttribute("destination", uri + query);
		}
	}
*/
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("engnrID") == null){			
			response.sendRedirect("/");
			return true;
		} 
        return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
}
