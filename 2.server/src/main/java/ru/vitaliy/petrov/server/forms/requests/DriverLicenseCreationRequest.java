package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class DriverLicenseCreationRequest {
    private final String driverLicenseNumber;
    private final String driverLicenseCategory;
    private final String driverLicenseDateOfIssue;
}
