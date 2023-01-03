package ch.hearc.tvdb.repertoire.repository;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.tvdb.repertoire.model.Series;

public interface SeriesRepository extends CrudRepository<Series, Long> {
    Series findByName(String name);
}
