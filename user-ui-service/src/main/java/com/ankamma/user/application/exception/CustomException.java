package com.ankamma.user.application.exception;

import java.util.HashMap;
import java.util.Map;

public class CustomException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String,Object> map=new HashMap<String,Object>();
	
	public CustomException() {
		super();
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	@Override
	public String toString() {
		return "CustomException [map=" + map + "]";
	}

}
