package ch.hearc.tvdb.repertoire.service;

import java.util.List;

import ch.hearc.tvdb.repertoire.model.Director;

public interface DirectorService {
    public String getDirector(Long id);
    public Director getDirectorById(Long id);
    public List<Director> getAllDirectors();
    public void addDirector(Director director);
}
