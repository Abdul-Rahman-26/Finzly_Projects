package com.finzly.fxTrade.exception;

public class CurrencyPairAlreadyExistsException extends RuntimeException {

	private String message;

	public CurrencyPairAlreadyExistsException() {

	}

	public CurrencyPairAlreadyExistsException(String message) {
		super(message);
		this.message = message;
	}
}
