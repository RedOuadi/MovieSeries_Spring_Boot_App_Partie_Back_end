package service;

import dto.FilmDTO;
import dto.SerieDTO;
import exception.ResourceNotFoundException;
import model.Favori;
import model.Film;
import model.Serie;
import model.User;
import repositorie.FavoriRepository;
import repositorie.FilmRepository;
import repositorie.SerieRepository;
import repositorie.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriService {

    @Autowired
    private FavoriRepository favoriRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private FilmService filmService;

    @Autowired
    private SerieService serieService;

    public void addFilmToFavori(Long userId, Long filmId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Film film = filmRepository.findById(filmId).orElseThrow(() -> new ResourceNotFoundException("Film not found"));

        Favori favori = new Favori();
        favori.setUser(user);
        favori.setFilm(film);
        favoriRepository.save(favori);
    }

    public void addSerieToFavori(Long userId, Long serieId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Serie serie = serieRepository.findById(serieId).orElseThrow(() -> new ResourceNotFoundException("Serie not found"));

        Favori favori = new Favori();
        favori.setUser(user);
        favori.setSerie(serie);
        favoriRepository.save(favori);
    }

    public List<Favori> getUserFavoris(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return favoriRepository.findByUser(user);
    }

    public List<FilmDTO> getUserFavoriteFilms(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return favoriRepository.findByUser(user).stream()
                .map(Favori::getFilm)
                .filter(film -> film != null)
                .map(filmService::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<SerieDTO> getUserFavoriteSeries(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return favoriRepository.findByUser(user).stream()
                .map(Favori::getSerie)
                .filter(serie -> serie != null)
                .map(serieService::convertToDTO)
                .collect(Collectors.toList());
    }
}
