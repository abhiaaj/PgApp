package com.sapple.tot.exceptions;

public class CustomObjectException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 * @param cause
	 */
	public CustomObjectException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public CustomObjectException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public CustomObjectException(Throwable cause) {
		super(cause);
	}
}
