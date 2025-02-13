package com.sapple.tot.requestDto;

import java.util.List;

import com.sapple.tot.helperDto.MealSkipCreationDto;

public class MealSkipRequestCreationRequest {
	private Long hostelId;
	private List<MealSkipCreationDto> list;
	
	public Long getHostelId() {
		return hostelId;
	}

	public void setHostelId(Long hostelId) {
		this.hostelId = hostelId;
	}

	public List<MealSkipCreationDto> getList() {
		return list;
	}

	public void setList(List<MealSkipCreationDto> list) {
		this.list = list;
	}
}