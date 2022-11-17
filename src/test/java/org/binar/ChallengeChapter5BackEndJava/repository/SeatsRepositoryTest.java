package org.binar.ChallengeChapter5BackEndJava.repository;

import org.binar.ChallengeChapter5BackEndJava.model.Seats;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
public class SeatsRepositoryTest {

    @Autowired
    SeatsRepository seatsRepository;

    /**
     * Method testing repository yang berkaitan dengan proses mendapatkan semua seat dengan status available
     */
    @Test
    public void getAllSeatsAvailable(){
        List<Seats> listSeatsAvailable = seatsRepository.repoGetAllSeatsAvailable();
        Assertions.assertNotNull(listSeatsAvailable);
    }
}
