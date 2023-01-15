package com.spark.rest.api;

import static spark.Spark.get;
import static spark.Spark.port;

import com.google.gson.Gson;
import com.spark.rest.api.route.ContactRoute;
import com.spark.rest.api.route.UserRoute;

public class App {

    public static void main(String[] args) {
        port(9999);
        Gson gson = new Gson();
        UserRoute.configureRoutes(gson);
        ContactRoute.configureRoutes(gson);
        get("/hello", (req, res) -> "Hello World");
        
     
       
    }


}