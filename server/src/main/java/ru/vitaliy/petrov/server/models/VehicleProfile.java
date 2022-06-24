package ru.vitaliy.petrov.server.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "vehicle_profile")
public class VehicleProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "vehicle_profile_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vehicle_profile_user", referencedColumnName = "user_id", nullable = false)
    private Users vehicleProfileUser;

    @Column(name = "vehicle_brand")
    private String vehicleBrand;

    @Column(name = "vehicle_vin")
    private String vehicleVin;

    @Column(name = "vehicle_registration_sign")
    private String vehicleRegistrationSign;

    @Column(name = "vehicle_registration_certificate")
    private String vehicleRegistrationCertificate;

    @Column(name = "vehicle_owner_full_name")
    private String vehicleOwnerFullName;

    @Column(name = "vehicle_owner_residential_address")
    private String vehicleOwnerResidentialAddress;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vehicle_driver", referencedColumnName = "user_id")
    private Users vehicleDriver;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vehicle_insurance_policy", referencedColumnName = "vehicle_insurance_policy_id", nullable = false)
    private VehicleInsurancePolicy vehicleInsurancePolicy;

}
