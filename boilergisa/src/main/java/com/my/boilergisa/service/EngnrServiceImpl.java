package com.my.boilergisa.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.my.boilergisa.dao.EngnrFileTbMapper;
import com.my.boilergisa.dao.EngnrTbMapper;
import com.my.boilergisa.dto.EngnrFileVO;
import com.my.boilergisa.dto.EngnrVO;
import com.my.boilergisa.utils.EncryptionUtils;
import com.my.boilergisa.utils.EngnrFileUtils;

import lombok.extern.java.Log;


@Service
@Log
public class EngnrServiceImpl implements EngnrService {
	
	@Autowired
	private EngnrFileUtils engnrFileUtils;
	@Autowired 
	private EngnrTbMapper engnrTbMapper;		
	
	//엔지니어 목록
	public List<EngnrVO> selectEngnrList(String setAddress) throws Exception{
		return engnrTbMapper.selectEngnrList(setAddress);
	}
	
	//로그인	
	public int engnrLogin(Map<String, Object> input) throws Exception{
		String engnrID = (String)input.get("engnrID");
		String engnrPassword = (String)input.get("engnrPassword");
		
		String encryPassword = EncryptionUtils.encryptSHA256(engnrPassword);
		
		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("engnrID", engnrID);
		paraMap.put("engnrPassword", encryPassword);
				
		return engnrTbMapper.engnrLogin(paraMap);
	};
	
	//엔지니어 정보
	public EngnrVO getEngnrInfo(String engnrID) throws Exception{
		return engnrTbMapper.getEngnrInfo(engnrID);
	};
	
	//엔지니어 파일수정
	 /*
	public int updateEngnrInfo(Map<String, Object> input, MultipartFile file) throws Exception{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String engnrNo = (String)input.get("engnrNo");
		String changeYn = (String)input.get("changeYn");
		String baseFilePath = EngnrFileUtils.getBasePath();
		
		
		if("Y".equals(changeYn)) {
			
			EngnrFileVO engnrFile = engnrFileTbMapper.selectFileDetail(engnrNo);
			if(engnrFile != null) {
				engnrFileUtils.deleteFile(engnrFile.getThumbnailName(), engnrNo, baseFilePath);
				engnrFileTbMapper.deleteFile(engnrNo);
			}
			paramMap = engnrFileUtils.uploadFiles(file, engnrNo, baseFilePath);
			engnrFileTbMapper.insertFile(paramMap);
		}
		return engnrTbMapper.updateEngnrInfo(input);		
	}
	*/
	
	//엔지니어 정보 수정
	public int updateEngnrInfo(EngnrVO engnr) throws Exception{		
		return engnrTbMapper.updateEngnrInfo(engnr);		
	}
	
	//로그인쿠키
	public int engnrKeepLogin(Map<String, Object> resultMap)throws Exception {		
		
		return engnrTbMapper.engnrKeepLogin(resultMap);
	};
	
	//로그인한 날짜
	public int updateLoginDate(String engnrID) throws Exception{
		
		return engnrTbMapper.updateLoginDate(engnrID);
	};
	//쿠키체크
	public String checkLoginBefore(String sessionID) throws Exception {
		
		return engnrTbMapper.checkEngnrWithSessionKey(sessionID);
	};
}
