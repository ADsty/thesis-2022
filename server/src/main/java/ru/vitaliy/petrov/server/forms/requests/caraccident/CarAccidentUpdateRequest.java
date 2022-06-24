package ru.vitaliy.petrov.server.forms.requests.caraccident;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;

@Data
public class CarAccidentUpdateRequest {

    @NotNull
    @NotEmpty
    private final String updatedCarAccidentScene;

    @NotNull
    private final Date updatedCarAccidentDate;

    @NotNull
    private final Time updatedCarAccidentTime;

}
