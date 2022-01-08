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
public class EngnrFileServiceImpl implements EngnrFileService {
	
	@Autowired
	private EngnrFileTbMapper engnrFileTbMapper;	
	
	//엔지니어 파일 목록
	public List<EngnrFileVO> engnrFileList(EngnrFileVO engnrFile) throws Exception{
		return engnrFileTbMapper.engnrFileList(engnrFile);
	}
	
	//엔지니어 파일정보 수정
	public int updateEngnrInfo(EngnrFileVO engnrFile) throws Exception{		
		
		
		return engnrFileTbMapper.insertFile(engnrFile);		
	}
	
	//엔지니어 파일 삭제
	public int deleteFile(Long fileIdx) throws Exception{
		return engnrFileTbMapper.deleteFile(fileIdx);
	}
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
	
		
}
