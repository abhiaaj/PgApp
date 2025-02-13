package com.sapple.tot.requestDto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MealSkipGetDataRequest {
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date date;
	private Long hostelId;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getHostelId() {
		return hostelId;
	}

	public void setHostelId(Long hostelId) {
		this.hostelId = hostelId;
	}
}
