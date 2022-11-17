package org.binar.ChallengeChapter5BackEndJava.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.binar.ChallengeChapter5BackEndJava.model.Films;
import org.binar.ChallengeChapter5BackEndJava.model.Schedules;
import org.binar.ChallengeChapter5BackEndJava.model.dto.SchedulesDto;
import org.binar.ChallengeChapter5BackEndJava.repository.FilmsRepository;
import org.binar.ChallengeChapter5BackEndJava.repository.SchedulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class SchedulesServicesImpl implements SchedulesServices {

    @Autowired
    SchedulesRepository schedulesRepository;

    @Autowired
    FilmsRepository filmsRepository;

    /**
     * Method yang digunakan untuk mengambil semua jadwal tersedia untuk film dengan judul tertentu
     * @param filmName parameter nama/judul film
     * @return list schedule film terkait
     */
    @Override
    public List<Schedules> schedulesOfFilmsByName(String filmName) {
        List<Schedules> listOfAllSchedules = schedulesRepository.findAll();
        List<Schedules> result = new ArrayList<>();
        for (Schedules schedules : listOfAllSchedules) {
            if (Objects.equals(schedules.getFilmsCode(), findFilmIdByName(filmName))) {
                result.add(schedules);
            }
        }
        return result;
    }


    /**
     * Method untuk mendapatkan schedule berdasarkan id-nya
     * @param idSchedule parameter untuk id dari schedule yang dimaksud
     * @return objek schedule hasil pencarian berdasarkan id-nya
     */
    @Override
    public Schedules findScheduleById(Long idSchedule) {
        if(schedulesRepository.findById(idSchedule).isPresent()){
            return schedulesRepository.findById(idSchedule).get();
        }else{
            return null;
        }
    }


    /**
     * Method yang digunakna untuk mengupdate kode film pada table jadwal/schedule
     * @param schedulesId parameter untuk id dari schedule yang dimaksud
     * @param filmId parameter untuk id film yang ingin dimasukkan
     */
    @Override
    public void updateFilmCodeOfSchedules(Long schedulesId, Long filmId) {
        schedulesRepository.repoUpdateFilmsCodeOfSchedules(filmId, schedulesId);
    }


    /**
     * Method yang digunakan untuk menambahkan jadwal/schedule baru
     * @param schedules parameter untuk objek schedule
     * @return objek schedule yang berhasil tersimpan
     */
    @Override
    public Schedules addSchedule(Schedules schedules) {
        return schedulesRepository.save(schedules);
    }


    /**
     * Method yang digunakan untuk menghapus schedule film berdasarkan id dari schedule
     * @param idSchedule parameter untuk id schedule yang dimaksud
     */
    @Override
    public void deleteSchedule(Long idSchedule) {
        schedulesRepository.deleteById(idSchedule);
    }


    /**
     * Method yang digunakan untuk menghapus schedule film berdasarkan id film
     * @param filmId parameter untuk id dari film
     */
    @Override
    public void deleteSchedulesByFilmId(Long filmId) {
        schedulesRepository.repoDeleteScheduleByFilmId(filmId);
    }


    //mapper untuk keperluan pemetaan objek
    ObjectMapper mapper = new ObjectMapper();

    /**
     * Method yang digunakan untuk memetakan objek SchedulesDto menjadi Schedules
     * @param schedulesDto parameter untuk objek SchedulesDto
     * @return hasil pemetaan menjadi objek Schedules
     */
    @Override
    public Schedules scheduleDtoMapToEntity(SchedulesDto schedulesDto) {
        return mapper.convertValue(schedulesDto, Schedules.class);
    }


    /**
     * Method uang digunakan untuk mencari id film berdasarkan nama/judulnya
     * @param filmName parameter untuk nama dari film
     * @return hasil pencarian id film
     */
    public Long findFilmIdByName(String filmName) {
        List<Films> listOfAllFilms = filmsRepository.findAll();
        Long filmCode = 0L;
        for (Films films : listOfAllFilms) {
            if (Objects.equals(films.getFilmName(), filmName)) {
                filmCode = films.getFilmCode();
            }
        }
        return filmCode;
    }
}
