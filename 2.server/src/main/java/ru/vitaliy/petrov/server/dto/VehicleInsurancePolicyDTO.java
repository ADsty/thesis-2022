package ru.vitaliy.petrov.server.dto;

import lombok.Data;
import ru.vitaliy.petrov.server.models.VehicleInsurancePolicy;

import java.sql.Date;

@Data
public class VehicleInsurancePolicyDTO {

    private Long vehicleInsurancePolicyID;
    private Long vehicleInsurancePolicyUserID;
    private String vehicleInsuranceCompany;
    private String vehicleInsurancePolicyNumber;
    private Date vehicleInsurancePolicyExpirationDate;

    public VehicleInsurancePolicyDTO(VehicleInsurancePolicy vehicleInsurancePolicy) {
        this.vehicleInsurancePolicyID = vehicleInsurancePolicy.getId();
        this.vehicleInsurancePolicyUserID = vehicleInsurancePolicy.getVehicleInsurancePolicyUser().getId();
        this.vehicleInsuranceCompany = vehicleInsurancePolicy.getVehicleInsuranceCompany();
        this.vehicleInsurancePolicyNumber = vehicleInsurancePolicy.getVehicleInsurancePolicyNumber();
        this.vehicleInsurancePolicyExpirationDate = vehicleInsurancePolicy.getVehicleInsurancePolicyExpirationDate();
    }

}
