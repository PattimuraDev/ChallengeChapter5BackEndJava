package org.binar.ChallengeChapter5BackEndJava.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seats")
public class Seats {
    @EmbeddedId
    private SeatNumberCompositeKey seatNumberCompositeKey;
    private String studioName;
    private String status;
    @Column(name = "schedule_id")
    private Long scheduleID;
}
