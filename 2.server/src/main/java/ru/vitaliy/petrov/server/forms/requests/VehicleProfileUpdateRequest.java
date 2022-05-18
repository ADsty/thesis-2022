package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class VehicleProfileUpdateRequest {
    private final String updatedVehicleBrand;
    private final String updatedVehicleVIN;
    private final String updatedVehicleRegistrationSign;
    private final String updatedVehicleRegistrationCertificate;
    private final String updatedVehicleOwnerFullName;
    private final String updatedVehicleOwnerResidentialAddress;
    private final String updatedVehicleInsurancePolicyNumber;
}
