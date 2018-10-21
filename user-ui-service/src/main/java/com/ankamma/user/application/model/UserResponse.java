package com.ankamma.user.application.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResponse {
	@JsonProperty("id")
	private long id;

	public UserResponse() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
