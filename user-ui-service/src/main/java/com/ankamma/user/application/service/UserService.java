package com.ankamma.user.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ankamma.user.application.exception.CustomException;
import com.ankamma.user.application.exception.CustomResponseErrorHandler;
import com.ankamma.user.application.exception.UserDetailsException;
import com.ankamma.user.application.model.RoleList;
import com.ankamma.user.application.model.UserExit;
import com.ankamma.user.application.model.UserList;
import com.ankamma.user.application.model.UserRequest;
import com.ankamma.user.application.model.UserResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class UserService {

	@LoadBalanced
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	CustomResponseErrorHandler customResponseErrorHandler;

	@Value("${userdomainserviceUrl}")
	private String userdomainserviceUrl;

	public UserResponse createUser(UserRequest userRequest) {
		UserResponse response = null;
		try {
			restTemplate.setErrorHandler(customResponseErrorHandler);

			StringBuilder url = new StringBuilder();
			url.append(userdomainserviceUrl).append("/users");

			response = restTemplate.postForObject(url.toString(), userRequest, UserResponse.class);
		} catch (CustomException customException) {
			throw new UserDetailsException(customException.getMap().get("errorMessage").toString(),
					customException.getMap().get("httpStatusCode").toString(),
					customException.getMap().get("details").toString());
		}

		return response;
	}

	@HystrixCommand(fallbackMethod = "getUserExit")
	public UserExit existsByUsername(String username) {
		UserExit userExit = null;

		try {
			restTemplate.setErrorHandler(customResponseErrorHandler);
			StringBuilder url = new StringBuilder();
			url.append(userdomainserviceUrl).append("/user/checkUserExit?username=").append(username);
			userExit = restTemplate.getForObject(url.toString(), UserExit.class);
		} catch (CustomException customException) {
			throw new UserDetailsException(customException.getMap().get("errorMessage").toString(),
					customException.getMap().get("httpStatusCode").toString(),
					customException.getMap().get("details").toString());
		}
		return userExit;
	}

	public UserExit getUserExit(String username) {
		UserExit userExit = new UserExit();
		userExit.setAvailable(false);

		return userExit;

	}

	@HystrixCommand(fallbackMethod = "existsByEmailHystrix")
	public UserExit existsByEmail(String email) {
		UserExit userExit = null;
		try {
			restTemplate.setErrorHandler(customResponseErrorHandler);

			StringBuilder url = new StringBuilder();
			url.append(userdomainserviceUrl).append("/user/checkEmailExit?email=").append(email);
			userExit = restTemplate.getForObject(url.toString(), UserExit.class);
		} catch (CustomException customException) {
			throw new UserDetailsException(customException.getMap().get("errorMessage").toString(),
					customException.getMap().get("httpStatusCode").toString(),
					customException.getMap().get("details").toString());
		}
		return userExit;
	}

	public UserExit existsByEmailHystrix(String email) {
		UserExit exit = new UserExit();
		exit.setAvailable(false);

		return exit;

	}

	@HystrixCommand(fallbackMethod = "getUserNamesHystrix", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") })
	public UserList getUserNames(String username) {
		UserList userList = null;
		try {
			restTemplate.setErrorHandler(customResponseErrorHandler);
			StringBuilder url = new StringBuilder();
			url.append(userdomainserviceUrl).append("/user/").append(username);
			userList = restTemplate.getForObject(url.toString(), UserList.class);
		} catch (CustomException customException) {
			throw new UserDetailsException(customException.getMap().get("errorMessage").toString(),
					customException.getMap().get("httpStatusCode").toString(),
					customException.getMap().get("details").toString());
		}
		return userList;

	}

	public UserList getUserNamesHystrix(String username) {
		UserList list = new UserList();
		list.setName(username);
		list.setEmail("ankamma.java@gmail.com");
		list.setId(12l);
		list.setPassword("ankammma");
		list.setRoleList(setRoleList());
		return list;

	}

	private List<RoleList> setRoleList() {
		List<RoleList> list = new ArrayList<>();
		RoleList roleList = new RoleList();
		roleList.setId(13l);
		roleList.setName("admin");
		list.add(roleList);
		return list;
	}

	@HystrixCommand(fallbackMethod = "getUserListHystrix", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") })
	public List<UserList> getUserList() {
		List<UserList> userList = null;
		try {
			restTemplate.setErrorHandler(customResponseErrorHandler);

			StringBuilder url = new StringBuilder();
			url.append(userdomainserviceUrl).append("/users");
			userList = restTemplate.getForObject(url.toString(), List.class);
		} catch (CustomException customException) {
			throw new UserDetailsException(customException.getMap().get("errorMessage").toString(),
					customException.getMap().get("httpStatusCode").toString(),
					customException.getMap().get("details").toString());
		}
		return userList;
	}

	public List<UserList> getUserListHystrix() {
		return new ArrayList<>();

	}

}
