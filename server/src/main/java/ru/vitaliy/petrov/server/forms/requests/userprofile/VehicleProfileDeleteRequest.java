package ru.vitaliy.petrov.server.forms.requests.userprofile;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class VehicleProfileDeleteRequest {

    @NotNull
    @NotEmpty
    private final String vehicleVIN;
}
