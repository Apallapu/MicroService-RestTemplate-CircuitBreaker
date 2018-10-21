package com.ankamma.user.application.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomResponseErrorHandler implements ResponseErrorHandler {

	private ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();

	public boolean hasError(ClientHttpResponse response) throws IOException {
		return errorHandler.hasError(response);
	}

	public void handleError(ClientHttpResponse response) throws IOException {
		String theString = IOUtils.toString(response.getBody());
		CustomException exception = new CustomException();
		checkIsJsonValid(theString);
		Map<String, Object> properties = new HashMap<String, Object>();
		JSONObject json = new JSONObject(theString);

		properties.put("httpStatusCode", json.get("errorCode"));
		properties.put("header", response.getHeaders());

		properties.put("errorMessage", json.get("message"));

		properties.put("details", json.get("details"));

		exception.setMap(properties);
		throw exception;
	}

	private void checkIsJsonValid(String theString) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.readTree(theString);
		} catch (IOException exception) {

		}

	}
}
