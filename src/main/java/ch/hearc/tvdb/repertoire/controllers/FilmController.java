package ch.hearc.tvdb.repertoire.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FilmController {
    @GetMapping(value = {"/films"})
    public String showFilmsPage() {
        System.out.println("FilmController.showFilmsPage() called");
        return "tvdb-films";
    }

    @GetMapping(value = {"/films/add"})
    public String showAddFilmPage() {
        System.out.println("FilmController.showAddFilmPage() called");
        return "tvdb-films-add";
    }

    @GetMapping(value = {"/films/edit"})
    public String showEditFilmPage() {
        System.out.println("FilmController.showEditFilmPage() called");
        return "tvdb-films-edit";
    }

    @GetMapping(value = {"/films/delete"})
    public String showDeleteFilmPage() {
        System.out.println("FilmController.showDeleteFilmPage() called");
        return "tvdb-films-delete";
    }

    @GetMapping(value = {"/films/details"})
    public String showDetailsFilmPage() {
        System.out.println("FilmController.showDetailsFilmPage() called");
        return "tvdb-films-details";
    }
}
