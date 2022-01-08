/*
package ms.infotech.report.controller.rest;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import ms.infotech.report.model.dto.BizFiFileTbDTO;
import ms.infotech.report.model.service.BizFiFileTbService;
import ms.infotech.report.util.common.FileUploadUtil;
import ms.infotech.report.util.common.UUIDUtil;
import ms.infotech.report.util.encript.AES256;
import ms.infotech.report.util.staticval.CommonValue;

@RestController
public class FileController {
	
	@Autowired BizFiFileTbService bizFiFileTbService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "uploads/{uploadType}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> fileUploadList(HttpServletRequest httpServletRequest, @PathVariable String uploadType) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		String BASE_PATH = FileUploadUtil.getBasePath();
		
		MultipartHttpServletRequest multiPart = (MultipartHttpServletRequest)httpServletRequest;
		Iterator<String> fileNames = multiPart.getFileNames();
		int i=0;
		while (fileNames.hasNext()) {
			MultipartFile file = multiPart.getFile(fileNames.next());
			//String inputName = file.getName();
			if(!file.isEmpty()) {
				
				BizFiFileTbDTO eDTO = new BizFiFileTbDTO();
				// 파일 정보
				String originFilename = file.getOriginalFilename();
				Long size = file.getSize();
				String extension = originFilename.substring(originFilename.lastIndexOf(".") + 1).toLowerCase();
				
				// 서버에서 저장 할 파일 이름(물리명)
				String serverFileName = UUIDUtil.createUUID();
				// 서버에서 저장 할 파일 위치
				String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd"));
				
				String addtionalPath = "/" + uploadType + "/" + today;
				
				String serverFilePath = BASE_PATH + addtionalPath;
				String newFileName = serverFileName + "." + extension;
				File target = new File(serverFilePath + "/" + newFileName);
				// 경로 생성
				if (!new File(serverFilePath).exists()) {
					new File(serverFilePath).mkdirs();
				}
				// 파일 복사
				try {
					FileCopyUtils.copy(file.getBytes(), target);
				} catch (Exception e) {
					e.printStackTrace();
				}
				eDTO.setFileIdx(serverFileName);
				eDTO.setPath(addtionalPath);
				eDTO.setOriginNm(originFilename);
				eDTO.setExtension(extension);
				eDTO.setMimeType(file.getContentType());
				eDTO.setFileSize(String.valueOf(size));
				bizFiFileTbService.addBizFiFileTb(eDTO);
				
				result.put("fileIdx", serverFileName);
				SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
				String time = format1.format(new Date());
				result.put("regDt", time);
			}
		}
		
		return result;
	}
	
	@RequestMapping(value = "uploads/all/{uploadType}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> fileUploadAllList(HttpServletRequest httpServletRequest, @PathVariable String uploadType) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		String BASE_PATH = FileUploadUtil.getBasePath();
		
		MultipartHttpServletRequest multiPart = (MultipartHttpServletRequest)httpServletRequest;
		Iterator<String> fileNames = multiPart.getFileNames();
		int i=1;
		while (fileNames.hasNext()) {
			MultipartFile file = multiPart.getFile(fileNames.next());
			if(!file.isEmpty()) {
				
				BizFiFileTbDTO eDTO = new BizFiFileTbDTO();
				// 파일 정보
				String originFilename = file.getOriginalFilename();
				Long size = file.getSize();
				String extension = originFilename.substring(originFilename.lastIndexOf(".") + 1).toLowerCase();
				
				// 서버에서 저장 할 파일 이름(물리명)
				String serverFileName = UUIDUtil.createUUID();
				// 서버에서 저장 할 파일 위치
				String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd"));
				
				String addtionalPath = "/" + uploadType + "/" + today;
				
				String serverFilePath = BASE_PATH + addtionalPath;
				String newFileName = serverFileName + "." + extension;
				File target = new File(serverFilePath + "/" + newFileName);
				// 경로 생성
				if (!new File(serverFilePath).exists()) {
					new File(serverFilePath).mkdirs();
				}
				// 파일 복사
				try {
					FileCopyUtils.copy(file.getBytes(), target);
				} catch (Exception e) {
					e.printStackTrace();
				}
				eDTO.setFileIdx(serverFileName);
				eDTO.setPath(addtionalPath);
				eDTO.setOriginNm(originFilename);
				eDTO.setExtension(extension);
				eDTO.setMimeType(file.getContentType());
				eDTO.setFileSize(String.valueOf(size));
				bizFiFileTbService.addBizFiFileTb(eDTO);
				
				result.put("fileIdx_" + i, serverFileName);
				SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
				String time = format1.format(new Date());
				result.put("regDt_" + i, time);
				i++;
			}
		}
		
		return result;
	}
	
	@RequestMapping(value = "uploads/editor/{uploadType}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> fileUploadEditorList(HttpServletRequest httpServletRequest, @PathVariable String uploadType) throws Exception {
		
		String BASE_PATH = FileUploadUtil.getBasePath();
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		MultipartHttpServletRequest multiPart = (MultipartHttpServletRequest)httpServletRequest;
		Iterator<String> fileNames = multiPart.getFileNames();
		int i = 1;
		while (fileNames.hasNext()) {
			MultipartFile file = multiPart.getFile(fileNames.next());
			if(!file.isEmpty()) {
				
				BizFiFileTbDTO eDTO = new BizFiFileTbDTO();
				// 파일 정보
				String originFilename = file.getOriginalFilename();
				Long size = file.getSize();
				String extension = originFilename.substring(originFilename.lastIndexOf(".") + 1).toLowerCase();
				
				// 서버에서 저장 할 파일 이름(물리명)
				String serverFileName = UUIDUtil.createUUID();
				// 서버에서 저장 할 파일 위치
				String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd"));
				
				String addtionalPath = "/" + uploadType + "/" + today;
				
				String serverFilePath = BASE_PATH + addtionalPath;
				String newFileName = serverFileName + "." + extension;
				File target = new File(serverFilePath + "/" + newFileName);
				// 경로 생성
				if (!new File(serverFilePath).exists()) {
					new File(serverFilePath).mkdirs();
				}
				// 파일 복사
				try {
					FileCopyUtils.copy(file.getBytes(), target);
				} catch (Exception e) {
					e.printStackTrace();
				}
				eDTO.setFileIdx(serverFileName);
				eDTO.setPath(addtionalPath);
				eDTO.setOriginNm(originFilename);
				eDTO.setExtension(extension);
				eDTO.setMimeType(file.getContentType());
				eDTO.setFileSize(String.valueOf(size));
				eDTO.setDivision(uploadType);
				bizFiFileTbService.addBizFiFileTb(eDTO);
				
				
				Map<String, Object> innerMap = new HashMap<String, Object>();
				innerMap.put("id", serverFileName);
				innerMap.put("url", "/download/" + serverFileName);
				innerMap.put("title", originFilename);
				innerMap.put("thumb", "/download/" + serverFileName);
				resultMap.put("file-" + i, innerMap);
				i++;
			}
		}
		return resultMap;
	}

	@RequestMapping(value = "/download/{fileIdx}", method = RequestMethod.GET)
	public void downloadFile(@RequestParam Map<String, Object> input, @PathVariable String fileIdx, HttpServletRequest req, HttpServletResponse response) throws Exception {
		String fileName = "";
		String contentType = "";
		String filePath = "";
		InputStream inputStream = null;
		
		String BASE_PATH = FileUploadUtil.getBasePath();
		
		try {
			BizFiFileTbDTO eDTO = new BizFiFileTbDTO();
			eDTO.setFileIdx(fileIdx);
			
			BizFiFileTbDTO fileInfo = bizFiFileTbService.getBizFiFileTbList(eDTO).get(0);
			String original_File_Name = fileInfo.getOriginNm();
			String stored_File_Name = fileIdx;
			filePath = BASE_PATH + fileInfo.getPath() + "/" + stored_File_Name + "." + fileInfo.getExtension();

			File file = new File(filePath);
			inputStream = new FileInputStream(filePath); // 파일 읽어오기 
			OutputStream out = response.getOutputStream();
			int read = 0;
			byte[] buffer = new byte[1024];
			fileName = URLEncoder.encode(original_File_Name, "UTF-8");
			contentType = fileInfo.getMimeType();
		} catch (Exception e) {
			ClassPathResource resource = new ClassPathResource("/static/img/no-img.jpg");
			try {
				inputStream = new FileInputStream(resource.getFile());
			} catch(Exception e2) {
				inputStream = resource.getInputStream();
			}
			fileName = URLEncoder.encode("no-img.jpg", "UTF-8");
			contentType = "image/jpeg";
		}
		// 이강민 
		response.setHeader("Content-Type", contentType);
		//response.setHeader("Content-Length", file.length()+"");
		
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";");
		response.setHeader("Content-Description", "File Transfer");
		
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");

		OutputStream out = response.getOutputStream();
		FileCopyUtils.copy(inputStream, out);

		inputStream.close();
		out.flush();
	}
	
	@RequestMapping(value = "/external/download/{fileIdx}", method = RequestMethod.GET)
	@CrossOrigin
	public void externalDownloadFile(@RequestParam Map<String, Object> input, @PathVariable String fileIdx, HttpServletRequest req, HttpServletResponse response) throws Exception {
		String newfileIdx = AES256.decrypt(CommonValue.AES_ENCRIPT_KEY, fileIdx);
		if(newfileIdx.equals(fileIdx)) {
			logger.error("암호화되지 않은 파일 요청! 잘못된 요청!");
			throw new BadCredentialsException("wrong decript");
		}
		fileIdx = newfileIdx;
		String fileName = "";
		String contentType = "";
		String filePath = "";
		InputStream inputStream = null;
		
		String BASE_PATH = FileUploadUtil.getBasePath();
		
		try {
			BizFiFileTbDTO eDTO = new BizFiFileTbDTO();
			eDTO.setFileIdx(fileIdx);
			
			BizFiFileTbDTO fileInfo = bizFiFileTbService.getBizFiFileTbList(eDTO).get(0);
			String original_File_Name = fileInfo.getOriginNm();
			String stored_File_Name = fileIdx;
			filePath = BASE_PATH + fileInfo.getPath() + "/" + stored_File_Name + "." + fileInfo.getExtension();

			File file = new File(filePath);
			inputStream = new FileInputStream(filePath); // 파일 읽어오기 
			OutputStream out = response.getOutputStream();
			int read = 0;
			byte[] buffer = new byte[1024];
			fileName = URLEncoder.encode(original_File_Name, "UTF-8");
			contentType = fileInfo.getMimeType();
		} catch (Exception e) {
			ClassPathResource resource = new ClassPathResource("/static/img/no-img.jpg");
			try {
				inputStream = new FileInputStream(resource.getFile());
			} catch(Exception e2) {
				inputStream = resource.getInputStream();
			}
			fileName = URLEncoder.encode("no-img.jpg", "UTF-8");
			contentType = "image/jpeg";
		}
		// 이강민 
		response.setHeader("Content-Type", contentType);
		//response.setHeader("Content-Length", file.length()+"");
		
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";");
		response.setHeader("Content-Description", "File Transfer");
		
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");

		OutputStream out = response.getOutputStream();
		FileCopyUtils.copy(inputStream, out);

		inputStream.close();
		out.flush();
	}
}
*/