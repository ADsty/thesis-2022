package ru.vitaliy.petrov.server.forms.requests.userprofile;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
public class VehicleInsurancePolicyCreationRequest {

    @NotNull
    @NotEmpty
    private final String vehicleInsuranceCompany;

    @NotNull
    @NotEmpty
    private final String vehicleInsurancePolicyNumber;

    @NotNull
    private final Date vehicleInsurancePolicyExpirationDate;
}
