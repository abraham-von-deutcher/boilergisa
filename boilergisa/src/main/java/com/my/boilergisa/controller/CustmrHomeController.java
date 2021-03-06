package com.my.boilergisa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.my.boilergisa.dto.CustmrVO;
import com.my.boilergisa.service.CustmrService;

import lombok.extern.java.Log;

//컨트롤러
@RestController
@Log
public class CustmrHomeController {
	
	@Autowired 
	CustmrService custmrService;
	
	@GetMapping(value = "/custmr/custmrMain")
	public ModelAndView custmrMain(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		String custmrID = (String) session.getAttribute("custmrID");
		log.info(custmrID);
		CustmrVO custmr = custmrService.getCustmrInfo(custmrID);
		mv.addObject("custmr", custmr);
		mv.setViewName("/custmr/custmrMain");
		log.info(request.getRequestURL().toString()+"고객 메인 페이지");
		return mv;
	}
	
	@GetMapping(value = "/custmr/custmrSetting")
	public ModelAndView custmrSetting(HttpServletRequest request) throws Exception{
		
		log.info(request.getRequestURL().toString()+"고객 설정페이지");
		ModelAndView mv = new ModelAndView("/custmr/custmrSetting");
		
		return mv;
	}
	
	@GetMapping(value = "/custmr/requestBoiler")
	public ModelAndView requestBoiler(HttpServletRequest request) throws Exception{
		
		log.info(request.getRequestURL().toString()+"보일러 견적요청페이지");
		ModelAndView mv = new ModelAndView("/custmr/requestBoiler");
		
		return mv;
	}
	@GetMapping(value = "/custmr/requestWaterHeater")
	public ModelAndView requestWaterHeater(HttpServletRequest request) throws Exception{
		
		log.info(request.getRequestURL().toString()+"온수기 견적요청페이지");
		ModelAndView mv = new ModelAndView("/custmr/requestWaterHeater");
		
		return mv;
	}
	@GetMapping(value = "/custmr/searchEngnr")
	public ModelAndView searchEngnr(HttpServletRequest request) throws Exception{
		
		log.info(request.getRequestURL().toString()+"업체찾기");
		ModelAndView mv = new ModelAndView("/custmr/searchEngnr");
		
		return mv;
	}
}