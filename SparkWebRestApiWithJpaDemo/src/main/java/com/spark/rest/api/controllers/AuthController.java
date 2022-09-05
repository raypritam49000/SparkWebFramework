package com.spark.rest.api.controllers;

import static spark.Spark.post;
import static spark.Spark.before;

import org.mindrot.jbcrypt.BCrypt;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.spark.rest.api.dto.UserDto;
import com.spark.rest.api.entity.User;
import com.spark.rest.api.filter.AuthFilter;
import com.spark.rest.api.response.JwtResponse;
import com.spark.rest.api.response.StandardResponse;
import com.spark.rest.api.response.StatusResponse;
import com.spark.rest.api.services.UserService;
import com.spark.rest.api.services.impl.UserServiceImpl;
import com.spark.rest.api.utils.JwtUtils;

public class AuthController {

	private static String BASE_URL = "/rest/auth/";
	private static UserService userService = new UserServiceImpl();

	public static void init() {

		before(new AuthFilter());

		post(BASE_URL + "/register", (request, response) -> {

			response.type("application/json");
			
			try {
				User user = new Gson().fromJson(request.body(), User.class);
				
				if (userService.findByUsername(user.getUsername()) != null) {
					return new Gson()
							.toJson(new StandardResponse(StatusResponse.ALREADY_EXISTS, 401, "Username already exists"));
				}

				if (userService.findByEmail(user.getEmail()) != null) {
					return new Gson()
							.toJson(new StandardResponse(StatusResponse.ALREADY_EXISTS, 401, "Email already exists"));
				}

				user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

				User registerdUser = userService.registerUser(user);

				String createdResponse = new Gson().toJson(registerdUser);

				return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, 201, "User Registered",
						new Gson().fromJson(createdResponse, JsonElement.class)));
			} catch (Exception e) {
				return new Gson().toJson(
						new StandardResponse(StatusResponse.INTERNAL_SERVER_ERROR, 502, "Internl Server Error"));
			}
		});

		post(BASE_URL + "/login", (request, response) -> {

			response.type("application/json");

			try {
			
			UserDto userDto = new Gson().fromJson(request.body(), UserDto.class);

			User user = userService.findByUsername(userDto.getUsername());

			if (user == null) {
				return new Gson().toJson(new StandardResponse(StatusResponse.NOT_FOUND, 404, "User Not Found"));
			}

			Boolean isPassMatch = BCrypt.checkpw(userDto.getPassword(), user.getPassword());

			if (!isPassMatch) {
				return new Gson().toJson(
						new StandardResponse(StatusResponse.PASSWORD_NOT_MATCH, 401, "Password does not match"));
			}

			String token = JwtUtils.generateToken(user);

			JwtResponse jwtResponse = new JwtResponse(user, token);

			return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, 200, "User Login Successfully",
					new Gson().toJsonTree(jwtResponse)));
			
			} catch (Exception e) {
				return new Gson().toJson(
						new StandardResponse(StatusResponse.INTERNAL_SERVER_ERROR, 502, "Internl Server Error"));
			}
		});
	}
}
