package com.sapple.tot.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapple.helper.HelperUtils;
import com.sapple.tot.exceptions.CustomObjectException;
import com.sapple.tot.requestDto.DataSetIdentifierRequest;
import com.sapple.tot.requestDto.OwnerDataRequest;
import com.sapple.tot.requestDto.UserRegistrationRequest;
import com.sapple.tot.responseDto.BaseObjectResponse;
import com.sapple.tot.responseDto.BlankResponse;
import com.sapple.tot.responseDto.OwnerInfoResponse;
import com.sapple.tot.service.HostelOwnerService;

@RestController
@PropertySource("classpath:application.properties")
public class HostelOwnerController {
	// logs written in a file
	private static final Logger logger = LoggerFactory.getLogger(HostelOwnerController.class);

	@Autowired
	private HostelOwnerService hostelOwnerService;

	@PutMapping("/secureApi/owner")
	public @ResponseBody BaseObjectResponse<BlankResponse> saveOwnerInfo(@RequestBody OwnerDataRequest request,
			HttpServletRequest httpReq) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		logger.info("inside hostel owner controller");
		logger.info("PUT api : /secureApi/owner");
		logger.info("api desc: save owner info");
		String jsonObjectData = mapper.writeValueAsString(request);
		logger.debug("Input string " + jsonObjectData);

		if (request != null) {
			hostelOwnerService.saveOwnerInfo(request, HelperUtils.userId);
			BaseObjectResponse<BlankResponse> response = new BaseObjectResponse<BlankResponse>();
			response.setResponseObject(new BlankResponse());
			response.setErrorCode(0);
			response.setMessage("success");
			response.setTimestamp(System.currentTimeMillis());
			return response;
		} else {
			throw new CustomObjectException("2:Missing required parameter");
		}
	}

	@PostMapping("/secureApi/owner")
	public @ResponseBody BaseObjectResponse<OwnerInfoResponse> getOwnerInfo(
			@RequestBody DataSetIdentifierRequest request, HttpServletRequest httpReq) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		logger.info("inside hostel owner controller");
		logger.info("PUT api : /secureApi/owner");
		logger.info("api desc: save owner info");
		String jsonObjectData = mapper.writeValueAsString(request);
		logger.debug("Input string " + jsonObjectData);

		if (request != null) {
			BaseObjectResponse<OwnerInfoResponse> response = new BaseObjectResponse<OwnerInfoResponse>();
			response.setResponseObject(
					hostelOwnerService.getOwnerInfo(request.getDataSetIdentifier(), HelperUtils.userId));
			response.setErrorCode(0);
			response.setMessage("success");
			response.setTimestamp(System.currentTimeMillis());
			return response;
		} else {
			throw new CustomObjectException("2:Missing required parameter");
		}
	}

	@PostMapping("/secureApi/enlistUser")
	public @ResponseBody BaseObjectResponse<BlankResponse> enlistUser(@RequestBody UserRegistrationRequest request,
			HttpServletRequest httpReq) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		logger.info("inside hostel owner controller");
		logger.info("PUT api : /secureApi/enlistUser");
		logger.info("api desc: enlist user (tenant/guard/warden)");
		String jsonObjectData = mapper.writeValueAsString(request);
		logger.debug("Input string " + jsonObjectData);

		if (request != null) {
			hostelOwnerService.registerUser(request, HelperUtils.userId);
			BaseObjectResponse<BlankResponse> response = new BaseObjectResponse<BlankResponse>();
			response.setResponseObject(new BlankResponse());
			response.setErrorCode(0);
			response.setMessage("success");
			response.setTimestamp(System.currentTimeMillis());
			return response;
		} else {
			throw new CustomObjectException("2:Missing required parameter");
		}
	}
}
