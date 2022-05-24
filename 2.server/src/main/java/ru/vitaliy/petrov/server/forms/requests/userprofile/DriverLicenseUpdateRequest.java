package ru.vitaliy.petrov.server.forms.requests.userprofile;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class DriverLicenseUpdateRequest {

    @NotNull
    @NotEmpty
    private final String updatedDriverLicenseNumber;

    @NotNull
    @NotEmpty
    private final String updatedDriverLicenseCategory;

    @NotNull
    @NotEmpty
    private final String updatedDriverLicenseDateOfIssue;
}
