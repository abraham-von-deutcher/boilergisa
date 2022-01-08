package com.my.boilergisa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.my.boilergisa.dto.EngnrFileVO;
import com.my.boilergisa.dto.EngnrVO;
import com.my.boilergisa.service.EngnrService;

import lombok.extern.java.Log;

//컨트롤러
@RestController
@Log
public class EngnrHomeController {
	
	@Autowired 
	EngnrService engnrService;
	
	@GetMapping(value = "/engnr/engnrHome")
	public ModelAndView engnrHome(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		String engnrID = (String) session.getAttribute("engnrID");
		log.info(engnrID);
		EngnrVO engnr = engnrService.getEngnrInfo(engnrID);
		
		mv.addObject("setAddress", engnr.getSetAddress());
		mv.setViewName("/engnr/engnrHome");
		log.info(request.getRequestURL().toString()+"엔지니어 게시판 페이지");
		return mv;
	}
	
	@GetMapping(value = "/engnr/engnrOrder")
	public ModelAndView engnrOrder(HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView();
		log.info(request.getRequestURL().toString()+"견적 정보");
		mv.setViewName("/engnr/engnrOrder");
		
		return mv;
	}
	
	@GetMapping(value = "/engnr/engnrChatBox")
	public ModelAndView engnrChatBox(HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView();
		log.info(request.getRequestURL().toString()+"고객상담 내용");
		mv.setViewName("/engnr/engnrChatBox");
		
		return mv;
	}
	
	@GetMapping(value = "/engnr/myRecordList")
	public ModelAndView myRecordList(HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView();
		log.info(request.getRequestURL().toString()+"엔지니어 기록");
		mv.setViewName("/engnr/myRecordList");
		
		return mv;
	}
	
	@GetMapping(value = "/engnr/engnrMypage")
	public ModelAndView engnrMypage(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		String engnrID = (String) session.getAttribute("engnrID");
		EngnrVO engnr = engnrService.getEngnrInfo(engnrID);

		mv.addObject("engnr", engnr);
		mv.setViewName("/engnr/engnrMypage");
		log.info(request.getRequestURL().toString()+"엔지니어 정보");
		
		return mv;
	}
	@GetMapping(value = "/engnr/engnrSetting")
	public ModelAndView searchEngnr(HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView();
		log.info(request.getRequestURL().toString()+"엔지니어 설정");
		mv.setViewName("/engnr/engnrSetting");
		
		return mv;
	}
}