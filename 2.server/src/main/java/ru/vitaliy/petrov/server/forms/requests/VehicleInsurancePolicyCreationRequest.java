package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class VehicleInsurancePolicyCreationRequest {
    private final String vehicleInsuranceCompany;
    private final String vehicleInsurancePolicyNumber;
    private final String vehicleInsurancePolicyExpirationDate;
}
