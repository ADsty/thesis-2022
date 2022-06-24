package ru.vitaliy.petrov.server.forms.requests.userprofile;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
public class DriverLicenseCreationRequest {

    @NotNull
    @NotEmpty
    private final String driverLicenseNumber;

    @NotNull
    @NotEmpty
    private final String driverLicenseCategory;

    @NotNull
    private final Date driverLicenseDateOfIssue;
}
