package com.intershop.shoping.controller;

import java.util.HashMap;
import java.util.Map;

public class ControllerResponse {
	private Boolean success; 
	private String message;
	private Map<String, Object> data = new HashMap<String, Object>();

	 
	public ControllerResponse(){
		message = "success";
		success = true;
	}
	
	public ControllerResponse(String msg) {
		message = msg;
		success = true;
	}
	 
	public Boolean getSuccess() {
		return success;
	}
	 
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	 
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
