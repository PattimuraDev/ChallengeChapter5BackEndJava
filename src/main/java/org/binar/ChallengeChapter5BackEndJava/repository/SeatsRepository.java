package org.binar.ChallengeChapter5BackEndJava.repository;

import org.binar.ChallengeChapter5BackEndJava.model.SeatNumberCompositeKey;
import org.binar.ChallengeChapter5BackEndJava.model.Seats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatsRepository extends JpaRepository<Seats, SeatNumberCompositeKey> {
    @Query(value = "call UPDATE_SEATS(:nomor_baris_kursi, :nomor_kolom_kursi, :status)", nativeQuery = true)
    @Modifying
    void repoUpdateSeats(
            @Param("status") String newStatus,
            @Param("nomor_baris_kursi") String nomorBarisKursi,
            @Param("nomor_kolom_kursi") String NomorKolomKursi
    );

    @Query(value = "select * from ALL_SEATS_AVAILABLE()", nativeQuery = true)
    List<Seats> repoGetAllSeatsAvailable();
}
