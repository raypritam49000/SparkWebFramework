package com.spark.rest.api.response;

public enum StatusResponse {
	SUCCESS("Success"), ERROR("Error"), NOT_FOUND("Data Not Found"), CREATED("Data Created"),
	BAD_REQUEST("Bad Request"), INTERNAL_SERVER_ERROR("Internal Server Error"),
	PASSWORD_NOT_MATCH("Password does not match"), ALREADY_EXISTS("User Already Exists");
	;

	final private String status;

	StatusResponse(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}