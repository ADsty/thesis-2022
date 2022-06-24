package ru.vitaliy.petrov.server.forms.requests.userprofile;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class VehicleProfileCreationRequest {

    @NotNull
    @NotEmpty
    private final String vehicleBrand;

    @NotNull
    @NotEmpty
    private final String vehicleVIN;

    @NotNull
    @NotEmpty
    private final String vehicleRegistrationSign;

    @NotNull
    @NotEmpty
    private final String vehicleRegistrationCertificate;

    @NotNull
    @NotEmpty
    private final String vehicleOwnerFullName;

    @NotNull
    @NotEmpty
    private final String vehicleOwnerResidentialAddress;

    @NotNull
    @NotEmpty
    private final String vehicleInsurancePolicyNumber;
}
