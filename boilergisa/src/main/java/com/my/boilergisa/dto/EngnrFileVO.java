package com.my.boilergisa.dto;

import java.util.Date;

import lombok.Data;

@Data
public class EngnrFileVO {
	Long fileIdx;
	String typePath;
	String typeNo;
	String originalName;
	String thumbnailName;
	Long fileSize;
	String delteYn;
	Date insertDate;
	Date deleteDate;
	
}