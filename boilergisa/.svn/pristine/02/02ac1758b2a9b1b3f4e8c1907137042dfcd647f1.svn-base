package com.my.boilergisa.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.my.boilergisa.dto.CustmrVO;

@Mapper
public interface CustmrTbMapper {
	
	int custmrIdCheck(String custmrID) throws Exception;
	
	int custmrRegister(Map<String, Object> input) throws Exception;
	
	int custmrLogin(Map<String, Object> input) throws Exception;
	
	CustmrVO getCustmrInfo(String custmrID) throws Exception;
	
	int custmrKeepLogin(Map<String, Object> resultMap)throws Exception;
		
	int updateLoginDate(String custmrID)throws Exception;
	
	String checkCustmrWithSessionKey(String sessionID) throws Exception;
}
