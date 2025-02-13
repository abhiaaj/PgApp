package com.sapple.tot.service;

import com.sapple.tot.requestDto.OtpConfirmRequest;
import com.sapple.tot.requestDto.UserRegistrationRequest;
import com.sapple.tot.responseDto.ProfileStatusResponse;

public interface UserService {
	Long createUser(UserRegistrationRequest request);
	
	void confirmOtp(OtpConfirmRequest request);
	
	void sendOtp(String contactNo);
	
	ProfileStatusResponse getProfileStatus(Long hostelId, Long userId);
}
