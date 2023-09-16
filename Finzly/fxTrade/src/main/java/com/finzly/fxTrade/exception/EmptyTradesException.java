package com.finzly.fxTrade.exception;

public class EmptyTradesException extends RuntimeException {
	private String message;

	public EmptyTradesException() {

	}

	public EmptyTradesException(String message) {
		super(message);
		this.message = message;
	}

}
