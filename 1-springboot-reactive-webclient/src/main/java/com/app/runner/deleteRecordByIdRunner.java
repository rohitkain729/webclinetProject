package com.app.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

//@Component
public class deleteRecordByIdRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {

		WebClient client = WebClient.create("http://localhost:9999");

		Mono<String> mono = client.delete().uri("/employee/delete/7")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).retrieve().bodyToMono(String.class);

		mono.subscribe(System.out::println);

	}

}
