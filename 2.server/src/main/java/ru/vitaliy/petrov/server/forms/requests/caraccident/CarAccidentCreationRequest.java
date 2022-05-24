package ru.vitaliy.petrov.server.forms.requests.caraccident;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CarAccidentCreationRequest {

    @NotNull
    @NotEmpty
    private final String carAccidentScene;

    @NotNull
    @NotEmpty
    private final String carAccidentDate;

    @NotNull
    @NotEmpty
    private final String carAccidentTime;

    @NotNull
    @NotEmpty
    private final Integer carAccidentParticipantsNumber;

    @NotNull
    @NotEmpty
    private final Integer carAccidentWitnessesNumber;

    @NotNull
    @NotEmpty
    private final List<Long> carAccidentSynchronizedParticipants;
}
