package com.sapple.tot.service;

import com.sapple.tot.requestDto.OwnerDataRequest;
import com.sapple.tot.requestDto.UserRegistrationRequest;
import com.sapple.tot.responseDto.OwnerInfoResponse;

public interface HostelOwnerService {
	void saveOwnerInfo(OwnerDataRequest request, Long userId);
	
	OwnerInfoResponse getOwnerInfo(Integer dataSetIdentifier, Long userId);
	
	void registerUser(UserRegistrationRequest request, Long userId);
}
