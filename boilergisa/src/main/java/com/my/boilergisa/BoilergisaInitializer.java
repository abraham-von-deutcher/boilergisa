package com.my.boilergisa;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Component
@Log
public class BoilergisaInitializer implements ApplicationRunner{
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.fine("프로젝트 기동시 시작되는 위치");
	}
}
