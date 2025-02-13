package com.sapple.tot.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapple.helper.HelperUtils;
import com.sapple.tot.exceptions.CustomObjectException;
import com.sapple.tot.requestDto.OtpConfirmRequest;
import com.sapple.tot.requestDto.ContactNoRequest;
import com.sapple.tot.requestDto.IdRequest;
import com.sapple.tot.requestDto.UserRegistrationRequest;
import com.sapple.tot.responseDto.BaseObjectResponse;
import com.sapple.tot.responseDto.BlankResponse;
import com.sapple.tot.responseDto.ProfileStatusResponse;
import com.sapple.tot.responseDto.UserRegistrationResponse;
import com.sapple.tot.security.TokenProvider;
import com.sapple.tot.service.UserService;
import com.sapple.tot.serviceImpl.UserServiceImpl;

@RestController
@PropertySource("classpath:application.properties")
public class UserController {
	// logs written in a file
	private static final Logger logger = LoggerFactory.getLogger(HostelOwnerController.class);
	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	UserService userService;

	@Autowired
	private TokenProvider jwtTokenUtil;

	@GetMapping("/secureApi/user")
	public @ResponseBody String getUser() {
		return "success";
	}

	@PostMapping("/api/user")
	public @ResponseBody BaseObjectResponse<UserRegistrationResponse> registerUser(
			@RequestBody UserRegistrationRequest request) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		logger.info("inside user controller");
		logger.info("POST api : /api/user");
		logger.info("api desc: register user send otp");
		String jsonObjectData = mapper.writeValueAsString(request);
		logger.debug("Input string " + jsonObjectData);

		if (request != null) {
			Long userId = userService.createUser(request);
			BaseObjectResponse<UserRegistrationResponse> response = new BaseObjectResponse<UserRegistrationResponse>();
			UserRegistrationResponse subResponse = new UserRegistrationResponse();
			response.setErrorCode(0);
			response.setMessage("otp sent to user");
			response.setTimestamp(System.currentTimeMillis());

			subResponse.setUserId(userId);
			response.setResponseObject(subResponse);
			return response;
		} else {
			throw new CustomObjectException("2:Missing required parameter");
		}
	}

	@PutMapping("/api/user")
	public @ResponseBody ResponseEntity<BaseObjectResponse<BlankResponse>> confirmOtp(
			@RequestBody OtpConfirmRequest request) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		logger.info("inside user controller");
		logger.info("PUT api : /api/user");
		logger.info("api desc: confirm otp");
		String jsonObjectData = mapper.writeValueAsString(request);
		logger.debug("Input json " + jsonObjectData);

		if (request != null) {
			userService.confirmOtp(request);
			BaseObjectResponse<BlankResponse> response = new BaseObjectResponse<BlankResponse>();

			final UserDetails userDetails = userServiceImpl.loadUserByUsername(request.getContactNo());
			final String token = jwtTokenUtil.generateToken(userDetails);
			HttpHeaders responseHeaders = new HttpHeaders();

			responseHeaders.set("Access-Control-Expose-Headers", "Authorization");
			responseHeaders.set("Access-Control-Allow-Headers",
					"Authorization, X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");
			responseHeaders.set("Authorization", token);

			response.setResponseObject(new BlankResponse());
			response.setErrorCode(0);
			response.setMessage("account created successfully");
			response.setTimestamp(System.currentTimeMillis());
			return new ResponseEntity<BaseObjectResponse<BlankResponse>>(response, responseHeaders, HttpStatus.OK);
		} else {
			throw new CustomObjectException("2:Missing required parameter");
		}
	}

	@PostMapping("/api/user/otp")
	public @ResponseBody BaseObjectResponse<BlankResponse> sendOtp(@RequestBody ContactNoRequest request)
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		logger.info("inside user controller");
		logger.info("POST api : /api/user/otp");
		logger.info("api desc: register user send otp");
		String jsonObjectData = mapper.writeValueAsString(request);
		logger.debug("Input json " + jsonObjectData);

		if (request != null) {
			userService.sendOtp(request.getContactNo());
			BaseObjectResponse<BlankResponse> response = new BaseObjectResponse<BlankResponse>();
			response.setResponseObject(new BlankResponse());
			response.setErrorCode(0);
			response.setMessage("otp sent successfully");
			response.setTimestamp(System.currentTimeMillis());
			return response;
		} else {
			throw new CustomObjectException("2:Missing required parameter");
		}
	}

	@PostMapping("/secureApi/profileStatus")
	public @ResponseBody BaseObjectResponse<ProfileStatusResponse> getTenantProfileStatus(
			@RequestBody IdRequest request, HttpServletRequest httpReq) throws JsonProcessingException {
		logger.info("inside user controller");
		logger.info("GET api : /secureApi/profileStatus");
		logger.info("api desc: get the profile update status for user");

		BaseObjectResponse<ProfileStatusResponse> response = new BaseObjectResponse<ProfileStatusResponse>();
		response.setResponseObject(userService.getProfileStatus(request.getId(), HelperUtils.userId));
		response.setErrorCode(0);
		response.setMessage("success");
		response.setTimestamp(System.currentTimeMillis());
		return response;
	}
}
