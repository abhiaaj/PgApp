package com.sapple.tot.responseDto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sapple.tot.helperDto.MealSkipCommonDataDto;

public class MealSkipForTenantResponse {
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date date;
	private List<MealSkipCommonDataDto> mealTypeList;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<MealSkipCommonDataDto> getMealTypeList() {
		return mealTypeList;
	}
	public void setMealTypeList(List<MealSkipCommonDataDto> mealTypeList) {
		this.mealTypeList = mealTypeList;
	}
}
