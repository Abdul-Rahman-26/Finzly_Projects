package com.finzly.fxTrade.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.finzly.fxTrade.dto.ErrorResponse;

@ControllerAdvice
public class CurrencyPairAlreadyExistsExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(CurrencyPairAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleEmptyTradesException(CurrencyPairAlreadyExistsException ex) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErrorResponse errorResponse = new ErrorResponse("CurrencyPair already Exists");
		return new ResponseEntity<>(errorResponse, status);
	}

}
