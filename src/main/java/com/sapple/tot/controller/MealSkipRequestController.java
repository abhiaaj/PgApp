package com.sapple.tot.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapple.helper.HelperUtils;
import com.sapple.tot.exceptions.CustomObjectException;
import com.sapple.tot.requestDto.IdRequest;
import com.sapple.tot.requestDto.MealSkipGetDataRequest;
import com.sapple.tot.requestDto.MealSkipRequestCreationRequest;
import com.sapple.tot.responseDto.BaseListResponse;
import com.sapple.tot.responseDto.BaseObjectResponse;
import com.sapple.tot.responseDto.BlankResponse;
import com.sapple.tot.responseDto.MealSkipForOwnerResponse;
import com.sapple.tot.responseDto.MealSkipForTenantResponse;
import com.sapple.tot.service.MealSkipRequestService;

@RestController
@PropertySource("classpath:application.properties")
public class MealSkipRequestController {
	// logs written in a file
	private static final Logger logger = LoggerFactory.getLogger(HostelOwnerController.class);

	@Autowired
	private MealSkipRequestService mealSkipReqService;

	@PostMapping("/secureApi/mealSkipRequest")
	public @ResponseBody BaseObjectResponse<BlankResponse> createMealSkipRequest(
			@RequestBody MealSkipRequestCreationRequest request, HttpServletRequest httpReq)
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		logger.info("inside meal skip requests controller");
		logger.info("PUT api : /secureApi/mealSkipRequest");
		logger.info("api desc: create a new meal skip request");
		String jsonObjectData = mapper.writeValueAsString(request);
		logger.debug("Input string " + jsonObjectData);

		if (request != null) {
			mealSkipReqService.createMealSkipRequest(request, HelperUtils.userId);
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

	@DeleteMapping("/secureApi/mealSkipRequest")
	public @ResponseBody BaseObjectResponse<BlankResponse> deleteMealSkipRequest(@RequestBody IdRequest request,
			HttpServletRequest httpReq) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		logger.info("inside meal skip requests controller");
		logger.info("PUT api : /secureApi/mealSkipRequest");
		logger.info("api desc: create a new meal skip request");
		String jsonObjectData = mapper.writeValueAsString(request);
		logger.debug("Input string " + jsonObjectData);

		if (request != null) {
			mealSkipReqService.deleteRequest(request.getId(), HelperUtils.userId);
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

	@PostMapping("/secureApi/mealSkipRequests/tenant")
	public @ResponseBody BaseListResponse<MealSkipForTenantResponse> getMealSkipDataForTenant(
			@RequestBody MealSkipGetDataRequest request, HttpServletRequest httpReq) {
		logger.info("inside meal skip requests controller");
		logger.info("PUT api : /secureApi/mealSkipRequests/tenant");
		logger.info("api desc: get all the meal skip requests by the tenant");

		BaseListResponse<MealSkipForTenantResponse> response = new BaseListResponse<MealSkipForTenantResponse>();
		response.setResponseList(mealSkipReqService.getMealSkipDataForTenant(request.getDate(), HelperUtils.userId));
		response.setErrorCode(0);
		response.setMessage("success");
		response.setTimestamp(System.currentTimeMillis());
		return response;
	}

	@PostMapping("/secureApi/mealSkipRequests/owner")
	public @ResponseBody BaseObjectResponse<MealSkipForOwnerResponse> getMealSkipDataForOwner(
			@RequestBody MealSkipGetDataRequest request, HttpServletRequest httpReq) {
		logger.info("inside meal skip requests controller");
		logger.info("PUT api : /secureApi/mealSkipRequests/owner");
		logger.info("api desc: get all the meal skip requests for the owner");

		BaseObjectResponse<MealSkipForOwnerResponse> response = new BaseObjectResponse<MealSkipForOwnerResponse>();
		response.setResponseObject(mealSkipReqService.getMealSkipDataForOwner(request, HelperUtils.userId));
		response.setErrorCode(0);
		response.setMessage("success");
		response.setTimestamp(System.currentTimeMillis());
		return response;
	}
}
