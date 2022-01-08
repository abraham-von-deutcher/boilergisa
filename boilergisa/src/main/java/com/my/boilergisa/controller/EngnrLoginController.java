package com.my.boilergisa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.Cookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.my.boilergisa.dto.EngnrFileVO;
import com.my.boilergisa.dto.EngnrVO;
import com.my.boilergisa.service.EngnrService;
import com.my.boilergisa.utils.EngnrFileUtils;

import lombok.extern.java.Log;

@Controller
@Log

public class EngnrLoginController {
	
	@Autowired
	EngnrService engnrService;
	
	//로그인 페이지
	@GetMapping(value = "/engnr/engnrLogin")
	 public ModelAndView engnrLoginGET(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/engnr/engnrLogin");
		log.info("엔지니어 로그인페이지 이동");
		return mv;
		
	}
	
	//로그인처리
	@PostMapping(value = "/engnr/engnrLogin")
	@ResponseBody	
	public Map<String,Object> engnrLogiPOST(@RequestBody Map<String,Object> input, HttpServletRequest request,HttpServletResponse response)throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpSession session = request.getSession();
			
		String engnrId = (String)input.get("engnrID");
		String engnrCookie = (String)input.get("engnrCookie").toString();
		
		if(session.getAttribute("engnrID") != null && session.getAttribute("custmrID") != null){
			session.removeAttribute("engnrID");
			session.removeAttribute("custmrID");
		}
		try {
			int result = engnrService.engnrLogin(input);
			
			if(result > 0) {
				session.setAttribute("engnrID",engnrId);
				
				if(engnrCookie == "true"){
					Cookie cookie =new Cookie("loginCookie", session.getId());
					cookie.setPath("/");
					int amount = 60 * 60 * 24 * 365;
					cookie.setMaxAge(amount);
					response.addCookie(cookie); 
					
		        	Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * amount));
					resultMap.put("engnrID",engnrId);
					resultMap.put("sessionID",session.getId());
					resultMap.put("sessionLimit",sessionLimit);
					
					engnrService.engnrKeepLogin(resultMap);
				}			
				
				engnrService.updateLoginDate(engnrId);
				
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
	@GetMapping(value = "/engnr/engnrLogout")
	public ModelAndView engnrLogout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		Object object = session.getAttribute("engnrID");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ModelAndView mv = new ModelAndView();
		
		if(object != null) {
			String engnrId = (String)object;
			session.removeAttribute("engnrID");
			session.invalidate();
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			
			if (loginCookie != null) {
				
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
		               
		        resultMap.put("engnrID",engnrId);
		        resultMap.put("sessionID","none");
				resultMap.put("sessionLimit",new Date());
				engnrService.engnrKeepLogin(resultMap);
			}
		}
		mv.addObject("result","logout");
		mv.setViewName("index");
		return mv;
	}
	
	
	
	//엔지니어 수정페이지 이동
	@GetMapping(value = "/engnr/engnrUpdate")
	public ModelAndView updateEngnrPage(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		String engnrID = (String) session.getAttribute("engnrID");
		EngnrVO engnr = engnrService.getEngnrInfo(engnrID);
		
		mv.addObject("engnr", engnr);
		log.info(request.getRequestURL().toString()+"엔지니어 정보수정 페이지");
		mv.setViewName("/engnr/engnrUpdate");
		
		return mv;
	}	
	//로그인 정보 수정
	@PostMapping(value="/engnr/engnrInfo/{engnrNo}", produces = "application/json; charset=utf-8")
	@ResponseBody	
	public Map<String, Object> engnrInfoUpdate(@RequestBody Map<String, Object> input, @PathVariable String engnrNo, HttpServletRequest req, HttpSession session) throws Exception{
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		EngnrVO engnr = new EngnrVO();
		
		engnr.setEngnrNo(engnrNo);
		engnr.setEngnrID((String)input.get("engnrID"));
		engnr.setEngnrName((String)input.get("engnrName"));
		engnr.setAddress1((String)input.get("address1"));
		engnr.setAddress2((String)input.get("address2"));
		engnr.setEngnrNumber((String)input.get("engnrNumber"));
		engnr.setEngnrIntro((String)input.get("engnrIntro"));
		engnr.setBusinessType((String)input.get("businessType"));
		engnr.setWorkTime1((String)input.get("workTime1"));
		engnr.setWorkTime2((String)input.get("workTime2"));
		engnr.setBreakTime((String)input.get("breakTime"));
		
		engnrService.updateEngnrInfo(engnr);
		
		resultMap.put("result", "Success");
		
		return resultMap;
	}
}