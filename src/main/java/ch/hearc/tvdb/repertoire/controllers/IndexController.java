package ch.hearc.tvdb.repertoire.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ch.hearc.tvdb.repertoire.model.Director;
import ch.hearc.tvdb.repertoire.model.Site;
import ch.hearc.tvdb.repertoire.model.TvdbUser;
import ch.hearc.tvdb.repertoire.service.DirectorService;
import ch.hearc.tvdb.repertoire.service.FilmsService;
import ch.hearc.tvdb.repertoire.service.SeriesService;
import ch.hearc.tvdb.repertoire.service.SitesService;
import ch.hearc.tvdb.repertoire.service.TvdbUsersService;

@Controller
public class IndexController {

    @Autowired
    private FilmsService filmService;

    @Autowired
    private SeriesService seriesService;

    @Autowired
    private DirectorService directorService;

    @Autowired
    private SitesService siteService;

    @Autowired
    private TvdbUsersService tvdbUsersService;

    @GetMapping(value = { "/", "home" })
    public String showIndexPage(Model model, HttpSession session) {
        TvdbUser user = (TvdbUser) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("logged", true);
            model.addAttribute("films", filmService.getFilmsByUser(user));
            model.addAttribute("series", seriesService.getSeriesByUser(user));
        } else {
            model.addAttribute("logged", false);
            model.addAttribute("films", filmService.getAllFilms());
            model.addAttribute("series", seriesService.getAllSeries());
        }
        // return "";
        return "tvdb-index";
    }

    @GetMapping(value = { "/about" })
    public String showAboutPage(Model model, HttpSession session) {
        TvdbUser user = (TvdbUser) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("logged", true);
        } else {
            model.addAttribute("logged", false);
        }
        // return "";
        return "tvdb-about";
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedDirectors();
        seedSites();
        seedUsers();
    }

    public void seedDirectors() {
        List<String> directorNames = new ArrayList<String>();
        directorNames.add("Steven Spielberg");
        directorNames.add("James Cameron");
        directorNames.add("Christopher Nolan");
        directorNames.add("Quentin Tarantino");
        directorNames.add("Martin Scorsese");
        directorNames.add("Stanley Kubrick");
        directorNames.add("Alfred Hitchcock");
        directorNames.add("David Fincher");
        directorNames.add("Francis Ford Coppola");
        directorNames.add("Peter Jackson");
        directorNames.add("George Lucas");
        directorNames.add("Ridley Scott");
        directorNames.add("Robert Zemeckis");
        directorNames.add("Tim Burton");
        directorNames.add("Clint Eastwood");
        directorNames.add("Sergio Leone");
        directorNames.add("John Ford");
        directorNames.add("Akira Kurosawa");

        for (String name : directorNames) {
            Director director = new Director();
            director.setName(name);
            directorService.addDirector(director);
        }

    }

    public void seedSites() {
        Map<String, String> sites = new HashMap<String, String>();
        sites.put("Netflix", "www.netflix.com");
        sites.put("Amazon Prime", "www.amazon.com/Amazon-Video/");
        sites.put("Hulu", "www.hulu.com/welcome");
        sites.put("Disney+", "www.disneyplus.com/");
        sites.put("HBO Max", "www.hbomax.com/");
        sites.put("Apple TV+", "tv.apple.com/");
        sites.put("Peacock", "www.peacocktv.com/");
        sites.put("CBS All Access", "www.cbs.com/all-access/");
        sites.put("Crunchyroll", "www.crunchyroll.com/");
        sites.put("Funimation", "www.funimation.com/");
        sites.put("AnimeLab", "www.animelab.com/");
        sites.put("YouTube", "www.youtube.com/");
        sites.put("Google Play", "play.google.com/store/movies");
        sites.put("Microsoft Store", "www.microsoft.com/en-us/p/movies-tv/9wzdncrfj3t5");
        sites.put("Torrent", "google.com/search?q=torrents");
        sites.put("Other", "google.com/search?q=streaming+sites");

        for (Map.Entry<String, String> entry : sites.entrySet()) {
            Site site = new Site();
            site.setName(entry.getKey());
            site.setUrl(entry.getValue());
            siteService.addSite(site);
        }
    }

    public void seedUsers() {
        TvdbUser user = new TvdbUser();
        user.setUsername("admin");
        user.setUser_password("admin");
        user.setEmail("admin@tvdb.ch");
        tvdbUsersService.saveUser(user);
    }

}
