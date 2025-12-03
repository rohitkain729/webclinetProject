package com.app.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.app.model.Employee;

import reactor.core.publisher.Mono;

@Component
public class updateEmployeeTestByIdRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {

		WebClient client = WebClient.create("http://localhost:9999/employee");

		Mono<Employee> mono = client.put().uri("/update/2")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(new Employee(2,"meena", "QA", 100000.00)), Employee.class).retrieve()
				.bodyToMono(Employee.class);

		mono.subscribe(System.out::println);
	}

}
