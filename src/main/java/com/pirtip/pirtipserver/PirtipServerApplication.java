package com.pirtip.pirtipserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PirtipServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PirtipServerApplication.class, args);
	}

}
