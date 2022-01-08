package com.my.boilergisa.service;

import java.util.Map;

import com.my.boilergisa.dto.CustmrVO;

public interface CustmrService {
	
	//id 체크
	public int custmrIdCheck(String custmrID) throws Exception;
	
	//회원가입처리
	public int custmrRegister(Map<String, Object> input) throws Exception;
	
	//로그인	
	public int custmrLogin(Map<String, Object> input) throws Exception;
	
	//고객정보
	public CustmrVO getCustmrInfo(String custmrID) throws Exception;	
	
	//로그인쿠키
	public int custmrKeepLogin(Map<String, Object> resultMap)throws Exception;
	
	//로그인한 날짜
	public int updateLoginDate(String custmrID)throws Exception;
	
	//쿠기조회
	public String checkLoginBefore(String sessionID)throws Exception;
	
}