package com.sapple.tot.exceptions;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sapple.tot.responseDto.BaseListResponse;
import com.sapple.tot.responseDto.BaseObjectResponse;
import com.sapple.tot.responseDto.BlankResponse;

/*
 * @ControllerAdvice used for global exception handling
 */
@RestControllerAdvice
public class RestExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

	@ExceptionHandler(CustomObjectException.class)
	public BaseObjectResponse<BlankResponse> handleException(CustomObjectException exc) {
		logger.debug(exc.getMessage());
		String[] tempData = exc.getMessage().split(":");
		BaseObjectResponse<BlankResponse> response = new BaseObjectResponse<BlankResponse>();
		response.setErrorCode(Integer.valueOf(tempData[0]));
		response.setMessage(tempData[1]);
		response.setTimestamp(System.currentTimeMillis());
		response.setResponseObject(new BlankResponse());
		return response;
	}

	@ExceptionHandler(CustomListException.class)
	public BaseListResponse<BlankResponse> handleException(CustomListException exc) {
		logger.debug(exc.getMessage());
		String[] tempData = exc.getMessage().split(":");
		BaseListResponse<BlankResponse> response = new BaseListResponse<BlankResponse>();
		response.setErrorCode(Integer.valueOf(tempData[0]));
		response.setMessage(tempData[1]);
		response.setTimestamp(System.currentTimeMillis());
		response.setResponseList(new ArrayList<BlankResponse>());
		return response;
	}

	@ExceptionHandler
	public BaseObjectResponse<BlankResponse> handleException(Exception exc) {
		logger.debug(exc.getMessage());
		BaseObjectResponse<BlankResponse> response = new BaseObjectResponse<BlankResponse>();
		response.setErrorCode(1);
		response.setMessage("something went wrong");
		response.setTimestamp(System.currentTimeMillis());
		response.setResponseObject(new BlankResponse());
		return response;
	}
}
