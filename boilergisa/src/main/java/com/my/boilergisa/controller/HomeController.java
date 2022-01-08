package com.my.boilergisa.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.my.boilergisa.service.CustmrService;
import com.my.boilergisa.service.EngnrService;

import lombok.extern.java.Log;

//컨트롤러
@RestController
@Log
public class HomeController {
	
	@Autowired
	CustmrService custmrService;
	
	@Autowired
	EngnrService engnrService;
	//초기 페이지
	@RequestMapping(value = {"/"},method = RequestMethod.GET)
	public ModelAndView checkLoginBefore(HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
		log.info(request.getRequestURL().toString()+" index 페이지");
   	 	mv.setViewName("index");
		
   	 	if(session.getAttribute("custmrID") != null) {
        	mv.setViewName("redirect:/custmr/custmrMain");			
		} 
		
		if(session.getAttribute("engnrID") != null) {
			mv.setViewName("redirect:/engnr/engnrHome");
		}
		
		if (loginCookie != null){
			String getCustmrID = custmrService.checkLoginBefore(loginCookie.getValue());
			String getEngnrID = engnrService.checkLoginBefore(loginCookie.getValue());
			
			if(getCustmrID != null) {
				session.setAttribute("custmrID", getCustmrID);
				log.info(request.getRequestURL().toString()+" 고객 페이지");
				mv.setViewName("redirect:/custmr/custmrMain");
			}else if(getEngnrID != null){
				session.setAttribute("engnrID", getEngnrID);
				log.info(request.getRequestURL().toString()+" 엔지니어 페이지");
				mv.setViewName("redirect:/engnr/engnrHome");
			}	        	
	     }
		return mv;
	}
}