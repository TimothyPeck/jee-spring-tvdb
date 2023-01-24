package ch.hearc.tvdb;

import java.sql.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import ch.hearc.tvdb.repertoire.model.Director;
import ch.hearc.tvdb.repertoire.model.Film;
import ch.hearc.tvdb.repertoire.model.Site;
import ch.hearc.tvdb.repertoire.model.TvdbUser;
import ch.hearc.tvdb.repertoire.service.DirectorService;
import ch.hearc.tvdb.repertoire.service.FilmsService;
import ch.hearc.tvdb.repertoire.service.SitesService;
import ch.hearc.tvdb.repertoire.service.TvdbUsersService;

@SpringBootTest
class TvdbApplicationTests {

	@Autowired
	FilmsService filmsService;

	@Autowired
	SitesService sitesService;

	@Autowired
	DirectorService directorService;

	@Autowired
	TvdbUsersService userService;

	Director director = new Director();
	Site site = new Site();
	Film film = new Film();
	TvdbUser user = new TvdbUser();

	@Test
	void canAddSite() {
		site.setName("testSite");
		site.setUrl("example.com");

		sitesService.addSite(site);

		Assert.notNull(sitesService.getSiteById(site.getId()), "Site not found");
	}

	@Test
	void canAddDirector() {
		director.setName("test");

		directorService.addDirector(director);

		Assert.notNull(directorService.getDirectorById(director.getId()), "Director not found");
	}

	@Test
	void canAddUser() {
		user.setUsername("test");
		user.setPassword("test");
		user.setEmail("test@test.com");

		userService.saveUser(user);

		Assert.notNull(userService.getUserById(user.getId()), "User not found");
	}

	@Test
	void canAddFilm() {
		film.setTitle("Test");
		film.setRelease_date(Date.valueOf("2021-01-01"));
		film.setDuration_minutes(120);
		film.setSite(sitesService.getSiteById(1L));
		film.setDirector(directorService.getDirectorById(1L));
		film.setUser(userService.getUserById(1L));

		filmsService.addFilm(film);

		Assert.notNull(filmsService.getFilmById(film.getId()), "Film not found");
	}

	@Test
	void canGetAllFilms() {
		List<Film> films = filmsService.getAllFilms();

		Assert.notEmpty(films, "No films found");
	}

	@Test
	void canGetFilmById() {
		Film f = filmsService.getFilmById(1L);

		Assert.notNull(f, "Film not found");
	}

	@Test
	void canDeleteFilmById() {
		film.setTitle("Test1");
		film.setRelease_date(Date.valueOf("2021-01-01"));
		film.setDuration_minutes(120);
		film.setSite(sitesService.getSiteById(1L));
		film.setDirector(directorService.getDirectorById(1L));
		film.setUser(userService.getUserById(1L));

		filmsService.addFilm(film);

		filmsService.deleteFilmById(film.getId());

		try {
			filmsService.getFilmById(film.getId());
		} catch (Exception e) {
			Assert.isTrue(true, "Film not deleted");
		}
		Assert.isTrue(false, "Film not deleted");
	}

	@Test
	void canUpdateFilm() {
		film.setTitle("Test2");
		film.setRelease_date(Date.valueOf("2021-01-01"));
		film.setDuration_minutes(120);
		film.setSite(sitesService.getSiteById(1L));
		film.setDirector(directorService.getDirectorById(1L));
		film.setUser(userService.getUserById(1L));

		filmsService.addFilm(film);

		film.setTitle("Test3");

		filmsService.updateFilm(film);

		Assert.isTrue(filmsService.getFilmById(film.getId()).getTitle().equals("Test3"), "Film not updated");
	}

	@Test
	void canDeleteFilm() {
		filmsService.deleteFilm(film);
	}

	@Test
	void canGetFilmsByUser() {
		film.setTitle("Test4");
		film.setRelease_date(Date.valueOf("2021-01-01"));
		film.setDuration_minutes(120);
		film.setSite(sitesService.getSiteById(1L));
		film.setDirector(directorService.getDirectorById(1L));
		film.setUser(userService.getUserById(1L));

		TvdbUser u = userService.getUserById(1L);

		filmsService.addFilm(film);
		List<Film> films = filmsService.getFilmsByUser(u);

		Assert.notEmpty(films, "No films found");
	}

}
