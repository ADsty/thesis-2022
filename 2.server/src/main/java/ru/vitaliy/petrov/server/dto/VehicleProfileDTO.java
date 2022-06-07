package ru.vitaliy.petrov.server.dto;

import lombok.Data;
import ru.vitaliy.petrov.server.models.VehicleProfile;

@Data
public class VehicleProfileDTO {

    private Long vehicleProfileID;
    private Long vehicleProfileUserID;
    private String vehicleBrand;
    private String vehicleVin;
    private String vehicleRegistrationSign;
    private String vehicleRegistrationCertificate;
    private String vehicleOwnerFullName;
    private String vehicleOwnerResidentialAddress;
    private Long vehicleDriverID;
    private Long vehicleInsurancePolicyID;

    public VehicleProfileDTO(VehicleProfile vehicleProfile) {
        this.vehicleProfileID = vehicleProfile.getId();
        this.vehicleProfileUserID = vehicleProfile.getVehicleProfileUser().getId();
        this.vehicleBrand = vehicleProfile.getVehicleBrand();
        this.vehicleVin = vehicleProfile.getVehicleVin();
        this.vehicleRegistrationSign = vehicleProfile.getVehicleRegistrationSign();
        this.vehicleRegistrationCertificate = vehicleProfile.getVehicleRegistrationCertificate();
        this.vehicleOwnerFullName = vehicleProfile.getVehicleOwnerFullName();
        this.vehicleOwnerResidentialAddress = vehicleProfile.getVehicleOwnerResidentialAddress();
        this.vehicleDriverID = vehicleProfile.getVehicleDriver().getId();
        this.vehicleInsurancePolicyID = vehicleProfile.getVehicleInsurancePolicy().getId();
    }

}
