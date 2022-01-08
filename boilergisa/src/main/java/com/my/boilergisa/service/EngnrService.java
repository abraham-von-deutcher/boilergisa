package com.my.boilergisa.service;

import java.util.List;
import java.util.Map;


import org.springframework.web.multipart.MultipartFile;

import com.my.boilergisa.dto.EngnrFileVO;
import com.my.boilergisa.dto.EngnrVO;

public interface EngnrService {
	
	//엔지니어 목록
	public List<EngnrVO> selectEngnrList(String setAddress) throws Exception;
	
	//로그인	
	public int engnrLogin(Map<String, Object> input) throws Exception;
	
	//고객정보
	public EngnrVO getEngnrInfo(String engnrID) throws Exception;	
	
	//엔지니어 사진정보 수정
	public int updateEngnrInfo(EngnrVO engnr) throws Exception;	
		
	//로그인쿠키
	public int engnrKeepLogin(Map<String, Object> resultMap)throws Exception;
	
	//로그인한 날짜
	public int updateLoginDate(String engnrID)throws Exception;
	
	//쿠기조회
	public String checkLoginBefore(String sessionID)throws Exception;
	
}