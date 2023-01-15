package com.spark.rest.api.service;

import java.util.List;

import com.google.gson.Gson;
import com.spark.rest.api.entity.Contact;
import com.spark.rest.api.entity.Message;
import com.spark.rest.api.repository.ContactRepository;
import com.spark.rest.api.repository.Repository;

import spark.Request;

public class ContactService {

	private Repository<Long, Contact> repository;

	public ContactService() {
		this.repository = new ContactRepository();
	}

	public List<Contact> findAll() {
		return repository.findAll();
	}

	public Contact findById(Request request) {
		Long id = Long.parseLong(request.params("id"));
		return repository.findById(id);
	}

	public Contact save(Request request, Gson gson) {
		Contact contact = gson.fromJson(request.body(), Contact.class);
		return repository.save(contact);
	}

	public Contact update(Request request, Gson gson) {
		Contact contact = gson.fromJson(request.body(), Contact.class);
		return repository.save(contact);
	}

	public Message delete(Request request) {
		Long id = Long.parseLong(request.params("id"));
		repository.delete(id);
		return new Message("Contact with identifier " + id + " was deleted.");
	}

}