package org.binar.ChallengeChapter5BackEndJava.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schedules")
public class Schedules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schedulesId;
    private Long filmsCode;
    private String date;
    private String startTime;
    private String endTime;
    private Long ticketPrice;
    private String studioName;
}