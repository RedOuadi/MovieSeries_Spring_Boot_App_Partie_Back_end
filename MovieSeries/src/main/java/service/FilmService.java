package service;

import dto.FilmDTO;
import model.Film;
import repositorie.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class FilmService {

    private final FilmRepository filmRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<FilmDTO> getAllFilmsDTO() {
        List<Film> films = filmRepository.findAll();
        return films.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<FilmDTO> getFilmDTOById(Long id) {
        Optional<Film> filmOptional = filmRepository.findById(id);
        return filmOptional.map(this::convertToDTO);
    }

    public FilmDTO saveFilm(FilmDTO filmDTO) {
        Film film = convertToEntity(filmDTO);
        Film savedFilm = filmRepository.save(film);
        return convertToDTO(savedFilm);
    }

    public void deleteFilm(Long id) {
        filmRepository.deleteById(id);
    }

    FilmDTO convertToDTO(Film film) {
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setIdFilm(film.getId_film());
        filmDTO.setTitre(film.getTitre());
        filmDTO.setDateSortie(film.getDateSortie());
        filmDTO.setGenre(film.getGenre());
        filmDTO.setRealisateur(film.getRealisateur());
        filmDTO.setNote(film.getNote());
        return filmDTO;
    }

    private Film convertToEntity(FilmDTO filmDTO) {
        Film film = new Film();
        film.setId_film(filmDTO.getIdFilm());
        film.setTitre(filmDTO.getTitre());
        film.setDateSortie(filmDTO.getDateSortie());
        film.setGenre(filmDTO.getGenre());
        film.setRealisateur(filmDTO.getRealisateur());
        film.setNote(filmDTO.getNote());
        return film;
    }
}
