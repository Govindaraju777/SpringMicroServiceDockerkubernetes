Spring boot microservice application
Example Spring boot microservice application which consumes another microservice - https://github.com/Govindaraju777/SpringMicroServiceDockerkubernetes/tree/EmployeeService

Pom.xml

<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<!-- <version>1.5.2.RELEASE</version> -->
		<version>2.1.8.RELEASE</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.example.microservice</groupId>
	<artifactId>employee-consumer</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>employee-consumer</name>
	<description>Demo project for Spring Boot</description>

	<properties>
		<java.version>1.8</java.version>
		<spring-boot-admin.version>2.1.5</spring-boot-admin.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		
		 
	</dependencies>



<br>


package com.example.microservice;

import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.RestClientException;

@SpringBootApplication
//@EnableDiscoveryClient
//@EnableEurekaClient
public class EmployeeConsumerApplication implements CommandLineRunner {

	public static void main(String[] args) throws RestClientException, IOException {
		ApplicationContext ctx;
		ctx = SpringApplication.run(EmployeeConsumerApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

	}
}


<br>


package com.example.microservice.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@RestController
public class ConsumerControllerClient {

	@Autowired
	private RestTemplate restTemplate;
	
	
	@GetMapping("/testMicroserviceRestClient")
	public ResponseEntity<String> getEmployee() throws RestClientException, IOException {

		String baseUrl = "http://localhost:8081/employee";
		ResponseEntity<String> response = null;

		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class);
			//response = restTemplate.getForObject("http://employee-service//api/v1/employees/employee",String.class);
			//response = restTemplate.getForObject("http://EMPLOYEE-SERVICE/employee", String.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		System.out.println("-------------------------------------"+response);
		return response;
	}

	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}
}




