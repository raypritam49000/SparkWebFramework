package com.spark.rest.api.filter;

import static spark.Spark.halt;

import com.spark.rest.api.utils.JwtUtils;

import spark.Filter;
import spark.Request;
import spark.Response;

public class AuthFilter implements Filter {

	@Override
	public void handle(Request request, Response response) throws Exception {
		if (!isLoginRequest(request) && !isRegistrationRequest(request)) {
			String authorizationHeader = request.headers("Authorization");
			if (authorizationHeader == null) {
				halt(401, "Missing Authorization header");
			}
			if (JwtUtils.getUserPrincipal(authorizationHeader.replace("Bearer", "")) == null) {
				halt(401, "Expired token " + authorizationHeader);
			}

			//System.out.println(JwtUtils.getUserPrincipal(authorizationHeader.replace("Bearer", "")));
			request.session().attribute("user",JwtUtils.getUserPrincipal(authorizationHeader.replace("Bearer", ""))); 
		}

	}

	private boolean isLoginRequest(Request request) {
		return request.uri().equals("/rest/auth/login") && request.requestMethod().equals("POST");
	}

	private boolean isRegistrationRequest(Request request) {
		return request.uri().equals("/rest/auth/register") && request.requestMethod().equals("POST");
	}

}
