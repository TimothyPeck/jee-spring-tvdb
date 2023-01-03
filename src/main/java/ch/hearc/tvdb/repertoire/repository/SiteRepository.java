package ch.hearc.tvdb.repertoire.repository;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.tvdb.repertoire.model.Site;

public interface SiteRepository extends CrudRepository<Site, Long>{
    public Site findByName(String name);
}
