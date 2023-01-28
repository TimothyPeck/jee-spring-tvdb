package ch.hearc.tvdb.repertoire.controllers;

import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public String showFilmsPage(Model model, HttpSession session, @RequestParam("page") Optional<Integer> page) {
        int currentPage = page.orElse(1);
        TvdbUser user = (TvdbUser) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("logged", true);
            Page<Film> filmPage = filmService.getFilmsByUserPaginated(PageRequest.of(currentPage - 1, 10), user);
            int totalPages = filmPage.getTotalPages();
            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }
            model.addAttribute("films", filmPage);
        } else {
            model.addAttribute("logged", false);
            Page<Film> filmPage = filmService.getFilmsPaginated(PageRequest.of(currentPage - 1, 10));
            int totalPages = filmPage.getTotalPages();
            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }
            model.addAttribute("films", filmPage);
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
