package com.example.microservice.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author govindarajuv
 *
 */
@RestController
public class AppHealthCheckApiController {
	@GetMapping("/service-health-check")
	public ResponseEntity<String> myCustomCheck() {
		return new ResponseEntity<>("Application is up and running.", HttpStatus.OK);
	}
}
