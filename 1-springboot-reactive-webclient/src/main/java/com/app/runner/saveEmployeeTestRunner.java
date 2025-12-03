package com.app.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.app.model.Employee;

import reactor.core.publisher.Mono;

//@Component
public class saveEmployeeTestRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {

//		WebClient client = WebClient.builder().create("http://localhost:9999").

		WebClient client = WebClient.builder().baseUrl("http://localhost:9999")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

		Mono<String> mono = client.post().uri("/employee/save")
				.body(Mono.just(new Employee("ravi", "dev", 8000000.00)), Employee.class).retrieve()
				.bodyToMono(String.class);

//		Mono<String> mono = client.post().uri("/employee/save").bodyValue(new Employee( "abhi", "dev", 88800000.00))
//				.retrieve().bodyToMono(String.class)
//				.doOnError(err -> System.out.println("Error: " + err.getMessage()));

//		mono.subscribe(System.out::println);
		mono.subscribe(
			    msg -> System.out.println("Response: " + msg),
			    err -> System.err.println("WebClient ERROR: " + err)
			);
	}

}
