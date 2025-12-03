package com.app.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.app.model.Employee;

import reactor.core.publisher.Flux;

//@Component
public class getAllEmployeeTestData implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {

		WebClient client = WebClient.create("http://localhost:9999");

		Flux<Employee> flux = client.get().uri("/employee/getall")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).retrieve()
				.bodyToFlux(Employee.class);

		flux.doOnNext(System.out::println).blockLast();

	}

}
