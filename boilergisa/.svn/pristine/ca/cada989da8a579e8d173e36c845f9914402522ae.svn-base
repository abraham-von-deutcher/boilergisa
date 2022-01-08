package com.my.boilergisa.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@SuppressWarnings("deprecation")
public class CustmrAfterInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		// 로그인 후 로그인 페이지 or 회원가입 페이지 이동할 경우
		HttpSession session = request.getSession();
		if(session.getAttribute("custmrID") != null) {
			response.sendRedirect(request.getContextPath()+ "/custmr/custmrMain");
			return false;
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
