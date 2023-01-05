package ch.hearc.tvdb.repertoire.service;

import java.util.List;

import ch.hearc.tvdb.repertoire.model.Film;
import ch.hearc.tvdb.repertoire.model.TvdbUser;

public interface FilmsService {
    public void addFilm(Film film);

    public List<Film> getAllFilms();

    public Film getFilmById(Long id);

    public void deleteFilmById(Long id);

    public Film updateFilm(Film film);

    public void deleteFilm(Film film);

    public List<Film> getFilmsByUser(TvdbUser user);
}
