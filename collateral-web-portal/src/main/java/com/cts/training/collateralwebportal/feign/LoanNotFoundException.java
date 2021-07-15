package com.cts.training.collateralwebportal.feign;

public class LoanNotFoundException extends RuntimeException {

	/**
	 * empty constructor
	 */
	public LoanNotFoundException() {
		super();
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public LoanNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public LoanNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 * @param message
	 */
	public LoanNotFoundException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param cause
	 */
	public LoanNotFoundException(Throwable cause) {
		super(cause);
	}

}