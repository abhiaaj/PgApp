package com.sapple.tot.exceptions;

public class CustomListException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 * @param cause
	 */
	public CustomListException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public CustomListException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public CustomListException(Throwable cause) {
		super(cause);
	}
}
