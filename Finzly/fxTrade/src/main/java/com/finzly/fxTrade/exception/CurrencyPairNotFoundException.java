package com.finzly.fxTrade.exception;

public class CurrencyPairNotFoundException extends RuntimeException {

	private String message;

	public CurrencyPairNotFoundException() {

	}

	public CurrencyPairNotFoundException(String message) {
		super(message);
		this.message = message;
	}

}
