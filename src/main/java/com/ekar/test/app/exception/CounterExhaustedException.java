package com.ekar.test.app.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CounterExhaustedException extends RuntimeException {
	private  String errorCode;
	private String message;
	private HttpStatus httpStatus;


	public CounterExhaustedException(String message, String errorCode, HttpStatus httpStatus) {
		this.message = message;
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}
}
