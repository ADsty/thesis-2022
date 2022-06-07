package ru.vitaliy.petrov.server.forms.requests.caraccident;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Data
public class CarAccidentCreationRequest {

    @NotNull
    @NotEmpty
    private final String carAccidentScene;

    @NotNull
    private final Date carAccidentDate;

    @NotNull
    private final Time carAccidentTime;

    @NotNull
    @NotEmpty
    private final List<Long> carAccidentSynchronizedParticipants;

    @NotNull
    @NotEmpty
    private final List<Long> carAccidentParticipantsVehicles;
}
