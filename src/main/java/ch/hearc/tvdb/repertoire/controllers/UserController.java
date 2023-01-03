package ch.hearc.tvdb.repertoire.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login-user")
    public String loginUser() {
        return "login-user";
    }

    @GetMapping("/create-account")
    public String register() {
        return "register";
    }
}
