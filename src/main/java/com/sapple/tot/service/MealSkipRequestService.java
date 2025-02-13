package com.sapple.tot.service;

import java.util.Date;
import java.util.List;

import com.sapple.tot.requestDto.MealSkipGetDataRequest;
import com.sapple.tot.requestDto.MealSkipRequestCreationRequest;
import com.sapple.tot.responseDto.MealSkipForOwnerResponse;
import com.sapple.tot.responseDto.MealSkipForTenantResponse;

public interface MealSkipRequestService {
	void createMealSkipRequest(MealSkipRequestCreationRequest request, Long userId);
	
	void deleteRequest(Long id, Long userId);
	
	List<MealSkipForTenantResponse> getMealSkipDataForTenant(Date date, Long userId);
	
	MealSkipForOwnerResponse getMealSkipDataForOwner(MealSkipGetDataRequest request, Long userId);
}
