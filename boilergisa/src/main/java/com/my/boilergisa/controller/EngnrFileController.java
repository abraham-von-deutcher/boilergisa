package com.my.boilergisa.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.my.boilergisa.dto.EngnrFileVO;
import com.my.boilergisa.dto.EngnrVO;
import com.my.boilergisa.service.EngnrFileService;
import com.my.boilergisa.utils.EngnrFileUtils;

import lombok.extern.java.Log;

@Controller
@Log

public class EngnrFileController {
	
	@Autowired
	EngnrFileService engnrFileService;
	
	@GetMapping(value="/showImage/engnr/{engnrNo}")
	public ModelAndView showAttachList(@PathVariable String engnrNo, HttpServletRequest req, HttpServletResponse response) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		EngnrFileVO eDTO = new EngnrFileVO();
		EngnrVO engnr = new EngnrVO();
		
		eDTO.setTypeNo(engnrNo);
		
		List<EngnrFileVO> fileList = engnrFileService.engnrFileList(eDTO);		
		EngnrFileVO fileInfo = engnrFileService.engnrFileList(eDTO).get(0);
		
		engnr.setEngnrNumber(engnrNo);		
		
		mv.addObject("fileInfo", fileInfo);
		mv.addObject("fileList", fileList);
		
		mv.addObject("engnr", engnr);
		mv.setViewName("/commons/showImage");
		
