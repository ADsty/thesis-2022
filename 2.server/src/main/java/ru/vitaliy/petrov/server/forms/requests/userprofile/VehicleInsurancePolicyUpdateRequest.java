package ru.vitaliy.petrov.server.forms.requests.userprofile;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class VehicleInsurancePolicyUpdateRequest {

    @NotNull
    @NotEmpty
    private final String updatedVehicleInsuranceCompany;

    @NotNull
    @NotEmpty
    private final String updatedVehicleInsurancePolicyNumber;

    @NotNull
    @NotEmpty
    private final String updatedVehicleInsurancePolicyExpirationDate;
}
