package ch.hearc.tvdb.repertoire.repository;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.tvdb.repertoire.model.TvdbUser;

public interface TvdbUserRepository extends CrudRepository<TvdbUser, Long> {
    public TvdbUser findByUsername(String name);

    public TvdbUser findByEmail(String email);

    public TvdbUser findById(long id);
}
