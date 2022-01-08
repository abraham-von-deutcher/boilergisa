package com.my.boilergisa.service;

import java.util.List;

import com.my.boilergisa.dto.EngnrFileVO;

public interface EngnrFileService {
	
	//엔지니어 파일 목록
	public List<EngnrFileVO> engnrFileList(EngnrFileVO engnrFile) throws Exception;
	
	//엔지니어 사진정보 수정
	public int updateEngnrInfo(EngnrFileVO engnrFile) throws Exception;
	
	//엔지니어 파일 삭제
	public int deleteFile(Long fileIdx) throws Exception;
	
}