package ch.hearc.tvdb.repertoire.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ch.hearc.tvdb.repertoire.service.FilmsService;
import ch.hearc.tvdb.repertoire.service.SitesService;

@Controller
public class FilmController {

    @Autowired
    FilmsService filmService;

    @Autowired
    SitesService siteService;

    @GetMapping(value = { "/films" })
    public String showFilmsPage(Model model) {
        model.addAttribute("films", filmService.getAllFilms());
        return "tvdb-films";
    }

    @GetMapping(value = { "/films/add" })
    public String showAddFilmPage(Model model) {
        model.addAttribute("sites", siteService.getAllSites());
        System.out.println("FilmController.showAddFilmPage() called");
        return "tvdb-films-add";
    }

    @GetMapping(value = { "/films/edit" })
    public String showEditFilmPage() {
        System.out.println("FilmController.showEditFilmPage() called");
        return "tvdb-films-edit";
    }

    @GetMapping(value = { "/films/delete" })
    public String showDeleteFilmPage() {
        System.out.println("FilmController.showDeleteFilmPage() called");
        return "tvdb-films-delete";
    }

    @GetMapping(value = { "/films/details" })
    public String showDetailsFilmPage() {
        System.out.println("FilmController.showDetailsFilmPage() called");
        return "tvdb-films-details";
    }
}
