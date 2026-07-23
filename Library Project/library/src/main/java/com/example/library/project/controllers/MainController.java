package com.example.library.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home() {
        return "welcome";
    }

    @GetMapping("/users")
    public String users() {
        return "user";
    }

    @GetMapping("/roles")
    public String roles() {
        return "role";
    }

    @GetMapping("/persons")
    public String persons() {
        return "person";
    }

    @GetMapping("/publisherTypes")
    public String publisherTypes() {
        return "publisherType";
    }

    @GetMapping("/publishers")
    public String publishers() {
        return "publisher";
    }

    @GetMapping("/bookTypes")
    public String bookTypes() {
        return "bookType";
    }

    @GetMapping("/authorTypes")
    public String authorTypes() {
        return "authorType";
    }

    @GetMapping("/libraryTypes")
    public String libraryTypes() {
        return "library";
    }
}