package ch.hearc.tvdb.repertoire.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.hearc.tvdb.repertoire.model.Film;
import ch.hearc.tvdb.repertoire.repository.FilmRepository;
import ch.hearc.tvdb.repertoire.service.FilmsService;

@Service
public class FilmsServiceImplementation implements FilmsService {
    @Autowired
    private FilmRepository filmRepository;

    @Override
    public void addFilm(Film film) {
        filmRepository.save(film);

    }

    @Override
    public List<Film> getAllFilms() {
        List<Film> films = new ArrayList<Film>();
        filmRepository.findAll().forEach(films::add);
        return films;
    }

    @Override
    public Film getFilmById(Long id) {
        return filmRepository.findById(id).get();
    }

    @Override
    public void deleteFilmById(Long id) {
        filmRepository.deleteById(id);
    }

    @Override
    public Film updateFilm(Film film) {
        filmRepository.save(film);
        return film;
    }

}
