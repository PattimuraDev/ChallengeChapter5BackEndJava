package org.binar.ChallengeChapter5BackEndJava.repository;

import org.binar.ChallengeChapter5BackEndJava.model.Films;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
public class FilmsRepositoryTest {

    @Autowired
    FilmsRepository filmsRepository;

    /**
     * Method testing repository yang berkaitan dengan proses mendapatkan semua film dengan status tayang pada saat ini
     */
    @Test
    void getAllFilmIsPlaying(){
        List<Films> listFilmResult = filmsRepository.repoGetFilmIsPlaying();
        Assertions.assertNotNull(listFilmResult);
    }

}
