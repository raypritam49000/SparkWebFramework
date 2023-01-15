package com.spark.rest.api.route;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;
import static spark.Spark.put;

import com.google.gson.Gson;
import com.spark.rest.api.service.UserService;

public class UserRoute {

    private static UserService service;

    static {
        service = new UserService();
    }

    public static void configureRoutes(Gson gson) {
        path("/api/users", () -> {
            get("", (request, response) -> service.findAll(request), gson::toJson);
            get("/:id", (request, response) -> service.findById(request), gson::toJson);
            post("", (request, response) -> service.save(request, gson), gson::toJson);
            put("", (request, response) -> service.update(request, gson), gson::toJson);
            delete("/:id", (request, response) -> service.delete(request), gson::toJson);
        });
    }

}