package org.binar.ChallengeChapter5BackEndJava.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "films")
public class Films {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long filmCode;
    @Column(name = "film_name", unique = true)
    private String filmName;
    private Boolean isPlaying;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_schedules")
    private List<Schedules> schedulesList;
}
