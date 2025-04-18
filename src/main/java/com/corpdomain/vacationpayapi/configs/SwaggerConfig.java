package com.corpdomain.vacationpayapi.configs;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;

@Configuration
public class SwaggerConfig {
	@Bean
	OpenAPI configureOpenAPI() {
		try {
			String openApiContent = new String(Files.readAllBytes(Paths.get("src/main/resources/openapi.json")));
			return new OpenAPIV3Parser().readContents(openApiContent, null, null).getOpenAPI();
		} catch (Exception e) {
			throw new RuntimeException("Failed to load OpenAPI spec", e);
		}
	}
}
