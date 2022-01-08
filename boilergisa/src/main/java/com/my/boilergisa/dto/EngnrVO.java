package com.my.boilergisa.dto;

import java.util.Date;
import lombok.Data;

@Data
public class EngnrVO {
	String engnrNo;
	String engnrID;
	String engnrPassword;	
	String engnrName;
	String address1;
	String address2;
	String engnrNumber;
	String engnrIntro;
	String businessType;
	String workTime1;
	String workTime2;
	String breakTime;
	String engnrProfile;
	String setAddress;
	Date engnrJionDate;
	Date engnrLoginDate;
	
	double engnrRating;
	
}