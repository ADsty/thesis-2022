package ru.vitaliy.petrov.server.dto;

import lombok.Data;
import ru.vitaliy.petrov.server.models.CarAccident;

import java.sql.Date;
import java.sql.Time;

@Data
public class CarAccidentDTO {

    private Long carAccidentID;
    private String carAccidentScene;
    private Date carAccidentDate;
    private Time carAccidentTime;
    private int carAccidentParticipantsNumber;
    private int carAccidentWitnessesNumber;

    public CarAccidentDTO(CarAccident carAccident) {
        this.carAccidentID = carAccident.getId();
        this.carAccidentScene = carAccident.getCarAccidentScene();
        this.carAccidentDate = carAccident.getCarAccidentDate();
        this.carAccidentTime = carAccident.getCarAccidentTime();
        this.carAccidentParticipantsNumber = carAccident.getCarAccidentParticipantsNumber();
        this.carAccidentWitnessesNumber = carAccident.getCarAccidentWitnessesNumber();
    }

}
