package com.ankamma.user.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserDetailsException.class)
	public final ResponseEntity<UserDetailsHttpException> handleUserDetailsException(UserDetailsException ex,
			WebRequest request) {
		UserDetailsHttpException exceptionResponse = new UserDetailsHttpException(ex.getMessage(), ex.getErrorCode(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

}
