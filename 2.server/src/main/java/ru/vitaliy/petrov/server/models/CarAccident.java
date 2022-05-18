package ru.vitaliy.petrov.server.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "car_accident")
public class CarAccident implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "car_accident_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_accident_scene")
    private String carAccidentScene;

    @Column(name = "car_accident_date")
    private Date carAccidentDate;

    @Column(name = "car_accident_time")
    private String carAccidentTime;

    @Column(name = "car_accident_participants_number")
    private int carAccidentParticipantsNumber;

    @Column(name = "car_accident_witnesses_number")
    private int carAccidentWitnessesNumber;

}
