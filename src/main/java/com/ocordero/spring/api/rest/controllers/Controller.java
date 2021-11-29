package com.ocordero.spring.api.rest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/")
    public String index() {

        return "<h1>Welcome in CRUD of Clients (API SERVICES)</h1>";
    }

}
