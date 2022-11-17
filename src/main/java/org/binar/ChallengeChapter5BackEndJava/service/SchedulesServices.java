package org.binar.ChallengeChapter5BackEndJava.service;

import org.binar.ChallengeChapter5BackEndJava.model.Schedules;
import org.binar.ChallengeChapter5BackEndJava.model.dto.SchedulesDto;

import java.util.List;

public interface SchedulesServices {
    List<Schedules> schedulesOfFilmsByName(String filmName);
    Schedules findScheduleById(Long idSchedule);
    void updateFilmCodeOfSchedules(Long schedulesId, Long filmId);
    Schedules addSchedule(Schedules schedules);
    void deleteSchedule(Long idSchedule);
    void deleteSchedulesByFilmId(Long filmId);
    Schedules scheduleDtoMapToEntity(SchedulesDto schedulesDto);
}
