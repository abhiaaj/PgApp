package com.sapple.tot.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapple.helper.HelperUtils;
import com.sapple.tot.exceptions.CustomObjectException;
import com.sapple.tot.requestDto.IdRequest;
import com.sapple.tot.requestDto.RegisterHostelRequest;
import com.sapple.tot.responseDto.BaseListResponse;
import com.sapple.tot.responseDto.BaseObjectResponse;
import com.sapple.tot.responseDto.HostelDataResponse;
import com.sapple.tot.responseDto.HostelListResponse;
import com.sapple.tot.responseDto.HostelRegistrationResponse;
import com.sapple.tot.service.HostelService;

@RestController
@PropertySource("classpath:application.properties")
public class HostelController {
	// logs written in a file
	private static final Logger logger = LoggerFactory.getLogger(HostelOwnerController.class);

	@Autowired
	private HostelService hostelService;

	@PostMapping("/secureApi/hostel")
	public @ResponseBody BaseObjectResponse<HostelRegistrationResponse> createHostel(
			@RequestBody RegisterHostelRequest request, HttpServletRequest httpReq) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		logger.info("inside hostel controller");
		logger.info("POST api : /secureApi/hostel");
		logger.info("api desc: register hostel");
		String jsonObjectData = mapper.writeValueAsString(request);
		logger.debug("Input string " + jsonObjectData);

		if (request != null) {
			Long hostelId = hostelService.createHostel(request, HelperUtils.userId);
			BaseObjectResponse<HostelRegistrationResponse> response = new BaseObjectResponse<HostelRegistrationResponse>();
			HostelRegistrationResponse subResponse = new HostelRegistrationResponse();
			response.setErrorCode(0);
			response.setMessage("hostel registered successfully");
			response.setTimestamp(System.currentTimeMillis());

			subResponse.setHostelId(hostelId);
			response.setResponseObject(subResponse);
			return response;
		} else {
			throw new CustomObjectException("2:Missing required parameter");
		}
	}

	@PostMapping("/secureApi/hostelData")
	public @ResponseBody BaseObjectResponse<HostelDataResponse> getHostelData(@RequestBody IdRequest request,
			HttpServletRequest httpReq) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		logger.info("inside hostel controller");
		logger.info("POST api : /secureApi/hostel");
		logger.info("api desc: get hostel info");
		String jsonObjectData = mapper.writeValueAsString(request);
		logger.debug("Input string " + jsonObjectData);

		if (request != null) {
			BaseObjectResponse<HostelDataResponse> response = new BaseObjectResponse<HostelDataResponse>();
			response.setResponseObject(hostelService.getHostelData(request.getId()));
			response.setErrorCode(0);
			response.setMessage("success");
			response.setTimestamp(System.currentTimeMillis());
			return response;
		} else {
			throw new CustomObjectException("2:Missing required parameter");
		}
	}

	@GetMapping("/secureApi/hostelList")
	public @ResponseBody BaseListResponse<HostelListResponse> getHostelList(HttpServletRequest httpReq)
			throws JsonProcessingException {
		logger.info("inside hostel controller");
		logger.info("POST api : /secureApi/hostelList");
		logger.info("api desc: get hostel list from owner");

		BaseListResponse<HostelListResponse> response = new BaseListResponse<HostelListResponse>();
		response.setResponseList(hostelService.getHostelsForOwner(HelperUtils.userId));
		response.setErrorCode(0);
		response.setMessage("success");
		response.setTimestamp(System.currentTimeMillis());
		return response;
	}
}
