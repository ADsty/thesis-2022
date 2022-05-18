package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

import java.util.List;

@Data
public class CarAccidentCreationRequest {
    private final String carAccidentScene;
    private final String carAccidentDate;
    private final String carAccidentTime;
    private final Integer carAccidentParticipantsNumber;
    private final Integer carAccidentWitnessesNumber;
    private final List<Long> carAccidentSynchronizedParticipants;
}
