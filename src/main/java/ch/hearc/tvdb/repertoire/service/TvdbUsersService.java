package ch.hearc.tvdb.repertoire.service;

import ch.hearc.tvdb.repertoire.model.TvdbUser;

public interface TvdbUsersService {
    public TvdbUser getUser(String username);

    public TvdbUser getUserById(long l);

    public void saveUser(TvdbUser user);
}
