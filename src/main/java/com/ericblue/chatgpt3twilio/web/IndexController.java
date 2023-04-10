package com.ericblue.chatgpt3twilio.web;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;

import java.util.HashMap;
import java.util.Map;


@Controller("/")
public class IndexController {

    @View("index")
    @Get
    public Map<String, Object> index() {
        Map<String, Object> model = new HashMap<>();
        model.put("message", "Hello World");
        return model;
    }

}