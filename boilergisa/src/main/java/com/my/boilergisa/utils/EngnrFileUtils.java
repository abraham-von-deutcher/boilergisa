package com.my.boilergisa.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.java.Log;


@Component
@Log
public class EngnrFileUtils {	
private static String OS = System.getProperty("os.name").toLowerCase();
		
	private static final String WINDOW_PROPERTIES_PATH = "C://boilergisa/";
	private static final String LINUX_PROPERTIES_PATH  = "/boilergisa/";
	
	public Map<String, Object> uploadFiles(MultipartFile infile, String engnrNo, String baseFilePath){
		Map<String, Object> returnInfo = new HashMap<String, Object>();
		Date nowDate = new Date();
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMMdd");
		String date = simpleDate.format(nowDate);
		
		String newFilePath =  baseFilePath + File.separator + engnrNo + File.separator;
		/* uploadPath에 해당하는 디렉터리가 존재하지 않으면, 부모 디렉터리를 포함한 모든 디렉터리를 생성 */
		File file = new File(newFilePath);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		UUID uuid = UUID.randomUUID();
		String originalFileName = infile.getOriginalFilename().replace(" ",""); // 파일명
		
		String extension = FilenameUtils.getExtension(originalFileName);		
		String newFileName = date + "_" + uuid.toString() + "_" +originalFileName + "." + extension;
		file = new File(newFilePath + newFileName);
			
		
		try {
			/* 1. 파일명 중복 방지, 서버에 저장할 파일명 (랜덤 문자열 + 확장자) */		
			infile.transferTo(file);
			BufferedImage originalImg = ImageIO.read(file);			
	        // 원본이미지를 축소
	        BufferedImage thumbnailImg = Scalr.resize(originalImg, 1080, 1440, null);			
	        String thumbnailImgName = "boilergisa_" + newFileName;
	        // 썸네일 업로드 경로
	        String fullPath = newFilePath +  thumbnailImgName;
	        
	        // 썸네일 파일 객체생성
	        File newFile = new File(fullPath);	        
	        ImageIO.write(thumbnailImg, extension, newFile);
			
	        /* 파일 정보 추가 */
	        returnInfo.put("engnrNo",engnrNo);
	        returnInfo.put("originalName",newFileName);
	        returnInfo.put("thumbnailName",thumbnailImgName);
	        returnInfo.put("fileSize",infile.getSize());
		
		} catch (IOException e) {
			//throw new AttachFileException("[" + file.getOriginalFilename() + "] failed to save file...");

		} catch (Exception e) {
			//throw new AttachFileException("[" + file.getOriginalFilename() + "] failed to save file...");
		}
	 // end of for
		return returnInfo;
	}
	
	public static String getBasePath() {
		boolean isWindow = OS.contains("win") ? true : false;
		String basePath = WINDOW_PROPERTIES_PATH; 
		if(isWindow) {
			basePath = WINDOW_PROPERTIES_PATH;
		}else {
			basePath = LINUX_PROPERTIES_PATH;
		}
		return basePath;
	}
	
	// 파일 삭제 처리
	public static void deleteFile(String fileName, String engnrNo, String baseFilePath) {
		try{
			
			String originalImg = fileName.substring(11);
			//1. 원본 이미지 파일 삭제
			new File(baseFilePath + File.separator + engnrNo + File.separator + originalImg.replace('/', File.separatorChar)).delete();
			//2. 파일 삭제(썸네일이미지 or 일반파일)
			new File(baseFilePath + File.separator + engnrNo + File.separator + fileName.replace('/', File.separatorChar)).delete();
			
		} catch(Exception e) {
			log.info("delete file error");
		}
	}
}