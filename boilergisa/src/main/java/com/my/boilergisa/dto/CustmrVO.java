package com.my.boilergisa.dto;

import java.util.Date;
import lombok.Data;

@Data
public class CustmrVO {
	private String custmrID;
	private String custmrName;
	private String custmrPassword;
	private String custmrSetAddress;
	private String custmrProfile;
	private Date custmrJoinDate;
	private Date custmrLoginDate;
	
}