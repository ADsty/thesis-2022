package ru.vitaliy.petrov.server.forms.requests.caraccident;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CarAccidentInfoRequest {

    @NotNull
    @NotEmpty
    private final Long carAccidentEntityID;
}
