package com.fincare.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UpdateDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpdateDetailsApplication.class, args);
	}

}
