package ru.vitaliy.petrov.server.forms.requests.caraccident;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CarAccidentWitnessUpdateRequest {

    @NotNull
    @NotEmpty
    private final String updatedWitnessFullName;

    @NotNull
    @NotEmpty
    private final String updatedWitnessResidentialAddress;

    @NotNull
    @NotEmpty
    private final String updatedWitnessPhoneNumber;

    @NotNull
    @NotEmpty
    private final Long witnessID;
}
