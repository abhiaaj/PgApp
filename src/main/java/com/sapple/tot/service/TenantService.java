package com.sapple.tot.service;

import com.sapple.tot.requestDto.TenantDataRequest;
import com.sapple.tot.responseDto.TenantInfoResponse;
import com.sapple.tot.responseDto.ProfileStatusResponse;

public interface TenantService {
	ProfileStatusResponse getTenantProfileStatus(Long userId);
	
	void saveTenantInfo(TenantDataRequest request, Long userId);
	
	TenantInfoResponse getTenantInfo(Integer dataSetIdentifier, Long userId);
}
