package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class CarAccidentUpdateRequest {
    private final String updatedCarAccidentScene;
    private final String updatedCarAccidentDate;
    private final String updatedCarAccidentTime;
    private final Integer updatedCarAccidentParticipantsNumber;
    private final Integer updatedCarAccidentWitnessesNumber;
}
