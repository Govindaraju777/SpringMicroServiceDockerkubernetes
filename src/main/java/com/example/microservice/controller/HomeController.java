/**
 * 
 */
package com.example.microservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author govindarajuv
 *
 */
@RestControllerAdvice
@RequestMapping("/")
public class HomeController {

	public ResponseEntity<String> home(){
		return new ResponseEntity<>("Application is up and running.", HttpStatus.OK);
	}
}
 