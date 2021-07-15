package com.cts.training.collateralwebportal.exception;

import org.springframework.http.HttpStatus;

import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiOperation(value = "Api Error Response Class")
public class ApiErrorResponse {

	private HttpStatus httpStatus;


	private String message;

	private String localizedMessage;

	/**
	 * Empty constructor
	 */
	public ApiErrorResponse() {
	}

	/**
	 * 
	 * @param httpStatus
	 */
	public ApiErrorResponse(HttpStatus httpStatus) {
		this();
		this.httpStatus = httpStatus;
	}

	/**
	 * 
	 * @param httpStatus
	 * @param throwable
	 */
	public ApiErrorResponse(HttpStatus httpStatus, Throwable throwable) {
		this();
		this.httpStatus = httpStatus;
		this.message = "Unexpected error in the request";
		this.localizedMessage = throwable.getLocalizedMessage();
	}

	/**
	 * 
	 * @param httpStatus
	 * @param message
	 * @param throwable
	 */
	public ApiErrorResponse(HttpStatus httpStatus, String message, Throwable throwable) {
		this();
		this.httpStatus = httpStatus;
		this.message = message;
		this.localizedMessage = throwable.getLocalizedMessage();
	}

}
