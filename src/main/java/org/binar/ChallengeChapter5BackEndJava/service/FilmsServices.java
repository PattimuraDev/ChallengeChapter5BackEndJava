package org.binar.ChallengeChapter5BackEndJava.service;

import org.binar.ChallengeChapter5BackEndJava.model.Films;
import org.binar.ChallengeChapter5BackEndJava.model.dto.FilmsDto;

import java.util.List;

public interface FilmsServices {
    Films addFilms(Films films);
    void updateFilmByName(String oldFilmName, String newFilmName);
    Films getFilmById(Long idFilm);
    void deleteFilmById(Long idFilm);
    List<Films> getFilmIsPlaying();
    Films filmDtoMapToEntity(FilmsDto filmsDto);
}
