package com.milap.tambolaticketgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TambolaTicketGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TambolaTicketGeneratorApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/new").allowedOrigins("http://192.168.1.7:3000", "http://localhost:3000");
			}
		};
	}
}
