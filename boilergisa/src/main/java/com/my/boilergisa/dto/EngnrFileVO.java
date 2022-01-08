package com.my.boilergisa.dto;

import java.util.Date;

import lombok.Data;

@Data
public class EngnrFileVO {
	Long fileIdx;
	String engnrNo;
	String originalName;
	String thumbnailName;
	Long fileSize;
	Date insertDate;
}