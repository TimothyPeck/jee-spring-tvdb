package ch.hearc.tvdb.repertoire.service.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.hearc.tvdb.repertoire.model.Site;
import ch.hearc.tvdb.repertoire.repository.SiteRepository;
import ch.hearc.tvdb.repertoire.service.SitesService;

@Service
public class SitesServiceImplementation implements SitesService {

    @Autowired
    private SiteRepository siteRepository;

    @Override
    public List<Site> getAllSites() {
        List<Site> sites = new ArrayList<Site>();
        siteRepository.findAll().forEach(sites::add);
        return sites;
    }

    @Override
    public Site getSiteById(Long id) {
        return siteRepository.findById(id).get();
    }

    @Override
    public void addSite(Site site) {
        siteRepository.save(site);
    }

    public void seedSites() {
        Map<String, String> sites = new HashMap<String, String>();
        sites.put("Netflix", "https://www.netflix.com");
        sites.put("Amazon Prime", "https://www.amazon.com");
        sites.put("Hulu", "https://www.hulu.com");
        sites.put("Disney+", "https://www.disneyplus.com");
        sites.put("Apple TV+", "https://www.apple.com");
        sites.put("HBO Max", "https://www.hbomax.com");
        sites.put("Peacock", "https://www.peacocktv.com");
        sites.put("CBS All Access", "https://www.cbs.com");
        sites.put("Showtime", "https://www.showtime.com");
        sites.put("Starz", "https://www.starz.com");
        sites.put("HBO", "https://www.hbo.com");
        sites.put("Crunchyroll", "https://www.crunchyroll.com");
        sites.put("Funimation", "https://www.funimation.com");
        sites.put("AnimeLab", "https://www.animelab.com");
        sites.put("YouTube", "https://www.youtube.com");
        sites.put("Torrent", "google.com");
        sites.put("Other", "google.com");

        for (Map.Entry<String, String> entry : sites.entrySet()) {
            Site site = new Site();
            site.setName(entry.getKey());
            site.setUrl(entry.getValue());
            siteRepository.save(site);
        }
    }

}
