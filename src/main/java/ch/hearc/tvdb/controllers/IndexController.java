package ch.hearc.tvdb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {
    @GetMapping(value = "/")
    public String getIndex() {
        return "index";
    }
}
