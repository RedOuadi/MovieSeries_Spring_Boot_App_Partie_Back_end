package service;

import model.Film;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FilmServiceTest implements CommandLineRunner {

    private final FilmService filmService;

    public FilmServiceTest(FilmService filmService) {
        this.filmService = filmService;
    }

    @Override
    public void run(String... args) throws Exception {
        testSaveFilm();
        testDeleteFilm();
    }

    private void testSaveFilm() {
        Film newFilm = new Film();
        newFilm.setTitre("New Film Title");
        newFilm.setDateSortie(new Date()); // Replace with appropriate date
        newFilm.setGenre("Action");
        newFilm.setRealisateur("John Doe");
        newFilm.setNote(8.0);

//        Film savedFilm = filmService.saveFilm(newFilm);
//        System.out.println("Saved Film: " + savedFilm);
    }

    private void testDeleteFilm() {
        Long filmIdToDelete = 2L; // Replace with an existing film ID from your database
        filmService.deleteFilm(filmIdToDelete);
        System.out.println("Film deleted with ID: " + filmIdToDelete);
    }
}
