package ru.vitaliy.petrov.server.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "vehicle_insurance_policy")
public class VehicleInsurancePolicy implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "vehicle_insurance_policy_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vehicle_insurance_policy_user", referencedColumnName = "user_id", nullable = false)
    private Users vehicleInsurancePolicyUser;

    @Column(name = "vehicle_insurance_company")
    private String vehicleInsuranceCompany;

    @Column(name = "vehicle_insurance_policy_number")
    private String vehicleInsurancePolicyNumber;

    @Column(name = "vehicle_insurance_policy_expiration_date")
    private Date vehicleInsurancePolicyExpirationDate;

}
