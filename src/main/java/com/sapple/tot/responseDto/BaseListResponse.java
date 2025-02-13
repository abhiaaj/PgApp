package com.sapple.tot.responseDto;

import java.util.List;

public class BaseListResponse<T> extends BaseResponse {
	private List<T> responseList;

	public List<T> getResponseList() {
		return responseList;
	}

	public void setResponseList(List<T> responseList) {
		this.responseList = responseList;
	}
}
