package com.fdmgroup.spring_webclient_exercise_one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class SpringWebClientExerciseOneApplication {

	private final String CONNECTION_URL = "http://localhost:8081";

	public static void main(String[] args) {
		SpringApplication.run(SpringWebClientExerciseOneApplication.class, args);
	}

	@Bean
	public WebClient contactClient() {
		return WebClient.builder().baseUrl(CONNECTION_URL)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}
}
