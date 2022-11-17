package org.binar.ChallengeChapter5BackEndJava.repository;

import org.binar.ChallengeChapter5BackEndJava.model.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulesRepository extends JpaRepository<Schedules, Long> {
    @Query(value = "call DELETE_SCHEDULE_BY_FILM_CODE(:input_kode_film)", nativeQuery = true)
    @Modifying
    void repoDeleteScheduleByFilmId(@Param("input_kode_film") Long filmId);

    @Query(value = "call UPDATE_FILM_CODE_ON_SCHEDULE(:kode_film, :input_id_schedule)", nativeQuery = true)
    @Modifying
    void repoUpdateFilmsCodeOfSchedules(
            @Param("kode_film") Long kodeFilm,
            @Param("input_id_schedule") Long shedulesId
    );

}
