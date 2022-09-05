package com.spark.rest.api.utils;

import lombok.Data;

@Data
public final class UserPrincipal {
	private String username;
	private String email;

	public UserPrincipal() {
		super();
	}

	public UserPrincipal(String username, String email) {
		super();
		this.username = username;
		this.email = email;
	}

	public static UserPrincipal of(String username, String email) {
		return new UserPrincipal(username, email);
	}
}
