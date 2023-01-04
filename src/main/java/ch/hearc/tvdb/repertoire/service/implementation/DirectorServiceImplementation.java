package ch.hearc.tvdb.repertoire.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.hearc.tvdb.repertoire.model.Director;
import ch.hearc.tvdb.repertoire.repository.DirectorRepository;
import ch.hearc.tvdb.repertoire.service.DirectorService;

@Service
public class DirectorServiceImplementation implements DirectorService {

    @Autowired
    private DirectorRepository directorRepository;

    @Override
    public String getDirector(Long id) {
        return directorRepository.findById(id).get().getName();
    }

    @Override
    public List<Director> getAllDirectors() {
        List<Director> directors = new ArrayList<Director>();
        directorRepository.findAll().forEach(directors::add);
        return directors;
    }

    @Override
    public Director getDirectorById(Long id) {
        return directorRepository.findById(id).get();
    }

    @Override
    public void addDirector(Director director) {
        directorRepository.save(director);
    }

    @Override
    public void deleteDirector(Director director) {
        directorRepository.delete(director);
    }

    @Override
    public void deleteDirectorById(Long id) {
        directorRepository.deleteById(id);
    }
}
