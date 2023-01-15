package com.spark.rest.api.response;

import com.spark.rest.api.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
	private User user;
	private String token;
}
