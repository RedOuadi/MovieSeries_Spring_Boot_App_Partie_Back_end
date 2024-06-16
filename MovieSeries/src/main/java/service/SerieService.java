// src/main/java/com/msproje/movieSerieApp/service/SerieService.java
package service;

import dto.SerieDTO;
import model.Serie;
import repositorie.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SerieService {

    private final SerieRepository serieRepository;

    @Autowired
    public SerieService(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public List<SerieDTO> getAllSeriesDTO() {
        List<Serie> series = serieRepository.findAll();
        return series.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<SerieDTO> getSerieDTOById(Long id) {
        Optional<Serie> serieOptional = serieRepository.findById(id);
        return serieOptional.map(this::convertToDTO);
    }

    public SerieDTO addSerie(SerieDTO serieDTO) {
        Serie serie = convertToEntity(serieDTO);
        Serie savedSerie = serieRepository.save(serie);
        return convertToDTO(savedSerie);
    }

    public void deleteSerie(Long id) {
        serieRepository.deleteById(id);
    }

    SerieDTO convertToDTO(Serie serie) {
        SerieDTO serieDTO = new SerieDTO();
        serieDTO.setIdSerie(serie.getId_serie());
        serieDTO.setTitre(serie.getTitre());
        serieDTO.setDateDebut(serie.getDateDebut());
        serieDTO.setDateFin(serie.getDateFin());
        serieDTO.setGenre(serie.getGenre());
        serieDTO.setCreateur(serie.getCreateur());
        serieDTO.setNote(serie.getNote());
        return serieDTO;
    }

    private Serie convertToEntity(SerieDTO serieDTO) {
        Serie serie = new Serie();
        serie.setId_serie(serieDTO.getIdSerie());
        serie.setTitre(serieDTO.getTitre());
        serie.setDateDebut(serieDTO.getDateDebut());
        serie.setDateFin(serieDTO.getDateFin());
        serie.setGenre(serieDTO.getGenre());
        serie.setCreateur(serieDTO.getCreateur());
        serie.setNote(serieDTO.getNote());
        return serie;
    }
}
