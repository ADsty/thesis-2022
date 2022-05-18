package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class UserProfileUpdateRequest {
    private final String updatedFullName;
    private final String updatedDateOfBirth;
    private final String updatedResidentialAddress;
    private final String updatedPlaceOfWork;
    private final String updatedPositionAtWork;
    private final String updatedWorkPhoneNumber;
    private final String updatedDriverLicenseNumber;
}
