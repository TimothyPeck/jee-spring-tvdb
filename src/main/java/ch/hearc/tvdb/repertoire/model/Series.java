package ch.hearc.tvdb.repertoire.model;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Date release_date;
    private int seasons;
    private int episodes;

    @ManyToOne
    @JoinColumn(name = "site_id", nullable = false)
    private Site site;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private TvdbUser user;

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

    public TvdbUser getUser() {
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

    public void setUser(TvdbUser user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        return Objects.hash(episodes, id, name, release_date, seasons, site, user);
    }

    @Override
    public boolean equals(Object arg0) {
        if (this == arg0)
            return true;
        if (arg0 == null)
            return false;
        if (getClass() != arg0.getClass())
            return false;
        Series other = (Series) arg0;
        return episodes == other.episodes && Objects.equals(id, other.id) && Objects.equals(name, other.name)
                && Objects.equals(release_date, other.release_date) && seasons == other.seasons
                && Objects.equals(site, other.site) && Objects.equals(user, other.user);
    }
}
