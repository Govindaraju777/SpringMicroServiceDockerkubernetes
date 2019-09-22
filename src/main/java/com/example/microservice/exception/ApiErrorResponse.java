package com.example.microservice.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author govindarajuv
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@Accessors(chain = true)
@Builder
public class ApiErrorResponse {
	
	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private List<ApiError> apiErrors;
}
