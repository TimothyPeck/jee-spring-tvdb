package ch.hearc.tvdb.repertoire.controllers;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import ch.hearc.tvdb.repertoire.model.Director;
import ch.hearc.tvdb.repertoire.model.TvdbUser;
import ch.hearc.tvdb.repertoire.service.DirectorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class DirectorController {
    @Autowired
    private DirectorService directorService;

    @GetMapping(value = { "/director" })
    public String showDirectorPage(Model model, HttpSession session) {
        TvdbUser user = (TvdbUser) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("logged", true);
        } else {
            model.addAttribute("logged", false);
        }
        model.addAttribute("directors", directorService.getAllDirectors());
        return "tvdb-directors";
    }

    @GetMapping(value = "/directors/add-director")
    public String getDirectorAdd(Model model, HttpSession session) {
        TvdbUser user = (TvdbUser) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("logged", true);
        } else {
            return "redirect:/login";
        }
        return "tvdb-directors-add";
    }

    @PostMapping(value = "/directors/save-director")
    public String saveDirector(@ModelAttribute Director director) {
        directorService.addDirector(director);
        return "redirect:/director";
    }

    @GetMapping(value = "/directors/edit/{id}")
    public String showEditDirectorPage(@ModelAttribute Director director, Model model, HttpSession session) {
        TvdbUser user = (TvdbUser) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("logged", true);
        } else {
            return "redirect:/login";
        }
        model.addAttribute("director", directorService.getDirectorById(director.getId()));
        return "tvdb-directors-edit";
    }

    @PostMapping(value = "/directors/edit-director")
    public String editDirector(@ModelAttribute Director director, Model model) {
        directorService.deleteDirector(director);
        directorService.addDirector(director);
        return "redirect:/director";
    }

}
