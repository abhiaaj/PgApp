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
import com.sapple.tot.requestDto.TenantDataRequest;
import com.sapple.tot.responseDto.BaseObjectResponse;
import com.sapple.tot.responseDto.BlankResponse;
import com.sapple.tot.responseDto.TenantInfoResponse;
import com.sapple.tot.service.TenantService;

@RestController
@PropertySource("classpath:application.properties")
public class TenantController {
	// logs written in a file
	private static final Logger logger = LoggerFactory.getLogger(TenantController.class);

	@Autowired
	TenantService tenantService;

	@PutMapping("/secureApi/tenant")
	public @ResponseBody BaseObjectResponse<BlankResponse> saveTenantInfo(@RequestBody TenantDataRequest request,
			HttpServletRequest httpReq) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		logger.info("inside tenant controller");
		logger.info("PUT api : /api/tenant");
		logger.info("api desc: save tenant info");
		String jsonObjectData = mapper.writeValueAsString(request);
		logger.debug("Input string " + jsonObjectData);

		if (request != null) {
			tenantService.saveTenantInfo(request, HelperUtils.userId);
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
	
	@PostMapping("/secureApi/tenantData")
	public @ResponseBody BaseObjectResponse<TenantInfoResponse> getTenantInfo(@RequestBody DataSetIdentifierRequest request,
			HttpServletRequest httpReq) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		logger.info("inside tenant controller");
		logger.info("PUT api : /secureApi/tenant");
		logger.info("api desc: get tenant info");
		String jsonObjectData = mapper.writeValueAsString(request);
		logger.debug("Input string " + jsonObjectData);

		if (request != null) {
			BaseObjectResponse<TenantInfoResponse> response = new BaseObjectResponse<TenantInfoResponse>();
			response.setResponseObject(tenantService.getTenantInfo(request.getDataSetIdentifier(), HelperUtils.userId));
			response.setErrorCode(0);
			response.setMessage("success");
			response.setTimestamp(System.currentTimeMillis());
			return response;
		} else {
			throw new CustomObjectException("2:Missing required parameter");
		}
	}
}
