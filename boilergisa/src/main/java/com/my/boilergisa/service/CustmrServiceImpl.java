package com.my.boilergisa.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.boilergisa.dao.CustmrTbMapper;
import com.my.boilergisa.dto.CustmrVO;
import com.my.boilergisa.utils.EncryptionUtils;


@Service
public class CustmrServiceImpl implements CustmrService {
	@Autowired 
	private CustmrTbMapper custmrTbMapper;	

	//id 체크
	public int custmrIdCheck(String custmrID) throws Exception{
		return custmrTbMapper.custmrIdCheck(custmrID);
	};
	
		
	//회원가입처리
	public int custmrRegister(Map<String, Object> input) throws Exception{
		
		String custmrID = (String)input.get("custmrID");
		String custmrPassword = (String)input.get("custmrPassword");
		String custmrName = (String)input.get("custmrName");
		
		String encryPassword = EncryptionUtils.encryptSHA256(custmrPassword);
		
		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("custmrID", custmrID);
		paraMap.put("custmrPassword", encryPassword);
		paraMap.put("custmrName", custmrName);
		
		return custmrTbMapper.custmrRegister(paraMap);
	};
	
	//로그인	
	public int custmrLogin(Map<String, Object> input) throws Exception{
		String custmrID = (String)input.get("custmrID");
		String custmrPassword = (String)input.get("custmrPassword");
		
		String encryPassword = EncryptionUtils.encryptSHA256(custmrPassword);
		
		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("custmrID", custmrID);
		paraMap.put("custmrPassword", encryPassword);
				
		return custmrTbMapper.custmrLogin(paraMap);
	};
	
	//고객정보
	public CustmrVO getCustmrInfo(String custmrID) throws Exception{
		return custmrTbMapper.getCustmrInfo(custmrID);
	};	
	
	//로그인쿠키
	public int custmrKeepLogin(Map<String, Object> resultMap)throws Exception {		
		
		return custmrTbMapper.custmrKeepLogin(resultMap);
	};
	
	//로그인한 날짜
	public int updateLoginDate(String custmrID) throws Exception{
		
		return custmrTbMapper.updateLoginDate(custmrID);
	};
	//쿠키체크
	public String checkLoginBefore(String sessionID) throws Exception {
		
		return custmrTbMapper.checkCustmrWithSessionKey(sessionID);
	};
}
