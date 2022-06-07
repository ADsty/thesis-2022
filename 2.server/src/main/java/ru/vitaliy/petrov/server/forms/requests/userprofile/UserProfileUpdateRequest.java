package ru.vitaliy.petrov.server.forms.requests.userprofile;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
public class UserProfileUpdateRequest {

    @NotNull
    @NotEmpty
    private final String updatedFullName;

    @NotNull
    private final Date updatedDateOfBirth;

    @NotNull
    @NotEmpty
    private final String updatedResidentialAddress;

    @NotNull
    @NotEmpty
    private final String updatedPlaceOfWork;

    @NotNull
    @NotEmpty
    private final String updatedPositionAtWork;

    @NotNull
    @NotEmpty
    private final String updatedWorkPhoneNumber;

    @NotNull
    @NotEmpty
    private final String updatedDriverLicenseNumber;
}
