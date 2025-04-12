package com.corpdomain.vacationpayapi.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	@Bean
	OpenAPI configureOpenAPI() {
		return new OpenAPI().info(new Info()
				.title("API Vacation Pay Calculator")
				.version("1.0")
				.description("API for calculating the amount of vacation pay"));
	}
}
