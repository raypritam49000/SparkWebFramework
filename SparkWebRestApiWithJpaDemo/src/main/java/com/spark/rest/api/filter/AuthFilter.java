package com.spark.rest.api.filter;

import static spark.Spark.halt;
import com.spark.rest.api.utils.JwtUtils;
import spark.Filter;
import spark.Request;
import spark.Response;

public class AuthFilter implements Filter {

	@Override
	public void handle(Request request, Response response) throws Exception {

		try {
			if (!isLoginRequest(request) && !isRegistrationRequest(request)) {
				String authorizationHeader = request.headers("Authorization");
				
				if (authorizationHeader == null) {
					throw new Exception("Missing Authorization Header");
				}
				
				if (JwtUtils.getUserPrincipal(authorizationHeader.replace("Bearer", "")) == null) {
					halt(401, "Expired token " + authorizationHeader);
				}

				request.session().attribute("user",
						JwtUtils.getUserPrincipal(authorizationHeader.replace("Bearer", "")));
			}
		} 
		catch (Exception e) {
			halt(401, e.getMessage());
		}

	}

	private boolean isLoginRequest(Request request) {
		return request.uri().equals("/rest/auth/login") && request.requestMethod().equals("POST");
	}

	private boolean isRegistrationRequest(Request request) {
		return request.uri().equals("/rest/auth/register") && request.requestMethod().equals("POST");
	}

}
