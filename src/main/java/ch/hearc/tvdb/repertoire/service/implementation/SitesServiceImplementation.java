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

    @Override
    public void deleteSite(Site site) {
        siteRepository.delete(site);
    }

    @Override
    public void deleteSiteById(Long id) {
        siteRepository.deleteById(id);
    }
}
