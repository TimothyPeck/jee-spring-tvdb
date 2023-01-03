package ch.hearc.tvdb.repertoire.repository;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.tvdb.repertoire.model.Film;

public interface FilmRepository extends CrudRepository<Film, Long> {
    Film findByTitle(String title);

}
