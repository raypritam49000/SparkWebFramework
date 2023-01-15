package com.spark.rest.api.service;

import java.util.List;

import com.google.gson.Gson;
import com.spark.rest.api.entity.Message;
import com.spark.rest.api.entity.User;
import com.spark.rest.api.repository.Repository;
import com.spark.rest.api.repository.UserRepository;

import spark.Request;

public class UserService {

	private Repository<Long, User> repository;

	public UserService() {
		repository = new UserRepository();
	}

	public List<User> findAll(Request request) {
		return repository.findAll();
	}

	public User findById(Request request) {
		Long id = Long.parseLong(request.params("id"));
		return repository.findById(id);
	}

	public User save(Request request, Gson gson) {
		User user = gson.fromJson(request.body(), User.class);
		return repository.save(user);
	}

	public User update(Request request, Gson gson) {
		User user = gson.fromJson(request.body(), User.class);
		return repository.save(user);
	}

	public Message delete(Request request) {
		Long id = Long.parseLong(request.params("id"));
		repository.delete(id);
		return new Message("User with identifier " + id + " was deleted.");
	}

}