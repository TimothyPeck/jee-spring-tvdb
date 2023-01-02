package ch.hearc.tvdb.repertoire.model;

import java.sql.Date;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Date release_date;
    private int seasons;
    private int episodes;

    @Embedded
    private Site site;

    @Embedded
    private User user;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public int getSeasons() {
        return seasons;
    }

    public int getEpisodes() {
        return episodes;
    }

    public Site getSite() {
        return site;
    }

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
