package ch.hearc.tvdb.repertoire.model;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Date release_date;
    private Time duration_minutes;

    @ManyToOne
    @JoinColumn(name = "director_id", nullable = false)
    private Director director;

    @ManyToOne
    @JoinColumn(name = "site_id", nullable = false)
    private Site site;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private TvdbUser user;

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    @Override
    public int hashCode() {
        return Objects.hash(director, duration_minutes, id, release_date, site, title, user);
    }

    @Override
    public boolean equals(Object arg0) {
        if (this == arg0) {
            return true;
        }
        if (arg0 == null) {
            return false;
        }
        if (getClass() != arg0.getClass()) {
            return false;
        }
        Film other = (Film) arg0;
        return Objects.equals(director, other.director) && duration_minutes == other.duration_minutes
                && Objects.equals(id, other.id) && Objects.equals(release_date, other.release_date)
                && Objects.equals(site, other.site) && Objects.equals(title, other.title)
                && Objects.equals(user, other.user);
    }

    public Long getId() {
        return id;
    }

    public Time getDuration_minutes() {
        return duration_minutes;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public Site getSite() {
        return site;
    }

    public String getTitle() {
        return title;
    }

    public TvdbUser getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDuration_minutes(int duration_minutes) {
        this.duration_minutes = new Time(duration_minutes * 60 * 1000);
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUser(TvdbUser user) {
        this.user = user;
    }

    public String getDuration() {
        return duration_minutes.toString();
    }
}
