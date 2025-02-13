package com.sapple.tot.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.sapple.tot.requestDto.OtpConfirmRequest;
import com.sapple.tot.requestDto.VisitorRequestCreationRequest;
import com.sapple.tot.requestDto.VisitorRequestUpdateRequest;
import com.sapple.tot.responseDto.BaseListResponse;
import com.sapple.tot.responseDto.BaseObjectResponse;
import com.sapple.tot.responseDto.BlankResponse;
import com.sapple.tot.responseDto.VisitiorOtpVerificationResponse;
import com.sapple.tot.responseDto.VisitorRequestsForOwnerResponse;
import com.sapple.tot.responseDto.VisitorRequestsForTenantResponse;
import com.sapple.tot.service.VisitorRequestsService;

@RestController
@PropertySource("classpath:application.properties")
public class VisitorRequestsController {
	// logs written in a file
	private static final Logger logger = LoggerFactory.getLogger(HostelOwnerController.class);

	@Autowired
	private VisitorRequestsService visitorReqService;

	@PostMapping("/secureApi/visitorRequest")
	public @ResponseBody BaseObjectResponse<BlankResponse> createVisitorRequest(
			@RequestBody VisitorRequestCreationRequest request, HttpServletRequest httpReq)
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		logger.info("inside visitor requests controller");
		logger.info("PUT api : /secureApi/visitorRequest");
		logger.info("api desc: create a new visitor request");
		String jsonObjectData = mapper.writeValueAsString(request);
		logger.debug("Input string " + jsonObjectData);

		if (request != null) {
			visitorReqService.createVisitorRequest(request, HelperUtils.userId);
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

	@GetMapping("/secureApi/visitorRequestsForTenant")
	public @ResponseBody BaseListResponse<VisitorRequestsForTenantResponse> getVisitorRequestsForTenant(
			HttpServletRequest httpReq) {
		logger.info("inside visitor requests controller");
		logger.info("PUT api : /secureApi/visitorRequestsByTenant");
		logger.info("api desc: get all requestsfor the tenant");

		BaseListResponse<VisitorRequestsForTenantResponse> response = new BaseListResponse<VisitorRequestsForTenantResponse>();
		response.setResponseList(visitorReqService.getVisitorRequestsForTenant(HelperUtils.userId));
		response.setErrorCode(0);
		response.setMessage("success");
		response.setTimestamp(System.currentTimeMillis());
		return response;
	}

	@PostMapping("/secureApi/visitorRequestsForOwner")
	public @ResponseBody BaseObjectResponse<VisitorRequestsForOwnerResponse> getVisitorRequestsForOwner(
			@RequestBody IdRequest request, HttpServletRequest httpReq) {
		logger.info("inside visitor requests controller");
		logger.info("PUT api : /secureApi/visitorRequestsByTenant");
		logger.info("api desc: get all requestsfor the tenant");

		BaseObjectResponse<VisitorRequestsForOwnerResponse> response = new BaseObjectResponse<VisitorRequestsForOwnerResponse>();
		response.setResponseObject(visitorReqService.getVisitorRequestsForOwner(request.getId(), HelperUtils.userId));
		response.setErrorCode(0);
		response.setMessage("success");
		response.setTimestamp(System.currentTimeMillis());
		return response;
	}

	@DeleteMapping("/secureApi/visitorRequest")
	public @ResponseBody BaseObjectResponse<BlankResponse> deleteVisitorRequest(@RequestBody IdRequest request,
			HttpServletRequest httpReq) {
		logger.info("inside visitor requests controller");
		logger.info("DELETE api : /secureApi/visitorRequest");
		logger.info("api desc: delete visitor request");

		if (request != null) {
			visitorReqService.deleteRequest(request.getId(), HelperUtils.userId);
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

	@PostMapping("/secureApi/updateVisitorRequestStatus")
	public @ResponseBody BaseObjectResponse<BlankResponse> updateVisitorRequestStatus(
			@RequestBody VisitorRequestUpdateRequest request, HttpServletRequest httpReq)
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		logger.info("inside visitor request controller");
		logger.info("PUT api : /secureApi/updateVisitorRequestStatus");
		logger.info("api desc: update visitor request status");
		String jsonObjectData = mapper.writeValueAsString(request);
		logger.debug("Input string " + jsonObjectData);

		if (request != null) {
			visitorReqService.updateVisitorRequestStatus(request, HelperUtils.userId);
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

	@PostMapping("/secureApi/verifyVisitorOtp")
	public @ResponseBody BaseObjectResponse<VisitiorOtpVerificationResponse> verifyVisitorOtp(
			@RequestBody OtpConfirmRequest request) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		logger.info("inside visitor request controller");
		logger.info("PUT api : /secureApi/updateVisitorRequestStatus");
		logger.info("api desc: update visitor request status");
		String jsonObjectData = mapper.writeValueAsString(request);
		logger.debug("Input string " + jsonObjectData);

		if (request != null) {
			BaseObjectResponse<VisitiorOtpVerificationResponse> response = new BaseObjectResponse<VisitiorOtpVerificationResponse>();
			response.setResponseObject(visitorReqService.verifyVisitorOtp(request, HelperUtils.userId));
			response.setErrorCode(0);
			response.setMessage("success");
			response.setTimestamp(System.currentTimeMillis());
			return response;
		} else {
			throw new CustomObjectException("2:Missing required parameter");
		}
	}
}
