package ch.hearc.tvdb.repertoire.service;

import org.apache.catalina.User;

import ch.hearc.tvdb.repertoire.model.TvdbUser;

public interface TvdbUsersService {
    public TvdbUser getUser(String username);

    public TvdbUser getUserById(long l);
}
