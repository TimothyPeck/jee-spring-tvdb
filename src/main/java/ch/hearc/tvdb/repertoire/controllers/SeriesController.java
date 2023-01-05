package ch.hearc.tvdb.repertoire.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ch.hearc.tvdb.repertoire.model.Series;
import ch.hearc.tvdb.repertoire.model.Site;
import ch.hearc.tvdb.repertoire.model.TvdbUser;
import ch.hearc.tvdb.repertoire.service.SeriesService;
import ch.hearc.tvdb.repertoire.service.SitesService;
import ch.hearc.tvdb.repertoire.service.TvdbUsersService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SeriesController {
    @Autowired
    private SeriesService seriesService;

    @Autowired
    private SitesService siteService;

    @Autowired
    private TvdbUsersService tvdbUsersService;

    @GetMapping(value = { "/series" })
    public String showSeriesPage(Model model, HttpSession session) {
        TvdbUser user = (TvdbUser) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("logged", true);
            model.addAttribute("series", seriesService.getSeriesByUser(user));
        } else {
            model.addAttribute("logged", false);
            model.addAttribute("series", seriesService.getAllSeries());
        }
        return "tvdb-series";
    }

    @GetMapping(value = { "/series/add-series" })
    public String showAddSeriesPage(Model model) {
        model.addAttribute("sites", siteService.getAllSites());
        return "tvdb-series-add";
    }

    @PostMapping(value = "/series/save-series")
    public String saveSeries(@ModelAttribute Series series, BindingResult errors, Model model, @RequestParam int site) {
        Site s = siteService.getSiteById(Long.valueOf(site));
        TvdbUser u = tvdbUsersService.getUserById(1L);
        series.setSite(s);
        series.setUser(u);
        seriesService.addSeries(series);
        return "redirect:/series";
    }

    @GetMapping(value = "/series/edit/{id}")
    public String showEditSeriesPage(@ModelAttribute Series series, Model model) {
        model.addAttribute("series", seriesService.getSeriesById(series.getId()));
        model.addAttribute("sites", siteService.getAllSites());
        return "tvdb-series-edit";
    }

    @PostMapping(value = "/series/edit-series")
    public String editSeries(@ModelAttribute Series series, @RequestParam int site) {
        Site s = siteService.getSiteById(Long.valueOf(site));
        TvdbUser u = tvdbUsersService.getUserById(1L);
        seriesService.deleteSeries(series);
        series.setUser(u);
        series.setSite(s);
        seriesService.addSeries(series);
        return "redirect:/series";
    }

}
