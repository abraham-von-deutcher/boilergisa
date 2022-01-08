package com.my.boilergisa.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.my.boilergisa.dto.EngnrFileVO;
import com.my.boilergisa.dto.EngnrVO;

@Mapper
public interface EngnrTbMapper {
	
	//엔지니어 목록
	List<EngnrVO> selectEngnrList(String setAddress) throws Exception;
	//로그인	
	int engnrLogin(Map<String, Object> input) throws Exception;
	//엔지니어
	EngnrVO getEngnrInfo(String engnrID) throws Exception;
	
	//엔지니어 정보 수정	
	int updateEngnrInfo(EngnrVO engnr)throws Exception;
	
	//로그인쿠키
	int engnrKeepLogin(Map<String, Object> resultMap)throws Exception;

	//로그인한 날짜
	int updateLoginDate(String engnrID)throws Exception;
	
	//쿠기조회
	String checkEngnrWithSessionKey(String sessionID) throws Exception;
}
