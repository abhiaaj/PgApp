package com.sapple.tot.responseDto;

public class BaseObjectResponse<T> extends BaseResponse {
	private T responseObject;

	public T getResponseObject() {
		return responseObject;
	}

	public void setResponseObject(T responseObject) {
		this.responseObject = responseObject;
	}
}
