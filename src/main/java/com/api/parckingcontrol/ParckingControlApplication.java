package com.api.parckingcontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ParckingControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParckingControlApplication.class, args);
	}
	
	@GetMapping("/")
	public String index() {
		return "Hello World!";
	}
}
