package org.binar.ChallengeChapter5BackEndJava.service;


import org.binar.ChallengeChapter5BackEndJava.model.SeatNumberCompositeKey;
import org.binar.ChallengeChapter5BackEndJava.model.Seats;
import org.binar.ChallengeChapter5BackEndJava.model.dto.SeatsDto;

import java.util.List;

public interface SeatsServices {
    Seats addSeats(Seats seats);
    void updateSeatsStatus(String newStatus, String nomorBarisKursi, String nomorKolomKursi);
    Seats getSeatById(SeatNumberCompositeKey seatNumberCompositeKey);
    List<Seats> getAllSeatsAvailable();
    void deleteSeats(SeatNumberCompositeKey idSeat);
    Seats seatsDtoMapToEntity(SeatsDto seatsDto);
}
