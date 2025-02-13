package com.sapple.tot.helperDto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MealSkipCreationDto {
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date date;
	private List<Long> mealTypeList;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<Long> getMealTypeList() {
		return mealTypeList;
	}
	public void setMealTypeList(List<Long> mealTypeList) {
		this.mealTypeList = mealTypeList;
	}
}
