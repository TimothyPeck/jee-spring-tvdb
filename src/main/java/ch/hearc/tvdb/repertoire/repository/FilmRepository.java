package ch.hearc.tvdb.repertoire.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.tvdb.repertoire.model.Film;
import ch.hearc.tvdb.repertoire.model.TvdbUser;

public interface FilmRepository extends CrudRepository<Film, Long> {
    public Film findByTitle(String title);

    public List<Film> findByUser(TvdbUser user);

}
