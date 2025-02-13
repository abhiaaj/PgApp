package com.sapple.tot.service;

import java.util.List;

import com.sapple.tot.requestDto.OtpConfirmRequest;
import com.sapple.tot.requestDto.VisitorRequestCreationRequest;
import com.sapple.tot.requestDto.VisitorRequestUpdateRequest;
import com.sapple.tot.responseDto.VisitorRequestsForTenantResponse;
import com.sapple.tot.responseDto.VisitiorOtpVerificationResponse;
import com.sapple.tot.responseDto.VisitorRequestsForOwnerResponse;

public interface VisitorRequestsService {
	void createVisitorRequest(VisitorRequestCreationRequest request, Long userId);
	
	List<VisitorRequestsForTenantResponse> getVisitorRequestsForTenant(Long userId);
	
	VisitorRequestsForOwnerResponse getVisitorRequestsForOwner(Long hostelId, Long userId);
	
	void deleteRequest(Long id, Long userId);
	
	void updateVisitorRequestStatus(VisitorRequestUpdateRequest request, Long userId);
	
	VisitiorOtpVerificationResponse verifyVisitorOtp(OtpConfirmRequest request, Long userId);
}
