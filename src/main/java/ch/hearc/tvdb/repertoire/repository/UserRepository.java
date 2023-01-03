package ch.hearc.tvdb.repertoire.repository;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.tvdb.repertoire.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String name);
}
