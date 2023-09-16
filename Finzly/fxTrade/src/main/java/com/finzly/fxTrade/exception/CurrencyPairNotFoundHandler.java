package com.finzly.fxTrade.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.finzly.fxTrade.dto.ErrorResponse;

@ControllerAdvice
public class CurrencyPairNotFoundHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(CurrencyPairNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleEmptyTradesException(CurrencyPairNotFoundException ex) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErrorResponse errorResponse = new ErrorResponse("CurrencyPair is  Not Found");
		return new ResponseEntity<>(errorResponse, status);
	}

}