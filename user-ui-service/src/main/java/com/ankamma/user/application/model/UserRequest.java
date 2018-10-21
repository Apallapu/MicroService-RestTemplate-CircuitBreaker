package com.ankamma.user.application.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRequest {
	@JsonProperty("id")
	private long id;
	@JsonProperty("email")
	private String email;
	@JsonProperty("name")
	private String name;
	@JsonProperty("password")
	private String password;
	@JsonProperty("username")
	private String username;
	@JsonProperty("roleRequest")
	private List<RoleRequest> roleRequest;
	public UserRequest() {
		
	}

	public List<RoleRequest> getRoleRequest() {
		return roleRequest;
	}

	public void setRoleRequest(List<RoleRequest> roleRequest) {
		this.roleRequest = roleRequest;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
