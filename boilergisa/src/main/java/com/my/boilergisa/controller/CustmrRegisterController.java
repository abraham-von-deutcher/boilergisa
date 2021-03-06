package com.my.boilergisa.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.my.boilergisa.service.CustmrService;

import lombok.extern.java.Log;

@Controller
@Log
public class CustmrRegisterController {
	
	@Autowired
	private CustmrService custmrService;
	
	//회원가입 페이지
	@GetMapping(value = "/custmr/custmrRegister")
	public ModelAndView registerGET() throws Exception{
		log.info("회원가입 페이지 이동");
		ModelAndView mv = new ModelAndView("/custmr/custmrRegister");
		return mv;
	}
	
	//아이디 중복체크
	@PostMapping(value="/custmr/custmrIdCheck", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> custmrIdCheck(@RequestBody String custmrID) throws Exception{
		Map<String, Object> output = new HashMap<>();		
		int result = custmrService.custmrIdCheck(custmrID);
		
		if(result > 0 ) {
			output.put("confirm", "NO");
		} else {
			output.put("confirm", "OK");
		}
		return output;
	}
	
	//회원가입
	@PostMapping(value="/custmr/custmrRegister")
	@ResponseBody	
	public Map<String, Object> custmrRegister(@RequestBody Map<String, Object> input) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			int result = custmrService.custmrRegister(input);			
			if(result > 0) {
				resultMap.put("result", "Success");
			} else {
				resultMap.put("result", "Failed");
			}
		} catch (DataAccessException e) {
			resultMap.put("result", "DBFailed");
		} catch (Exception e) {
			resultMap.put("result", "SystemFailed");
		}
			return resultMap;
		}
	}
/*
//RequestBody라고 하는 메소드가 json을 맵형태로 변한해줘요(사용되는 라이브러리가 jackson(기본)임)
//리턴할때는 responsebody가 Map형태를 js가 알아듣게 Json 형태로 변환시켜주는 역활을 해요(사용되는 라이브러리가 jackson(기본)임)
//ModelandView는 JS에서 알아들을수 없는 형태의 언어임 왜냐하면 dataType을 이미 JSON으로 명시했기 때문에
// MV는 HTML리턴하는데 AJAX는 JSON이나 XML 같은 값을 리턴 받아야대요
	// 비동기는 화면을 받기위해 만든 통신기법이 아니에요
@RequestMapping(value ="test", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public Map<String, Object> test(@RequestBody Map<String, Object> input) throws Exception{
	Map<String, Object> output = new HashMap<String, Object>();
	
	System.out.println(input.get("text").toString());
	output.put("returnText", "hi!");
	Thread.sleep(3000);
	return output;
	
	
	//여기서 리턴되는 JSON 문자열은 {"returnText":"hi!"} JSON은 20글자
	//만약에 XML 방식이면 
	//<returnText>
	//	<value>hi<value>
	//</returnText>
	//이런식으로 전송되는데 뭐 대충 30글자 용량차이가 나죠
	//전공이시니깐 아실테지만
	//array에 push해서 하나씩 집어넣어서 전송하는 반면이 있는가 하면
	//문자열로 ㅁㅁㅁ/ㅠㅠㅠ/ㅊㅊㅊ/ㄷㄷㄷ(얘를 스플릿(/) 하면 배열이 되죠?)이렇게 구분자로 문자열로 전송하는 방식이 있는데
	//그건 그때마다 개발자가 선택해서 보내는거에요
	
}*/