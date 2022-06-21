package com.dating.springboot.exceptions;

public class InterestsNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InterestsNotFoundException() {
	}

	public InterestsNotFoundException(String message) {
		super(message);
	}
}
