package dev.me.hr.config.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ TransitionException.class })
	protected ResponseEntity<Object> handleTransitionException(Exception e, WebRequest request) {
		TransitionException ex = (TransitionException) e;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ErrorDTO errorClass = new ErrorDTO(ex.getErrorCode(), ex.getErrorMessage());

		return handleExceptionInternal(e, errorClass, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

}
