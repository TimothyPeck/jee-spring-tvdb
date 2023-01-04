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

    public void seedDirectors() {
        List<String> directorNames = new ArrayList<String>();
        directorNames.add("Steven Spielberg");
        directorNames.add("James Cameron");
        directorNames.add("Christopher Nolan");
        directorNames.add("Quentin Tarantino");
        directorNames.add("Martin Scorsese");
        directorNames.add("Stanley Kubrick");
        directorNames.add("Alfred Hitchcock");
        directorNames.add("David Fincher");
        directorNames.add("Francis Ford Coppola");
        directorNames.add("Peter Jackson");
        directorNames.add("George Lucas");
        directorNames.add("Ridley Scott");
        directorNames.add("Robert Zemeckis");
        directorNames.add("Tim Burton");
        directorNames.add("Clint Eastwood");
        directorNames.add("Sergio Leone");
        directorNames.add("John Ford");
        directorNames.add("Akira Kurosawa");
        directorNames.add("Ingmar Bergman");
        directorNames.add("Orson Welles");
        directorNames.add("Federico Fellini");
        directorNames.add("Roman Polanski");
        directorNames.add("Wes Anderson");
        directorNames.add("Hayao Miyazaki");
        directorNames.add("David Lynch");
        directorNames.add("Woody Allen");
        directorNames.add("Paul Thomas Anderson");
        directorNames.add("Joel Coen");
        directorNames.add("Ethan Coen");
        directorNames.add("John Carpenter");
        directorNames.add("Sam Raimi");
        directorNames.add("John Huston");

        for (String directorName : directorNames) {
            Director director = new Director();
            director.setName(directorName);
            directorRepository.save(director);
        }
    }
}
