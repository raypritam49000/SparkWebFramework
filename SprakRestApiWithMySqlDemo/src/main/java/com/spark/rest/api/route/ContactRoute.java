package com.spark.rest.api.route;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;
import static spark.Spark.put;

import com.google.gson.Gson;
import com.spark.rest.api.service.ContactService;

public class ContactRoute {

    private static ContactService service;

    static {
        service = new ContactService();
    }

    public static void configureRoutes(Gson gson) {
        path("/api/contacts", () -> {
            get("", (request, response) -> service.findAll(), gson::toJson);
            get("/:id", (request, response) -> service.findById(request), gson::toJson);
            post("", (request, response) -> service.save(request, gson), gson::toJson);
            put("", (request, response) -> service.update(request, gson), gson::toJson);
            delete("/:id", (request, response) -> service.delete(request), gson::toJson);
        });
    }

}