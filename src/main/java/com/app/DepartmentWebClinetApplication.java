package com.app;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.app.entity.Department;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
@RequestMapping("/department-client")
public class DepartmentWebClinetApplication {

	WebClient webClient;

	@PostConstruct
	public void init() {

		webClient = WebClient.builder().baseUrl("http://localhost:4444")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}

	@SuppressWarnings("deprecation")
	@PostMapping("/save")
	public Mono<String> Booknow(@RequestBody Department department) {
		return webClient.post().uri("/departments").syncBody(department).retrieve().bodyToMono(String.class);
	}

	@GetMapping("/all")
	public Flux<Department> traclAllDepartments() {
		return webClient.get().uri("/departments").retrieve().bodyToFlux(Department.class);
	}

	@GetMapping("all/{id}")
	public Mono<Department> getDeptById(@PathVariable("id") int id) {
		return webClient.get().uri("/departments/" + id).retrieve().bodyToMono(Department.class);
	}

	@DeleteMapping("/all/{id}")
	public Mono<String> deleteDepart(@PathVariable("id") int id) {

		return webClient.delete().uri("/departments/" + id).retrieve().bodyToMono(String.class);
	}

	@SuppressWarnings("deprecation")
	@PutMapping("/all/{id}")
	public Mono<Department> updateDepart(@PathVariable("id") int id, @RequestBody Department department) {

		return webClient.put().uri("/departments/" + id).syncBody(department).retrieve().bodyToMono(Department.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(DepartmentWebClinetApplication.class, args);
	}

}
