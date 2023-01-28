package ch.hearc.tvdb.repertoire.service.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ch.hearc.tvdb.repertoire.model.Film;
import ch.hearc.tvdb.repertoire.model.TvdbUser;
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

    @Override
    public void deleteFilm(Film film) {
        filmRepository.delete(film);
    }

    @Override
    public List<Film> getFilmsByUser(TvdbUser user) {
        return filmRepository.findByUser(user);
    }

    @Override
    public Page<Film> getFilmsPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Film> allFilms = this.getAllFilms();
        List<Film> pagedFilms;
        if (allFilms.size() < startItem) {
            pagedFilms = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, allFilms.size());
            pagedFilms = allFilms.subList(startItem, toIndex);
        }
        Page<Film> filmPage = new PageImpl<Film>(pagedFilms, PageRequest.of(currentPage, pageSize), allFilms.size());

        return filmPage;
    }

    @Override
    public Page<Film> getFilmsByUserPaginated(Pageable pageable, TvdbUser user) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Film> allFilms = this.getFilmsByUser(user);
        List<Film> pagedFilms;
        if (allFilms.size() < startItem) {
            pagedFilms = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, allFilms.size());
            pagedFilms = allFilms.subList(startItem, toIndex);
        }
        Page<Film> filmPage = new PageImpl<Film>(pagedFilms, PageRequest.of(currentPage, pageSize), allFilms.size());

        return filmPage;
    }
}
