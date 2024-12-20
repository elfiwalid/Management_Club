package com.tonorganisation.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // Autoriser CORS pour cette méthode spécifique
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/api/hello")
    public String sayHello() {
        return "Hello, walid!";
    }
}
