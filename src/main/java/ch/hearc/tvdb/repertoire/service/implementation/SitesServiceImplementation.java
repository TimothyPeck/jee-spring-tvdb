package ch.hearc.tvdb.repertoire.service.implementation;

import java.util.ArrayList;
import java.util.List;

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
}
