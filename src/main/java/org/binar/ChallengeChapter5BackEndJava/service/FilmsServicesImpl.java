package org.binar.ChallengeChapter5BackEndJava.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.binar.ChallengeChapter5BackEndJava.model.Films;
import org.binar.ChallengeChapter5BackEndJava.model.dto.FilmsDto;
import org.binar.ChallengeChapter5BackEndJava.repository.FilmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class FilmsServicesImpl implements FilmsServices {
    @Autowired
    FilmsRepository filmsRepository;

    /**
     * Method yang digunakan untuk add film
     * @param films paramater berisi data object film
     * @return film hasil save
     */
    @Override
    public Films addFilms(Films films) {
        return filmsRepository.save(films);
    }


    /**
     * Method yang digunakan untuk mengupdate nama/judul film
     * @param oldFilmName parameter untuk nama/judul awal film
     * @param newFilmName parameter untuk nama/judul film yang baru
     */
    @Override
    public void updateFilmByName(String oldFilmName, String newFilmName) {
        filmsRepository.repoUpdateFilmByName(oldFilmName, newFilmName);
    }


    /**
     * Method untuk mendapatkan film berdasarkan id film
     * @param idFilm parameter untuk id dari film
     * @return film hasil pencarian berdasarkan id
     */
    @Override
    public Films getFilmById(Long idFilm) {
        if(filmsRepository.findById(idFilm).isPresent()){
            return filmsRepository.findById(idFilm).get();
        }else{
            return null;
        }
    }


    /**
     * Method yang digunakan untuk menghapus film berdasarkan id dari film
     * @param filmId parameter untuk id dari film
     */
    @Override
    public void deleteFilmById(Long filmId){
        filmsRepository.deleteById(filmId);
    }


    /**
     * Method untuk mengambil semua film dengan status tayang saat ini
     * @return semua film yang sedang tayang (list)
     */
    @Override
    public List<Films> getFilmIsPlaying() {
        return filmsRepository.repoGetFilmIsPlaying();
    }


    // mapper untuk keperluan pemetaan objek
    ObjectMapper mapper = new ObjectMapper();

    /**
     * Method untuk melakukan pemetaan dari objek FilmsDto menjadi films
     * @param filmsDto parameter untuk objek FilmsDto
     * @return hasil pemetaan menjadi objek Films
     */
    @Override
    public Films filmDtoMapToEntity(FilmsDto filmsDto) {
        return mapper.convertValue(filmsDto, Films.class);
    }
}
