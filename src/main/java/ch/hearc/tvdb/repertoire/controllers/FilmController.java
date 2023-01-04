package ch.hearc.tvdb.repertoire.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.hearc.tvdb.repertoire.model.Director;
import ch.hearc.tvdb.repertoire.model.Film;
import ch.hearc.tvdb.repertoire.model.Site;
import ch.hearc.tvdb.repertoire.model.TvdbUser;
import ch.hearc.tvdb.repertoire.service.DirectorService;
import ch.hearc.tvdb.repertoire.service.FilmsService;
import ch.hearc.tvdb.repertoire.service.SitesService;
import ch.hearc.tvdb.repertoire.service.TvdbUsersService;

@Controller
public class FilmController {

    @Autowired
    private FilmsService filmService;

    @Autowired
    private SitesService siteService;

    @Autowired
    private DirectorService directorService;

    @Autowired
    private TvdbUsersService tvdbUsersService;

    @GetMapping(value = { "/films" })
    public String showFilmsPage(Model model) {
        model.addAttribute("films", filmService.getAllFilms());
        return "tvdb-films";
    }

    @GetMapping(value = { "/films/add" })
    public String showAddFilmPage(Model model) {
        model.addAttribute("sites", siteService.getAllSites());
        model.addAttribute("directors", directorService.getAllDirectors());
        System.out.println("FilmController.showAddFilmPage() called");
        return "tvdb-films-add";
    }

    @PostMapping(value = { "/films/save-film" })
    public String saveFilm(@ModelAttribute Film film, BindingResult errors, Model model, @RequestParam String director,
            @RequestParam String site) {
        Director d = directorService.getDirectorById(Long.valueOf(director));
        Site s = siteService.getSiteById(Long.valueOf(site));
        TvdbUser u = tvdbUsersService.getUserById(1L);
        film.setDirector(d);
        film.setSite(s);
        film.setUser(u);
        filmService.addFilm(film);
        return "redirect:/films";
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
