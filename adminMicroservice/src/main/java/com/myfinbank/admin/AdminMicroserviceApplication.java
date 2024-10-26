package com.myfinbank.admin;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class AdminMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminMicroserviceApplication.class, args);
	}
	
//	 @Bean
//	    public RestTemplate restTemplate() {
//	        return new RestTemplate();
//	    }

	    @Bean
	    public ModelMapper modelMapper() {
	        return new ModelMapper();
	    }
}
