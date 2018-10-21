package com.ankamma.user.application.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleList {
	@JsonProperty("id")
	private long id;
	@JsonProperty("name")
	private String name;

	public RoleList() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
