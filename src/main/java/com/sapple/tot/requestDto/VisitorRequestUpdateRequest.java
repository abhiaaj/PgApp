package com.sapple.tot.requestDto;

public class VisitorRequestUpdateRequest {
	private Long visitorRequestId;
	private Integer statusId;
	public Long getVisitorRequestId() {
		return visitorRequestId;
	}
	public void setVisitorRequestId(Long visitorRequestId) {
		this.visitorRequestId = visitorRequestId;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
}
