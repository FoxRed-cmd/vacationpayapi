package com.corpdomain.vacationpayapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class VacationPayApplication {
	@Value("${server.port}")
	private int port;

	@Value("${server.address}")
	private String address;

	public static void main(String[] args) {
		SpringApplication.run(VacationPayApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void printSwaggerLink() {

		System.out.printf("http://%s:%d/swagger-ui/index.html\n", address, port);
	}

}
