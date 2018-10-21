package com.ankamma.user.application.exception;

public class UserDetailsHttpException   {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private String errorCode;

	private String details;

	public UserDetailsHttpException(String message, String errorCode, String details) {
		super();

		this.message = message;
		this.errorCode = errorCode;
		this.details = details;
	}

	public String getMessage() {
		return message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getDetails() {
		return details;
	}

}

