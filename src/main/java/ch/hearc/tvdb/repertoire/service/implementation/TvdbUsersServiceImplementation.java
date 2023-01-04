package ch.hearc.tvdb.repertoire.service.implementation;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.hearc.tvdb.repertoire.model.TvdbUser;
import ch.hearc.tvdb.repertoire.repository.TvdbUserRepository;
import ch.hearc.tvdb.repertoire.service.TvdbUsersService;

@Service
public class TvdbUsersServiceImplementation implements TvdbUsersService {
    @Autowired
    TvdbUserRepository tvdbUserRepository;

    @Override
    public TvdbUser getUser(String username) {
        return tvdbUserRepository.findByUsername(username);
    }

    @Override
    public TvdbUser getUserById(long l) {
        return tvdbUserRepository.findById(l);
    }

    @Override
    public void saveUser(TvdbUser user) {
        tvdbUserRepository.save(user);
    }

}
