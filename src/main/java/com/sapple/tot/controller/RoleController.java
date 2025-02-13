package com.sapple.tot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sapple.tot.responseDto.BaseListResponse;
import com.sapple.tot.responseDto.RolesResponse;
import com.sapple.tot.service.RoleService;

@RestController
@PropertySource("classpath:application.properties")
public class RoleController {
	// logs written in a file
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	RoleService roleService;

	@GetMapping("/api/roles")
	public @ResponseBody BaseListResponse<RolesResponse> getUserTypes() throws JsonProcessingException {
		logger.info("inside user controller");
		logger.info("POST api : /api/user");
		logger.info("api desc: register user send otp");

		BaseListResponse<RolesResponse> response = new BaseListResponse<RolesResponse>();
		response.setResponseList(roleService.getRoles());
		response.setMessage("success");
		response.setTimestamp(System.currentTimeMillis());
		return response;
	}
}
