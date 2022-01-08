package com.my.boilergisa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.my.boilergisa.interceptor.CustmrAfterInterceptor;
import com.my.boilergisa.interceptor.CustmrInterceptor;
import com.my.boilergisa.interceptor.EngnrAfterInterceptor;
import com.my.boilergisa.interceptor.EngnrInterceptor;

@Configuration 
public class InterceptorConfig implements WebMvcConfigurer {

	@Override  //인터셉터 등록
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new EngnrInterceptor())
        .addPathPatterns("/engnr/engnrHome","/engnr/engnrSetting")
        .excludePathPatterns("/static/**");
		
		registry.addInterceptor(new EngnrAfterInterceptor())
		.addPathPatterns("/engnr/engnrLogin");
		
		registry.addInterceptor(new CustmrInterceptor())
        .addPathPatterns("/custmr/custmrMain","/custmr/custmrSetting")
        .excludePathPatterns("/static/**");
		
		registry.addInterceptor(new CustmrAfterInterceptor())
		.addPathPatterns("/custmr/custmrLogin", "/custmr/custmrRegister");
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8"); // 파일 인코딩 설정
		multipartResolver.setMaxUploadSizePerFile(50 * 1024 * 1024); // 파일당 업로드 크기 제한 (5MB)
		return multipartResolver;
	}
}	
		
		/////////////////////////////////// MOBILE/PC 구분 Interceptor 설정
		/*
		 * registry.addInterceptor(new MobileCheckInterceptor()) .addPathPatterns("/**")
		 * .excludePathPatterns("/static/**") // TODO generator 추후 삭제
		 * .excludePathPatterns("/generator") .excludePathPatterns("/generator/**");
		 */
		
		
		////////////////////////////////// 업체 로그인  Interceptor 설정
//		registry.addInterceptor(new UserLoginInterceptor())
//			.addPathPatterns("/**")
//			.excludePathPatterns("/")
//			.excludePathPatterns("/login")
//			.excludePathPatterns("/static/**")
			// TODO generator 추후 삭제
//			.excludePathPatterns("/generator")
//			.excludePathPatterns("/generator/**")