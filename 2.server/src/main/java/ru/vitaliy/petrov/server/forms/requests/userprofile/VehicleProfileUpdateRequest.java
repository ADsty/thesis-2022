package ru.vitaliy.petrov.server.forms.requests.userprofile;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class VehicleProfileUpdateRequest {

    @NotNull
    @NotEmpty
    private final String updatedVehicleBrand;

    @NotNull
    @NotEmpty
    private final String updatedVehicleVIN;

    @NotNull
    @NotEmpty
    private final String updatedVehicleRegistrationSign;

    @NotNull
    @NotEmpty
    private final String updatedVehicleRegistrationCertificate;

    @NotNull
    @NotEmpty
    private final String updatedVehicleOwnerFullName;

    @NotNull
    @NotEmpty
    private final String updatedVehicleOwnerResidentialAddress;

    @NotNull
    @NotEmpty
    private final String updatedVehicleInsurancePolicyNumber;
}
