package com.dating.springboot.exceptions;

public class MatchNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MatchNotFoundException() {
	}

	public MatchNotFoundException(String message) {
		super(message);
	}
}
