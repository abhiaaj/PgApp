package com.sapple.tot.service;

import java.util.List;

import com.sapple.tot.requestDto.RegisterHostelRequest;
import com.sapple.tot.responseDto.HostelDataResponse;
import com.sapple.tot.responseDto.HostelListResponse;

public interface HostelService {
	Long createHostel(RegisterHostelRequest request, Long userId);
	
	HostelDataResponse getHostelData(Long hostelId);
	
	List<HostelListResponse> getHostelsForOwner(Long userId);
}
