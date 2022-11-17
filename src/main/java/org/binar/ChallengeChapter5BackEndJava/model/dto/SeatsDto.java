package org.binar.ChallengeChapter5BackEndJava.model.dto;

import lombok.Data;
import org.binar.ChallengeChapter5BackEndJava.model.SeatNumberCompositeKey;

@Data
public class SeatsDto {
    private SeatNumberCompositeKey seatNumberCompositeKey;
    private String studioName;
    private String status;
    private Long scheduleID;
}
