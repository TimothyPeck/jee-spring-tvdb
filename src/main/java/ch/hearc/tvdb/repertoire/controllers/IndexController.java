package ch.hearc.tvdb.repertoire.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ch.hearc.tvdb.repertoire.service.FilmsService;

@Controller
public class IndexController {

    @Autowired
    private FilmsService filmService;

    @GetMapping(value = { "/", "home" })
    public String showIndexPage(Model model) {
        model.addAttribute("films", filmService.getAllFilms());
        // return "";
        return "tvdb-index";
    }

    @GetMapping(value = { "/about" })
    public String showAboutPage(Model model) {
        System.out.println("IndexController.showAboutPage() called");
        // return "";
        return "tvdb-about";
    }
}
