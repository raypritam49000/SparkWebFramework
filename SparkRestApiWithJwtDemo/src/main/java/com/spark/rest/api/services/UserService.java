package com.spark.rest.api.services;

import com.spark.rest.api.entity.User;

public interface UserService {
	 public abstract User registerUser(User user);
	 public abstract User findByUsername(String username);
	 public abstract User findByEmail(String email);
}
