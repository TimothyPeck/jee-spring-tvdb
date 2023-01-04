package ch.hearc.tvdb.repertoire.service;

import java.util.List;

import ch.hearc.tvdb.repertoire.model.Site;

public interface SitesService {
    public List<Site> getAllSites();

    public Site getSiteById(Long id);

    public void addSite(Site site);
}
