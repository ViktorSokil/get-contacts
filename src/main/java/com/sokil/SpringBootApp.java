package com.sokil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.sokil"})
public class SpringBootApp  {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApp.class, args);
	}
}