		return mv;
	}
	
	//엔지니어 프로필 파일정보
	@GetMapping(value="/uploadType/engnr/{engnrNo}/{fileIdx}")
	public void showOriginFiles(@PathVariable String engnrNo, @PathVariable Long fileIdx, HttpServletRequest req, HttpServletResponse response) throws Exception {
		
		String fileName = "";
		String contentType = "";
		String filePath = "";
		InputStream inputStream = null;
		
		String getBasePath = EngnrFileUtils.getBasePath();
		
		try {
		
			EngnrFileVO eDTO = new EngnrFileVO(); 
			eDTO.setFileIdx(fileIdx);
			
			EngnrFileVO engnrFile = engnrFileService.engnrFileList(eDTO).get(0);
			
			String originName = engnrFile.getOriginalName();
			String typePath = engnrFile.getTypePath();
			//String originalFilename = engnrFile.getOriginalName();
			filePath = getBasePath + typePath + "/" + engnrNo + "/" + originName;			
			
			inputStream = new FileInputStream(filePath); 			
			fileName = URLEncoder.encode(originName, "UTF-8");
			
		} catch (Exception e) {
			ClassPathResource resource = new ClassPathResource("/static/img/commons/addimg_engnr.png");
			try {
				inputStream = new FileInputStream(resource.getFile());
			} catch(Exception e2) {
				inputStream = resource.getInputStream();
			}
			fileName = URLEncoder.encode("addimg_engnr.png", "UTF-8");
			contentType = "image/png";
		}
		/*
		response.setHeader("Content-Type", contentType);
		//response.setHeader("Content-Length", file.length()+"");
		
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";");
		response.setHeader("Content-Description", "File Transfer");
		
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		*/
		OutputStream out = response.getOutputStream();
		FileCopyUtils.copy(inputStream, out);

		inputStream.close();
		out.flush();
		
		
	}
	
	//엔지니어 최근 파일정보
	@GetMapping(value="/boilergisa/engnr/{engnrNo}")
	public void recentEngnrThumbFile(@PathVariable String engnrNo, HttpServletRequest req, HttpServletResponse response) throws Exception {
		String fileName = "";
		String contentType = "";
		String filePath = "";
		InputStream inputStream = null;
		
		
		String getBasePath = EngnrFileUtils.getBasePath();
		
		try {
		
			EngnrFileVO eDTO = new EngnrFileVO(); 
			eDTO.setTypeNo(engnrNo);
			
			EngnrFileVO engnrFile = engnrFileService.engnrFileList(eDTO).get(0);
			
			String thumbnailFilename = engnrFile.getThumbnailName();
			String typePath = engnrFile.getTypePath();
			
			//String originalFilename = engnrFile.getOriginalName();
			filePath = getBasePath + typePath + "/" + engnrNo + "/" + thumbnailFilename;			
			
			inputStream = new FileInputStream(filePath); 			
			fileName = URLEncoder.encode(thumbnailFilename, "UTF-8");
			
		} catch (Exception e) {
			ClassPathResource resource = new ClassPathResource("/static/img/commons/addimg_engnr.png");
			try {
				inputStream = new FileInputStream(resource.getFile());
			} catch(Exception e2) {
				inputStream = resource.getInputStream();
			}
			fileName = URLEncoder.encode("addimg_engnr.png", "UTF-8");
			contentType = "image/png";
		}
		/*
		response.setHeader("Content-Type", contentType);
		//response.setHeader("Content-Length", file.length()+"");
		
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";");
		response.setHeader("Content-Description", "File Transfer");
		
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		*/
		OutputStream out = response.getOutputStream();
		FileCopyUtils.copy(inputStream, out);

		inputStream.close();
		out.flush();
	}
	
	//엔지니어 파일 수정
	@PostMapping(value="/engnr/engnrFile/{engnrNo}")
	@ResponseBody	
	public Map<String, Object> updateEngnrFile(HttpServletRequest httpServletRequest, @PathVariable String engnrNo) throws Exception{
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String baseFilePath = EngnrFileUtils.getBasePath();		
		
		
		MultipartHttpServletRequest multiPart = (MultipartHttpServletRequest)httpServletRequest;
		Iterator<String> fileNames = multiPart.getFileNames();

		while (fileNames.hasNext()) {
			MultipartFile file = multiPart.getFile(fileNames.next());
			//String inputName = file.getName();
			if(!file.isEmpty()) {
				
				EngnrFileVO eDTO = new EngnrFileVO();
				eDTO.setTypeNo(engnrNo);
				EngnrFileVO engnrFile = engnrFileService.engnrFileList(eDTO).get(0);
				String typePath = engnrFile.getTypePath();
				
				Date nowDate = new Date();
				SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMMdd");
				String date = simpleDate.format(nowDate);
				UUID uuid = UUID.randomUUID();				
				
				String originalFileName = file.getOriginalFilename().replace(" ",""); // 파일명
				Long size = file.getSize();
				String extension = FilenameUtils.getExtension(originalFileName);
				
				String newFileName = date + "_" + uuid.toString() + "_" +originalFileName + "." + extension;
				String newFilePath =  baseFilePath + typePath + File.separator + engnrNo + File.separator;
				File target = new File(newFilePath);
				/* uploadPath에 해당하는 디렉터리가 존재하지 않으면, 부모 디렉터리를 포함한 모든 디렉터리를 생성 */
				if(!target.exists()) {
					target.mkdirs();
				}				
					
				
				target = new File(newFilePath + newFileName);
				
				/* 1. 파일명 중복 방지, 서버에 저장할 파일명 (랜덤 문자열 + 확장자) */		
				file.transferTo(target);
				BufferedImage originalImg = ImageIO.read(target);			
				//원본이미지를 축소
				BufferedImage thumbnailImg = Scalr.resize(originalImg, 1080, 1440, null);			
				String thumbnailImgName = "boilergisa_" + newFileName;
				// 썸네일 업로드 경로
				String fullPath = newFilePath +  thumbnailImgName;
			       
				// 썸네일 파일 객체생성
				File newFile = new File(fullPath);	        
				ImageIO.write(thumbnailImg, extension, newFile);
				
				/* 파일 정보 추가 */
				engnrFile.setTypePath(typePath);
				engnrFile.setTypeNo(engnrNo);
				engnrFile.setOriginalName(newFileName);
				engnrFile.setThumbnailName(thumbnailImgName);
				engnrFile.setFileSize(file.getSize());
				
				engnrFileService.updateEngnrInfo(engnrFile);
				resultMap.put("engnrNo", engnrNo);
					
				}
			resultMap.put("result", "Failed");
			}
		
		return resultMap;
	}
	//엔지니어 파일 삭제
	@PostMapping(value="/deleteType/engnr/{engnrNo}/{fileIdx}")
	@ResponseBody	
	public Map<String, Object>deleteEngnrFile(HttpServletRequest httpServletRequest, @PathVariable Long fileIdx) throws Exception{
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String baseFilePath = EngnrFileUtils.getBasePath();
		
		EngnrFileVO getFile = new EngnrFileVO();
		getFile.setFileIdx(fileIdx);
		
		EngnrFileVO engnrFile = engnrFileService.engnrFileList(getFile).get(0);
		EngnrFileUtils.deleteFile(engnrFile.getThumbnailName(), engnrFile.getTypeNo(), baseFilePath);
		
		engnrFileService.deleteFile(fileIdx);
		
		resultMap.put("result", "Success");
		
		return resultMap;
	}
	

}