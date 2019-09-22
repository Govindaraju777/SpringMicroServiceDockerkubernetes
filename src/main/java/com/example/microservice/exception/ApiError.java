package com.example.microservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * @author govindarajuv
 *
 */
@AllArgsConstructor
@Builder
@Getter
public class ApiError {
	private String message;
	private String debugMessage;
	private String errorMetaInformation;

}