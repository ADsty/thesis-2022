package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class VehicleInsurancePolicyUpdateRequest {
    private final String updatedVehicleInsuranceCompany;
    private final String updatedVehicleInsurancePolicyNumber;
    private final String updatedVehicleInsurancePolicyExpirationDate;
}
