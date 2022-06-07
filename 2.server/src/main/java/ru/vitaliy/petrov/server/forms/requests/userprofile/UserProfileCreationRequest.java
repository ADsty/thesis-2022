package ru.vitaliy.petrov.server.forms.requests.userprofile;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
public class UserProfileCreationRequest {

    @NotNull
    @NotEmpty
    private final String fullName;

    @NotNull
    private final Date dateOfBirth;

    @NotNull
    @NotEmpty
    private final String residentialAddress;

    @NotNull
    @NotEmpty
    private final String placeOfWork;

    @NotNull
    @NotEmpty
    private final String positionAtWork;

    @NotNull
    @NotEmpty
    private final String workPhoneNumber;

    @NotNull
    @NotEmpty
    private final String driverLicenseNumber;
}
