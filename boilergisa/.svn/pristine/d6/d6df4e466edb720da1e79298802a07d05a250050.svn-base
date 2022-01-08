package com.my.boilergisa.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.my.boilergisa.service.CustmrService;

import lombok.extern.java.Log;

@Controller
@Log

public class CustmrLoginController {
	
	@Autowired 
	CustmrService custmrService;
	
	//로그인 페이지
	@GetMapping(value = "/custmr/custmrLogin")
	 public ModelAndView custmrLoginGET(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/custmr/custmrLogin");
		log.info("로그인페이지 이동");
		return mv;
	}
	
	//로그인처리
	@PostMapping(value = "/custmr/custmrLogin")
	@ResponseBody	
	public Map<String,Object> custmrLoginPOST(@RequestBody Map<String,Object> input, HttpServletRequest request,HttpServletResponse response)throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		
		String custmrId = (String)input.get("custmrID");
		String custmrCookie = (String)input.get("custmrCookie").toString();
		
		if(session.getAttribute("engnrID") != null && session.getAttribute("custmrID") != null){
			session.removeAttribute("engnrID");
			session.removeAttribute("custmrID");
		}
		try {
			int result = custmrService.custmrLogin(input);
			
			if(result > 0) {
				session.setAttribute("custmrID",custmrId);
				
				if(custmrCookie == "true"){
					Cookie cookie =new Cookie("loginCookie", session.getId());
					cookie.setPath("/");
					int amount = 60 * 60 * 24 * 365;
					cookie.setMaxAge(amount);
					response.addCookie(cookie); 
					
		        	Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * amount));
					resultMap.put("custmrID",custmrId);
					resultMap.put("sessionID",session.getId());
					resultMap.put("sessionLimit",sessionLimit);
					
		        	custmrService.custmrKeepLogin(resultMap);
				}			
				
				custmrService.updateLoginDate(custmrId);
				
				resultMap.put("result", "Success");
			}else {
				resultMap.put("result", "Failed");
			} 
		} catch (DataAccessException e) {
			resultMap.put("result", "DBFailed");
	
		} catch (Exception e) {
			resultMap.put("result", "SystemFailed");
		}
		return resultMap;
	}
	
	//로그아웃
	@GetMapping(value = "/custmr/custmrLogout")
	public ModelAndView custmrLogout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		Object object = session.getAttribute("custmrID");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ModelAndView mv = new ModelAndView();
		
		if(object != null) {
			String custmrId = (String)object;
			session.removeAttribute("custmrID");
			session.invalidate();
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			if (loginCookie != null) {
	                loginCookie.setPath("/");
	                loginCookie.setMaxAge(0);
	                response.addCookie(loginCookie);
	                
	                resultMap.put("custmrID",custmrId);
					resultMap.put("sessionID","none");
					resultMap.put("sessionLimit",new Date());
	                custmrService.custmrKeepLogin(resultMap);
			}
		}
		mv.addObject("result","logout");
		mv.setViewName("index");
		return mv;
	}
}
