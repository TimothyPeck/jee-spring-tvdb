package ch.hearc.tvdb.repertoire.repository;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.tvdb.repertoire.model.Director;

public interface DirectorRepository extends CrudRepository<Director, Long>{
    public Director findByName(String name);

    public Director findById(long id);
}
