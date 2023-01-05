package ch.hearc.tvdb.repertoire.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import ch.hearc.tvdb.repertoire.model.Series;
import ch.hearc.tvdb.repertoire.model.TvdbUser;

public interface SeriesRepository extends CrudRepository<Series, Long> {
    public Series findByName(String name);

    public List<Series> findByUser(TvdbUser user);
}
