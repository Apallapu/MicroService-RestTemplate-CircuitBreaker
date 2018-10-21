package com.ankamma.user.application.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserExit {
	@JsonProperty("available")
	private Boolean available;
	
	public UserExit() {
		
	}

	public UserExit(Boolean available) {
		this.available = available;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}
}
