package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class UserProfileCreationRequest {
    private final String fullName;
    private final String dateOfBirth;
    private final String residentialAddress;
    private final String placeOfWork;
    private final String positionAtWork;
    private final String workPhoneNumber;
    private final String driverLicenseNumber;
}
