package ru.vitaliy.petrov.server.forms.requests.userprofile;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
public class DriverLicenseUpdateRequest {

    @NotNull
    @NotEmpty
    private final String updatedDriverLicenseNumber;

    @NotNull
    @NotEmpty
    private final String updatedDriverLicenseCategory;

    @NotNull
    private final Date updatedDriverLicenseDateOfIssue;
}
