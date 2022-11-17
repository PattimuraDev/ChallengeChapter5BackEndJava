package org.binar.ChallengeChapter5BackEndJava.model.dto;

import lombok.Data;
import org.binar.ChallengeChapter5BackEndJava.model.Schedules;
import java.util.List;

@Data
public class FilmsDto {
    private Long filmCode;
    private String filmName;
    private Boolean isPlaying;
    private List<Schedules> schedulesList;
}
