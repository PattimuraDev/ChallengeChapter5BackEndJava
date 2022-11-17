package org.binar.ChallengeChapter5BackEndJava.model.dto;

import lombok.Data;

@Data
public class SchedulesDto {
    private Long schedulesId;
    private Long filmsCode;
    private String date;
    private String startTime;
    private String endTime;
    private Long ticketPrice;
    private String studioName;
}
