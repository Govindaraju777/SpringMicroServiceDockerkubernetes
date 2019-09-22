package com.example.microservice.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.example.microservice.exception.ApiError;
import com.example.microservice.exception.ApiErrorResponse;
import com.example.microservice.exception.EmployeeException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author govindarajuv
 *
 */
@RestControllerAdvice
@Slf4j
public class ApiResponseEntityExceptionHandler { //extends ResponseEntityExceptionHandler {

	private static final String MALFORM_REQ_BODY_ERROR = "Malformed JSON request body.";
	private static final String MALFORM_REQ_BODY_META_INFO = "Please check input/form body.";
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,WebRequest request) {
		log.error("Request Error-Bad Format", ex);
		List<ApiError> apiErrors = new ArrayList<>();
		ApiError apError = ApiError.builder().message(MALFORM_REQ_BODY_ERROR)
				.errorMetaInformation(MALFORM_REQ_BODY_META_INFO).build();
		apiErrors.add(apError);
		ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder().status(HttpStatus.BAD_REQUEST).timestamp(LocalDateTime.now())
				.apiErrors(apiErrors).build();
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
	}
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler({NoHandlerFoundException.class})
	//@ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentNotValidException.class,HttpRequestMethodNotSupportedException.class})
	protected ResponseEntity<Object> handleNoHandlerFoundException(Exception ex) {
		log.error("Url Not Found : ", ex);
		List<ApiError> apiErrors = new ArrayList<>();
	    ApiError apError = ApiError.builder().message("Http Method/MIME-Type Violation: ").errorMetaInformation("Url Not Found").build();
	    apiErrors.add(apError);
		ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder().status(HttpStatus.NOT_FOUND).timestamp(LocalDateTime.now()).apiErrors(apiErrors).build();
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler({MethodArgumentNotValidException.class,HttpRequestMethodNotSupportedException.class,HttpMediaTypeNotSupportedException.class})
	public ResponseEntity<ApiErrorResponse> handleException(Exception ex) {
		log.error("Bad Request method/Argument : ", ex);
		List<ApiError> apiErrors = new ArrayList<>();
	    ApiError apError = ApiError.builder().message("Http Method/MIME-Type Violation: ").errorMetaInformation("Url Not Found").build();
	    apiErrors.add(apError);
		ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder().status(HttpStatus.BAD_REQUEST).timestamp(LocalDateTime.now()).apiErrors(apiErrors).build();
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmployeeException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(Exception ex, WebRequest request) {
        log.error("Validation Violations: ", ex);
        List<ApiError> apiErrors = new ArrayList<>();
        ApiError apError = ApiError.builder().message("MethodArgumentNotValid").errorMetaInformation("").build();
        apiErrors.add(apError);
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder().status(HttpStatus.BAD_REQUEST).timestamp(LocalDateTime.now()).apiErrors(apiErrors).build();
        
        log.error("T24AdaptorException :",ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
    }
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleInternalServerException(Exception ex, HttpServletRequest request) {
		log.error("Internal Error:",ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).timestamp(LocalDateTime.now()).build());
    }
	
}
