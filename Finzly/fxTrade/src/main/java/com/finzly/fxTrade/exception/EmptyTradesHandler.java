package com.finzly.fxTrade.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.finzly.fxTrade.dto.ErrorResponse;

@ControllerAdvice
public class EmptyTradesHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EmptyTradesException.class)
	public ResponseEntity<ErrorResponse> handleEmptyTradesException(EmptyTradesException ex) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErrorResponse errorResponse = new ErrorResponse("No Trades Found");
		return new ResponseEntity<>(errorResponse, status);
	}
}
