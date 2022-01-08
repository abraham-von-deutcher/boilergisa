package com.my.boilergisa.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.my.boilergisa.dto.EngnrFileVO;
import com.my.boilergisa.dto.EngnrVO;

@Mapper
public interface EngnrFileTbMapper {
	
	//엔지니어 파일 목록
	List<EngnrFileVO> engnrFileList(EngnrFileVO engnrFile) throws Exception;	
	
	//엔지니어 사진정보 수정
	int insertFile(EngnrFileVO engnrFile) throws Exception;	
	
	//엔지니어 사진정보 삭제
	int deleteFile(Long fileIdx) throws Exception;
	
}
