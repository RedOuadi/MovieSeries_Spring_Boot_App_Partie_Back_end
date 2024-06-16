package controller;

import com.msproje.movieSerieApp.dto.FilmDTO;
import com.msproje.movieSerieApp.dto.SerieDTO;
import com.msproje.movieSerieApp.model.Favori;
import com.msproje.movieSerieApp.service.FavoriService;
import com.msproje.movieSerieApp.service.FilmService;
import com.msproje.movieSerieApp.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favoris")
public class FavoriController {

    @Autowired
    private FavoriService favoriService;

    @Autowired
    private FilmService filmService;

    @Autowired
    private SerieService serieService;

    @PostMapping("/add-film")
    public void addFilmToFavori(@RequestParam Long userId, @RequestParam Long filmId) {
        favoriService.addFilmToFavori(userId, filmId);
    }

    @PostMapping("/add-serie")
    public void addSerieToFavori(@RequestParam Long userId, @RequestParam Long serieId) {
        favoriService.addSerieToFavori(userId, serieId);
    }

    @GetMapping("/user/{userId}/films")
    public ResponseEntity<?> getUserFavoriteFilms(@PathVariable Long userId) {
        try {
            List<FilmDTO> films = filmService.getAllFilmsDTO(); // Assuming getAllFilmsDTO() returns List<FilmDTO>
            // You can filter films by userId or process them as needed here
            return ResponseEntity.ok(films);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving user favorite films");
        }
    }

    @GetMapping("/user/{userId}/series")
    public ResponseEntity<?> getUserFavoriteSeries(@PathVariable Long userId) {
        try {
            List<SerieDTO> series = serieService.getAllSeriesDTO(); // Assuming getAllSeriesDTO() returns List<SerieDTO>
            // You can filter series by userId or process them as needed here
            return ResponseEntity.ok(series);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving user favorite series");
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Favori>> getUserFavoris(@PathVariable Long userId) {
        List<Favori> favoris = favoriService.getUserFavoris(userId);
        return new ResponseEntity<>(favoris, HttpStatus.OK);
    }
}
