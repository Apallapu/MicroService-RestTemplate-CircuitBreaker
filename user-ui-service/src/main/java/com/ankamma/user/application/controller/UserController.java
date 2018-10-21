package com.ankamma.user.application.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ankamma.user.application.exception.UserDetailsException;
import com.ankamma.user.application.exception.UserDetailsHttpException;
import com.ankamma.user.application.model.UserExit;
import com.ankamma.user.application.model.UserList;
import com.ankamma.user.application.model.UserRequest;
import com.ankamma.user.application.model.UserResponse;
import com.ankamma.user.application.service.UserService;

@RestController
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@PostMapping(path = "/users", consumes = "application/json", produces = "application/json")
	public UserResponse createUser(@RequestBody UserRequest UserRequest) {
		return userService.createUser(UserRequest);

	}

	@GetMapping("/user/checkUserExit")
	public UserExit checkUserExit(@RequestParam(value = "username") String username) {
		logger.info("UserController contoller class with checkUsernameAvailability user method start{}");

		UserExit userExit = userService.existsByUsername(username);

		return userExit;
	}

	@GetMapping("/user/checkEmailExit")
	public UserExit checkEmailExit(@RequestParam(value = "email") String email) {
		logger.info("UserController contoller class with checkEmailAvailability  method start{}");
		UserExit userExit = userService.existsByEmail(email);
		return userExit;
	}

	@GetMapping(path = "/user/{username}", produces = "application/json")
	public UserList getUserNames(@PathVariable String username) {
		return userService.getUserNames(username);
	}

	@GetMapping(path = "/users", produces = "application/json")
	public List<UserList> getUserList() {
		return userService.getUserList();
	}

}
