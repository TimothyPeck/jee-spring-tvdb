package ch.hearc.tvdb.repertoire.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping(value = {"/", "home"})
    public String showIndexPage(Model model) {
        System.out.println("IndexController.showIndexPage() called");
        //return "";
        return "tvdb-index";
    }
}
