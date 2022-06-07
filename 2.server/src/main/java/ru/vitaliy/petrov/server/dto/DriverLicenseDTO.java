package ru.vitaliy.petrov.server.dto;

import lombok.Data;
import ru.vitaliy.petrov.server.models.DriverLicense;

import java.sql.Date;

@Data
public class DriverLicenseDTO {

    private Long driverLicenseID;
    private String driverLicenseNumber;
    private String driverLicenseCategory;
    private Date driverLicenseDateOfIssue;

    public DriverLicenseDTO(DriverLicense driverLicense) {
        this.driverLicenseID = driverLicense.getId();
        this.driverLicenseNumber = driverLicense.getDriverLicenseNumber();
        this.driverLicenseCategory = driverLicense.getDriverLicenseCategory().getDriverLicenseCategory();
        this.driverLicenseDateOfIssue = driverLicense.getDriverLicenseDateOfIssue();
    }

}
