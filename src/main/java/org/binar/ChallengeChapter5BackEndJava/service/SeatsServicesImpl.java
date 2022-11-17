package org.binar.ChallengeChapter5BackEndJava.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.binar.ChallengeChapter5BackEndJava.model.SeatNumberCompositeKey;
import org.binar.ChallengeChapter5BackEndJava.model.Seats;
import org.binar.ChallengeChapter5BackEndJava.model.dto.SeatsDto;
import org.binar.ChallengeChapter5BackEndJava.repository.SeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SeatsServicesImpl implements SeatsServices{

    @Autowired
    SeatsRepository seatsRepository;

    /**
     * Method untuk menambah seats
     * @param seats object seats yang ingin ditambahkan
     * @return object seats yang berhasil ditambahkan
     */
    @Override
    public Seats addSeats(Seats seats) {
        return seatsRepository.save(seats);
    }


    /**
     * Method untuk mengupdate status kursi, apakah sudah dipesan atau belum
     * @param newStatus       parameter untuk mengubah status seats
     * @param nomorBarisKursi parameter nomor baris dari kursi/seats
     * @param nomorKolomKursi parameter nomor kolom dari kursi/seats
     */
    @Override
    public void updateSeatsStatus(String newStatus, String nomorBarisKursi, String nomorKolomKursi) {
        seatsRepository.repoUpdateSeats(newStatus, nomorBarisKursi, nomorKolomKursi);
    }


    /**
     * Method untuk mengambil data seat berdasarkan id
     * @param seatNumberCompositeKey parameter untuk composite key dari seat yang dicari
     * @return hasil pencarian seat
     */
    @Override
    public Seats getSeatById(SeatNumberCompositeKey seatNumberCompositeKey) {
        if(seatsRepository.findById(seatNumberCompositeKey).isPresent()){
            return seatsRepository.findById(seatNumberCompositeKey).get();
        }else{
            return null;
        }
    }


    /**
     * Method untuk mengambil semua seats dengan status tersedia/belum dipesan (available)
     * @return list seats dengan status tersedia
     */
    @Override
    public List<Seats> getAllSeatsAvailable() {
        return seatsRepository.repoGetAllSeatsAvailable();
    }


    /**
     * Method yang digunakan untuk menghapus seat berdasarkan id-nya (dalam bentuk composite key)
     * @param idSeat parameter untuk id daro seat yang dimaksud (dalam bentuk composite key)
     */
    @Override
    public void deleteSeats(SeatNumberCompositeKey idSeat) {
        seatsRepository.deleteById(idSeat);
    }

    // mapper untuk keperluan mapping objek
    ObjectMapper mapper = new ObjectMapper();

    /**
     * Method yang digunakan untuk memetakan objek SeatsDto menjadi Seats
     * @param seatsDto parameter untuk objek SeatsDto
     * @return hasil pemetaan menjadi objek Seats
     */
    @Override
    public Seats seatsDtoMapToEntity(SeatsDto seatsDto) {
        return mapper.convertValue(seatsDto, Seats.class);
    }

}
