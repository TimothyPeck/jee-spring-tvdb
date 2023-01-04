package ch.hearc.tvdb.repertoire.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ch.hearc.tvdb.repertoire.model.Site;
import ch.hearc.tvdb.repertoire.service.SitesService;

@Controller
public class SiteController {
    @Autowired
    private SitesService siteService;

    @GetMapping(value = { "/site" })
    public String showSitePage(Model model) {
        model.addAttribute("sites", siteService.getAllSites());
        return "tvdb-sites";
    }

    @GetMapping(value = { "/site/add-site" })
    public String showAddSitePage(Model model) {
        return "tvdb-sites-add";
    }

    @PostMapping(value = "/site/save-site")
    public String saveSite(@ModelAttribute Site site) {
        siteService.addSite(site);
        return "redirect:/site";
    }

    @GetMapping(value = "/site/edit/{id}")
    public String showEditSitePage(@ModelAttribute Site site, Model model) {
        model.addAttribute("site", siteService.getSiteById(site.getId()));
        return "tvdb-sites-edit";
    }

    @PostMapping(value = "/site/edit-site")
    public String editSite(@ModelAttribute Site site) {
        siteService.deleteSite(site);
        siteService.addSite(site);
        return "redirect:/site";
    }
}
