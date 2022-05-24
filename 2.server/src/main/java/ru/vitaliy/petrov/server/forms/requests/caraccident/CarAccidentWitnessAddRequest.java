package ru.vitaliy.petrov.server.forms.requests.caraccident;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CarAccidentWitnessAddRequest {

    @NotNull
    @NotEmpty
    private final Long entityID;

    @NotNull
    @NotEmpty
    private final String witnessFullName;

    @NotNull
    @NotEmpty
    private final String witnessResidentialAddress;

    @NotNull
    @NotEmpty
    private final String witnessPhoneNumber;
}
