package com.ymotse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;

/**
 * 
 * @author yitshhaq.fukushima
 *
 */
@SpringBootApplication
public class BootstrapAuthJwt {

	public static void main(String[] args) {
		SpringApplication.run(BootstrapAuthJwt.class, args);
		
		System.out.println("\n\n\n#################################################################");
		System.out.println("Bootstrap Auth JWT service started.");
		System.out.println("Spring Version: " + SpringVersion.getVersion());
		System.out.println("Spring Boot Version: " + SpringBootVersion.getVersion());
		System.out.println("#################################################################\n\n\n");
	}

}
