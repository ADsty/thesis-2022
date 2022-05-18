package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class DriverLicenseUpdateRequest {
    private final String updatedDriverLicenseNumber;
    private final String updatedDriverLicenseCategory;
    private final String updatedDriverLicenseDateOfIssue;
}
