package com.spark.rest.api.response;

import com.google.gson.JsonElement;

public class StandardResponse {
	private StatusResponse status;
	private Integer statusCode;
	private String message;
	private JsonElement data;

	public StandardResponse(StatusResponse status) {
		this.status = status;
	}

	public StandardResponse(StatusResponse status, Integer statusCode, String message) {
		super();
		this.status = status;
		this.statusCode = statusCode;
		this.message = message;
	}

	public StandardResponse(StatusResponse status, Integer statusCode, String message, JsonElement data) {
		super();
		this.status = status;
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}

	public StatusResponse getStatus() {
		return status;
	}

	public void setStatus(StatusResponse status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public JsonElement getData() {
		return data;
	}

	public void setData(JsonElement data) {
		this.data = data;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

}