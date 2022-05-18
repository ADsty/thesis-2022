package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class VehicleProfileCreationRequest {
    private final String vehicleBrand;
    private final String vehicleVIN;
    private final String vehicleRegistrationSign;
    private final String vehicleRegistrationCertificate;
    private final String vehicleOwnerFullName;
    private final String vehicleOwnerResidentialAddress;
    private final String vehicleInsurancePolicyNumber;
}
