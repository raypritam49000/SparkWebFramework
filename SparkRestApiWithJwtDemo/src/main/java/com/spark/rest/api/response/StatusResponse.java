package com.spark.rest.api.response;

public enum StatusResponse {
	SUCCESS("Success"), ERROR("Error"), NOT_FOUND("DATA NOT FOUND"),PASSWORD_NOT_MATCH("Password does not match"),ALREADY_EXISTS("User Already Exists");

	final private String status;

	StatusResponse(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}