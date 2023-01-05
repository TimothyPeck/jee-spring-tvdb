package ch.hearc.tvdb.repertoire.controllers;

import javax.servlet.http.HttpSession;

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

@Controller
public class FilmController {

    @Autowired
    private FilmsService filmService;

    @Autowired
    private SitesService siteService;

    @Autowired
    private DirectorService directorService;

    @GetMapping(value = { "/films" })
    public String showFilmsPage(Model model, HttpSession session) {
        TvdbUser user = (TvdbUser) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("logged", true);
            model.addAttribute("films", filmService.getFilmsByUser(user));
        } else {
            model.addAttribute("logged", false);
            model.addAttribute("films", filmService.getAllFilms());
        }
        return "tvdb-films";
    }

    @GetMapping(value = { "/films/add" })
    public String showAddFilmPage(Model model, HttpSession session) {
        TvdbUser user = (TvdbUser) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("logged", true);
        model.addAttribute("sites", siteService.getAllSites());
        model.addAttribute("directors", directorService.getAllDirectors());
        return "tvdb-films-add";
    }

    @PostMapping(value = { "/films/save-film" })
    public String saveFilm(@ModelAttribute Film film, BindingResult errors, Model model, @RequestParam String director,
            @RequestParam String site, HttpSession session) {
        Director d = directorService.getDirectorById(Long.valueOf(director));
        Site s = siteService.getSiteById(Long.valueOf(site));
        TvdbUser u = (TvdbUser) session.getAttribute("user");
        film.setDirector(d);
        film.setSite(s);
        film.setUser(u);
        filmService.addFilm(film);
        return "redirect:/films";
    }

    @GetMapping(value = { "/films/edit/{id}" })
    public String showEditFilmPage(@ModelAttribute Film film, Model model, HttpSession session) {
        TvdbUser user = (TvdbUser) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("logged", true);
        model.addAttribute("film", filmService.getFilmById(film.getId()));
        model.addAttribute("sites", siteService.getAllSites());
        model.addAttribute("directors", directorService.getAllDirectors());
        return "tvdb-films-edit";
    }

    @PostMapping(value = { "/films/edit-film" })
    public String editFilm(@ModelAttribute Film film, @RequestParam String director, @RequestParam String site,
            HttpSession session) {
        Director d = directorService.getDirectorById(Long.valueOf(director));
        Site s = siteService.getSiteById(Long.valueOf(site));
        TvdbUser u = (TvdbUser) session.getAttribute("user");
        film.setDirector(d);
        film.setSite(s);
        film.setUser(u);
        filmService.deleteFilm(film);
        filmService.addFilm(film);
        return "redirect:/films";
    }

}
