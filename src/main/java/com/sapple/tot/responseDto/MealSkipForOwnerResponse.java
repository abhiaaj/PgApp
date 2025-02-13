package com.sapple.tot.responseDto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sapple.tot.helperDto.TenantCommonInfoDto;

public class MealSkipForOwnerResponse {
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date date;
	private List<TenantCommonInfoDto> breakfastSkipList;
	private List<TenantCommonInfoDto> lunchSkipList;
	private List<TenantCommonInfoDto> dinnerSkipList;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<TenantCommonInfoDto> getBreakfastSkipList() {
		return breakfastSkipList;
	}
	public void setBreakfastSkipList(List<TenantCommonInfoDto> breakfastSkipList) {
		this.breakfastSkipList = breakfastSkipList;
	}
	public List<TenantCommonInfoDto> getLunchSkipList() {
		return lunchSkipList;
	}
	public void setLunchSkipList(List<TenantCommonInfoDto> lunchSkipList) {
		this.lunchSkipList = lunchSkipList;
	}
	public List<TenantCommonInfoDto> getDinnerSkipList() {
		return dinnerSkipList;
	}
	public void setDinnerSkipList(List<TenantCommonInfoDto> dinnerSkipList) {
		this.dinnerSkipList = dinnerSkipList;
	}
}
