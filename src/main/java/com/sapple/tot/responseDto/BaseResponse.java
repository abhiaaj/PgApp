package com.sapple.tot.responseDto;

public class BaseResponse {
	private String message;
	private long timestamp;
	private Integer errorCode;

	/**
	 * default constructor 
	 */
	public BaseResponse() {
		
	}

	/**
	 * @param status
	 * @param message
	 * @param timestamp
	 */
	public BaseResponse(String message, long timestamp) {
		super();
		this.message = message;
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long l) {
		this.timestamp = l;
	}
}

